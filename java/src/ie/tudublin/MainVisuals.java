package ie.tudublin;

import C21352183.*;
// import C21755919.*
// import C21436494.*
import ie.tudublin.Timer;
import ddf.minim.*;

public class MainVisuals extends Visual {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    MovingSpheres  js = new MovingSpheres(this);

    public void settings() {
        fullScreen(P3D);
    }

    public void setup() {
        colorMode(RGB);
        background(0);

        minim = new Minim(this);

        ap = minim.loadFile("bridge.mp3", 1024);
        ap.play();
        ab = ap.mix;

        js.createSphere(30, js.spheres1, -8000f, -1000f, 30f);
    }

    int seconds = 0;

    public void draw() {
        background(0);

        seconds = millis() / 1000;

        System.out.println(seconds);

        js.render(js.spheres1);
        /* 
        if(seconds < 0 && seconds < 37) {
            // cian Scene 1
            js.render(js.spheres1);

        }
        if(seconds > 37 && seconds < 62)
        {
            // red Scene 2
            background(255, 0, 0);
        }
        if(seconds > 62 && seconds < 83)
        {
            // red Scene 2
            background(0, 255, 0);
        }
        if(seconds > 83 && seconds < 109)
        {
            // red Scene 2
            background(0, 0, 255);
        }
        if(seconds > 109 && seconds < 136)
        {
            // red Scene 2
            background(0, 0, 255);
        }
        if(seconds > 136)
        {
            // red Scene 2
            background(0, 0, 255);
        }
        */
    }
}
