package C21755919;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import processing.core.*;
import ie.tudublin.*;

public class ShootingStarPlace {
    MainVisual mv;
    public ArrayList<Cstar2> star2 = new ArrayList<Cstar2>();

    public ShootingStarPlace(MainVisual mv) {
        this.mv = mv;
    }

    public void createShootingStars(int numStars, ArrayList<Cstar2> star2,float starX, float starY, float starSpeed) {
        Random r = new Random();

        for (int i = 0; i < numStars; i++) {
            float x = r.nextFloat(mv.width);
            float y = r.nextFloat(mv.height / 1.5f);
            float speed = r.nextFloat(20, 100);
            Cstar2 Cs;
            Cs = new Cstar2(1, x, y, speed);
            star2.add(Cs);
        }
    }

    public void drawShootingStars(ArrayList<Cstar2> star2) {
        Random r = new Random();
        //Drawing the stars randomly
        for (int i = 0; i < star2.size(); i++) {
            mv.stroke(0);
            mv.fill(255);
            mv.circle(star2.get(i).getShootingStarX(), star2.get(i).getShootingStarY(), mv.smoothedAmplitude * star2.get(i).getShootingStarSpeed());
            float x = star2.get(i).getShootingStarX();
            float y = star2.get(i).getShootingStarY();
            float speed = star2.get(i).getShootingStarSpeed();

            // star2
            x -= speed + r.nextFloat(-2, 2);
            y += r.nextFloat(0, 1.5f);

            if (x < 4) {
                x = mv.width;
                y = r.nextFloat(mv.height / 2);
                speed = r.nextFloat(5, 10);
            }
        }
    }

    public void render(ArrayList<Cstar2> star2) {
        drawShootingStars(star2);
    }
}
