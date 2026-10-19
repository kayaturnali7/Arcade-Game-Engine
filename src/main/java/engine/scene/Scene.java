package engine.scene;

import engine.actors.Actor;
import engine.actors.Entity;
import engine.audio.SoundSystem;

import java.awt.Color;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

public abstract class Scene {

    public String title;
    public Dimension dimension = new Dimension();
    public Color bgColor;

    protected final ArrayList<Actor> actors = new ArrayList<>();
    private final ArrayList<Actor> pendingActors = new ArrayList<>();

    protected final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<Entity> pendingEntities = new ArrayList<>();

    public Scene(){
        initialize();
        onStart();
        updateLists();
    }

    private void updateLists(){
        if(!pendingActors.isEmpty()){
            actors.addAll(pendingActors);
            pendingActors.clear();
        }

        if(!pendingEntities.isEmpty()){
            entities.addAll(pendingEntities);
            pendingActors.clear();
        }


        entities.removeIf(entity -> !entity.isActive());
        actors.removeIf(actor -> !actor.isActive());
    }

    public void update(){
        onUpdate();
        updateLists();
        for (Actor actor : actors) {
            actor.update();
        }
    }

    protected abstract void onUpdate();
    protected abstract void onStart();
    protected abstract void initialize();

    public void instantiate(Actor actor){
        pendingActors.add(actor);
        if (actor instanceof Entity){
            pendingEntities.add((Entity) actor);
        }
    }

    public void playSound(File sfx){
         try {
            SoundSystem.playSoundFile(sfx);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file not supported: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (LineUnavailableException e) {
            System.out.println("LineUnavailableException: " + e);
        }
    }

    public ArrayList<Actor> getActors(){return actors;}

    public ArrayList<Entity> getEntities(){return entities;}

    public void setDimension(int width, int height){
        dimension.setSize(width, height);
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setBgColor(Color color){
        this.bgColor = color;
    }
}