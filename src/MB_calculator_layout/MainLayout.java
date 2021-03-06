package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;

public class MainLayout {

    private JFrame frame;
    private JTabbedPane calculatorTypePanel;

    public MainLayout(){
    //Create main features of the layout.

        frame = new JFrame("Calculator");
        calculatorTypePanel = new JTabbedPane();
        calculatorTypePanel.setLocation(30,30);

        calculatorTypePanel.add("Scientific", new ScientificCalculator());
        calculatorTypePanel.add("Basic", new BasicCalculator());

        frame.getContentPane().add(BorderLayout.CENTER, calculatorTypePanel);

        frame.pack();
        frame.setSize(600,740);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
