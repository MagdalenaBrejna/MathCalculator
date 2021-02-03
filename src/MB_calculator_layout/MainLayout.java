package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;

public class MainLayout {

    private static JFrame frame;
    private static JTabbedPane calculatorTypePanel;

    public static void createLayout(){
        
        frame = new JFrame("Calculator");
        calculatorTypePanel = new JTabbedPane();

        calculatorTypePanel.add("Basic",new basicCalculator());
        calculatorTypePanel.add("Scientific",new scientificCalculator());

        frame.getContentPane().add(BorderLayout.CENTER, calculatorTypePanel);

        frame.setSize(400,600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
