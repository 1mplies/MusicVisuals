package C21522836;


public class Myco extends MycoVisual {
    private Bars barVisualizer;

    public void settings() {
        size(1600, 900);
    }

    public void setup() {
        startMinim();
        loadAudio("NoMoneyDownLowMonthlyPayments.mp3");
        getAudioPlayer().play();
        colorMode(HSB, 255);

        barVisualizer = new Bars(this);
        barVisualizer.setup();
    }

    public void draw() {
        barVisualizer.draw();
    }

    public void mousePressed() {
        float percentage = (float) mouseX / width;
        int newSongPosition = (int) (getAudioPlayer().length() * percentage);
        getAudioPlayer().cue(newSongPosition);
    }

}