package xml.examples;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XmlAnnotation{
	String tagNameA();
	String tagNameB() default "DEFAULT";
} /* AND if we gonna use the Annotation for such fields f1, f2, .. in */
