package task2;

import task2.calculator.*;
import static task2.calculator.Operations.*;

public class Hw11Task2{
	public static void main(String[] args){
		Calculator calculator = new Calculator();

		calculator.addOperation(ADD.getName(), ADD.getOp());
		calculator.addOperation(SUB.getName(), SUB.getOp());
		calculator.addOperation(MUL.getName(), MUL.getOp());
		calculator.addOperation(EXP.getName(), EXP.getOp());
		calculator.addOperation(DIV.getName(), DIV.getOp());
		calculator.addOperation(NQR.getName(), NQR.getOp());

		final double a = 2f; final double b = 4F;
		calculator.calculate(ADD.getName(), a, b);
		calculator.calculate(SUB.getName(), a, b);
		calculator.calculate(MUL.getName(), a, b);
		calculator.calculate(DIV.getName(), a, b);
		calculator.calculate(EXP.getName(), a, b);
		calculator.calculate(NQR.getName(), a, b);
	;}
}
