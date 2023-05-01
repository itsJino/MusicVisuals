package C21352183;

import java.util.ArrayList;
import java.util.Random;

import processing.core.*;
import ie.tudublin.MainVisuals;
import ie.tudublin.Visual;
import ie.tudublin.joshuaSphere;

public class MovingSpheres extends PApplet {
    MainVisuals mv;

    public ArrayList<joshuaSphere> spheres1 = new ArrayList<joshuaSphere>();

    public MovingSpheres(MainVisuals mv) {
        this.mv = mv;
    }

    public void render(ArrayList<joshuaSphere> spheres) {
        drawSpheres(spheres);
    }

    public void createSphere(int num, ArrayList<joshuaSphere> spheres, float low, float high, float radius) {
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            float x = r.nextFloat(width);
            float y = r.nextFloat(height);
            float z = r.nextFloat(low, high);
            float rotation = r.nextFloat(0,2);
            spheres.add(new joshuaSphere(radius, x, y, z, rotation));
        }     
    }

    public void drawSpheres(ArrayList<joshuaSphere> spheres) {
        for (int i = 0; i < spheres.size(); i++) {
            joshuaSphere s = spheres.get(i);
        }
    }
}