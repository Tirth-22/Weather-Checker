package Weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherFetcher {
    private static final String API_KEY = "0a5b5acad30a786d9c2f82f94b5ba112";  // Replace with your actual API key
    private static final int TIMEOUT = 5000;

    public static String getWeather(String city) {
        try {
            String encodedCity = URLEncoder.encode(city, "UTF-8");
            String endpoint = "https://api.openweathermap.org/data/2.5/weather?q="
                    + encodedCity + "&appid=" + API_KEY + "&units=metric";

            URL url = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(TIMEOUT);
            con.setReadTimeout(TIMEOUT);

            int status = con.getResponseCode();
            String responseContent = readResponse(con, status);

            // Print the full JSON response for debugging
            System.out.println("API Response: " + responseContent);

            JSONObject json = new JSONObject(responseContent);

            // Check for API error
            if (json.optInt("cod", 200) != 200) {
                return "API Error: " + json.optString("message", "Unknown error");
            }

            // Ensure required keys are present
            if (!json.has("weather") || !json.has("main")) {
                return "Error: Invalid weather data format";
            }

            JSONArray weatherArray = json.getJSONArray("weather");
            if (weatherArray.isEmpty()) {
                return "Error: No weather data available";
            }

            JSONObject mainObj = json.getJSONObject("main");
            String weatherMain = weatherArray.getJSONObject(0).optString("main", "Unknown");
            double temperature = mainObj.optDouble("temp", Double.NaN);

            if (Double.isNaN(temperature)) {
                return "Error: Temperature data missing or invalid";
            }

            return String.format("Weather: %s, Temperature: %.1f°C", weatherMain, temperature);

        } catch (Exception e) {
            e.printStackTrace(); // Show full stack trace in console
            return "Error: " + e.getMessage();
        }
    }

    private static String readResponse(HttpURLConnection con, int status) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                status >= 400 ? con.getErrorStream() : con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
}
