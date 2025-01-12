import javax.swing.*;
import java.awt.*;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        JTextField display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        frame.add(buttonPanel, BorderLayout.CENTER);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        final double[] num = {0};
        final String[] operator = {""};

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            buttonPanel.add(button);

            button.addActionListener(e -> {
                String command = e.getActionCommand();

                if ("0123456789".contains(command)) {
                    display.setText(display.getText() + command);
                } else if ("/*-+".contains(command)) {
                    num[0] = Double.parseDouble(display.getText());
                    operator[0] = command;
                    display.setText("");
                } else if (command.equals("=")) {
                    double secondNum = Double.parseDouble(display.getText());
                    double result = switch (operator[0]) {
                        case "+" -> num[0] + secondNum;
                        case "-" -> num[0] - secondNum;
                        case "*" -> num[0] * secondNum;
                        case "/" -> num[0] / secondNum;
                        default -> 0; // Optional: handle unexpected cases
                    };
                    display.setText(String.valueOf(result));
                } else if (command.equals("C")) {
                    display.setText("");
                }
            });
        }

        frame.setVisible(true);
    }
}
