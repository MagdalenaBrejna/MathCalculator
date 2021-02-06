package MB_calculator_action;

import java.util.ArrayList;

public class TextResultPreparations {

    public static int countDots(String text){

        int dotNumber = 0;
        for(int textPosition = 0; textPosition < text.length(); textPosition++)
            if(text.charAt(textPosition) == '.')
                dotNumber++;
        return dotNumber;
    }

    public static String returnPreviousText(String text){

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

    public static String countResult(ArrayList<String> textList){

        ArrayList<String> textAlteredList = new ArrayList<String>();
        String textONP = "";

        if (textList.size() > 0)
            try{
                textAlteredList = TextResultPreparations.createEntireNumbers(textList);
                textAlteredList = ONP.convertTextToONP(textAlteredList);
                textONP = ONP.count(textAlteredList);

            } catch(WrongExpressionException exception){
                return "";
            }

        return textONP;
    }

    public static ArrayList<String> createEntireNumbers(ArrayList<String> listText) throws WrongExpressionException{

        ArrayList<String> newListText = new ArrayList<String>();
        String stringText = "";

        for(int listPosition = 0; listPosition < listText.size(); listPosition++) {

            if((listText.get(listPosition).charAt(0) >= '0' && listText.get(listPosition).charAt(0) <= '9') || listText.get(listPosition).equals("."))
                stringText += listText.get(listPosition);
            else {
                if (!(stringText.equals(""))) {
                    if (stringText.equals("."))
                        stringText = "0";
                    else if (countDots(stringText) > 1)
                        throw new WrongExpressionException("Too many dots in the number.");

                    newListText.add(stringText);
                    stringText = "";

                } else if (listPosition > 1 && listPosition < (listText.size() - 2)){
                    if (listText.get(listPosition).equals("-") && listText.get(listPosition - 1).equals("("))
                        stringText += listText.get(listPosition);

                }else if(listPosition == 0 && listText.get(listPosition).equals("-"))
                    stringText += listText.get(listPosition);

                if(stringText.equals(""))
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

        return newListText;
    }
}
