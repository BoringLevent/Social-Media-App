import javax.swing.*;
import java.io.*;
import java.util.List;

public class PostManager {
    public static void createPost(JFrame frame) {
        String content = JOptionPane.showInputDialog(frame, "Enter your post:");
        if (content != null && !content.isEmpty()) {
            try {
                File dir = new File(Constants.POSTS_DIR);
                if (!dir.exists()) dir.mkdirs();
                File post = new File(Constants.POSTS_DIR + "/" + System.currentTimeMillis() + ".txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(post))) {
                    String timeStamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a"));
                    writer.write("Abdul Baloch\n" + content + "\n" + timeStamp + "\n0\n0");
                }
                loadPosts();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving post.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void loadPosts() {
        Main.mainPanel.removeAll();
        File dir = new File(Constants.POSTS_DIR);
        if (dir.exists() && dir.isDirectory()) {
            File[] posts = dir.listFiles((d, name) -> name.endsWith(".txt"));
            if (posts != null) {
                for (File post : posts) {
                    try {
                        List<String> lines = java.nio.file.Files.readAllLines(post.toPath());
                        if (lines.size() >= 4) {
                            String author = lines.get(0);
                            String content = lines.get(1);
                            String date = lines.get(2);
                            PostPanel postPanel = new PostPanel(author, content, date, post);
                            Main.mainPanel.add(postPanel);
                        }
                    } catch (IOException e) {
                        System.err.println("Could not read post: " + post.getName());
                    }
                }
            }
        }
        ThemeManager.applyTheme();
    }
}
