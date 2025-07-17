import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class ProfileManager {
    public static void showProfile() {
        Main.mainPanel.removeAll();
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField(20);
        JTextField usernameField = new JTextField(20);
        JTextArea bioField = new JTextArea(3, 20);
        bioField.setLineWrap(true);
        bioField.setWrapStyleWord(true);
        bioField.setBorder(BorderFactory.createLineBorder(Color.black));

        Dimension fieldSize = new Dimension(200, 25);
        nameField.setMaximumSize(fieldSize);
        usernameField.setMaximumSize(fieldSize);
        bioField.setMaximumSize(new Dimension(200, 50));

        try {
            File file = new File(Constants.PROFILE_FILE);
            if (file.exists()) {
                List<String> lines = java.nio.file.Files.readAllLines(file.toPath());
                if (lines.size() >= 3) {
                    nameField.setText(lines.get(0));
                    usernameField.setText(lines.get(1));
                    bioField.setText(lines.get(2));
                }
            }
        } catch (IOException ex) {
            System.err.println("Could not read profile.");
        }

        profilePanel.add(new JLabel("Name:"));
        profilePanel.add(nameField);
        profilePanel.add(new JLabel("Username:"));
        profilePanel.add(usernameField);
        profilePanel.add(new JLabel("Bio:"));
        profilePanel.add(bioField);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(ev -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.PROFILE_FILE))) {
                writer.write(nameField.getText() + "\n" + usernameField.getText() + "\n" + bioField.getText());
                JOptionPane.showMessageDialog(Main.mainPanel, "Profile saved.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(Main.mainPanel, "Error saving profile.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        profilePanel.add(saveBtn);
        Main.mainPanel.add(profilePanel);
        ThemeManager.applyTheme();
    }
}
