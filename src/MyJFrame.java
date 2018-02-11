
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyJFrame extends JFrame {

    final int FRAME_WIDTH = 1000;
    final int FRAME_HEIGHT = 700;

    public MyJFrame() {
        super("The News Hub");

        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        NewsDeck nd = new NewsDeck();

        NewsOutlets no = new NewsOutlets();
        
        add(nd, "Center");
        add(no, "South");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
//		
    }

}
