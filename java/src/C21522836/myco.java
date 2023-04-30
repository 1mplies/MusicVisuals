package C21522836;

import ie.tudublin.*;

public class Myco extends Visual {

    // rectangle/bar dimensions, constants
    private static final int RECT_HEIGHT = 12;
    private static final int RECT_GAP = 8;
    private static final int BAR_GAP = 14;

    //window settings
    public void settings() {
        size(1600, 900);
    }

    //initialize minim load/play audio
    public void setup() {
        startMinim();
        loadAudio("NoMoneyDownLowMonthlyPayments.mp3");
        getAudioPlayer().play();
    }

    //skipping through length via a mouseclick on the screen x axis
    public void mousePressed() {
        float percentage = (float) mouseX / width;
        int newSongPosition = (int) (getAudioPlayer().length() * percentage);
        getAudioPlayer().cue(newSongPosition);
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
        //calculate frequency bands
        calculateFrequencyBands();
        //calculateAverageAmplitude();

        float[] bands = getSmoothedBands();
        int numBands = bands.length;
        //int gap = 12; // Set the gap width between the bars
        float bandWidth = (float) (width - (numBands * BAR_GAP)) / numBands;

        for (int i = 0; i < numBands; i++) {
            float x = i * (bandWidth + BAR_GAP);
            float y = height;
            float bandHeight = bands[i];

            for (int j = 0; j < bandHeight / (RECT_HEIGHT + RECT_GAP); j++) {
                float rectY = y - (j * (RECT_HEIGHT + RECT_GAP));
                fill(255);
                rect(x, rectY, bandWidth, RECT_HEIGHT);
            }
        }
    }
}
