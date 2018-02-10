
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
        news = new JLabel("");
        news.setFont(new Font("Serif", Font.BOLD, 20));
        add(news, "Center");
        
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(getNewsData());
            JSONArray articles = (JSONArray) json.get("articles");
            JSONObject article = (JSONObject) articles.get(0);
            
            news.setText((String)article.get("title"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    public String getNewsData(){
        
        String response = "";
        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=74e0caef3f69426e9228da0337cfbb18");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // read the response and obtain string value of JSON data
            InputStream in = new BufferedInputStream(connection.getInputStream());
            response = convertJSONtoString(in);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
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
