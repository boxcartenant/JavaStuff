import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {
        // Create a new frame (window)
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        // Create a text field to display results
        JTextField display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Button labels
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Variables to store current operation
        final double[] num = {0};
        final String[] operator = {""};

        // Add buttons to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            buttonPanel.add(button);

            // Add action listener to each button
            button.addActionListener(d -> {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();

                    if ("0123456789".contains(command)) {
                        // Append number to the display
                        display.setText(display.getText() + command);
                    } else if ("/*-+".contains(command)) {
                        // Store the first number and the operator
                        num[0] = Double.parseDouble(display.getText());
                        operator[0] = command;
                        display.setText("");
                    } else if (command.equals("=")) {
                        // Perform the calculation
                        double secondNum = Double.parseDouble(display.getText());
                        double result = switch (operator[0]) {
                            case "+" -> num[0] + secondNum;
                            case "-" -> num[0] - secondNum;
                            case "*" -> num[0] * secondNum;
                            case "/" -> num[0] / secondNum;
                        };
                        display.setText(String.valueOf(result));
                    } else if (command.equals("C")) {
                        // Clear the display
                        display.setText("");
                    }
                }
            });
        }

        // Show the frame
        frame.setVisible(true);
    }
}
