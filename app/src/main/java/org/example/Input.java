package org.example;

import org.lwjgl.glfw.GLFW;

public class Input {
    private long window;

    public Input(long window) {
        this.window = window;
    }

    public Boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(this.window, key) == GLFW.GLFW_PRESS;
    }
}
