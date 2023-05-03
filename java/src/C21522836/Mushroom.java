package C21522836;

import processing.core.PApplet;

public class Mushroom implements VisualComponent {
    private MycoVisual parent;
    private float initialStemHeight;
    private float maxStemHeight;
    private float initialCapDiameter;
    private float maxCapDiameter;
    private float xPosition;
    private float startY;
    private float startGrowingAt;
    

    public Mushroom(MycoVisual parent, float initialStemHeight, float maxStemHeight, float initialCapDiameter,
        float maxCapDiameter, float xPosition, float startY, float startGrowingAt) {
        this.parent = parent;
        this.initialStemHeight = initialStemHeight;
        this.maxStemHeight = maxStemHeight;
        this.initialCapDiameter = initialCapDiameter;
        this.maxCapDiameter = maxCapDiameter;
        this.xPosition = xPosition;
        this.startY = startY;
        this.startGrowingAt = startGrowingAt;
    }

    public void setup() {
    }

    public void draw() {
        parent.colorMode(PApplet.RGB);
        float progress = (float) parent.getAudioPlayer().position() / parent.getAudioPlayer().length();

        if (progress < startGrowingAt) {
            progress = 0;
        } else {
            progress = PApplet.map(progress, startGrowingAt, 1, 0, 1);
        }

        float stemHeight = PApplet.lerp(initialStemHeight, maxStemHeight, progress);

        // mushroom cap
        //diameter is no longer used for drawing cap but is used for determining other dimensions
        float capDiameter = PApplet.lerp(initialCapDiameter, maxCapDiameter, progress);
        float capWidth = PApplet.map(progress, 0, 1, initialCapDiameter / 4, capDiameter);
        float capHeight = PApplet.map(progress, 0, 1, capDiameter, capDiameter / 2);

        int startColor = parent.color(51, 26, 0);
        int endColor = parent.color(178, 119, 0);
        int currentColor = parent.lerpColor(startColor, endColor, progress);
        parent.fill(currentColor);

        //bezier curve for mushroom cap
        parent.beginShape();
        parent.vertex(xPosition - capWidth / 2, startY - stemHeight);
        float controlPointOffset = capWidth / 4;
        parent.bezierVertex(xPosition - capWidth / 2 + controlPointOffset, startY - stemHeight - capHeight,
                xPosition + capWidth / 2 - controlPointOffset, startY - stemHeight - capHeight,
                xPosition + capWidth / 2, startY - stemHeight);
        parent.endShape();

        // stem
        float initialStemWidth = initialCapDiameter / 4;
        float stemWidthBase = PApplet.lerp(initialStemWidth, capDiameter / 4, progress);
        float stemWidthTop = stemWidthBase * 0.5f;

        parent.fill(204, 194, 143);
        parent.beginShape();
        parent.vertex(xPosition - stemWidthBase / 2, startY);
        parent.vertex(xPosition + stemWidthBase / 2, startY);
        parent.vertex(xPosition + stemWidthTop / 2, startY - stemHeight);
        parent.vertex(xPosition - stemWidthTop / 2, startY - stemHeight);
        parent.endShape(PApplet.CLOSE);
    }

    public
 float getCapWidth() {
        float progress = (float) parent.getAudioPlayer().position() / parent.getAudioPlayer().length();
        return PApplet.lerp(initialCapDiameter, maxCapDiameter, progress);
    }

    public float getCapY() {
        float progress = (float) parent.getAudioPlayer().position() / parent.getAudioPlayer().length();
        float stemHeight = PApplet.lerp(initialStemHeight, maxStemHeight, progress);
        return parent.height - stemHeight;
    }

}