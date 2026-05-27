package org.example.game;

import org.lwjgl.opengl.GL11;

public class World {
    public static final int TILE_SIZE = 32;
    private int width, height;
    private int[][] maze;
    private boolean[][] coins;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];
        this.coins = new boolean[height][width];

        generateMaze();
        generateCoins();
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

    private void generateCoins() {
        for(int y = 1; y < height - 1; y++){
            for(int x = 1;x < width - 1; x++){
                if(maze[y][x] == 0 && x % 2 == 0) {
                    coins[y][x] = true;
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

    public boolean[][] getWalkableGrid() {
        boolean[][] walkable = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                walkable[y][x] = maze[y][x] == 0;
            }
        }
        return walkable;
    }

    public boolean hasCoin(float x, float y) {  // Add this method
        int tileX = (int) (x / TILE_SIZE);
        int tileY = (int) (y / TILE_SIZE);
        if (tileX < 0 || tileX >= width || tileY < 0 || tileY >= height) {
            return false;
        }
        return coins[tileY][tileX];
    }

    public void collectCoin(float x, float y) {  // Add this method
        int tileX = (int) (x / TILE_SIZE);
        int tileY = (int) (y / TILE_SIZE);
        if (tileX >= 0 && tileX < width && tileY >= 0 && tileY < height) {
            coins[tileY][tileX] = false;
        }
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
                else if(coins[y][x]){
                    //Rendering coins as a small yellow circle
                    GL11.glColor3f(1.0f, 1.0f, 0.0f); //Yellow
                    float centerX = (x + 0.5f) * TILE_SIZE;
                    float centerY = (y + 0.5f) * TILE_SIZE;
                    float radius = 4.0f;
                    GL11.glBegin(GL11.GL_TRIANGLE_FAN);
                    for(int i = 1; i <= 8; i++){
                        float angle = (float)(2 * Math.PI * i / 8);
                        GL11.glVertex2d(
                            centerX + radius * (float) Math.cos(angle) , 
                            centerY + radius * (float) Math.sin(angle)
                        );
                    }
                    GL11.glEnd();
                }
            }
        }
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
