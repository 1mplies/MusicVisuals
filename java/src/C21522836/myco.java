package C21522836;

import ie.tudublin.*;
import processing.core.PApplet;

public class Myco extends Visual {

    int numBars = 9;
    float barWidth;

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        startMinim();
        loadAudio("NoMoneyDownLowMonthlyPayments.mp3");
        getAudioPlayer().play();

        barWidth = width / (float) numBars;
    }

    public void draw() {
        background(0);

        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }

        calculateFrequencyBands();
        float[] bands = getSmoothedBands();

        for (int i = 0; i < numBars; i++) {
            float bandValue = bands[i];
            float barHeight = map(bandValue, 0, 100, height, height / 2);
            float x = i * barWidth;
            float h = height - barHeight;

            // Customize the appearance of the bars
            fill(255, 50 + i * 8, 50);
            stroke(255);

            // Draw the segmented rectangles
            int numRects = 5;
            float rectHeight = h / numRects;
            for (int j = 0; j < numRects; j++) {
                float y = height - (j + 1) * rectHeight;
                rect(x, y, barWidth, rectHeight * 0.8f);
            }
        }
    }
}