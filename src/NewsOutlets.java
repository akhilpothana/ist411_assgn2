
import java.awt.*;
import javax.swing.*;

public class NewsOutlets extends JPanel {

    private String in;
    private JButton cnn;
    private JButton cbs;
    private JButton bbc;
    private JButton fox;

    public NewsOutlets() {
        super();
        setBackground(Color.yellow);
        setLayout(new GridLayout());
        CreateButtons();
    }

    public NewsOutlets(String info) {
        in = info;

    }

    public NewsOutlets(NewsDeck deck) {
    }

    public void CreateButtons() {
        cnn = new JButton("CNN");
        cnn.setFont(new Font("Serif", Font.BOLD, 38));
        add(cnn);
        cbs = new JButton("CBS NEWS");
        cbs.setFont(new Font("Serif", Font.BOLD, 38));
        add(cbs);
        bbc = new JButton("BBC");
        bbc.setFont(new Font("Serif", Font.BOLD, 38));
        add(bbc);
        fox = new JButton("FOX NEWS");
        fox.setFont(new Font("Serif", Font.BOLD, 38));
        add(fox);

    }
}
