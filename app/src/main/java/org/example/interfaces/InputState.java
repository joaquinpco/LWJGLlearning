package org.example.interfaces;

public interface InputState {
    /**
     * Returns true when the "up" input (for example: W key or up arrow)
     * is currently active/pressed.
     *
     * @return true if the up input is active, false otherwise
     */
    boolean isUp();

    /**
     * Returns true when the "down" input (for example: S key or down arrow)
     * is currently active/pressed.
     *
     * @return true if the down input is active, false otherwise
     */
    boolean isDown();

    /**
     * Returns true when the "left" input (for example: A key or left arrow)
     * is currently active/pressed.
     *
     * @return true if the left input is active, false otherwise
     */
    boolean isLeft();

    /**
     * Returns true when the "right" input (for example: D key or right arrow)
     * is currently active/pressed.
     *
     * @return true if the right input is active, false otherwise
     */
    boolean isRight();

    /**
     * Returns true when the "enter" input (Enter/Return key)
     * is currently active/pressed.
     *
     * @return true if the enter input is active, false otherwise
     */
    boolean isEnter();

    /**
     * Returns true when the "escape" input (Escape key)
     * is currently active/pressed.
     *
     * @return true if the escape input is active, false otherwise
     */
    boolean isEscapePressed();
}
