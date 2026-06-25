# PAC-MAN Game - LWJGL Java Edition

A Java recreation of PAC-MAN built with **LWJGL 3**, **OpenGL**, and **OpenAL**. This project pairs a playable 2D game with a learning-focused codebase for Java game development.

## About the Game

This PAC-MAN-style game includes:
- Main menu with Play, Settings, and Exit
- Difficulty and volume controls in Settings
- Player movement and smooth keyboard input handling
- Random ghost enemies that chase the player using A* pathfinding
- Dynamic maze generation and coin placement on walkable tiles
- Background music, coin sound effects, pause/resume, win, and game over screens

## Features

- Menu navigation with arrow keys or WASD
- Player movement with arrow keys or WASD
- Random enemy generation: 1-10 ghosts per game
- A* pathfinding for enemy pursuit
- Dynamic maze and coin layout based on window size
- Score tracking and win condition when all coins are collected
- Pause/resume gameplay with audio handling
- Difficulty and audio volume configuration

## Requirements

- **Java JDK:** 17 or newer
- **Gradle:** Included via Gradle wrapper (`./gradlew`)
- **OS:** macOS, Linux, or Windows with OpenGL support
- **Native Libraries:** Configured in `app/build.gradle`; default is `natives-macos-arm64`

## Installation

Clone the repository:

```bash
git clone <repository-url>
cd LWJGLlearning
```

Build the project:

```bash
./gradlew build
```

## Running the Game

```bash
./gradlew :app:run
```

If you need to use a specific JDK:

```bash
JAVA_HOME=/path/to/jdk ./gradlew :app:run
```

## Controls

**Menu & Settings:**
- `↑` or `W` - Move selection up
- `↓` or `S` - Move selection down
- `←` or `A` - Decrease setting value
- `→` or `D` - Increase setting value
- `ENTER` - Confirm selection

**Gameplay:**
- `↑` or `W` - Move up
- `↓` or `S` - Move down
- `←` or `A` - Move left
- `→` or `D` - Move right
- `ESC` - Pause/Resume game

Additional notes:
- Coins give +10 points when collected
- Collect all coins to win
- Colliding with a ghost triggers game over

## Project Structure

```
LWJGLlearning/
├── app/
│   ├── build.gradle                    # Gradle build configuration for the app module
│   ├── src/main/java/org/example/
│   │   ├── App.java                    # Main entry point and game loop manager
│   │   ├── algorithm/
│   │   │   ├── AStarPathFinder.java    # A* pathfinding implementation
│   │   │   ├── Heuristic.java          # Heuristic utilities for A*
│   │   │   └── PathNode.java           # Path node model
│   │   ├── audio/
│   │   │   └── AudioClip.java          # OpenAL audio playback and cleanup
│   │   ├── game/
│   │   │   ├── Enemy.java              # Enemy AI, movement, and collision
│   │   │   ├── Menu.java               # Menu rendering and selection logic
│   │   │   ├── Player.java             # Player movement and collision handling
│   │   │   ├── Settings.java           # Settings menu and difficulty/volume state
│   │   │   ├── World.java              # Maze layout, coin placement, and rendering
│   │   │   ├── Difficulty.java         # Difficulty settings and enemy speed
│   │   │   └── Constants.java          # Game constants such as tile size
│   │   ├── interfaces/
│   │   │   ├── AStar.java              # A* algorithm interface
│   │   │   ├── InputState.java         # Input contract interface
│   │   │   └── implementations/
│   │   │       └── Input.java          # Keyboard input implementation
│   │   └── render/
│   │       ├── Font.java               # Text rendering helper
│   │       └── Texture.java            # Texture loading and rendering
├── gradle/                             # Version catalog and wrapper files
├── settings.gradle                     # Gradle project settings
└── README.md                           # Project documentation
```

## Current Behavior

- Starts in a main menu with Play, Settings, and Exit
- Plays on a dynamically sized maze based on the window
- Places player and coins on walkable tiles
- Spawns a random number of ghosts for each game session
- Enemies chase the player using A* pathfinding
- `ESC` pauses the game and audio
- Winning occurs after all coins are collected
- Game over occurs on ghost collision

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Implement your changes and commit them
4. Push to your fork: `git push origin feature/your-feature`
5. Open a Pull Request with a description

### Guidelines

- Keep code style consistent
- Comment non-obvious logic
- Keep commits focused
- Test before submitting
- Update documentation when needed

### Ideas

- Improve visuals and maze design
- Add more enemy behaviors or smarter AI
- Add power-ups, level progression, or high score tracking
- Add new sound effects and music
- Create alternate maze layouts or game modes

## Configuration

### Window settings

Modify window creation in `App.java`:

```java
window = GLFW.glfwCreateWindow(800, 600, "PAC-MAN", 0, 0);
```

### Game states

The current game states are defined in `App.java`:

```java
enum GameState {
    MENU, PLAYING, PAUSED, SETTINGS, GAME_OVER, WIN
}
```

### Difficulty and volume

`Settings.java` exposes difficulty and volume controls. Difficulty adjusts enemy speed, and volume adjusts audio playback.

## Audio

The audio system uses **OpenAL** and supports WAV playback.

**Audio features:**
- Background music via `pacman.wav`
- Coin sound effect via `collected_coin.wav`
- Pause/resume audio with game state
- Volume control through Settings

## Running Tests

Run the suite with:

```bash
./gradlew test
```

For a clean build first:

```bash
./gradlew clean test
```
