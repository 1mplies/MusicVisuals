package C21522836;

import ie.tudublin.*;
import processing.core.PApplet;

public class Bars implements VisualComponent {
    private MycoVisual parent;

    private static final int RECT_HEIGHT = 15;
    private static final int RECT_GAP = 10;
    private static final int BAR_GAP = 20;

    public Bars(MycoVisual parent) {
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

        //calculate numBands and width
        float[] bands = parent.getSmoothedBands();
        int numBands = bands.length;
        float bandWidth = (float) (parent.width - (numBands * BAR_GAP)) / numBands;

        //iterate through bands
        for (int i = 0; i < numBands; i++) {
            //get xy, height and color
            float x = i * (bandWidth + BAR_GAP);
            float y = parent.height;
            float bandHeight = bands[i];
            int bandColor = getColor(i, numBands);
            
            //iterate through rectangles
            for (int j = 0; j < bandHeight / (RECT_HEIGHT + RECT_GAP); j++) {
                float rectY = y - (j * (RECT_HEIGHT + RECT_GAP));
                float opacity = PApplet.map(j, 0, bandHeight / (RECT_HEIGHT + RECT_GAP), 255, 0);
                int colorWithOpacity = parent.color(parent.hue(bandColor), parent.saturation(bandColor), parent.brightness(bandColor), opacity);
                parent.fill(colorWithOpacity);
                parent.rect(x, rectY, bandWidth, RECT_HEIGHT);
            }
        }
    }

    private int getColor(int index, int total) {
        float hue = PApplet.map(index, 0, total, 0, 255);
        return parent.color(hue, 220, 180);
    }
}