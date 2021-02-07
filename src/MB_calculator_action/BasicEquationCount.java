package MB_calculator_action;

public class BasicEquationCount {

    public static String count(String n1, String operationSymbol, String n2) throws NumberFormatException, WrongExpressionException{

        double number1 = Double.parseDouble(n1), number2 = Double.parseDouble(n2), numberResult = 0;
        String textResult = "";

        //Do an operation.
        switch(operationSymbol)
        {
            case("/"):
                if(number2 != 0)
                    numberResult = (number1 / number2);
                else
                    throw new WrongExpressionException("Division by 0.");
                break;

            case("*"):
                numberResult = (number1 * number2);
                break;

            case("-"):
                numberResult = (number1 - number2);
                break;

            case("+"):
                numberResult = (number1 + number2);
                break;
        }

        textResult = String.valueOf(numberResult);
        if(textResult.substring(textResult.length() - 2).equals(".0"))
            textResult = textResult.substring(0,textResult.length() - 2);

        return textResult;
    }
}
