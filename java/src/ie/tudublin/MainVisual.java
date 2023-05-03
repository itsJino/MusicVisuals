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
    int scene = 0;

    CianVisualizer cv = new CianVisualizer(this);
    StarPlace sp = new StarPlace(this);
    StarScene ss = new StarScene(this);
    JoshuaTerrain jt = new JoshuaTerrain(this);
    JoshuaSunSlit jss = new JoshuaSunSlit(this);
    JoshuaSunSlit2 jss2 = new JoshuaSunSlit2(this);

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
        sp.createStars(250, sp.stars, width, height, 75);
    }

    public void keyPressed() {
        if (key == ' ') {
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else {
                getAudioPlayer().play();
            }
        }
        if (key == '1') {
            scene = 0;
        }
        if (key == '2') {
            scene = 1;
        }
        if (key == '3') {
            scene = 2;
        }
        if (key == '4') {
            scene = 3;
        }
    }

    public void draw() {
        background(0);
        calculateAverageAmplitude();
        smoothed = getSmoothedAmplitude();

        //Switch statement to switch between scenes
        switch (scene) {
            case 0:
                sp.render(sp.stars);
                cv.render();
                break;
            case 1:
                ss.render();
                break;
            case 2:
                jss2.render();
                break;
        }
    }

    public float[] getLerpedBuffer() {
        return lerpedBuffer;
    }

    public void setLerpedBuffer(float[] lerpedBuffer) {
        this.lerpedBuffer = lerpedBuffer;
    }
}


