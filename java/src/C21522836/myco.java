package C21522836;

import ie.tudublin.*;
import processing.core.PApplet;

public class Myco extends Visual {

    // rectangle/bar dimensions, constants
    private static final int RECT_HEIGHT = 10;
    private static final int RECT_GAP = 4;

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
        int gap = 14; // Set the gap width between the bars
        float bandWidth = (float) (width - (numBands * gap)) / numBands;

        for (int i = 0; i < numBands; i++) {
            float x = i * (bandWidth + gap);
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
