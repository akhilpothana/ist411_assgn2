
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class NewsOutlets extends JPanel {

    private JButton home;
    private JButton cnn;
    private JButton cbs;
    private JButton bbc;
    private JButton fox;
    
    //The API key needed to fetch data
    private final String API_Key = "74e0caef3f69426e9228da0337cfbb18";
    
    private NewsDeck nd;
    
    public NewsOutlets(NewsDeck nd) {
        super();
        setLayout(new GridLayout());
        CreateButtons();
        
        this.nd = nd;
    }

    //Creates and add all the buttons to the UI. Listeners are also added
    //where new requests for news are made when the buttons are pressed
    public void CreateButtons() {
        home = new JButton("HOME");
        home.setFont(new Font("Serif", Font.BOLD, 38));
        add(home);
        cnn = new JButton("CNN");
        cnn.setFont(new Font("Serif", Font.BOLD, 38));
        add(cnn);
        cbs = new JButton("CBS");
        cbs.setFont(new Font("Serif", Font.BOLD, 38));
        add(cbs);
        bbc = new JButton("BBC");
        bbc.setFont(new Font("Serif", Font.BOLD, 38));
        add(bbc);
        fox = new JButton("FOX");
        fox.setFont(new Font("Serif", Font.BOLD, 38));
        add(fox);
        
        home.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                nd.getNews("", "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_Key);    
            }
        });
        
        cnn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                nd.getNews("1", "https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=" + API_Key);    
            }
        });
        
        cbs.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                nd.getNews("1", "https://newsapi.org/v2/top-headlines?sources=cbs-news&apiKey=" + API_Key);
            }
        });
        bbc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                nd.getNews("1", "https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=" + API_Key);
            }
        });
        
        fox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                nd.getNews("1", "https://newsapi.org/v2/top-headlines?sources=fox-news&apiKey=" + API_Key);
            }
        });
    }    
}
