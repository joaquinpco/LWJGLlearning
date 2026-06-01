# PAC-MAN Game - LWJGL Java Edition

A modern Java reimplementation of the classic PAC-MAN game using **LWJGL 3** and **OpenGL**. The project is both playable and intended as a learning resource for Java game development.

## About the Game

This PAC-MAN game includes:
- **Main Menu** with Play, Settings, and Exit options
- **Settings Screen** for difficulty and volume control
- **Gameplay** with player movement, AI enemies, collectible coins, scoring, and collision detection
- **OpenGL rendering** using a 2D orthographic projection
- **Keyboard input handling** for smooth movement and menu navigation
- **Dynamic world sizing** based on the current window dimensions

## Features

- Interactive main menu and settings menu
- Player movement using arrow keys or WASD
- Multiple ghost enemies using A* pathfinding
- Dynamic world resizing to fit the current window
- Coin placement and collection on empty tiles
- Score tracked in real time and displayed during gameplay
- Pause/resume functionality with overlay screen
- Background music and sound effects via OpenAL
- Settings-based volume and difficulty control
- Game over screen with final score display

## Requirements

- **Java JDK:** 17 or newer
- **Gradle:** Included via Gradle wrapper (`./gradlew`)
- **OS:** macOS, Linux, or Windows with OpenGL support
- **Native Libraries:** Automatically downloaded by Gradle

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

If you need to specify a JDK:

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
- `ESC` - Pause/Resume game and audio

Additional notes:
- Coins add points (default +10 per coin).
- Score appears in the top-left corner while playing.

## Project Structure

```
LWJGLlearning/
├── app/src/main/java/org/example/
│   ├── App.java                        # Main entry point and game state manager
│   ├── audio/
│   │   └── AudioClip.java              # Audio playback and OpenAL handling
│   ├── game/
│   │   ├── Enemy.java                  # Enemy AI and movement
│   │   ├── Menu.java                   # Menu rendering and navigation
│   │   ├── Player.java                 # Player movement and coin collection
│   │   ├── Settings.java               # Settings menu logic
│   │   ├── World.java                  # Maze, coins, rendering, and world logic
│   │   └── Difficulty.java             # Difficulty configuration
│   ├── interfaces/
│   │   ├── InputState.java             # Input contract interface
│   │   └── implementations/
│   │       └── Input.java              # Keyboard input implementation
│   └── render/
│       ├── Font.java                   # Font rendering helper
│       └── Texture.java                # Texture loading and rendering
├── build.gradle                        # Gradle build configuration
├── gradle/                             # Gradle wrapper files
└── README.md                           # Project documentation
```

## Recent Additions

- **Window-based world sizing** so the maze fits the current window dimensions
- **Dynamic coin spawning** on available tiles
- **Audio lifecycle fixes** so music and sound effects pause/resume cleanly
- **Game over flow** with score persistence and return to menu
- **Player/enemy collision detection** and game-over handling

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Make your changes and commit them
4. Push to your fork: `git push origin feature/your-feature`
5. Open a Pull Request with a description

### Guidelines

- Keep the code style consistent
- Add comments for non-obvious logic
- Keep commits focused
- Test before submitting
- Update documentation when needed

### Contribution ideas

- Better graphics and visual polish
- More sound effects and music tracks
- Smarter enemy AI and maze behavior
- Power-ups and level progression
- Scoreboards and high-score saving
- Multiple maze layouts or levels

## Configuration

### Window settings

Modify the window creation in `App.java`:

```java
window = GLFW.glfwCreateWindow(800, 600, "PAC-MAN", 0, 0);
```

### GameState

The current game states are defined in `App.java`:

```java
enum GameState {
    MENU, PLAYING, PAUSED, SETTINGS, GAME_OVER
}
```

### Package organization

Recommended package layout for new features:
- `org.example.game` - game logic and entities
- `org.example.audio` - audio management
- `org.example.render` - rendering helpers
- `org.example.interfaces` - input contracts
- `org.example.util` - utility classes

If you add new gameplay options, document them in `Settings.java` and update this README.

## Audio

The audio system is built on **OpenAL** and currently supports WAV playback.

**Audio features:**
- Pause/resume with game state
- Volume control
- Background music and sound effects
- OpenAL initialization and cleanup at app exit

## Notes

The game now adjusts the maze size based on the window dimensions, so it should fit better on different screen sizes. If walls or tiles appear too large, change `World.TILE_SIZE` in `World.java`.

## Running Tests

Run the test suite with:

```bash
./gradlew test
```

If you want a clean build first:

```bash
./gradlew clean test
```

## Troubleshooting

**LWJGL native libraries fail to load:**
- Verify your Java version: `java -version`
- On macOS, ensure OpenGL support is available
- Delete Gradle cache and retry: `./gradlew clean build --refresh-dependencies`

**Window or display issues:**
- Make sure your GPU drivers are up to date
- Test on another machine if OpenGL support is uncertain
- Confirm the window is created successfully and GLFW reports no errors

**Performance problems:**
- Reduce the number of on-screen objects
- Check console logs for OpenGL or LWJGL errors
- Profile with Java Flight Recorder or a similar tool

## Resources

- [LWJGL Documentation](https://www.lwjgl.org/)
- [OpenGL Tutorials](https://learnopengl.com/)
- [Java Game Development](https://docs.oracle.com/en/java/)
- [Gradle Build Tool](https://gradle.org/learn-more/)

- Try running with explicit JDK: `JAVA_HOME=/path/to/jdk ./gradlew :app:run`

**Performance issues:**
- Check for GL state validation errors in the console
- Reduce the number of rendered objects
- Profile using Java Flight Recorder

## Learning Resources

- [LWJGL Documentation](https://www.lwjgl.org/)
- [OpenGL Tutorials](https://learnopengl.com/)
- [Java Game Development](https://docs.oracle.com/en/java/)
- [Gradle Build Tool](https://gradle.org/learn-more/)

- Build fails with missing LWJGL natives: delete Gradle caches and run
	`./gradlew clean build --refresh-dependencies`.
- Runtime OpenGL errors: update macOS / GPU drivers or test on another machine.

Contact / Notes

For further help, tell me the exact Gradle error or runtime stacktrace and I
can help adjust the Gradle configuration or native extraction.
