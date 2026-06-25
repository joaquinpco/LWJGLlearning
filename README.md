# PAC-MAN Game - LWJGL Java Edition

A modern Java recreation of PAC-MAN built with **LWJGL 3**, **OpenGL**, and **OpenAL**. This project is both a playable game and a learning resource for Java game development.

## About the Game

This PAC-MAN-style game includes:
- Main Menu with Play, Settings, and Exit options
- Settings screen for Difficulty and Volume controls
- Gameplay with player movement, randomly spawned ghost enemies, collectible coins, scoring, and collision detection
- OpenGL rendering using a 2D orthographic projection
- Keyboard input handling for responsive movement and menu navigation
- Dynamic maze sizing based on current window dimensions
- Win state when all coins are collected

## Features

- Interactive menu and settings navigation
- Player movement with arrow keys or WASD
- Random enemy generation: 1-10 ghosts per game
- A* pathfinding for enemy chasing behavior
- Dynamic maze generation and coin spawning
- Real-time score display during gameplay
- Pause/resume state with overlay screen
- Background music and coin sound effects via OpenAL
- Difficulty and volume configuration
- Game over and win screens with final score display

## Changelog

- Added randomized enemy spawn count and dynamic pathfinding
- Updated game loop to handle pause, win, and game over states cleanly
- Added settings screen for difficulty and volume adjustments
- Improved terrain generation and coin placement for replayability

## Requirements

- **Java JDK:** 17 or newer
- **Gradle:** Included via Gradle wrapper (`./gradlew`)
- **OS:** macOS, Linux, or Windows with OpenGL support
- **Native Libraries:** Downloaded automatically by Gradle

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
- Reaching all coins triggers the win screen
- Colliding with an enemy triggers game over

## Project Structure

```
LWJGLlearning/
├── app/
│   ├── build.gradle                    # Gradle build configuration for the app module
│   ├── src/main/java/org/example/
│   │   ├── App.java                    # Main entry point and game state manager
│   │   ├── algorithm/
│   │   │   ├── AStarPathFinder.java    # Pathfinding implementation
│   │   │   └── PathNode.java           # Pathfinding node model
│   │   ├── audio/
│   │   │   └── AudioClip.java          # Audio playback and OpenAL handling
│   │   ├── game/
│   │   │   ├── Enemy.java              # Enemy AI, movement, and collision
│   │   │   ├── Menu.java               # Menu rendering and navigation
│   │   │   ├── Player.java             # Player movement and coin collection
│   │   │   ├── Settings.java           # Settings menu logic
│   │   │   ├── World.java              # Maze generation, coin logic, and rendering
│   │   │   └── Difficulty.java         # Difficulty configuration values
│   │   ├── interfaces/
│   │   │   ├── InputState.java         # Input contract interface
│   │   │   └── implementations/
│   │   │       └── Input.java          # Keyboard input implementation
│   │   └── render/
│   │       ├── Font.java               # Font rendering helper
│   │       └── Texture.java            # Texture loading and rendering
├── gradle/                             # Gradle wrapper files and plugin settings
├── settings.gradle                     # Multi-project Gradle settings
└── README.md                           # Project documentation
```

## Current Behavior

- The game starts in the main menu
- Selecting Play creates a maze sized to the current window
- Player and coins are placed on walkable tiles
- Ghost enemies spawn randomly and chase using A*
- Pressing `ESC` during play pauses the game and audio
- Collecting all coins switches to the win screen
- Colliding with a ghost switches to the game over screen

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Make your changes and commit them
4. Push to your fork: `git push origin feature/your-feature`
5. Open a Pull Request with a description

### Guidelines

- Keep code style consistent
- Add comments for non-obvious logic
- Keep commits focused
- Test before submitting
- Update documentation when needed

### Contribution ideas

- Improved visuals and maze design
- More enemies or smarter enemy behaviors
- Additional sound effects and music tracks
- Power-ups, level progression, or high-scores
- Multiple maze layouts or alternative game modes

## Configuration

### Window settings

Modify the window creation in `App.java`:

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

`Settings.java` exposes difficulty and volume controls. Difficulty adjusts enemy speed, and volume controls the audio playback level.

## Audio

The audio system uses **OpenAL** and supports WAV playback.

**Audio features:**
- Background music and sound effects
- Pause/resume audio with game state
- Volume control in settings
- OpenAL initialization and cleanup at exit

## Running Tests

Run the test suite with:

```bash
./gradlew test
```

For a clean build first:

```bash
./gradlew clean test
```
