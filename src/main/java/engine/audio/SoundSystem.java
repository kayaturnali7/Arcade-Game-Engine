package engine.audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundSystem {

    public static void playSoundFile(File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file not supported: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (LineUnavailableException e) {
            System.out.println("LineUnavailableException: " + e);
        }
    }



}