package org.example.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {

    public final int id;
    public final int width;
    public final int height;

    public Texture(String resource) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            // Load image from classpath
            ByteBuffer imageBuffer = loadResource(resource, 8 * 1024);

            stbi_set_flip_vertically_on_load(true);
            ByteBuffer image = stbi_load_from_memory(imageBuffer, w, h, channels, 4);

            if (image == null) {
                throw new RuntimeException("Failed to load texture: " + stbi_failure_reason());
            }

            width = w.get(0);
            height = h.get(0);

            id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glTexImage2D(
                    GL_TEXTURE_2D,
                    0,
                    GL_RGBA8,
                    width,
                    height,
                    0,
                    GL_RGBA,
                    GL_UNSIGNED_BYTE,
                    image);

            stbi_image_free(image);
        } catch (IOException e) {
            throw new RuntimeException("Could not load texture resource: " + resource, e);
        }
    }

    private static ByteBuffer loadResource(String resource, int bufferSize) throws IOException {
        InputStream is = Texture.class
                .getClassLoader()
                .getResourceAsStream(resource);

        if (is == null) {
            throw new IOException("Resource not found: " + resource);
        }

        ByteBuffer buffer;

        try (ReadableByteChannel rbc = Channels.newChannel(is)) {
            buffer = BufferUtils.createByteBuffer(bufferSize);

            while (true) {
                int bytes = rbc.read(buffer);
                if (bytes == -1)
                    break;

                if (buffer.remaining() == 0) {
                    ByteBuffer newBuffer = BufferUtils.createByteBuffer(buffer.capacity() * 2);
                    buffer.flip();
                    newBuffer.put(buffer);
                    buffer = newBuffer;
                }
            }
        }

        buffer.flip();
        return buffer;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void cleanup() {
        glDeleteTextures(id);
    }
}
