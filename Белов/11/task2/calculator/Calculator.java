package task2.calculator;

import java.util.HashMap;

public class Calculator {
    private final HashMap<String, Operation> operations = new HashMap<>();
    public double calculate(final String operationName
            , final Double number1, final Double number2){
        if( !operations.containsKey(operationName)) {
            throw new IllegalArgumentException("Operation "
                    + operationName + " is not valid");
        }
        final double result = operations.get(operationName).doOperation(number1, number2);
        System.out.println("Operation " + operationName
                + " for " + number1 + " by " + number2
                + " gives " + result);
        return result;
    }
    public void addOperation(final String operationName
            , final Operation implementation){
        operations.put(operationName, implementation);
    }
}
