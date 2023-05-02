package C21522836;

import processing.core.PApplet;

public class Mushroom implements VisualComponent {
    private MycoVisual parent;
    private float initialStemHeight;
    private float maxStemHeight;
    private float initialCapDiameter;
    private float maxCapDiameter;

    public Mushroom(MycoVisual parent, float initialStemHeight, float maxStemHeight, float initialCapDiameter,
            float maxCapDiameter) {
        this.parent = parent;
        this.initialStemHeight = initialStemHeight;
        this.maxStemHeight = maxStemHeight;
        this.initialCapDiameter = initialCapDiameter;
        this.maxCapDiameter = maxCapDiameter;
    }

    public void setup() {
    }

    public void draw() {
        parent.colorMode(PApplet.RGB);
        float progress = (float) parent.getAudioPlayer().position() / parent.getAudioPlayer().length();

        float mushroomX = parent.width / 2;
        float stemHeight = PApplet.lerp(initialStemHeight, maxStemHeight, progress);
        float mushroomY = parent.height;

        // mushroom cap
        final float capDiameter = PApplet.lerp(initialCapDiameter, maxCapDiameter, progress);
        float capWidth = PApplet.map(progress, 0, 1, initialCapDiameter / 4, capDiameter);
        float capHeight = PApplet.map(progress, 0, 1, capDiameter, capDiameter / 2);

        int startColor = parent.color(51, 26, 0);
        int endColor = parent.color(178, 119, 0);
        int currentColor = parent.lerpColor(startColor, endColor, progress);
        parent.fill(currentColor);

        parent.beginShape();
        parent.vertex(mushroomX - capWidth / 2, mushroomY - stemHeight);
        float controlPointOffset = capWidth / 4;
        parent.bezierVertex(mushroomX - capWidth / 2 + controlPointOffset, mushroomY - stemHeight - capHeight,
                mushroomX + capWidth / 2 - controlPointOffset, mushroomY - stemHeight - capHeight,
                mushroomX + capWidth / 2, mushroomY - stemHeight);
        parent.endShape();

        // stem
        float initialStemWidth = initialCapDiameter / 4;
        float stemWidthBase = PApplet.lerp(initialStemWidth, capDiameter / 4, progress);
        float stemWidthTop = stemWidthBase * 0.5f;

        parent.fill(204, 194, 143);
        parent.beginShape();
        parent.vertex(mushroomX - stemWidthBase / 2, mushroomY);
        parent.vertex(mushroomX + stemWidthBase / 2, mushroomY);
        parent.vertex(mushroomX + stemWidthTop / 2, mushroomY - stemHeight);
        parent.vertex(mushroomX - stemWidthTop / 2, mushroomY - stemHeight);
        parent.endShape(PApplet.CLOSE);
    }

    public float getCapWidth() {
        float progress = (float) parent.getAudioPlayer().position() / parent.getAudioPlayer().length();
        return PApplet.lerp(initialCapDiameter, maxCapDiameter, progress);
    }

    public float getCapY() {
        float progress = (float) parent.getAudioPlayer().position() / parent.getAudioPlayer().length();
        float stemHeight = PApplet.lerp(initialStemHeight, maxStemHeight, progress);
        float capHeight = PApplet.map(progress, 0, 1, initialCapDiameter, maxCapDiameter) / 2;
        return parent.height - stemHeight;
    }

}