package Weather;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaSwing1 extends JFrame implements ActionListener {

    JTextField cityField;
    JLabel titleLabel, resultLabel;
    JFrame jFrame;

    public JavaSwing1() {
        jFrame = new JFrame("Weather App");
        jFrame.setSize(500, 400);
        jFrame.setLayout(null);
        jFrame.getContentPane().setBackground(new Color(230, 240, 255));
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Weather Information System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(50, 20, 380, 40);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        titleLabel.setBorder(border);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.WHITE);

        cityField = new JTextField();
        cityField.setBounds(100, 90, 200, 30);
        cityField.setFont(new Font("Arial", Font.PLAIN, 14));
        cityField.setToolTipText("Enter city name");

        JButton enterButton = new JButton("Get Weather");
        enterButton.setBounds(310, 90, 120, 30);
        enterButton.setBackground(Color.BLUE);
        enterButton.setForeground(Color.WHITE);
        enterButton.setFocusPainted(false);
        enterButton.addActionListener(this);

        resultLabel = new JLabel("Enter a city to get weather data.", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        resultLabel.setBounds(50, 150, 380, 30);
        resultLabel.setForeground(Color.DARK_GRAY);

        jFrame.add(titleLabel);
        jFrame.add(cityField);
        jFrame.add(enterButton);
        jFrame.add(resultLabel);

        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String city = cityField.getText().trim();
        if (city.isEmpty()) {
            resultLabel.setText("❌ Please enter a valid city name.");
        } else {

            resultLabel.setText("No data now, but this is where it will show.");
        }
    }

    public static void main(String[] args) {
        new JavaSwing1();
    }
}
