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

    public Boolean isUp() {
        return isKeyDown(GLFW.GLFW_KEY_W);
    }

    public Boolean isDown() {
        return isKeyDown(GLFW.GLFW_KEY_S);
    }

    public Boolean isLeft() {
        return isKeyDown(GLFW.GLFW_KEY_A);
    }

    public Boolean isRight() {
        return isKeyDown(GLFW.GLFW_KEY_D);
    }
}
