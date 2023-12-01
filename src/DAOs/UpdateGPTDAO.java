package DAOs;

import Usecase.UpdateGPTcomment.GPTupdateDAI;
import domain.Activity;
import domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

public class UpdateGPTDAO implements GPTupdateDAI{
    @Override
    public String getComment(User user, String weather) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "YOUR API KEY HERE";
        String model = "gpt-3.5-turbo";
        String formattedActivity = null;
        for (Activity activity: user.getActivities()) {
            if (activity.getTime().getDayOfYear() == LocalDateTime.now().getDayOfYear()) {
                formattedActivity += ("Activity:" + activity.getDescription() + " Start time:" + activity.getTime() +
                        " Duration:" + activity.getDuration() + "\n");
            }
        }
        String message = "Please tell me how to improve my physical activity schedual for today, what may I adjust" +
                 "given today's weather is :" + weather + "and my activities are : ";
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" +
                    message + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            int start = response.indexOf("content")+ 11;

            int end = response.indexOf("\"", start);

            return response.substring(start, end);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
