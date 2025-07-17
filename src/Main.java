import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static JPanel mainPanel;
    public static JPanel menuBar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("KHUSH PUSH");
        frame.setLayout(new BorderLayout());

        menuBar = new JPanel();
        JPanel sidebar = new JPanel();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        menuBar.setPreferredSize(new Dimension(180, Constants.HEIGHT));
        sidebar.setPreferredSize(new Dimension(88, Constants.HEIGHT));
        mainPanel.setPreferredSize(new Dimension(650, Constants.HEIGHT));
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("KHUSH PUSH");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
        menuBar.add(usernameLabel);

        JButton homeButton = Constants.createSidebarButton("Home", "/home.png");
        JButton postButton = Constants.createSidebarButton("Post", "/post.png");
        JButton profileButton = Constants.createSidebarButton("Profile", "/profile.png");
        JButton settingsButton = Constants.createSidebarButton("Settings", "/settings.png");
        JButton aboutButton = Constants.createSidebarButton("About", "about.png");

        aboutButton.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Social Media App\nVersion 1.0\nCreated by:\nAbdul Qadeer\nLaiba Bashir\nAman Jadoon",
                "About", JOptionPane.INFORMATION_MESSAGE));

        homeButton.addActionListener(e -> PostManager.loadPosts());

        postButton.addActionListener(e -> PostManager.createPost(frame));

        profileButton.addActionListener(e -> ProfileManager.showProfile());

        settingsButton.addActionListener(e -> ThemeManager.showSettings());

        menuBar.add(homeButton);
        menuBar.add(postButton);
        menuBar.add(profileButton);
        menuBar.add(settingsButton);
        menuBar.add(Box.createVerticalGlue());
        menuBar.add(aboutButton);

        frame.add(menuBar, BorderLayout.WEST);
        frame.add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        frame.setSize(800, Constants.HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PostManager.loadPosts();
    }
}
