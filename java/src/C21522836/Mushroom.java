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
        float capWidth = capDiameter;
        float capHeight = PApplet.map(progress, 0, 1, capDiameter, capDiameter / 2);
        parent.fill(40, 100, 100, 100);
        parent.beginShape();
        parent.vertex(mushroomX - capWidth / 2, mushroomY);
        float controlPointOffset = capWidth / 4;
        parent.bezierVertex(mushroomX - capWidth / 2 + controlPointOffset, mushroomY - capHeight, mushroomX + capWidth / 2 - controlPointOffset, mushroomY - capHeight, mushroomX + capWidth / 2, mushroomY);
        parent.endShape();

        //stem
        float stemWidth = capDiameter / 4;
        parent.fill(40, 50, 80, 255);
        parent.rect(mushroomX - stemWidth / 2, mushroomY, stemWidth, stemHeight);
    }
}