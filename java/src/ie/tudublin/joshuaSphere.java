package ie.tudublin;

import processing.core.PApplet;

public class joshuaSphere extends Visual {
    private float radius;
    private float x;
    private float y;
    private float z;
    private float rotation;
    private float speed;

    // setter
    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y= y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    // getter
    public joshuaSphere(float radius, float x, float y, float z, float rotation) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotation = rotation;
        this.speed = 10;
    }

    // render
    public void drawSpheres(MainVisuals mv) {
        pushMatrix();
        translate(x, y, z);
        rotateX(rotation);
        rotateY(rotation);
        sphere(radius);
        popMatrix();

        if(this.z > 1000)
        {
            this.z = -1000;
        }
    }
}
