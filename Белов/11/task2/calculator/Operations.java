package task2.calculator;

public enum Operations{
	ADD("Addition"      , (a,b) -> a+b),
	SUB("Subtraction"   , (a,b) -> a-b),
	MUL("Multiplication", (a,b) -> a*b),
	DIV("Division"      , (a,b) -> a/b),
	EXP("Exponentiation", (a,b) -> Math.pow(a,b)),
	NQR("Nth root"      , (a,b) -> Math.pow(a,1F/ b));
	private final String name;
	private final Operation op;
	Operations(final String name, final Operation op){
		this.name = name;
		this.op = op;
	}
	public String getName(){return name;}
	public Operation getOp(){return op;}
}

