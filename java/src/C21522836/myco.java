package C21522836;

import ie.tudublin.*;

public class Myco extends MycoVisual {
    private Bars bars;
    private Mushroom mushroom;
    private ProgressBar progressBar;
    private boolean isPlaying = true;
    private Aura aura;
    private Spores sporeEmitter;

    public void settings() {
        fullScreen();
        //size(1600, 900);
    }

    public void setup() {
        startMinim();
        // loadAudio("この星で.mp3");
        loadAudio("NoMoneyDownLowMonthlyPayments.mp3");
        getAudioPlayer().play();
        colorMode(HSB, 255);

        bars = new Bars(this);
        bars.setup();

        aura = new Aura(this);
        aura.setup();

        mushroom = new Mushroom(this, 1, 600, 2, 400);
        mushroom.setup();

        getAudioPlayer().play();
        isPlaying = true;

        progressBar = new ProgressBar(this);
        progressBar.setup();

        sporeEmitter = new Spores(this, mushroom);
        sporeEmitter.setup();
    }

    public void draw() {
        background(0);

        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }

        if (isPlaying) {
            calculateFrequencyBands();
        }
        calculateAverageAmplitude();

        aura.draw();
        bars.draw();
        mushroom.draw();
        progressBar.draw();
        sporeEmitter.draw();
    }

    public void mousePressed() {
        float percentage = (float) mouseX / width;
        int newSongPosition = (int) (getAudioPlayer().length() * percentage);
        getAudioPlayer().cue(newSongPosition);
    }

    public void keyPressed() {
        if (key == ' ') {
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
                isPlaying = false;
            } else {
                getAudioPlayer().play();
                isPlaying = true;
            }
        }
    }

}