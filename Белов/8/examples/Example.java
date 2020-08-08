package examples;

import xml.examples.XmlAnnotation;

public class Example{
	// If we have such Annotation defined:
	@XmlAnnotation(tagNameA = "A1")
	String f1 = "value1";
	@XmlAnnotation(tagNameA = "A2")
	String f2 = "value2";
	@XmlAnnotation(tagNameA = "A3", tagNameB = "B3")
	String f3  = "value3";
	@XmlAnnotation(tagNameA = "A4", tagNameB = "B4")
	String f4  = "value4";
	public void usage(){
		Object obj = new Object();
		final XmlAnnotation a =
				obj.getClass().getDeclaredFields()[0].getAnnotation(XmlAnnotation.class);
		/* EITHER */ final String tagNameA = a.tagNameA();
		// f1 -> <A1>value1</A1>
		// f2 -> <A2>value2</A2>
		// f3 -> <A3>value3</A3>
		// f4 -> <A4>value4</A4>
		/* OR */ final String tagNameB = a.tagNameB();
		// f1 -> <DEFAULT>value1</DEFAULT>
		// f2 -> <DEFAULT>value2</DEFAULT>
		// f3 -> <B3>value3</B3>
		// f4 -> <B4>value4</B4>
	}
}
