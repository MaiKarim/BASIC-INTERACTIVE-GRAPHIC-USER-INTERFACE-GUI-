import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingAssignment extends JFrame {

    // Required JFrame, JPanel and JLabel variables
    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    private JButton changeTextButton;
    private JButton changeBackgroundButton;

    // Constructor
    public SwingAssignment() {

        // Create and configure the JFrame
        frame = new JFrame("Java Swing Assignment");

        // Create the JPanel
        panel = new JPanel();

        // Create the JLabel with centered text alignment
        label = new JLabel(
                "Welcome to Java Swing Individual Assignment by Maimunatu A. R. Massaquoi",
                SwingConstants.CENTER
        );

        // Create buttons for the additional functionality
      changeTextButton = new JButton("Update Text");
        changeBackgroundButton = new JButton("Change Background");

        // Configure the panel with GridBagLayout for perfect vertical/horizontal centering
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.PINK);

        // Improve the JLabel appearance
        label.setFont(new Font("Tahoma", Font.BOLD, 16)); // Slightly reduced size to fit nicely in 800px width
        label.setOpaque(true);
        label.setBackground(Color.RED); // Background color inside the curved border
        label.setForeground(Color.WHITE); // Text color

        // Apply padding inside the label and add the custom Curved Border (20px radius, 2px thickness)
        label.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(Color.LIGHT_GRAY, 2, 20),
                BorderFactory.createEmptyBorder(15, 20, 15, 20) // Padding inside the curve
        ));

        // Layout constraints to stack components vertically and center them
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, gbc);

        // Panel container for buttons to sit side-by-side cleanly
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false); // Keeps background color seamless
        buttonPanel.add(changeTextButton);
        buttonPanel.add(changeBackgroundButton);

        gbc.gridy = 1;
        panel.add(buttonPanel, gbc);

        // Add the JPanel to the JFrame
        frame.add(panel);

        // Change the JLabel text when the button is clicked
        changeTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Mr. Amandus, The text has been updated successfully! Thank you.");
            }
        });

        // Change the JPanel background when the button is clicked
        changeBackgroundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.WHITE);
            }
        });

        // Add the custom application icon
        try {
            ImageIcon icon = new ImageIcon("images/java_icon.png");
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Icon missing; defaulting to standard system icon.");
        }

        // JFrame configuration
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // Custom class to draw a beautiful anti-aliased curved border
    private static class RoundedBorder extends AbstractBorder {
        private final Color color;
        private final int thickness;
        private final int radius;

        public RoundedBorder(Color color, int thickness, int radius) {
            this.color = color;
            this.thickness = thickness;
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            // Enable Anti-Aliasing for smooth, non-pixelated curved edges
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));

            // Draw the rounded border framework adjusted for thickness
            g2d.drawRoundRect(x + thickness / 2, y + thickness / 2,
                    width - thickness, height - thickness,
                    radius, radius);
            g2d.dispose();
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingAssignment();
            }
        });
    }
}