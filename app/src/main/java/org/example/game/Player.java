package org.example.game;

import static org.lwjgl.opengl.GL11.*;

import org.example.interfaces.implementations.Input;

public class Player {

    // Position
    public float x, y;
    // Size
    public float width = 32, height = 32;
    // Speed in pixels per second
    public float speed = 200f;
    // Color
    public float r = 1.0f, g = 1.0f, b = 0.0f; // yellow

    public Player(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }

    // Update position using Input
    public void update(double delta, Input input) {
        if (input.isUp())
            y -= speed * delta;
        if (input.isDown())
            y += speed * delta;
        if (input.isLeft())
            x -= speed * delta;
        if (input.isRight())
            x += speed * delta;
    }

    // Draw player rectangle
    public void render() {
        glColor3f(r, g, b); // player color
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x + width, y + height);
        glVertex2f(x, y + height);
        glEnd();
    }
}
