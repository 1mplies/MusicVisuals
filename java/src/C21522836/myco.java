package C21522836;

import ie.tudublin.*;

public class Myco extends Visual {

    // rectangle/bar dimensions, constants
    private static final int RECT_HEIGHT = 20;
    private static final int RECT_GAP = 10;
    private static final int BAR_GAP = 60;

    //window settings
    public void settings() {
        size(1600, 900);
    }

    //initialize minim load/play audio
    public void setup() {
        startMinim();
        loadAudio("NoMoneyDownLowMonthlyPayments.mp3");
        getAudioPlayer().play();
        colorMode(HSB, 255);
    }

    //skipping through length via a mouseclick on the screen x axis
    public void mousePressed() {
        float percentage = (float) mouseX / width;
        int newSongPosition = (int) (getAudioPlayer().length() * percentage);
        getAudioPlayer().cue(newSongPosition);
    }

    //getting color by index
    private int getColorByIndex(int index, int total) {
        float hue = map(index, 0, total, 0, 255);
        return color(hue, 220, 180);
    }

    //draw method
    public void draw() {
        background(0);

        //calculate FFT
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        calculateFrequencyBands();

        float[] bands = getSmoothedBands();
        int numBands = bands.length;
        float bandWidth = (float) (width - (numBands * BAR_GAP)) / numBands;

        for (int i = 0; i < numBands; i++) {
            float x = i * (bandWidth + BAR_GAP);
            float y = height;
            float bandHeight = bands[i];
            int bandColor = getColorByIndex(i, numBands); //get the color for the current band

            for (int j = 0; j < bandHeight / (RECT_HEIGHT + RECT_GAP); j++) {
                float rectY = y - (j * (RECT_HEIGHT + RECT_GAP));
                //mapping opacity to rectangles based on position in band
                float opacity = map(j, 0, bandHeight / (RECT_HEIGHT + RECT_GAP), 255, 0);
                int colorWithOpacity = (bandColor & 0x00FFFFFF) | ((int) opacity << 24); 
                fill(colorWithOpacity);
                rect(x, rectY, bandWidth, RECT_HEIGHT);
            }
        }
    }
}