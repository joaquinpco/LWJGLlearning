package org.example.game;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.util.List;

import org.example.algorithm.AStarPathFinder;
import org.example.algorithm.PathNode;
import org.example.render.Texture;

public class Enemy {

    private Texture texture;
    private AStarPathFinder pathFinder;

    public float x, y;
    public float width = 32, height = 32;
    public float speed = 50f;
    private float directionX = 1f;

    private World world;

    public Enemy(float startX, float startY, String spriteName, World world) {
        texture = new Texture(spriteName);
        this.x = startX;
        this.y = startY;
        this.world = world;
        this.pathFinder = new AStarPathFinder();
    }

    public void update(double delta, Player player) {
        List<PathNode> path = getPathToPlayer(player);
        if (path.size() > 1) {
            PathNode next = path.get(1);
            int currentTileX = getTileX();
            int currentTileY = getTileY();
            int dxTile = next.getX() - currentTileX;
            int dyTile = next.getY() - currentTileY;
            float dx = dxTile * speed * (float) delta;
            float dy = dyTile * speed * (float) delta;
            moveIfValid(dx, dy);
        } else {
            float dx = (float) (directionX * speed * delta);
            if (Math.random() < 0.01) {
                dx = -dx;
            }
            moveIfValid(dx, 0);
        }
    }

    public void render() {
        texture.bind();

        glColor3f(1f, 1f, 1f);
        glBegin(GL_QUADS);

        glTexCoord2f(0f, 0f);
        glVertex2d(x, y);

        glTexCoord2f(1f, 0f);
        glVertex2d(x + width, y);

        glTexCoord2f(1f, 1f);
        glVertex2d(x + width, y + height);

        glTexCoord2f(0f, 1f);
        glVertex2d(x, y + height);

        glEnd();
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

    private int getTileX() {
        return (int) ((x + width / 2) / World.TILE_SIZE);
    }

    private int getTileY() {
        return (int) ((y + height / 2) / World.TILE_SIZE);
    }

    private int getPlayerTileX(Player player) {
        return (int) ((player.x + player.width / 2) / World.TILE_SIZE);
    }

    private int getPlayerTileY(Player player) {
        return (int) ((player.y + player.height / 2) / World.TILE_SIZE);
    }

    private List<PathNode> getPathToPlayer(Player player) {
        PathNode start = new PathNode(getTileX(), getTileY());
        PathNode goal = new PathNode(getPlayerTileX(player), getPlayerTileY(player));
        return pathFinder.findPath(start, goal, world.getWalkableGrid());
    }
}
