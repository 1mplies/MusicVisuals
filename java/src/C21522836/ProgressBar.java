package C21522836;

public class ProgressBar implements VisualComponent {
    private MycoVisual parent;
    private float progress = 0;

    public ProgressBar(MycoVisual parent) {
        this.parent = parent;
    }

    public void setup() {
    }

    public void draw() {

        progress = (float) parent.getAudioPlayer().position() / parent.getAudioPlayer().length();

        parent.noStroke();
        parent.fill(90);
        parent.rect(0, parent.height - 4, parent.width * progress, 5);
    }
}
