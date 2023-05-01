package C21522836;

public class Circle implements VisualComponent {
    private MycoVisual parent;
    private float maxRadius;
    private float minRadius; // Add a minimum radius

    public Circle(MycoVisual parent, float maxRadius, float minRadius) { // Add a new parameter for the minimum radius
        this.parent = parent;
        this.maxRadius = maxRadius;
        this.minRadius = minRadius;
    }

    public void setup() {

    }

    public void draw() {
        float amplitude = parent.getSmoothedAmplitude() * (maxRadius - minRadius);
        float circleX = parent.width / 2;
        float circleY = parent.height / 2 - 120;
        float diameter = (amplitude + minRadius) * 2; // Add the minimum radius to the amplitude

        parent.noStroke();
        parent.fill(20, 250, 20, 255);
        parent.ellipse(circleX, circleY, diameter, diameter);
    }
}