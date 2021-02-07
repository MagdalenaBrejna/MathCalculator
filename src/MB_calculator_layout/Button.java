package MB_calculator_layout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Button extends JButton implements MouseListener{

    protected Font buttonFont = new Font("Helvetica", Font.ITALIC, 18);

    protected Border raisedLevelBorder = BorderFactory.createRaisedBevelBorder();
    protected Border lowerLevelBorder = BorderFactory.createLoweredBevelBorder();

    public Button(String textNameButton, ActionListener actionListenerButton){
        super(textNameButton);
        addActionListener(actionListenerButton);
        setFont(buttonFont);
        setBorder(raisedLevelBorder);
    }

    public void mousePressed(MouseEvent event){
        this.setBorder(lowerLevelBorder);
    }

    public void mouseReleased(MouseEvent event){
        this.setBorder(raisedLevelBorder);
    }

    public void mouseClicked(MouseEvent event){}

    public abstract void mouseEntered(MouseEvent event);

    public abstract void mouseExited(MouseEvent event);

}
