package C21436494;

import ie.tudublin.*;
import java.util.ArrayList;
import java.util.Random;

public class StarScene extends Visual {
    MainVisual mv;
    public ArrayList<Star> star3 = new ArrayList<Star>();

    public StarScene(MainVisual mv) {
        this.mv = mv;
    }

    public void createStars2(int num, ArrayList<Star> star3, float x, float y, float z, float px, float py, float pz) {
        Random r = new Random();
        for(int i = 0; i < num; i++) {
            x = r.nextFloat(-mv.width / 2, mv.width / 2	);
            y = r.nextFloat(-mv.height / 2, mv.height / 2);
            z = r.nextFloat(mv.width / 2);
            star3.add(new Star(1, x, y, z, px, py, pz));
        }
    }

    public void update(float volume, float x, float y, float z, float px, float py, float pz) {
        Random r = new Random();
        z = z - 2 - volume * 100;
        if(z < 1) {
            z = mv.width / 2;
            x = r.nextFloat(-mv.width / 2, mv.width / 2);
            y = r.nextFloat(-mv.height / 2, mv.height / 2);
            pz = z;
        }
    }

    void show(float x, float y, float z, float px, float py, float pz) {
        float sx = MainVisual.map(x / z, 0, 1, 0, mv.width);
        float sy = MainVisual.map(y / z, 0, 1, 0, mv.height);
        float r = MainVisual.map(z, 0, mv.width / 2, 16, 0);

        mv.ellipse(sx / 2, sy / 2, r / 2, r / 2);
        
        px = MainVisual.map(x / pz, 0, 1, 0, mv.width);
        py = MainVisual.map(y / pz, 0, 1, 0, mv.height);

        mv.stroke(255, 128);
        mv.line(sx, sy, px, py);
        px = sx;
        py = sy;
        pz = z;
    }

    public void render(ArrayList<Star> stars) {
        mv.beginShape();
        mv.fill(255);
        float volume = mv.getSmoothedAmplitude(); // Get the current amplitude
        for(Star s : stars) {
            update(volume, s.getX(), s.getY(), s.getZ(), s.getPx(), s.getPy(), s.getPz());
            show(s.getX(), s.getY(), s.getZ(), s.getPx(), s.getPy(), s.getPz());
        }
        mv.endShape();
    }
}