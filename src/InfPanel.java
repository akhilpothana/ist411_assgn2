import java.awt.*;
import javax.swing.*;
public class InfPanel extends JPanel
{
    private JLabel scrlTxt;
    private String in;
    public InfPanel ()
    {
        super ();
        setLayout(new GridLayout());
        setBackground(Color.red);
        addScrollText();
        
    }
     
    
    public InfPanel(String info){
        in = info;
        
    }
    
    public void addScrollText(){
        
        scrlTxt = new JLabel("Headlines: ");
        scrlTxt.setFont(new Font("Serif", Font.BOLD, 38));
        scrlTxt.setForeground(Color.white);
        add(scrlTxt);
        
        
    }
    
    
}
 