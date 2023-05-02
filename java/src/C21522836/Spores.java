package C21522836;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Iterator;

public class Spores implements VisualComponent {
    private MycoVisual parent;
    private Mushroom mushroom;
    private ArrayList<Spore> spores;
    //threshold to be exceeded for spores to drop ~0.1 seems to be enough for spores to only drop after the first drop in song
    private final float emissionThreshold = 0.13f;

    //constructor for spores class
    public Spores(MycoVisual parent, Mushroom mushroom) {
        this.parent = parent;
        this.mushroom = mushroom;
        spores = new ArrayList<>();
    }

    public void setup() {
    }

    //drawing the spores
    public void draw() {
        parent.colorMode(PApplet.RGB);

        float avgAmplitude = parent.getSmoothedAmplitude();
        //checking for amplitude exceeding threshold
        if (avgAmplitude > emissionThreshold) {
            //calculate spawn probability
            float spawnProbability = PApplet.map(avgAmplitude, emissionThreshold, 1, 0, 1);
            // adding a new spore if the random value is less than spawn probability
            if (parent.random(1) < spawnProbability) {
                spores.add(createSpore());
            }
        }

        //iterating through spores array, updating position and velocity
        Iterator<Spore> iterator = spores.iterator();
        while (iterator.hasNext()) {
            Spore spore = iterator.next();
            spore.update();
            spore.display();
            // ### WHY NO WORK
            if (spore.isOutOfScreen()) {
               iterator.remove();
            }
        }
    }

    //randomizing spore properties
    private Spore createSpore() {
        //### still spawning outside of cap width? why?
        float capWidth = mushroom.getCapWidth();
        float x = parent.width / 2 + parent.random(-capWidth / 3, capWidth / 3);
        float y = mushroom.getCapY();
        //random angle
        float angle = PApplet.radians(parent.random(-135, -45));
        float speed = parent.random(0.5f, 4);
        //creating vector
        PVector velocity = new PVector(speed * PApplet.cos(angle), 0);
        int size = 3;
        int sporeColor = parent.color(135, 200, 255);
        return new Spore(parent, x, y, velocity, size, sporeColor);
    }

    // spore object
    private class Spore {
        PApplet p;
        PVector position;
        PVector velocity;
        float size;
        int sporeColor;

        // constructor
        Spore(PApplet p, float x, float y, PVector velocity, float size, int sporeColor) {
            this.p = p;
            this.position = new PVector(x, y);
            this.velocity = velocity;
            this.size = size;
            this.sporeColor = sporeColor;

        }

        //PVector & gravity
        void update() {
            position.add(velocity);
            velocity.y += 0.05;
        }

        void display() {
            p.fill(sporeColor);
            //p.noStroke();
            p.circle(position.x, position.y, size);
        }

        //check if out of screen
        boolean isOutOfScreen() {
            return position.y > p.height + size || position.x < -size || position.x > p.width + size;
        }
    }
}