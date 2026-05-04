# 👾 PAC-MAN Game - LWJGL Java Edition

A modern reimplementation of the classic PAC-MAN game built with **LWJGL 3.3** (Lightweight Java Game Library) and **OpenGL**. This project serves as both a fully playable game and an educational resource for learning game development with Java.

## 🎮 About the Game

This is a PAC-MAN game featuring:
- **Main Menu** with Play, Settings, and Exit options
- **Settings Screen** to adjust difficulty (Easy/Medium/Hard) and volume
- **Gameplay** with player-controlled character, AI enemies (ghosts), and collision detection
- **Real-time rendering** using OpenGL with a 2D orthographic projection
- **Input handling** for smooth keyboard-based movement

## Features

- Interactive main menu with navigation
- Settings screen for difficulty and volume control
- Player movement (WASD or Arrow Keys)
- Multiple enemy AI characters
- Texture-based rendering
- Real-time delta time-based updates
- Organized game state management (Menu, Settings, Playing, Paused)

## Requirements

- **Java JDK:** 17 or newer (LTS preferred)
- **Gradle:** Included via Gradle wrapper (`./gradlew`)
- **OS:** macOS, Linux, or Windows with OpenGL 2.0+ support
- **Native Libraries:** Automatically downloaded by Gradle

## Installation

Clone the repository:

```bash
git clone <repository-url>
cd lwjgl-project
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

## 🎮 Controls

**Main Menu & Settings:**
- `↑ UP Arrow` or `W` - Move selection up
- `↓ DOWN Arrow` or `S` - Move selection down
- `← LEFT Arrow` or `A` - Decrease setting value
- `→ RIGHT Arrow` or `D` - Increase setting value
- `ENTER` - Confirm selection

**Gameplay:**
- `↑ UP Arrow` or `W` - Move up
- `↓ DOWN Arrow` or `S` - Move down
- `← LEFT Arrow` or `A` - Move left
- `→ RIGHT Arrow` or `D` - Move right

## 📁 Project Structure

```
lwjgl-project/
├── app/src/main/java/org/example/
│   ├── App.java                           # Main entry point
│   ├── game/
│   │   ├── Menu.java                      # Main menu screen
│   │   ├── Settings.java                  # Settings screen
│   │   ├── Player.java                    # Player character
│   │   ├── Enemy.java                     # Enemy AI
│   │   ├── World.java                     # Game world
│   │   └── Constants.java                 # Game constants
│   ├── interfaces/
│   │   ├── InputState.java                # Input interface
│   │   └── implementations/
│   │       ├── Input.java                 # Input handler
│   │       └── InputState.java
│   └── render/
│       ├── Font.java                      # Font rendering
│       └── Texture.java                   # Texture handling
├── build.gradle                           # Gradle configuration
└── README.md                              # This file
```

## Contributing

We welcome contributions! Here's how to help:

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/your-feature`
3. **Make** your changes and commit: `git commit -m "Add your feature"`
4. **Push** to your fork: `git push origin feature/your-feature`
5. **Submit** a Pull Request with a clear description

### Contribution Guidelines

- Follow the existing code style and package structure
- Add comments for complex logic
- Keep commits focused and descriptive
- Test your changes before submitting a PR
- Update documentation if needed

### Ideas for Contribution

- Improve graphics and visual effects
- Add sound effects and background music
- Enhance enemy AI behavior
- Add power-ups and special abilities
- Implement score tracking and high scores
- Add level design system
- Bug fixes and performance improvements

## Configuration

### Changing Window Settings

Edit `App.java` to modify window properties:

```java
window = GLFW.glfwCreateWindow(800, 600, "PAC-MAN", 0, 0);
```

### Adding New Game States

Extend the `GameState` enum in `App.java`:

```java
enum GameState {
    MENU, PLAYING, PAUSED, SETTINGS, GAME_OVER
}
```

### Package Organization

Recommended package structure for new features:
- `org.example.game` - Game logic and entities
- `org.example.render` - Rendering and graphics
- `org.example.interfaces` - Input and event handling
- `org.example.util` - Utility functions and constants

## Troubleshooting

**LWJGL native libraries fail to load:**
- Verify your Java version: `java -version`
- On macOS, ensure OpenGL support is available
- Check that Gradle correctly downloaded natives: `ls ~/.gradle/caches/`

**Window doesn't appear:**
- Ensure your GPU driver is up-to-date
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
