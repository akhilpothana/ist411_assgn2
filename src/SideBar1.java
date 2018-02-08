import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import javax.swing.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
public class SideBar1 extends JPanel
{
    private JLabel city;
    private JButton advert;
    private JButton something1;
    private JButton something2;
    
    private String in;
    public SideBar1 ()
    {
        super ();
        setBackground(Color.white);
        setLayout(new GridLayout(5,1));
        CreateButtons();
    }

    public SideBar1(String info){
        in = info;
    }

    SideBar1(NewsDeck deck) {
    }
    
    public void CreateButtons(){
        city = new JLabel();
        add(city);
        advert = new JButton("Some advert here  <<<>>>>");
        add(advert);
        something1 = new JButton("Something 1");
        add(something1);
        something2 = new JButton("Something 2");
        add(something2);
        
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(getWeather());
            JSONArray days = (JSONArray) json.get("consolidated_weather");
            JSONObject day = (JSONObject) days.get(0);
            
            city.setText("<html>Weather for Philadelphia<br> " + day.get("weather_state_name") + "<br>" + day.get("the_temp") + " C" + "</html>");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
               
    }
    
    /**
     * Method to retrieve weather data and display it
     */
    public String getWeather(){
        
        String response = "";
        try {
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

 