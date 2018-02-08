
import java.awt.*;
import javax.swing.*;

public class NewsDeck extends JPanel {

    private String in;
    private JLabel news;

    public NewsDeck() {
        super();
        setBackground(Color.white);
        setLayout(new GridLayout());
        getNews();

    }

    public NewsDeck(String info) {
        in = info;

    }

    NewsDeck(InfPanel inf) {
    }

    private void getNews() {
        news = new JLabel("Coming soon...");
        news.setFont(new Font("Serif", Font.BOLD, 20));
        add(news, "Center");

    }
}
