package org.example.game;

import static org.lwjgl.opengl.GL11.*;

import org.example.game.Constants.Direction;
import org.example.interfaces.implementations.Input;
import org.example.render.Texture;

public class Player {

    // Position
    public float x, y;
    // Size
    public float width = 32, height = 32;
    // Speed in pixels per second
    public float speed = 200f;
    // Color
    public float r = 1.0f, g = 1.0f, b = 0.0f; // yellow

    private World world;
    private static final int COLLISION_RADIUS = 12;
    private static final int SPRITE_SIZE = 32;

    private Texture rightText;
    private Texture leftText;
    private Texture upText;
    private Texture downText;

    private Direction currentDirection = Direction.RIGHT;

    public Player(float startX, float startY, World world) {
        this.world = world;

        this.x = startX;
        this.y = startY;

        // Load player texture
        this.rightText = new Texture("pacmanRigth.png");
        this.leftText = new Texture("pacmanLeft.png");
        this.upText = new Texture("pacmanUp.png");
        this.downText = new Texture("pacmanDown.png");
    }

    // Update position using Input
    public void update(double delta, Input input) {

        if (input.isUp()) {
            moveIfValid(0, (int) -(speed * delta));
            this.currentDirection = Constants.Direction.UP;
        }
        if (input.isDown()) {
            moveIfValid(0, (int) (speed * delta));
            this.currentDirection = Constants.Direction.DOWN;
        }
        if (input.isLeft()) {
            moveIfValid((int) -(speed * delta), 0);
            this.currentDirection = Constants.Direction.LEFT;
        }
        if (input.isRight()) {
            moveIfValid((int) (speed * delta), 0);
            this.currentDirection = Constants.Direction.RIGHT;
        }
    }

    // Draw player rectangle
    public void render() {
        Texture currentTexture = switch (currentDirection) {
            case UP -> downText;
            case DOWN -> upText;
            case LEFT -> leftText;
            case RIGHT -> rightText;
        };

        currentTexture.bind();

        glBegin(GL_QUADS);

        glTexCoord2f(0f, 0f);
        glVertex2f(x, y);

        glTexCoord2f(1f, 0f);
        glVertex2f(x + width, y);

        glTexCoord2f(1f, 1f);
        glVertex2f(x + width, y + height);

        glTexCoord2f(0f, 1f);
        glVertex2f(x, y + height);

        glEnd();
    }

    private void moveIfValid(int dx, int dy) {
        float newX = x + dx;
        float newY = y + dy;

        if (!checkCollision(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    private boolean checkCollision(float x, float y) {
        // Calculate sprite center
        float centerX = x + SPRITE_SIZE / 2;
        float centerY = y + SPRITE_SIZE / 2;

        // Check circle around center with COLLISION_RADIUS
        return world.isWall((int) (centerX - COLLISION_RADIUS), (int) (centerY - COLLISION_RADIUS)) ||
                world.isWall((int) (centerX + COLLISION_RADIUS), (int) (centerY - COLLISION_RADIUS)) ||
                world.isWall((int) (centerX - COLLISION_RADIUS), (int) (centerY + COLLISION_RADIUS)) ||
                world.isWall((int) (centerX + COLLISION_RADIUS), (int) (centerY + COLLISION_RADIUS)) ||
                world.isWall((int) centerX, (int) centerY); // Check center point too
    }
}
