package C21755919;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import processing.core.*;
import ie.tudublin.*;

public class StarPlace extends Visual{
    MainVisual mv;
    public ArrayList<Cstar> stars = new ArrayList<Cstar>();

    public StarPlace(MainVisual mv) {
        this.mv = mv;
    }

    public void createStars(int numStars, ArrayList<Cstar> stars,float starX, float starY, float starReact) {
        Random r = new Random();

        for (int i = 0; i < numStars; i++) {
            float x = r.nextFloat(mv.width);
            float y = r.nextFloat(mv.height / 1.5f);
            float react = r.nextFloat(20, 100);
            Cstar Cs;
            Cs = new Cstar(1, x, y, react);
            stars.add(Cs);
        }
    }

    public void drawStars(ArrayList<Cstar> stars) {
        //Drawing the stars randomly
        for (int i = 0; i < stars.size(); i++) {
            mv.stroke(0);
            mv.strokeWeight(1);
            mv.fill(255);

            mv.circle(stars.get(i).getStarX(), stars.get(i).getStarY(), mv.smoothedAmplitude * stars.get(i).getStarReact());
        }
    }

    public void render(ArrayList<Cstar> stars) {
        drawStars(stars);
    }
}





