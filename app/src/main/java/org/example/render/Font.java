package org.example.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBEasyFont;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;

public class Font {

    /**
     * Renders a text string at the given position using STB easy font.
     *
     * @param x the x coordinate in screen space where text will start
     * @param y the y coordinate in screen space where text will start
     * @param text the text to render
     */
    public static void renderText(float x, float y, String text) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer buffer = stack.malloc(text.length() * 300);

            int quads = STBEasyFont.stb_easy_font_print(x, y, text, null, buffer);

            GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
            GL11.glVertexPointer(2, GL11.GL_FLOAT, 16, buffer);
            GL11.glDrawArrays(GL11.GL_QUADS, 0, quads * 4);
            GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        }
    }

    /**
     * Renders a text string at the given position using STB easy font, scaled by the provided factor.
     *
     * This method uses an OpenGL matrix transform so the rendered text size can be adjusted.
     *
     * @param x the x coordinate in screen space where text will start
     * @param y the y coordinate in screen space where text will start
     * @param text the text to render
     * @param scale the scale factor to apply to the rendered text (1.0 = normal size)
     */
    public static void renderText(float x, float y, String text, float scale) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer buffer = stack.malloc(text.length() * 300);

            GL11.glPushMatrix();
            GL11.glTranslatef(x, y, 0);
            GL11.glScalef(scale, scale, 1f);

            int quads = STBEasyFont.stb_easy_font_print(0, 0, text, null, buffer);

            GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
            GL11.glVertexPointer(2, GL11.GL_FLOAT, 16, buffer);
            GL11.glDrawArrays(GL11.GL_QUADS, 0, quads * 4);
            GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);

            GL11.glPopMatrix();
        }
    }
}