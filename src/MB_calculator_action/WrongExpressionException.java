package MB_calculator_action;

public class WrongExpressionException extends Exception{
    //exception which occurs when expression is impossible to count. It returns a cause of a fault.
    private static String errorMessage = "";

    public WrongExpressionException(String message){
        super(message);
        errorMessage = message;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }
}
