
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JButton weatherButton = new JButton("");
    private JButton articleButtons[] = new JButton[7];
    
    private final String API_Key = "74e0caef3f69426e9228da0337cfbb18";
    private Desktop desktop = Desktop.getDesktop();    

    public NewsDeck() {
        super();
        setBackground(Color.white);
        setLayout(new GridLayout(8,0));        
        weatherButton.addActionListener((ActionEvent e) -> {
                    
                   try {
                        desktop.browse(new URL("https://weather.com/weather/today/l/USPA1276:1:US").toURI());
                    }
                    catch(Exception ex) {
                        ex.printStackTrace();
                    }
                });
        add(weatherButton);
        for(int i=0; i<= 6; i++)
        {
            articleButtons[i] = new JButton("");
            add(articleButtons[i]);
        }
        getNews("", "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_Key);  
        getWeather();
    }
    
    //Initiate retrieval and display of weather
    public void getWeather(){
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(getWeatherData());
            JSONArray days = (JSONArray) json.get("consolidated_weather");
            JSONObject day = (JSONObject) days.get(0);
            
            weatherButton.setText("<html>Weather for Philadelphia<br> " + day.get("weather_state_name") + "<br>" + day.get("the_temp") + " C" + "</html>");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Initiate retrieval and display of news
    public void getNews(String news, String urlString) {
      
        JSONParser parser = new JSONParser();
        for(int i=0; i<= 6; i++)
        {
            try {  
                JSONObject json = (JSONObject) parser.parse(getNewsData(urlString));
                JSONArray articles = (JSONArray) json.get("articles");
                JSONObject article = (JSONObject) articles.get(i);

                getArticleButtons()[i].setText((String)article.get("title"));
                
                //Removeve earlier actionlistener, so new ones can be set for the new articles
                if(!news.equals(""))
                {
                    for(ActionListener al : getArticleButtons()[i].getActionListeners() ) {
                        getArticleButtons()[i].removeActionListener(al);
                    }
                }

                getArticleButtons()[i].addActionListener((ActionEvent e) -> {
                    
                    try {
                       desktop.browse(new URL((String)article.get("url")).toURI());
                    }
                    catch(Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        } 
        
    }
    
    public String getNewsData(String urlString){
        
        String response = "";
        try {
            URL url = new URL(urlString);
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
    
    /**
     * This method takes in input stream and correct formats it so be parsed as JSON
     * @return - a JSON formatted String
     */
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
    
    /**
     * Method to retrieve weather data and display it
     */
    public String getWeatherData(){
        
        String response = "";
        try {
            //Obtaining weather data for the nearest major location, Philadelphia
            URL url = new URL("https://www.metaweather.com/api/location/2471217/");
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

    /**
     * @return the articleButtons
     */
    public JButton[] getArticleButtons() {
        return articleButtons;
    }

    /**
     * @param articleButtons the articleButtons to set
     */
    public void setArticleButtons(JButton[] articleButtons) {
        this.articleButtons = articleButtons;
    }
}
