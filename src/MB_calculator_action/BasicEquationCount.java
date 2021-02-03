package MB_calculator_action;

public class BasicEquationCount {

    public static String count(String a, String znak, String b)
    {
        double al = Double.parseDouble(a), bl = Double.parseDouble(b), wl;
        String w = "";
        switch(znak)
        {
            case("/"):
                wl = al/bl;
                w = String.valueOf(wl);
                break;

            case("*"):
                wl = al*bl;
                w = String.valueOf(wl);
                break;

            case("-"):
                wl=al-bl;
                w=String.valueOf(wl);
                break;

            case("+"):
                wl=al+bl;
                w=String.valueOf(wl);
                break;

        }
        if(w.substring(w.length() - 2).equals(".0"))
            w = w.substring(0,w.length() - 2);

        return w;
    }
}
