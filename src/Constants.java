import javax.swing.*;
import java.awt.*;

public class Constants {
    public static final int HEIGHT = 600;
    public static final String BASE_PATH = "D:/University/2nd Semester/OOP Lab/Project/Actual project/Social Media App";
    public static final String ICONS_DIR = BASE_PATH + "/icons";
    public static final String POSTS_DIR = ICONS_DIR + "/posts";
    public static final String PROFILE_FILE = BASE_PATH + "/profile.txt";

    public static Color currentTheme = Color.white;

    public static JButton createSidebarButton(String text, String iconFile) {
        JButton button = new JButton(text, new ImageIcon(ICONS_DIR + "/" + iconFile));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setIconTextGap(10);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        return button;
    }
}
