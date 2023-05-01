package ie.tudublin;

import ddf.minim.*;
import C21352183.*;
import C21436494.*;
import C21755919.*;

public class MainVisual extends Visual {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    public void settings() {
        size(1920, 1080, P3D);
    }

    Scene1 s1 = new Scene1();
    scene2 s2 = new scene2();
    BridgeScene bs = new BridgeScene();

    public void setup() {
        colorMode(RGB);
        minim = new Minim(this);
        ap = minim.loadFile("java_bin_starryeyed.mp3", 1024);
        ap.play();
        ab = ap.mix;
    }

    public void draw() {
        background(0);


    }
}
