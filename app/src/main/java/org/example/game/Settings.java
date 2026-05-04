package org.example.game;

import org.lwjgl.opengl.GL11;
import org.example.render.Font;

public class Settings {
    private String[] options = { "Difficulty", "Volume", "Back" };
    private int selectedIndex = 0;

    private String difficulty = "MEDIUM";
    private int volume = 75; // 0-100

    public void render() {
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 600, 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glDisable(GL11.GL_TEXTURE_2D);

        // Draw title
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        Font.renderText(250, 80, "SETTINGS");

        // Render settings items
        for (int i = 0; i < options.length; i++) {
            float y = 200 + (i * 80);

            if (i == selectedIndex) {
                GL11.glColor3f(1.0f, 1.0f, 1.0f); // White - selected
            } else {
                GL11.glColor3f(0.5f, 0.5f, 1.0f); // Light blue
            }

            drawSettingItem(100, y, i);
        }

        // Draw hint
        GL11.glColor3f(0.7f, 0.7f, 0.7f);
        Font.renderText(100, 520, "UP/DOWN: Select, LEFT/RIGHT: Adjust, ENTER: Confirm");

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private void drawSettingItem(float x, float y, int index) {
        // Draw rectangle
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + 300, y);
        GL11.glVertex2f(x + 300, y + 60);
        GL11.glVertex2f(x, y + 60);
        GL11.glEnd();

        // Draw text
        GL11.glColor3f(0.0f, 0.0f, 0.0f); // Black text

        if (index == 0) { // Difficulty
            Font.renderText(x + 10, y + 15, "Difficulty: " + difficulty);
        } else if (index == 1) { // Volume
            Font.renderText(x + 10, y + 15, "Volume: " + volume + "%");
        } else { // Back
            Font.renderText(x + 100, y + 15, "BACK");
        }

        // Draw border
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + 300, y);
        GL11.glVertex2f(x + 300, y + 60);
        GL11.glVertex2f(x, y + 60);
        GL11.glEnd();
    }

    public void selectUp() {
        selectedIndex = (selectedIndex - 1 + options.length) % options.length;
    }

    public void selectDown() {
        selectedIndex = (selectedIndex + 1) % options.length;
    }

    public void selectLeft() {
        if (selectedIndex == 0) { // Difficulty
            difficulty = difficulty.equals("EASY") ? "HARD" : difficulty.equals("MEDIUM") ? "EASY" : "MEDIUM";
        } else if (selectedIndex == 1) { // Volume
            volume = Math.max(0, volume - 10);
        }
    }

    public void selectRight() {
        if (selectedIndex == 0) { // Difficulty
            difficulty = difficulty.equals("EASY") ? "MEDIUM" : difficulty.equals("MEDIUM") ? "HARD" : "EASY";
        } else if (selectedIndex == 1) { // Volume
            volume = Math.min(100, volume + 10);
        }
    }

    public String getSelectedOption() {
        return options[selectedIndex];
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getVolume() {
        return volume;
    }
}