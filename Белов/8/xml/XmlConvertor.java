package xml;

import xml.annotations.XmlAnnotationRestricted;
import xml.annotations.XmlAnnotation;
import xml.annotations.XmlAnnotationPassword;
import xml.annotations.XmlClassName;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collection;

public class XmlConvertor{
	StringBuilder sb = new StringBuilder();
	public StringBuilder serialize(final Object obj)
			throws IllegalAccessException{
		if(! (obj instanceof Collection<?>)){
			appendToXml(obj);
		}
		final Collection<?> objects = (Collection<?>) obj;
		for(Object o : objects){
			sb.append(serialize(o));
		}
		return sb;
	}
	// ' &apos; " &quot; & &amp; < &lt; > &gt;
	// tagNameA = tagNameA.replaceAll("&(?!.{2,4};)", "&amp;");
	private void appendToXml(Object obj) throws IllegalAccessException{
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
			for(Method method : password.annotationType().getMethods()){
				if(!method.toString().contains(XmlAnnotationPassword.class.getSimpleName())){
					continue;
				}
				try{
					final String tag = method.invoke(password, null).toString();
					//if(!tag.isEmpty()){ //would be more smart to set default annotation as empty and check it?
						if("Password removed".equals(tag)){
							return encloseWithNewLine(tag, "");
						}
						if("Password encrypted".equals(tag)){
							return encloseWithNewLine(tag, encrypt(value));
						}
						if("Password under stars".equals(tag)){
							return encloseWithNewLine(tag, "***");
						}
					//}
				}catch(InvocationTargetException e){
					e.printStackTrace();
				}
			}
			throw new IllegalAccessException("Unknown XmlAnnotationPassword: " +
					password.toString());
		}}
		throw new IllegalAccessException("Unknown annotation met" +
				" while serializing a field in serializeField()." +
				"\nAdd an annotation to each field in class");
	}
	private String encrypt(final String enc){
		final byte[] bytes = enc.getBytes();
		for(int i=0; i<bytes.length; i++){
			bytes[i] += 1;
		}
		return Arrays.toString(bytes);
	}

	//-------------------------------------------------
	private String decrypt(final String dec){
		final byte[] bytes = dec.getBytes();
		for(int i=0; i<bytes.length; i++){
			bytes[i] -= 1;
		}
		return Arrays.toString(bytes);
	}
	private int[] parseText(final int begin, final int end){
		final int b1 = sb.indexOf("<", begin);
		if(begin != b1) throw new AssertionError();
		final int e1 = sb.indexOf(">", b1);
		if(-1 == b1 || -1 == e1) throw new AssertionError();
		final String tag = sb.substring(b1, e1);
		final int b2 = sb.indexOf(enclose(tag, false), e1);
		if(-1 == b2) throw new AssertionError();
		//if(b2+tag.length() != end) throw new AssertionError();
		if(tag.isEmpty()) throw new AssertionError();
		if(!tag.equals(sb.substring(b2, end))) throw new AssertionError();
		final int[] subIndex = new int[2];
		subIndex[0] = e1;
		subIndex[1] = b2;
		return subIndex;
	}
	public String deserialize(final String serializedText, final Class<?> clazz)	throws IllegalAccessException{
		final String className = clazz.getSimpleName();
		int posForParsing = 0;
		do{
			final int[] subIndex = parseText(posForParsing, serializedText.length());
			assert(className.equals(sb.substring(posForParsing+1, subIndex[0])));
			deserializeFields(clazz, subIndex);
			posForParsing = subIndex[1]+className.length();
		}while(posForParsing < serializedText.length());
	}
	private String deserializeFields(final Class<?> clazz, int[] index)throws IllegalAccessException{
		Object obj = null;
		try{
			obj = newObject(clazz);
		}catch(InstantiationException | InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}
		int posForParsing = index[0];
		do{
			final int[] subIndex = parseText(posForParsing, index[1]);
			final String tag = sb.substring(posForParsing + 1, subIndex[0]);
			final String value = sb.substring(subIndex[0] + 1, subIndex[1]);
			for(Field field : clazz.getDeclaredFields()){
				createFields(obj, field, tag, value);
			}
		}while(posForParsing < index[1]);

	}
	private void createFields(final Object obj, final Field field
			, final String tag, String value)	throws IllegalAccessException{
			field.setAccessible(true);
			final XmlAnnotation           annotation = field.getAnnotation(XmlAnnotation.class);
			final XmlAnnotationRestricted restricted = field.getAnnotation(XmlAnnotationRestricted.class);
			final XmlAnnotationPassword     password = field.getAnnotation(XmlAnnotationPassword.class);
			String aFieldName = tag;
			if(null != password){
				if(password.underStars().equals(tag)){

				}


					if("Password removed".equals(tag)){
					aFieldName = password.removed();
					value = "";
				}
				if("Password encrypted".equals(tag)){
					aFieldName = password.encryptedAes256();
					value = decrypt(value);
				}
				if("Password under stars".equals(tag)){
					value = "";
				}
			}else if(null != restricted){
				aFieldName = restricted.tag();
			}else if(null != annotation){
				if(tag.equals(annotation.tag()))
				aFieldName = annotation.tag();
			}
			if((null == aFieldName)) throw new AssertionError();
			//aFieldName = (null!=aFieldName)?aFieldName:field.getName();
			if(!aFieldName.equals(name)){
				continue; //to get a next field
			}

			final String fieldType = field.getAnnotatedType().toString();
			if("int".equals(fieldType)){
				field.set(obj, Integer.parseInt(value));
			}else if("java.lang.String".equals(fieldType)){
				field.set(obj, value);
			}else if("java.lang.Integer".equals(fieldType)){
				field.set(obj, Integer.valueOf(value));
			}
	}
	Object newObject(Class<?> className)
			throws IllegalAccessException, InvocationTargetException, InstantiationException{
		@SuppressWarnings("rawtypes") Constructor[] ctors = className.getDeclaredConstructors();
		for(var c : ctors){
			if(0 == c.getParameterTypes().length){
				return c.newInstance();
			}
		}
		return null;
	}
}
