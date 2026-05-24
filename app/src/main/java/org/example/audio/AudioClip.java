package org.example.audio;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class AudioClip {
    private static long device;
    private static long context;

    private int source;
    private int buffer;

    public static void init() {
        // Get device for default audio
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);

        // Open device
        device = alcOpenDevice(defaultDeviceName);

        if (device == 0) {
            throw new IllegalStateException("Failed to OpenAl device");
        }

        // Creates current context
        context = alcCreateContext(device, (java.nio.IntBuffer) null);
        if (context == 0) {
            throw new IllegalStateException("Failed to create OpenAL context");
        }

        alcMakeContextCurrent(context);

        AL.createCapabilities(ALC.createCapabilities(device));
    }

    /**
     * Loads a WAV file from resources.
     *
     * @param path Path to the audio file inside resources
     */
    public AudioClip(String path, float volume) throws Exception {

        // Load file from resources
        InputStream is = AudioClip.class.getResourceAsStream(path);

        if (is == null) {
            throw new RuntimeException("Sound file not found: " + path);
        }

        // Read audio stream
        AudioInputStream ais = AudioSystem.getAudioInputStream(
                new BufferedInputStream(is));

        // Original audio format
        AudioFormat baseFormat = ais.getFormat();

        // Convert audio to 16-bit PCM_SIGNED
        AudioFormat decodedFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(),
                false);

        // Converted audio stream
        AudioInputStream dais = AudioSystem.getAudioInputStream(decodedFormat, ais);

        // Read all audio bytes
        byte[] bytes = dais.readAllBytes();

        // Create ByteBuffer for OpenAL
        ByteBuffer bufferData = BufferUtils.createByteBuffer(bytes.length);

        // Copy bytes into buffer
        bufferData.put(bytes);

        // Prepare buffer for reading
        bufferData.flip();

        int format;

        // Determine mono or stereo format
        if (decodedFormat.getChannels() == 1) {
            format = AL_FORMAT_MONO16;
        } else {
            format = AL_FORMAT_STEREO16;
        }

        // =========================
        // OPENAL BUFFER
        // =========================

        // Generate OpenAL buffer
        buffer = alGenBuffers();

        // Upload audio data into buffer
        alBufferData(
                buffer,
                format,
                bufferData,
                (int) decodedFormat.getSampleRate());

        // =========================
        // OPENAL SOURCE
        // =========================

        // Generate source
        source = alGenSources();

        // Attach buffer to source
        alSourcei(source, AL_BUFFER, buffer);

        // Loop while finishing. Due to is the same song
        alSourcei(source, AL_LOOPING, AL_TRUE);

        // Volume
        alSourcef(source, AL_GAIN, volume / 100.0f);

        // Playback speed/pitch
        alSourcef(source, AL_PITCH, 1f);
    }

    public void play() {
        alSourcePlay(source);
    }

    public void stop() {
        alSourcePause(source);
    }

    public void cleanUp() {
        alDeleteSources(source);
        alDeleteBuffers(buffer);
    }

    /**
     * Shuts down OpenAL.
     * Must be called when the game closes.
     */
    public static void destroy() {
        alcDestroyContext(context);
        alcCloseDevice(device);
    }
}
