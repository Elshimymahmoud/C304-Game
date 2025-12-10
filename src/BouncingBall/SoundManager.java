package BouncingBall;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundManager {
    private Clip backgroundClip;
    private boolean isMuted = false;

    private final String BG_MUSIC_FILE = "quran.wav";

    public SoundManager() {
        try {

            URL url = SoundManager.class.getResource(BG_MUSIC_FILE);
            if (url != null) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                backgroundClip = AudioSystem.getClip();
                backgroundClip.open(audioStream);
                System.out.println("Background music loaded successfully.");
            } else {
                System.err.println("Error: Audio file not found: " + BG_MUSIC_FILE);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading background music: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void playBackgroundMusic() {
        if (backgroundClip != null && !isMuted) {
            if (backgroundClip.isRunning()) {
                backgroundClip.stop();
            }
            backgroundClip.setFramePosition(0);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }

    public void toggleMute() {
        isMuted = !isMuted;
        if (isMuted) {
            stopBackgroundMusic();
        } else {
            playBackgroundMusic();
        }
    }

    public boolean isMuted() {
        return isMuted;
    }
}