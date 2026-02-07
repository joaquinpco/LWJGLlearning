package org.example.interfaces.implementations;

import org.lwjgl.glfw.GLFW;

public class Input implements InputState {
    private long window;

    public Input(long window) {
        this.window = window;
    }

    public Boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(this.window, key) == GLFW.GLFW_PRESS;
    }

    @Override
    public boolean isUp() {
        return isKeyDown(GLFW.GLFW_KEY_W) || isKeyDown(GLFW.GLFW_KEY_UP);
    }

    @Override
    public boolean isDown() {
        return isKeyDown(GLFW.GLFW_KEY_S) || isKeyDown(GLFW.GLFW_KEY_DOWN);
    }

    @Override
    public boolean isLeft() {
        return isKeyDown(GLFW.GLFW_KEY_A) || isKeyDown(GLFW.GLFW_KEY_LEFT);
    }

    @Override
    public boolean isRight() {
        return isKeyDown(GLFW.GLFW_KEY_D) || isKeyDown(GLFW.GLFW_KEY_RIGHT);
    }
}
