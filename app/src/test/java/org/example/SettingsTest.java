package org.example;

import org.example.game.Settings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @Test
    void defaultSettingsTest() {
        Settings settings = new Settings();

        assertEquals("MEDIUM", settings.getDifficulty());
        assertEquals(75, settings.getVolume());
        assertEquals(17.0f, settings.getEnemySpeed());
    }

    @Test
    void selectLeftChangesDifficultyTest() {
        Settings settings = new Settings();

        settings.selectLeft(); // MEDIUM -> EASY
        assertEquals("EASY", settings.getDifficulty());
        assertEquals(7.0f, settings.getEnemySpeed());

        settings.selectLeft(); // EASY -> HARD
        assertEquals("HARD", settings.getDifficulty());
        assertEquals(60.0f, settings.getEnemySpeed());
    }

    @Test
    void selectRightChangesDifficultyTest() {
        Settings settings = new Settings();

        settings.selectRight(); // MEDIUM -> HARD
        assertEquals("HARD", settings.getDifficulty());
        assertEquals(60.0f, settings.getEnemySpeed());

        settings.selectRight(); // HARD -> EASY
        assertEquals("EASY", settings.getDifficulty());
        assertEquals(7.0f, settings.getEnemySpeed());
    }

    @Test
    void volumeAdjustTest() {
        Settings settings = new Settings();

        settings.selectDown(); // Move to Volume
        settings.selectRight();
        settings.selectRight();
        assertEquals(95, settings.getVolume());

        settings.selectLeft();
        assertEquals(85, settings.getVolume());
    }
}
