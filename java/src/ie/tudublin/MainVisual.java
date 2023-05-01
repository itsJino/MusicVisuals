package ie.tudublin;

import ddf.minim.*;
import C21352183.*;
import C21436494.*;
import C21755919.*;

public class MainVisual extends Visual {

    public float[] lerpedBuffer;
    public float[] Stars;
    public float smoothed;
    public float flying;
    int mode = 0;

    StarPlace sp = new StarPlace(this);
    ShootingStarPlace ssp = new ShootingStarPlace(this);
    StarScene ss = new StarScene(this);

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
        sp.createStars(250, sp.stars, width, height, 30);
        ssp.createShootingStars(10, ssp.star2, width, height, 30);
        ss.createStars2(250, ss.star3, width, height, 30, 0, 0, 0);
    }

    //Switch statement to switch between scenes
    public void draw() {
        background(0);
        calculateAverageAmplitude();
        smoothed = getSmoothedAmplitude();
        //sp.render(sp.stars);
        //ssp.render(ssp.star2);
        ss.render(ss.star3);
    }

    public float[] getLerpedBuffer() {
        return lerpedBuffer;
    }

    public void setLerpedBuffer(float[] lerpedBuffer) {
        this.lerpedBuffer = lerpedBuffer;
    }

}


