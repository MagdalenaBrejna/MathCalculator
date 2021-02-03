package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FunctionalButton extends Button{

    public FunctionalButton(String nazwa, int dx, int dy, int x, int y, ActionListener a, JPanel p){
        super(nazwa, a);
        setBounds(dx,dy,x,y);
        p.add(this);
    }
}
