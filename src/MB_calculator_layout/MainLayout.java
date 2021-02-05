package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;

public class MainLayout {

    private JFrame frame;
    private JTabbedPane calculatorTypePanel;

    public MainLayout(){
        
        frame = new JFrame("Calculator");
        calculatorTypePanel = new JTabbedPane();
        calculatorTypePanel.setLocation(30,30);

        calculatorTypePanel.add("Basic", new BasicCalculator());
        calculatorTypePanel.add("Scientific", new ScientificCalculator());

        frame.getContentPane().add(BorderLayout.CENTER, calculatorTypePanel);

        frame.pack();
        frame.setSize(600,700);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
