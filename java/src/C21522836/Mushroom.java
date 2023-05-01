package C21522836;

import processing.core.PApplet;

public class Mushroom implements VisualComponent {
    private MycoVisual parent;
    private float initialStemHeight;
    private float maxStemHeight;
    private float initialCapDiameter;
    private float maxCapDiameter;

    public Mushroom(MycoVisual parent, float initialStemHeight, float maxStemHeight, float initialCapDiameter, float maxCapDiameter) {
        this.parent = parent;
        this.initialStemHeight = initialStemHeight;
        this.maxStemHeight = maxStemHeight;
        this.initialCapDiameter = initialCapDiameter;
        this.maxCapDiameter = maxCapDiameter;
    }

    public void setup() {

    }

    public void draw() {
        float progress = (float) parent.getAudioPlayer().position() / parent.getAudioPlayer().length();

        float mushroomX = parent.width / 2;
        float stemHeight = PApplet.lerp(initialStemHeight, maxStemHeight, progress);
        float mushroomY = parent.height - stemHeight;

        // Mushroom cap
        float capDiameter = PApplet.lerp(initialCapDiameter, maxCapDiameter, progress);
        parent.fill(255, 255, 255, 100);
        parent.arc(mushroomX, mushroomY, capDiameter, capDiameter, PApplet.PI, PApplet.TWO_PI);

        //stem
        float stemWidth = capDiameter / 4;
        parent.fill(40, 50, 80, 255);
        parent.rect(mushroomX - stemWidth / 2, mushroomY, stemWidth, stemHeight);
    }
}