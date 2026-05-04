package org.example.game;

import org.lwjgl.opengl.GL11;
import org.example.render.Font;

public class Menu {
    private String[] options = { "Play", "Settings", "Exit" };
    private int selectedIndex = 0;

    public void render() {
        // Save current state
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

        // Set up projection
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 600, 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        // Disable textures for solid color rendering
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        // Draw title
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        Font.renderText(250, 80, "PAC-MAN MENU");

        // Render menu items
        for (int i = 0; i < options.length; i++) {
            float y = 200 + (i * 80);

            if (i == selectedIndex) {
                GL11.glColor3f(1.0f, 1.0f, 1.0f); // White - selected
            } else {
                GL11.glColor3f(0.5f, 0.5f, 1.0f); // Light blue
            }

            drawMenuItem(100, y, options[i]);
        }

        // Draw hint
        GL11.glColor3f(0.7f, 0.7f, 0.7f);
        Font.renderText(150, 520, "UP/DOWN: Select, ENTER: Confirm");

        // Re-enable textures
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        // Restore state
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void drawMenuItem(float x, float y, String label) {
        // Draw rectangle
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + 200, y);
        GL11.glVertex2f(x + 200, y + 60);
        GL11.glVertex2f(x, y + 60);
        GL11.glEnd();

        // Draw text label
        GL11.glColor3f(0.0f, 0.0f, 0.0f); // Black text
        Font.renderText(x + 50, y + 15, label);

        // Draw border
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + 200, y);
        GL11.glVertex2f(x + 200, y + 60);
        GL11.glVertex2f(x, y + 60);
        GL11.glEnd();
    }

    public void selectUp() {
        selectedIndex = (selectedIndex - 1 + options.length) % options.length;
    }

    public void selectDown() {
        selectedIndex = (selectedIndex + 1) % options.length;
    }

    public String getSelectedOption() {
        return options[selectedIndex];
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }
}