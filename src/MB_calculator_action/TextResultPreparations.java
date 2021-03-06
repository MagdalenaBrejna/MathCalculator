package MB_calculator_action;

import javax.swing.*;
import java.util.ArrayList;

public class TextResultPreparations {

    private final static String sqrt = "\u221a";

    public static int countDots(String text){
    //Count the number of dots to check if the number is correct (contains max 1 dot).

        int dotNumber = 0;
        for(int textPosition = 0; textPosition < text.length(); textPosition++)
            if(text.charAt(textPosition) == '.')
                dotNumber++;
        return dotNumber;
    }

    public static String returnPreviousText(String text){
    //Check which symbol has been added in the last step and delete appropriate number of characters.
    // If the last step is a function with bracket, both of them will be deleted (apart from sqrt).

        char lastCharacter = text.charAt(text.length() - 1);

        if(text.length() > 1 && lastCharacter == '('){
            char secondLastCharacter = text.charAt(text.length() - 2);
            if(secondLastCharacter >= 'g' && secondLastCharacter <= 's'){
                String last2Characters = text.substring(text.length() - 2);
                if (last2Characters.equals("ln") || last2Characters.equals("tg"))
                    text = text.substring(0, text.length() - 3);
                else
                    text = text.substring(0, text.length() - 4);

            }else
                text = text.substring(0, text.length() - 1);

        } else
            text = text.substring(0, text.length() - 1);

        return text;
    }

    public static String countResult(ArrayList<String> textList) throws WrongExpressionException{
    //Try to create the ONP expression and count it. If an exception occurs, it is handled and relay further.

        ArrayList<String> textAlteredList = new ArrayList<String>();
        String textONP = "";

        if (textList.size() > 0)
            try{
                textAlteredList = TextResultPreparations.createEntireNumbers(textList);
                textAlteredList = ONP.convertTextToONP(textAlteredList);
                textONP = ONP.count(textAlteredList);

            } catch(WrongExpressionException exception){
                throw new WrongExpressionException(exception.getMessage());
            }

        return textONP;
    }

    public static void countCurrentResult(ArrayList<String> textList, JTextField resultTextField){
    //Count the current result if there is a possibility that the expression is correct. If not set empty String.

        if (textList.size() > 0 && !(textList.get(textList.size() - 1).equals("(") || textList.get(textList.size() - 1).equals("+") || textList.get(textList.size() - 1).equals("-") || textList.get(textList.size() - 1).equals("*") ||
                textList.get(textList.size() - 1).equals("/") || textList.get(textList.size() - 1).equals("^") || textList.get(textList.size() - 1).equals(sqrt)))
            try{
                resultTextField.setText(TextResultPreparations.countResult(textList));
            }catch(WrongExpressionException exception){
                resultTextField.setText("");
            }
    }

    public static boolean comafault(ArrayList<String> listText, int comaPosition){
    //Check whether or not an user has chosen coma instead of dot.

        for(int position = comaPosition - 1; position >= 0; position--) {
            if (listText.get(position).equals("log"))
                return false;
            else if(!(listText.get(position).charAt(0) >= '0' && listText.get(position).charAt(0) <= '9') && !(listText.get(position).charAt(0) == '('))
                return true;
        }
        return true;
    }

    public static int numberOfSymbols(ArrayList<String> list, String searchSymbol){
        int number = 0;

        for(String symbol : list)
            if(symbol.equals(searchSymbol))
                number++;

        return number;
    }

    public static ArrayList<String> createEntireNumbers(ArrayList<String> listText) throws WrongExpressionException{
    //Create entire numbers from separate characters, prepare expression to deal with negative numbers and lack of operation symbols or ending bracket.

        ArrayList<String> newListText = new ArrayList<String>();
        String stringText = "";

        for(int listPosition = 0; listPosition < listText.size(); listPosition++) {

            //If an user has made a mistake, swap coma and dot.
            if(listText.get(listPosition).charAt(0) == ',')
                if(comafault(listText, listPosition))
                    listText.set(listPosition, ".");

            //Create entire numbers.
            if((listText.get(listPosition).charAt(0) >= '0' && listText.get(listPosition).charAt(0) <= '9') || listText.get(listPosition).equals("."))
                stringText += listText.get(listPosition);
            else {

                //Add 0 if there is "-" on the first position.
                if (listPosition == 0 && listText.get(listPosition).charAt(0) == '-')
                    newListText.add("0");

                if (!(stringText.equals(""))) {
                    if (stringText.equals("."))
                        stringText = "0";
                    else if (countDots(stringText) > 1)
                        throw new WrongExpressionException("Too many dots in the number.");

                    newListText.add(stringText);
                    stringText = "";

                //Create positive and negative numbers.
                } else if (listPosition > 1 && listPosition < (listText.size() - 2)) {
                    if (listText.get(listPosition).equals("-") && listText.get(listPosition - 1).equals("("))
                        stringText += listText.get(listPosition);

                }if(stringText.equals(""))
                    newListText.add(listText.get(listPosition));
            }
        }

        if(!(stringText.equals(""))) {
            if(stringText.equals("."))
                stringText = "0";
            else if(countDots(stringText) > 1)
                throw new WrongExpressionException("Too many dots in the number.");
            newListText.add(stringText);
        }

        //If there is no operation symbol between a number and a function or before a bracket, add multiplication symbol between them.
        for(int listPosition = 1; listPosition < newListText.size(); listPosition++)
            if(newListText.get(listPosition - 1).charAt(0) >= '0' && newListText.get(listPosition - 1).charAt(0) <= '9' && (newListText.get(listPosition).charAt(0) >= 'c' && newListText.get(listPosition).charAt(0) <= 't' ||
                    newListText.get(listPosition).equals('(') || newListText.get(listPosition).equals(sqrt)))
                newListText.add(listPosition, "*");

        //If there is no ending bracket, add it to enable counting result.
        if(numberOfSymbols(newListText, "(") > numberOfSymbols(newListText, ")"))
            newListText.add(")");

        return newListText;
    }
}
