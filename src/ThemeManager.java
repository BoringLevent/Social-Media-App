import javax.swing.*;
import java.awt.*;

public class ThemeManager {
    public static void showSettings() {
        Main.mainPanel.removeAll();
        JPanel settings = new JPanel();
        settings.setLayout(new BoxLayout(settings, BoxLayout.Y_AXIS));
        JLabel themeLabel = new JLabel("Theme Options:");
        JButton toggleTheme = new JButton("Toggle Light/Dark Mode");

        toggleTheme.addActionListener(e -> {
            Constants.currentTheme = Constants.currentTheme.equals(Color.white) ? Color.darkGray : Color.white;
            applyTheme();
        });

        settings.add(themeLabel);
        settings.add(toggleTheme);
        Main.mainPanel.add(settings);
        applyTheme();
    }

    public static void applyTheme() {
        Main.mainPanel.setBackground(Constants.currentTheme);
        Main.menuBar.setBackground(Constants.currentTheme);
        for (Component comp : Main.mainPanel.getComponents()) {
            if (comp instanceof JPanel) {
                comp.setBackground(Constants.currentTheme);
                for (Component inner : ((JPanel) comp).getComponents()) {
                    if (!(inner instanceof JButton)) {
                        inner.setBackground(Constants.currentTheme);
                        inner.setForeground(Constants.currentTheme.equals(Color.white) ? Color.black : Color.white);
                    }
                }
            }
        }
        Main.mainPanel.revalidate();
        Main.mainPanel.repaint();
    }
}
