package engine.systems;

import engine.actors.Actor;
import engine.actors.Entity;

import java.awt.Color;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.stream.Stream;
import javax.sound.sampled.Clip;

import java.awt.Dimension;

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


    protected void loadAudioFiles(Path path) {
        try (Stream<Path> paths = Files.walk(path)) {
            paths.filter(Files::isRegularFile).forEach(file -> SoundSystem.addAudioFile(file.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void playSound(String sound) {
        SoundSystem.playSound(sound);
    }

    public Clip getSound(String sound) {
        return SoundSystem.getSound(sound);
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