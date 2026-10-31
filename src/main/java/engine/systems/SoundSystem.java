package engine.systems;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundSystem {

    private static final HashMap<String, Clip> soundEffects = new HashMap<>();

    public static void playSound(String sound){
        Clip clip = soundEffects.get(sound);

        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public static Clip getSound(String sound) {
        Clip clip = soundEffects.get(sound);
        return clip;
    }

    public static void addAudioFile(File file) {
        try {
            String name = file.getName();
            int extensionIndex = name.indexOf("."); 
            name = name.substring(0,extensionIndex);

            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            
            stream.close();

            soundEffects.putIfAbsent(name, clip);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}