
import java.awt.*;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.*;

public class NewsOutlets extends JPanel {

    private JButton cnn;
    private JButton cbs;
    private JButton bbc;
    private JButton fox;
    
    private final String API_Key = "74e0caef3f69426e9228da0337cfbb18";
    
    private NewsDeck nd;
    
    public NewsOutlets(NewsDeck nd) {
        super();
        setBackground(Color.yellow);
        setLayout(new GridLayout());
        CreateButtons();
        
        this.nd = nd;
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
    
    public String getNews(String source){
        
        String response = "";
        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?" + "sources=bbc-news&" + "apiKey=cac0bdfd2db24f4abb4cade9f209f999");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // read the response and obtain string value of JSON data
            InputStream in = new BufferedInputStream(connection.getInputStream());
            response = convertJSONtoString(in);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(response);
        return response;
    }
        
    private String convertJSONtoString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }      
        return sb.toString();
    }    
}
