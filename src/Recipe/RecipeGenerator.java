package Recipe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

public class RecipeGenerator {
    private static final String API_KEY = "2e354241431542068b8478ce6b6f90fd";
    private static final String API_URL = "https://api.spoonacular.com/recipes/random";

    public String generateRandomRecipe() {
        try {
            URL url = new URL(API_URL + "?apiKey=" + API_KEY + "&number=1"); // Requesting a single recipe for simplicity
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response to create a formatted recipe string
                return formatRecipe(new JSONObject(response.toString()));
            } else {
                return "Error fetching recipe data: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    private String formatRecipe(JSONObject jsonObject) {
        // Extract the recipes array
        JSONArray recipes = jsonObject.getJSONArray("recipes");
        // We requested only one recipe
        JSONObject recipe = recipes.getJSONObject(0);

        // Build a formatted string
        StringBuilder recipeBuilder = new StringBuilder();
        recipeBuilder.append("Title: ").append(recipe.getString("title")).append("\n\n");

        recipeBuilder.append("Servings: ").append(recipe.getInt("servings")).append("\n");
        recipeBuilder.append("Ready In Minutes: ").append(recipe.getInt("readyInMinutes")).append("\n");
        recipeBuilder.append("Health Score: ").append(recipe.getInt("healthScore")).append("\n");
        recipeBuilder.append("Source: ").append(recipe.getString("sourceUrl")).append("\n\n");

        recipeBuilder.append("Ingredients:\n");
        JSONArray ingredients = recipe.getJSONArray("extendedIngredients");
        for (int i = 0; i < ingredients.length(); i++) {
            JSONObject ingredient = ingredients.getJSONObject(i);
            // Try different keys if "originalString" is not available
            String ingredientDesc = ingredient.optString("originalString",
                    ingredient.optString("original",
                            ingredient.optString("name", "No description available")));
            recipeBuilder.append("- ").append(ingredientDesc).append("\n");
        }

        recipeBuilder.append("\nInstructions:\n");
        // Extracting instructions while handling HTML tags
        String instructions = recipe.optString("instructions", "No instructions provided.");
        if (instructions.contains("<ol>") && instructions.contains("</ol>")) {
            // Remove HTML tags if present
            instructions = instructions.replaceAll("<[^>]*>", "");
        }
        recipeBuilder.append(instructions);

        return recipeBuilder.toString();
    }


    public static void main(String[] args) {
        RecipeGenerator generator = new RecipeGenerator();
        String formattedRecipe = generator.generateRandomRecipe();
        System.out.println(formattedRecipe);
    }
}
