import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class PostPanel extends JPanel {
    int likes = 0;
    int dislikes = 0;
    File postFile;

    public PostPanel(String author, String content, String date, File file) {
        this.postFile = file;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 150));
        setMaximumSize(new Dimension(600, 150));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel avatar = new JLabel(new ImageIcon(Constants.ICONS_DIR + "/profile.png"));
        top.add(avatar);
        top.add(new JLabel(author));

        JButton deleteBtn = new JButton(new ImageIcon(Constants.ICONS_DIR + "/delete.png"));
        deleteBtn.setToolTipText("Delete Post");
        deleteBtn.setBorderPainted(false);
        deleteBtn.setContentAreaFilled(false);
        deleteBtn.setFocusPainted(false);

        deleteBtn.addActionListener(e -> {
            if (postFile.delete()) {
                JOptionPane.showMessageDialog(this, "Post deleted.");
                PostManager.loadPosts();
            }
        });

        top.add(deleteBtn);
        top.add(Box.createHorizontalStrut(250));
        top.add(new JLabel(date));
        add(top, BorderLayout.NORTH);

        JTextArea text = new JTextArea(content);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        add(text, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(245, 245, 245));

        JButton likeButton = new JButton(new ImageIcon(Constants.ICONS_DIR + "/like.png"));
        JButton dislikeButton = new JButton(new ImageIcon(Constants.ICONS_DIR + "/dislike.png"));

        JLabel likeText = new JLabel("0 Like");
        JLabel dislikeText = new JLabel("0 Dislike");

        likeButton.addActionListener(e -> {
            likes++;
            likeText.setText(likes + (likes == 1 ? " Like" : " Likes"));
        });

        dislikeButton.addActionListener(e -> {
            dislikes++;
            dislikeText.setText(dislikes + (dislikes == 1 ? " Dislike" : " Dislikes"));
        });

        buttons.add(likeButton);
        buttons.add(likeText);
        buttons.add(dislikeButton);
        buttons.add(dislikeText);

        add(buttons, BorderLayout.SOUTH);
    }
}
