
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyJFrame extends JFrame {

    final int FRAME_WIDTH = 1000;
    final int FRAME_HEIGHT = 700;

    public MyJFrame() {
        super("The News Hub");

        MyJPanel mjp = new MyJPanel();
        mjp.setBackground(Color.GRAY);

        add(mjp, "Center");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
//		
    }

}
