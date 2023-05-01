package C21522836;

import ie.tudublin.*;

public class Myco extends MycoVisual {
    private Bars bars;
    private Circle circle;

    public void settings() {
        size(1600, 900);
    }

    public void setup() {
        startMinim();
        loadAudio("NoMoneyDownLowMonthlyPayments.mp3");
        getAudioPlayer().play();
        colorMode(HSB, 255);

        bars = new Bars(this);
        bars.setup();

        circle = new Circle(this, 300, 200);
        circle.setup();
    }

    public void draw() {
        background(0);
    
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        calculateAverageAmplitude();
    
        bars.draw();
        circle.draw();
    }

    public void mousePressed() {
        float percentage = (float) mouseX / width;
        int newSongPosition = (int) (getAudioPlayer().length() * percentage);
        getAudioPlayer().cue(newSongPosition);
    }

}