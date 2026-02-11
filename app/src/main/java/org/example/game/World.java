package org.example.game;

import org.lwjgl.opengl.GL11;

public class World {
    private static final int TILE_SIZE = 32;
    private int width, height;
    private int[][] maze;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];

        generateMaze();
    }

    private void generateMaze() {
        // Clear maze (0 = empty, 1 = wall)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                maze[y][x] = 0;
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == height - 1 || x == 0 || x == width - 1) {
                    maze[y][x] = 1; // Border walls
                }
            }
        }

        for (int y = 2; y < height - 2; y += 4) {
            for (int x = 2; x < width - 2; x += 4) {

                if (Math.random() <= 0.8) { // 80% chance to place a wall
                    maze[y][x] = 1; // Place a wall tile
                }
            }
        }

    }

    public boolean isWall(float x, float y) {
        int tileX = (int) (x / TILE_SIZE);
        int tileY = (int) (y / TILE_SIZE);
        if (tileX < 0 || tileX >= width || tileY < 0 || tileY >= height) {
            return true; // Treat out of bounds as walls
        }
        return maze[tileY][tileX] == 1;
    }

    public void render() {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == 1) {
                    // Render wall tile (e.g., as a filled rectangle)
                    GL11.glColor3f(0.0f, 0.0f, 1.0f); // blue walls
                    GL11.glBegin(GL11.GL_QUADS);
                    GL11.glVertex2f(x * TILE_SIZE, y * TILE_SIZE);
                    GL11.glVertex2f((x + 1) * TILE_SIZE, y * TILE_SIZE);
                    GL11.glVertex2f((x + 1) * TILE_SIZE, (y + 1) * TILE_SIZE);
                    GL11.glVertex2f(x * TILE_SIZE, (y + 1) * TILE_SIZE);
                    GL11.glEnd();
                }
            }
        }
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
