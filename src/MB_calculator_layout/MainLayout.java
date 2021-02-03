package MB_calculator_layout;

import javax.swing.*;
import java.awt.*;

public class MainLayout {

    private JFrame frame;
    private JTabbedPane calculatorTypePanel;

    public MainLayout(){
        
        frame = new JFrame("Calculator");
        calculatorTypePanel = new JTabbedPane();

        calculatorTypePanel.add("Basic", new BasicCalculator());
        calculatorTypePanel.add("Scientific", new ScientificCalculator());

        frame.getContentPane().add(BorderLayout.CENTER, calculatorTypePanel);

        frame.pack();
        frame.setSize(400,600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
