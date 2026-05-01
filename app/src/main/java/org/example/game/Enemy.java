package org.example.game;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;

public class Enemy {
    public float x, y;
    public float width = 32, height = 32;
    public float speed = 120f;
    private float directionX = 1f;

    private World world;

    public Enemy(float startX, float startY, World world) {
        this.x = startX;
        this.y = startY;
        this.world = world;
    }

    public void update(double delta) {
        float dx = (float) (directionX * speed * delta);
        if (Math.random() < 0.01) {
            dx -= dx;
        }
        moveIfValid(dx, 0);
    }

    public void render() {
        // Turns off 2D texturing
        glDisable(GL_TEXTURE_2D);
        glColor3f(1f, 0f, 0f);
        // Starting drawing a quadrilateral primitive
        glBegin(GL_QUADS);
        // Draw rectangle
        glVertex2d(x, y);
        glVertex2d(x + width, y);
        glVertex2d(x + width, y + height);
        glVertex2d(x, y + height);
        glEnd();

        glColor3f(1f, 1f, 1f);
        glEnable(GL_TEXTURE_2D);
    }

    private void moveIfValid(float dx, float dy) {
        float newX = x + dx;
        float newY = y + dy;

        if (!checkCollision(newX, newY)) {
            x = newX;
            y = newY;
        } else {
            directionX *= -1;
        }
    }

    private boolean checkCollision(float x, float y) {
        float centerX = x + width / 2;
        float centerY = y + height / 2;
        int radius = 12;

        return world.isWall(centerX - radius, centerY - radius)
                || world.isWall(centerX + radius, centerY - radius)
                || world.isWall(centerX - radius, centerY + radius)
                || world.isWall(centerX + radius, centerY + radius)
                || world.isWall(centerX, centerY);
    }
}
