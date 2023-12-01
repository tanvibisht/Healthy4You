package Recipe;

import javax.swing.*;
import java.awt.*;

public class RecipeUI {
    public RecipeUI(String recipeText) {
        if (recipeText == null || recipeText.isEmpty()) {
            recipeText = "No recipe found.";
        }
        JFrame recipeFrame = new JFrame("Recipe");
        recipeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        recipeFrame.setSize(400, 300);

        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BorderLayout());

        JTextArea recipeTextArea = new JTextArea(recipeText);
        recipeTextArea.setWrapStyleWord(true);
        recipeTextArea.setLineWrap(true);
        recipeTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(recipeTextArea);
        recipePanel.add(scrollPane, BorderLayout.CENTER);

        recipeFrame.add(recipePanel);
        recipeFrame.setVisible(true);
    }
}

