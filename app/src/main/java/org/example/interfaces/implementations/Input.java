package org.example.interfaces.implementations;

import org.example.interfaces.InputState;
import org.lwjgl.glfw.GLFW;

public class Input implements InputState {
    private long window;

    // Track previous key states for debouncing
    private boolean prevUp = false;
    private boolean prevDown = false;
    private boolean prevEnter = false;
    private boolean prevLeft = false;
    private boolean prevRight = false;
    private boolean prevEscape = false;

    public Input(long window) {
        this.window = window;
    }

    private boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(this.window, key) == GLFW.GLFW_PRESS;
    }

    // Continuous key state for GAME (hold down = continuous)
    @Override
    public boolean isUp() {
        return (isKeyDown(GLFW.GLFW_KEY_W) || isKeyDown(GLFW.GLFW_KEY_UP)) && !isLeft() && !isRight();
    }

    @Override
    public boolean isDown() {
        return (isKeyDown(GLFW.GLFW_KEY_S) || isKeyDown(GLFW.GLFW_KEY_DOWN)) && !isLeft() && !isRight();
    }

    @Override
    public boolean isLeft() {
        return isKeyDown(GLFW.GLFW_KEY_A) || isKeyDown(GLFW.GLFW_KEY_LEFT);
    }

    @Override
    public boolean isRight() {
        return isKeyDown(GLFW.GLFW_KEY_D) || isKeyDown(GLFW.GLFW_KEY_RIGHT);
    }

    @Override
    public boolean isEnter() {
        return isKeyDown(GLFW.GLFW_KEY_ENTER);
    }

    // Debounced key presses for MENU (press once = one action)
    public boolean isUpPressed() {
        boolean currentUp = isKeyDown(GLFW.GLFW_KEY_W) || isKeyDown(GLFW.GLFW_KEY_UP);
        boolean pressed = currentUp && !prevUp;
        prevUp = currentUp;
        return pressed;
    }

    public boolean isDownPressed() {
        boolean currentDown = isKeyDown(GLFW.GLFW_KEY_S) || isKeyDown(GLFW.GLFW_KEY_DOWN);
        boolean pressed = currentDown && !prevDown;
        prevDown = currentDown;
        return pressed;
    }

    public boolean isEnterPressed() {
        boolean currentEnter = isKeyDown(GLFW.GLFW_KEY_ENTER);
        boolean pressed = currentEnter && !prevEnter;
        prevEnter = currentEnter;
        return pressed;
    }

    public boolean isLeftPressed() {
        boolean currentLeft = isKeyDown(GLFW.GLFW_KEY_A) || isKeyDown(GLFW.GLFW_KEY_LEFT);
        boolean pressed = currentLeft && !prevLeft;
        prevLeft = currentLeft;
        return pressed;
    }

    public boolean isRightPressed() {
        boolean currentRight = isKeyDown(GLFW.GLFW_KEY_D) || isKeyDown(GLFW.GLFW_KEY_RIGHT);
        boolean pressed = currentRight && !prevRight;
        prevRight = currentRight;
        return pressed;
    }

    public boolean isEscapePressed() {
        boolean currentEscape = isKeyDown(GLFW.GLFW_KEY_ESCAPE);
        boolean pressed = currentEscape && !prevEscape;
        prevEscape = currentEscape;
        return pressed;
    }
}
