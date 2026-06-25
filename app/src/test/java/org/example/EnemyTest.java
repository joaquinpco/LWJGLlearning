package org.example;

import org.example.game.Enemy;
import org.example.game.Player;
import org.example.game.World;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void enemyDetectsCollisionWithNearbyPlayerTest() throws Exception {
        Enemy enemy = createTestEnemy(100f, 100f, 32f, 32f);
        World world = new World(20, 15);
        Player player = new Player(110f, 110f, world, false);

        assertTrue(enemy.checkCollisionWithPlayer(player));
    }

    @Test
    void enemyDoesNotCollideWithFarAwayPlayerTest() throws Exception {
        Enemy enemy = createTestEnemy(0f, 0f, 32f, 32f);
        World world = new World(20, 15);
        Player player = new Player(200f, 200f, world, false);

        assertFalse(enemy.checkCollisionWithPlayer(player));
    }

    private static Enemy createTestEnemy(float x, float y, float width, float height) throws Exception {
        Enemy enemy = (Enemy) allocateInstance(Enemy.class);
        setField(enemy, "x", x);
        setField(enemy, "y", y);
        setField(enemy, "width", width);
        setField(enemy, "height", height);
        return enemy;
    }

    private static Object allocateInstance(Class<?> clazz) throws Exception {
        Field unsafeField = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Object unsafe = unsafeField.get(null);
        return unsafe.getClass().getMethod("allocateInstance", Class.class).invoke(unsafe, clazz);
    }

    private static void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = Enemy.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
