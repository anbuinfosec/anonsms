import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SendSms {
    public static void main(String[] args) {
        try {
            System.out.print("Enter your API key: ");
            String apiKey = new BufferedReader(new InputStreamReader(System.in)).readLine();
            System.out.print("Enter the mobile number (format: 01XXXXXXXXX): ");
            String mobile = new BufferedReader(new InputStreamReader(System.in)).readLine();
            System.out.print("Enter the message to send: ");
            String message = new BufferedReader(new InputStreamReader(System.in)).readLine();

            sendSms(apiKey, mobile, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendSms(String apiKey, String mobile, String message) {
        try {
            String urlString = "https://sms.anbuinfosec.xyz/api/sms?apikey=" + apiKey +
                               "&mobile=" + mobile + "&msg=" + URLEncoder.encode(message, "UTF-8");

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            String response = content.toString();
            System.out.println(response); // You can parse the JSON response here for success/error handling

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
