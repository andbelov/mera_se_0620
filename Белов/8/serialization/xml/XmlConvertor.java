package serialization.xml;

import annotations.XmlAnnotation;
import annotations.XmlAnnotationPassword;
import annotations.XmlAnnotationRestricted;
import annotations.XmlClassName;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public class XmlConvertor{
	StringBuilder sb = new StringBuilder();
	public StringBuilder serialize(final Object obj)
			throws IllegalAccessException{
		if(obj instanceof Collection<?>){
			final Collection<?> objects = (Collection<?>) obj;
			for(Object o : objects){
				serialize(o);
			}
		}else{
			serializeObject(obj);
		}
		return sb;
	}
	// ' &apos; " &quot; & &amp; < &lt; > &gt;
	// tagNameA = tagNameA.replaceAll("&(?!.{2,4};)", "&amp;");
	private void serializeObject(final Object obj) throws IllegalAccessException{
		XmlClassName classNameAnnotation = obj.getClass().getAnnotation(XmlClassName.class);
		if (null == classNameAnnotation) {
			return;
		}
		sb.append(enclose(classNameAnnotation.className(), true));
		serializeFields(obj);
		sb.append(encloseWithNewLine(classNameAnnotation.className(), false));
	}

	private String enclose(final String tagName, final boolean isOpeningTag){
		return (isOpeningTag ? "<" : "</") + tagName + '>';
	}
	private String encloseWithNewLine(final String tagName, boolean isOpeningTag){
		return '\n' + enclose( tagName, isOpeningTag);
	}
	private String encloseWithNewLine(final String tagName, final String value){
		return encloseWithNewLine(tagName, true) + value + enclose(tagName, false);
	}
	private void serializeFields(Object obj) throws IllegalAccessException{
		for(Field field : obj.getClass().getDeclaredFields()){
			field.setAccessible(true);
			final Object o = field.get(obj);
			final String sf = serializeField(field, null == o ? "" : o.toString());
			if(null==sf || "".equals(sf)){
				continue;
			}
			sb.append(sf);
		}
	}
	String serializeField(final Field field, final String value) throws IllegalAccessException{
		{final XmlAnnotation annotation = field.getAnnotation(XmlAnnotation.class);
		if(null != annotation){
			return encloseWithNewLine(annotation.tag(), value);
		}}
		{final XmlAnnotationRestricted restricted = field.getAnnotation(XmlAnnotationRestricted.class);
		if(null != restricted){
			final String tag = restricted.tag();
			if("REMOVED".equals(tag)){
				return null;
			}
			return encloseWithNewLine(tag, value);
		}}
		{final XmlAnnotationPassword password = field.getAnnotation(XmlAnnotationPassword.class);
		if(null != password){
			/* Не нашел ответа как, если я хочу выбрать из XmlAnnotationPassword
			 из только указанных в аннатации к полю и в своем порядке перебрра?
			for(Annotation fieldAnnotation : field.getAnnotations()){
				//System.out.println(fieldAnnotation.annotationType());
				//System.out.println(fieldAnnotation);
			}*/
			/* и как взять нужный медот по желаемому типу(или типам),
			 который бы я задал в аннотации?
			 я мог бы использовать доп символ в аннотации,
			  но 146 % - это как то неправильно
			 */
			for(Method method : password.annotationType().getMethods()){
				if(!method.toString().contains(XmlAnnotationPassword.class.getSimpleName())){
					continue; // avoid equal(), hash()
				}
				String tag = null;
				try{
					tag = method.invoke(password, null).toString();
				}catch(InvocationTargetException e){
					e.printStackTrace();
				}
				assert tag != null;
				if(tag.isEmpty()){
					continue;
				}
				String newValue = encryptPassword(tag, value);
				return encloseWithNewLine(tag, newValue);
			}
			throw new IllegalAccessException("Unknown XmlAnnotationPassword: " +
					password.toString());
		}}
		throw new IllegalAccessException("Unknown annotation met" +
				" while serializing a field in serializeField()." +
				"\nAdd an annotation to each field in class");
	}
	private String encryptPassword(final String tag, final String value){
		return switch(tag){
			case "Password under stars" -> "***";
			case "Password encrypted Aes-128" -> encrypt(128, value);
			case "Password encrypted Aes-256" -> encrypt(256, value);
			case "Don't write open password in xml" -> "XXX";
			default -> ""; //"Password removed"
		};
	}
	private String encrypt(final int cipher, final String enc){
		final StringBuilder sb = new StringBuilder(enc);
		for(int i=0; i<sb.length(); i++){
			sb.setCharAt(i, (char) (cipher+((int)sb.charAt(i))));
		}
		return sb.toString();
	}

	//-------------------------------------------------
	private String decrypt(final int cipher, final String dec){
		final StringBuilder sb = new StringBuilder(dec);
		for(int i=0; i<sb.length(); i++){
			sb.setCharAt(i, (char) (-cipher+((int)sb.charAt(i))));
		}
		return sb.toString();
	}
	public ArrayList<Object> deserialize(final String serializedText, final Class<?> clazz)
			throws IllegalAccessException, InvocationTargetException, InstantiationException{
		this.sb.append(serializedText);
		final ArrayList<Object> objects = new ArrayList<>();
		final String className = clazz.getDeclaredAnnotation(XmlClassName.class).className();
		final String[] xmlClasses = serializedText
				//" aaa <Person> <Name>Я</Name>   </Person>   <Person>   <Age>Ты</Age>     </Person> bbb ".strip()
				.split(enclose(className, true));
		for(int i=1; i<xmlClasses.length; ++i){
			Object obj = newObject(clazz);
			final String c1 = xmlClasses[i];
			final int indexOfClassName = c1.indexOf(enclose(className, false));
			final String c1Fields = c1.substring(0, indexOfClassName).strip();
			String[] fields = c1Fields.split("<");
			for(int j=1; j<fields.length; j+=1){
				if('/'==fields[j].charAt(0)){
					continue;
				}
				final String[] f1 = fields[j].split(">");
				assert(2>=f1.length && 1<=f1.length);
				for(Field field : clazz.getDeclaredFields()){
					if(setField(obj, field, f1[0], 1==f1.length ? "" : f1[1])){
						break;
					}
				}
			}
			objects.add(obj);
		}
		return objects;
//		objects.add(deserializeFields(subIndex[tagEnd]+1, subIndex[tagClose], clazz));
	}
	private boolean setField(final Object obj, final Field field
			, String tag, String value){
		field.setAccessible(true);
		final XmlAnnotation           annotation = field.getAnnotation(XmlAnnotation.class);
		final XmlAnnotationRestricted restricted = field.getAnnotation(XmlAnnotationRestricted.class);
		final XmlAnnotationPassword     password = field.getAnnotation(XmlAnnotationPassword.class);
		String aFieldName = null;
		if(null != password){
			if("Password removed".equals(tag)){
				aFieldName = password.removed();
				value = "";
			}
			if("Password under stars".equals(tag)){
				aFieldName = password.underStars();
				value = "";
			}
			if(";-)".equals(tag)){
				aFieldName = password.encryptedAes128();
				value = decrypt(128, value);
			}
			if("Password encrypted Aes-256".equals(tag)){
				aFieldName = password.encryptedAes256();
				value = decrypt(256, value);
			}
			if("Don't write open password in xml".equals(tag)){
				aFieldName = password.asis();
				value = "";
			}
		}
		if(null != restricted){
			aFieldName = restricted.tag();
		}else if(null != annotation){
			aFieldName = annotation.tag();
		}
		if(null == aFieldName || aFieldName.isEmpty()){
			return false;
		}
		/* field.getName() gives a field name from the class declared fields
		   aFieldName i take from xml, i need to deseral only available xml fields
		 aFieldName = (null!=aFieldName)?aFieldName:field.getName();
		 */
		if(!aFieldName.equals(tag)){
			return false; //to get a next field
		}
		final String fieldType = field.getAnnotatedType().toString();

		try{
			if("int".equals(fieldType)){
				field.set(obj, Integer.valueOf(value));
			}else if("java.lang.String".equals(fieldType)){
				field.set(obj, value);
			}else if("java.lang.Integer".equals(fieldType)){
				field.set(obj, Integer.valueOf(value));
			}else if("java.lang.Boolean".equals(fieldType)){
				field.set(obj, Boolean.valueOf(value));
			}
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}
		return true;
	}
	Object newObject(Class<?> className)
			throws IllegalAccessException, InvocationTargetException, InstantiationException{
		@SuppressWarnings("rawtypes")
		Constructor[] constructors = className.getDeclaredConstructors();
		for(var c : constructors){
			if(0 == c.getParameterTypes().length){
				return c.newInstance();
			}
		}
		return null;
	}
}
