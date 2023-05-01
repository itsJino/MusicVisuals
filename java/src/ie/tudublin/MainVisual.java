package ie.tudublin;

import ddf.minim.*;
import C21352183.*;
import C21436494.*;
import C21755919.*;

public class MainVisual extends Visual {

    public float[] lerpedBuffer;
    int mode = 0;

    BridgeScene bs;

    public void settings() {
        fullScreen(P3D);
    }

    public void setup() {
        colorMode(RGB);
        startMinim();
        loadAudio("starryeyed.mp3");
        getAudioPlayer().play();
        smooth();
        lerpedBuffer = new float[width];
    }

    //Switch statement to switch between scenes
    public void draw() {
        background(0);
        

        
    }
}
