# LWJGLlearning

This project is a small LWJGL-based Java app used for learning and experimenting
with OpenGL rendering and input handling.

**Requirements & Launch Configuration**

- **Java JDK:** 17 or newer (LTS preferred). Ensure `JAVA_HOME` points to the JDK
	installation.
- **Gradle:** The project includes the Gradle wrapper — use `./gradlew` (no
	global Gradle install required). Tested with Gradle 8.x.
- **Native libraries:** LWJGL uses native binaries. The Gradle run should
	download appropriate natives for your OS automatically. On macOS, ensure
	the machine has the required OpenGL support (macOS 10.14+ recommended).
- **Display / GPU drivers:** Up-to-date drivers that support OpenGL 2.0+
	(or higher) are required.

Installation and running

1. Build the project and download dependencies:

```bash
./gradlew build
```

2. Run the app (uses the Gradle `run` task if configured in `app/build.gradle`):

```bash
./gradlew :app:run
```

If your environment requires a specific JDK, run using that JDK explicitly:

```bash
JAVA_HOME=/path/to/jdk ./gradlew :app:run
```

Configuration notes

- To change window size, title, or other startup parameters, edit the
	application entry (look for `App` or `main` in `app/src/main/java/org/example`).
- If you move classes between packages (for organization), update their
	`package` declarations and imports accordingly. Recommended packages:
	`org.example.game`, `org.example.input`, `org.example.render`, `org.example.util`.
- If LWJGL native libraries fail to load on macOS, try running with the JVM
	option to allow AWT/GL compatibility or verify that the correct native
	classifier for macOS is present in Gradle's resolved dependencies.

Troubleshooting

- Build fails with missing LWJGL natives: delete Gradle caches and run
	`./gradlew clean build --refresh-dependencies`.
- Runtime OpenGL errors: update macOS / GPU drivers or test on another machine.

Contact / Notes

For further help, tell me the exact Gradle error or runtime stacktrace and I
can help adjust the Gradle configuration or native extraction.
