package MB_calculator_action;

import java.util.ArrayList;

public class Translation {

    public static ArrayList<String> translate(ArrayList<String> x)
    {
        ArrayList<String> wyr = new ArrayList<String>();
        String a = "";
        boolean blad = false;

        for(int j = 0; j < x.size(); j++)
        {
            if((x.get(j).charAt(0) >= '0' && x.get(j).charAt(0) <= '9') || x.get(j).equals("."))
                a += x.get(j);
            else {
                if(!(a.equals(""))) {
                    if(a.equals("."))
                        a = "0";
                    else {
                        int ile = 0;
                        for(int k = 0; k < a.length(); k++)
                            if(a.charAt(k) == '.')
                                ile++;
                        if(ile > 1)
                            blad = true;
                    }
                    wyr.add(a);
                    a = "";

                } else if(x.get(j).equals("-"))
                    a += x.get(j);

                if(a == "")
                    wyr.add(x.get(j));
            }
        }

        if(!(a.equals(""))) {
            if(a.equals("."))
                a = "0";
            else {
                int ile = 0;
                for(int k = 0; k < a.length(); k++)
                    if(a.charAt(k) == '.')
                        ile++;
                if(ile > 1)
                    blad = true;
            }
            wyr.add(a);
            a = "";
        }
        if(blad)
            wyr.clear();
        return wyr;
    }
}
