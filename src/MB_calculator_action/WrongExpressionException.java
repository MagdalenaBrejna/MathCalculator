package MB_calculator_action;

public class WrongExpressionException extends Exception{
    //The exception which occurs when expression is impossible to count.

    public WrongExpressionException(String message){
        super(message);
    }
}
