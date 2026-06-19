package org.example.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBEasyFont;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;

public class Font {

    public static void renderText(float x, float y, String text) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer buffer = stack.malloc(text.length() * 270);

            int quads = STBEasyFont.stb_easy_font_print(x, y, text, null, buffer);

            GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
            GL11.glVertexPointer(2, GL11.GL_FLOAT, 16, buffer);
            GL11.glDrawArrays(GL11.GL_QUADS, 0, quads * 4);
            GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        }
    }

    public static void renderText(float x, float y, String text, float scale) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            ByteBuffer buffer = stack.malloc(text.length() * 270);

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