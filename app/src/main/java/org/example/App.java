package org.example;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.example.audio.AudioClip;
import org.example.game.Enemy;
import org.example.game.Menu;
import org.example.game.Player;
import org.example.game.Settings;
import org.example.game.World;
import org.example.interfaces.implementations.Input;
import org.example.render.Font;

public class App {

    static Player player;
    static List<Enemy> enemies;
    static World world;

    static Input input;

    static Menu menu;
    public static Settings settings;

    static AudioClip audioClip;

    static long window;

    static int windowWidth;
    static int windowHeight;

    enum GameState {
        MENU, PLAYING, PAUSED, SETTINGS, GAME_OVER
    }

    static GameState currentState = GameState.MENU;
    static boolean isAudioPaused = false;

    public static int score = 0;

    public static void main(String[] args) {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("GLFW init failed");
        }

        window = GLFW.glfwCreateWindow(800, 600, "PAC-MAN", 0, 0);

        GLFW.glfwMakeContextCurrent(window);

        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);

        menu = new Menu();
        settings = new Settings();
        input = new Input(window);

        int[] windowW = new int[1];
        int[] windowH = new int[1];
        int[] fbW = new int[1];
        int[] fbH = new int[1];

        GLFW.glfwGetWindowSize(window, windowW, windowH);
        GLFW.glfwGetFramebufferSize(window, fbW, fbH);

        windowWidth = windowW[0];
        windowHeight = windowH[0];

        glViewport(0, 0, fbW[0], fbH[0]);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, windowWidth, windowHeight, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glDisable(GL_DEPTH_TEST);

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Black background
        double lastTime = GLFW.glfwGetTime();

        while (!GLFW.glfwWindowShouldClose(window)) {
            double now = GLFW.glfwGetTime();
            double delta = now - lastTime;
            lastTime = now;

            glClear(GL_COLOR_BUFFER_BIT);

            switch (currentState) {
                case MENU:
                    handleMenuInput();
                    menu.render();
                    break;

                case PLAYING:
                    if (isAudioPaused && audioClip != null) {
                        audioClip.play();
                        isAudioPaused = false;
                    }
                    updateGame(delta);
                    renderGame();
                    if (audioClip == null) {
                        startAudioWithConfig(settings);
                    }
                    break;
                case SETTINGS:
                    handleSettingsInput();
                    settings.render();
                    break;
                case PAUSED:
                    handlePauseInput();
                    renderPauseScreen();
                    if (audioClip != null && !isAudioPaused) {
                        audioClip.stop();
                        isAudioPaused = true;
                    }
                    break;
                case GAME_OVER:
                    handleGameOverInput();
                    renderGameOverScreen();
                    break;
            }

            org.lwjgl.glfw.GLFW.glfwSwapBuffers(window);
            org.lwjgl.glfw.GLFW.glfwPollEvents();
        }

        AudioClip.destroy();
        GLFW.glfwTerminate();
    }

    static void handleSettingsInput() {
        if (input.isUpPressed()) {
            settings.selectUp();
        }
        if (input.isDownPressed()) {
            settings.selectDown();
        }
        if (input.isLeftPressed()) {
            settings.selectLeft();
        }
        if (input.isRightPressed()) {
            settings.selectRight();
        }
        if (input.isEnterPressed()) {
            String selected = settings.getSelectedOption();
            if ("Back".equals(selected)) {
                currentState = GameState.MENU;
            }
        }
    }

    static void handleMenuInput() {
        if (input.isUpPressed()) {
            menu.selectUp();
        }
        if (input.isDownPressed()) {
            menu.selectDown();
        }
        if (input.isEnterPressed()) {
            String selected = menu.getSelectedOption();

            switch (selected) {
                case "Play":
                    startGame();
                    currentState = GameState.PLAYING;
                    break;

                case "Settings":
                    currentState = GameState.SETTINGS;
                    break;
                case "Exit":
                    GLFW.glfwSetWindowShouldClose(window, true);
                    break;
            }
        }
    }

    static void startGame() {
        // Initialize game if not already done
        if (player == null) {
            int[] width = new int[1];
            int[] height = new int[1];

            float centerOffset = (World.TILE_SIZE - 32) / 2f;

            int cols = Math.max(10, windowWidth / World.TILE_SIZE);
            int rows = Math.max(8, windowHeight / World.TILE_SIZE);

            GLFW.glfwGetFramebufferSize(window, width, height);

            world = new World(cols, rows);

            player = new Player(
                    World.TILE_SIZE + centerOffset,
                    World.TILE_SIZE + centerOffset,
                    world);

            enemies = new ArrayList<>();

            generateEnemies(cols, rows);
        }
    }

    static void generateEnemies(int cols, int rows) {
        Random randomEnemies = new Random();
        int numberOfEnemies = randomEnemies.nextInt(10) + 1;

        float centerOffset = (World.TILE_SIZE - 32) / 2f;

        boolean[][] walkable = world.getWalkableGrid();

        for (int y = 1; y <= numberOfEnemies; y++) {
            int tileX, tileY;
            do {
                tileX = 1 * randomEnemies.nextInt(cols - 2);
                tileY = 1 * randomEnemies.nextInt(rows - 2);
            } while (!walkable[tileY][tileX]);

            enemies.add(
                    new Enemy(
                            tileX * World.TILE_SIZE + centerOffset,
                            tileY * World.TILE_SIZE + centerOffset,
                            "ghost" + ((y % 3) + 1) + ".png", world, settings));
        }
    }

    static void updateGame(double delta) {
        if (input.isEscapePressed()) {
            currentState = GameState.PAUSED;
        }

        player.update(delta, input);
        for (Enemy enemy : enemies)
            enemy.update(delta, player);
    }

    static void renderGame() {
        glClear(GL_COLOR_BUFFER_BIT);
        world.render();
        world.updateAudio();
        player.render();

        glDisable(GL_TEXTURE_2D);
        glColor3f(1.0f, 1.0f, 1.0f);
        Font.renderText(10, 20, "Score: " + score);
        glEnable(GL_TEXTURE_2D);

        for (Enemy enemy : enemies) {
            enemy.render();
            if (enemy.checkCollisionWithPlayer(player)) {
                currentState = GameState.GAME_OVER;
            }
        }
    }

    static void handlePauseInput() {
        if (input.isEscapePressed()) {
            currentState = GameState.PLAYING;
        }
    }

    static void renderPauseScreen() {
        glClear(GL_COLOR_BUFFER_BIT);
        world.render();
        player.render();
        for (Enemy enemy : enemies)
            enemy.render();

        // Draw semi-transparent overlay and "PAUSED" text
        renderPauseOverlay();
    }

    static void startAudioWithConfig(Settings settings) {
        try {
            Boolean isBackgroundSong = true;
            AudioClip.init();
            audioClip = new AudioClip("/audio/pacman.wav", (float) (settings.getVolume() - settings.getVolume() * 0.5),
                    isBackgroundSong);
            audioClip.play();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    static void handleGameOverInput() {
        if (input.isEnterPressed()) {
            score = 0;
            player = null;
            currentState = GameState.MENU;
        }
    }

    static void renderPauseOverlay() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Draw a dark overlay
        glColor4f(0, 0, 0, 0.5f);
        glBegin(GL_QUADS);
        glVertex2f(0, 0);
        glVertex2f(windowWidth, 0);
        glVertex2f(windowWidth, windowHeight);
        glVertex2f(0, windowHeight);
        glEnd();

        glDisable(GL_BLEND);
        glColor4f(1, 1, 1, 1); // Reset color to white

        // Draw text
        glDisable(GL_TEXTURE_2D);
        Font.renderText(300, 250, "PAUSED");
        Font.renderText(250, 350, "Press ESC to Resume");
        glEnable(GL_TEXTURE_2D);
    }

    static void renderGameOverScreen() {
        glClear(GL_COLOR_BUFFER_BIT);

        if (world != null)
            world.render();

        if (player != null)
            player.render();

        if (enemies != null)
            for (Enemy enemy : enemies)
                enemy.render();

        if (audioClip != null) {
            audioClip.cleanUp();
            audioClip = null;
        }

        // Draw overlay and game over message
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Draw a dark overlay
        glColor4f(0, 0, 0, 0.7f);
        glBegin(GL_QUADS);
        glVertex2f(0, 0);
        glVertex2f(windowWidth, 0);
        glVertex2f(windowWidth, windowHeight);
        glVertex2f(0, windowHeight);
        glEnd();

        glDisable(GL_BLEND);
        glColor4f(1, 1, 1, 1);

        // Draw text
        glDisable(GL_TEXTURE_2D);
        Font.renderText(280, 200, "GAME OVER");
        Font.renderText(250, 300, "Final Score: " + score);
        Font.renderText(200, 400, "Press ENTER to return to Menu");
        glEnable(GL_TEXTURE_2D);
    }

}
