package MB_calculator_action;

import java.util.ArrayList;

public class Translation {

    public static int countDots(String text){
        int dotNumber = 0;
        for(int textPosition = 0; textPosition < text.length(); textPosition++)
            if(text.charAt(textPosition) == '.')
                dotNumber++;
        return dotNumber;
    }

    public static ArrayList<String> createEntireNumbers(ArrayList<String> listText) {

        ArrayList<String> newListText = new ArrayList<String>();
        String stringText = "";
        boolean ifError = false;

        for(int listPosition = 0; listPosition < listText.size(); listPosition++) {

            if((listText.get(listPosition).charAt(0) >= '0' && listText.get(listPosition).charAt(0) <= '9') || listText.get(listPosition).equals("."))
                stringText += listText.get(listPosition);
            else {
                if(!(stringText.equals(""))) {
                    if(stringText.equals("."))
                        stringText = "0";
                    else if(countDots(stringText) > 1)
                        ifError = true;

                    newListText.add(stringText);
                    stringText = "";

                } else if(listText.get(listPosition).equals("-"))
                    stringText += listText.get(listPosition);

                if(stringText == "")
                    newListText.add(listText.get(listPosition));
            }
        }

        if(!(stringText.equals(""))) {
            if(stringText.equals("."))
                stringText = "0";
            else if(countDots(stringText) > 1)
                ifError = true;
            newListText.add(stringText);
            stringText = "";
        }

        if(ifError)
            newListText.clear();

        return newListText;
    }
}
