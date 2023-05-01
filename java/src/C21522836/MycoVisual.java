package C21522836;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.FFT;
import ie.tudublin.*;

public abstract class MycoVisual extends PApplet {
    private int frameSize = 1024;
    private int sampleRate = 44100;

    private float[] bands;
    private float[] smoothedBands;

    private Minim minim;
    private AudioPlayer ap;
    private AudioBuffer ab;
    private FFT fft;

    public void startMinim() {
        minim = new Minim(this);

        fft = new FFT(frameSize, sampleRate);

        bands = new float[(int) log2(frameSize)];
        smoothedBands = new float[bands.length];
    }

    float log2(float f) {
        return log(f) / log(2.0f);
    }

    protected void calculateFFT() throws VisualException {
        fft.window(FFT.HAMMING);
        if (ab != null) {
            fft.forward(ab);
        } else {
            throw new VisualException("You must call loadAudio before calling fft");
        }
    }

    protected void calculateFrequencyBands() {
        for (int i = 0; i < bands.length; i++) {
            int start = (int) pow(2, i) - 1;
            int w = (int) pow(2, i);
            int end = start + w;
            float average = 0;
            for (int j = start; j < end; j++) {
                average += fft.getBand(j) * (j + 1);
            }
            average /= (float) w;
            bands[i] = average * 1.5f;
            smoothedBands[i] = lerp(smoothedBands[i], bands[i], 0.9f);
        }
    }

    public void loadAudio(String filename) {
        ap = minim.loadFile(filename, frameSize);
        ab = ap.mix;
    }

    public float[] getBands() {
        return bands;
    }

    public float[] getSmoothedBands() {
        return smoothedBands;
    }

    public AudioPlayer getAudioPlayer() {
        return ap;
    }
}