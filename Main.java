import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RobloxGroupFundsChecker {
    public static void main(String[] args) {
        try {
            // Replace <cookie> with your Roblox cookie
            String cookie = "<cookie>";
            
            // Replace <groupId> with the ID of the Roblox group you want to check funds for
            int groupId = <groupId>;
            
            // Replace <webhookUrl> with the URL of your Discord webhook
            String webhookUrl = "<webhookUrl>";
            
            // Construct the URL to fetch the group funds
            String url = "https://groups.roblox.com/v1/groups/" + groupId + "/currency";
            
            // Create a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            
            // Set the request method and add the cookie to the request header
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", ".ROBLOSECURITY=" + cookie);
            
            // Get the response code
            int responseCode = connection.getResponseCode();
            
            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            // Send the response to the Discord webhook
            String payload = "{\"content\": \"" + response.toString() + "\"}";
            HttpURLConnection webhookConnection = (HttpURLConnection) new URL(webhookUrl).openConnection();
            webhookConnection.setRequestMethod("POST");
            webhookConnection.setRequestProperty("Content-Type", "application/json");
            webhookConnection.setDoOutput(true);
            webhookConnection.getOutputStream().write(payload.getBytes("UTF-8"));
            webhookConnection.getResponseCode();
            
            // Print the response code
            System.out.println("Response Code: " + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// made by isha stan loona guys stan loona // for easier checking the group funds of your multiple groups at roblox
