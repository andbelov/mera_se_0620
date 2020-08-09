package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XmlAnnotationPassword{
	String asis()              default "";
	String encryptedAes128()   default "";
	String encryptedAes256();
	String removed()           default "";
	String underStars()        default "";
}
