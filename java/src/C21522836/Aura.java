package C21522836;

import ie.tudublin.*;
import processing.core.PApplet;

public class Aura implements VisualComponent {
    private MycoVisual parent;

    private static final int BAR_GAP = 20;

    public Aura(MycoVisual parent) {
        this.parent = parent;
    }

    public void setup() {

    }

    public void draw() {
        parent.colorMode(PApplet.HSB);

        try {
            parent.calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        if (parent.isPlaying()) {
            parent.calculateFrequencyBands();
        }

        float[] bands = parent.getSmoothedBands();
        int numBands = bands.length;
        float bandWidth = (float) (parent.width - (numBands * BAR_GAP)) / numBands;

        //iterate each frequency band
        for (int i = 0; i < numBands; i++) {
            float x = i * (bandWidth + BAR_GAP); //x of bar
            float y = parent.height; //y to bottom
            float bandHeight = bands[i];
            int bandColor = getColor(i, numBands);

            parent.noStroke();

            int numEllipses = 4;
            //iterate each ellipse
            for (int j = 0; j < numEllipses; j++) {
                float sizeMultiplier = (j + 1f); //current ellipse multiplier
                float ellipseWidth = bandWidth * sizeMultiplier;
                float ellipseHeight = bandHeight * sizeMultiplier;
                //map opacity based on index
                float opacity = PApplet.map(j, 0, numEllipses, 64, 0);
                int colorWithOpacity = parent.color(parent.hue(bandColor), parent.saturation(bandColor), parent.brightness(bandColor), opacity);
                parent.fill(colorWithOpacity);
                parent.ellipse(x + bandWidth / 2, y, ellipseWidth, ellipseHeight);
            }
        }
    }

    private int getColor(int index, int total) {
        float hue = PApplet.map(index, 0, total, 0, 255);
        return parent.color(hue, 255, 55);
    }
}
