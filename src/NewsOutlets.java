
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

    private String in;
    private JButton cnn;
    private JButton cbs;
    private JButton bbc;
    private JButton fox; 
    
    //API from NewsAPI.org, however it doesn't work with java
    //String bbcURL = "https://newsapi.org/v2/top-headlines?" +
      //    "sources=bbc-news&" +
        //  "apiKey=cac0bdfd2db24f4abb4cade9f209f999";
    
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
        
        cnn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("CNN Clicked!");
            }
        });
        
        cbs.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("CBS Clicked!");
            }
        });
        bbc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("BBC Clicked!");
            }
        });
        
        fox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("FOX Clicked!");
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
