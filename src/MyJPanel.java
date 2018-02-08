
import java.awt.*;
import javax.swing.*;

public class MyJPanel extends JPanel {

    public MyJPanel() {

        BorderLayout border = new BorderLayout();
        setLayout(border);
        setBackground(Color.white);
        
        
        InfPanel inf = new InfPanel();

        SideBar1 sb = new SideBar1();

        NewsDeck nd = new NewsDeck();

        NewsOutlets no = new NewsOutlets();
        add(inf, "North");
        add(nd, "Center");
        add(sb, "East");
        add(no, "South");
    }
    
    public void JSONparser()
    {
        
    }
}
