package MB_calculator_action;

public class WrongExpressionException extends Exception{

    private static String errorMessage = "";

    public WrongExpressionException(String message){
        super(message);
        errorMessage = message;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }
}
