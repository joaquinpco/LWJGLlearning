package org.example;

import org.example.game.World;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    void worldBorderWallsTest() {
        World world = new World(20, 15);

        assertTrue(world.isWall(0, 0));
        assertTrue(world.isWall(19 * World.TILE_SIZE, 0));
        assertTrue(world.isWall(0, 14 * World.TILE_SIZE));
        assertTrue(world.isWall(19 * World.TILE_SIZE, 14 * World.TILE_SIZE));

        // The inner tile at (1, 1) should not be a wall in the generated maze
        assertFalse(world.isWall(World.TILE_SIZE, World.TILE_SIZE));
    }

    @Test
    void worldCoinPlacementTest() {
        World world = new World(20, 15);

        float coinCenterX = 2 * World.TILE_SIZE + World.TILE_SIZE / 2f;
        float coinCenterY = 1 * World.TILE_SIZE + World.TILE_SIZE / 2f;

        assertTrue(world.hasCoin(coinCenterX, coinCenterY));
    }

    @Test
    void worldAllCoinsCollectedFalseByDefaultTest() {
        World world = new World(20, 15);
        assertFalse(world.allCoinsCollected());
    }

    @Test
    void worldAllCoinsCollectedTrueAfterClearingTest() throws Exception {
        World world = new World(20, 15);

        Field coinsField = World.class.getDeclaredField("coins");
        coinsField.setAccessible(true);
        boolean[][] coins = (boolean[][]) coinsField.get(world);

        for (int y = 0; y < coins.length; y++) {
            for (int x = 0; x < coins[y].length; x++) {
                coins[y][x] = false;
            }
        }

        assertTrue(world.allCoinsCollected());
    }
}
