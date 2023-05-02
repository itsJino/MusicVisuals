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
<<<<<<< HEAD
    int mode = 0;
    int visual = 0;
=======
    int scene = 0;
>>>>>>> f2b76e82cd1a4e68fe4ce36b5fe9e56b892b65a5

    CianVisualizer cv = new CianVisualizer(this);
    StarPlace sp = new StarPlace(this);
    StarScene ss = new StarScene(this);
    JoshuaTerrain jt = new JoshuaTerrain(this);

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
<<<<<<< HEAD
        // sp.createStars(250, sp.stars, width, height, 30);
        // ssp.createShootingStars(10, ssp.star2, width, height, 30);
        // ss.createStars2(250, ss.star3, width, height, 30, 0, 0, 0);
=======
        sp.createStars(250, sp.stars, width, height, 75);
>>>>>>> f2b76e82cd1a4e68fe4ce36b5fe9e56b892b65a5
    }

    public void keyPressed() {
        if (key == ' ') {
<<<<<<< HEAD
            getAudioPlayer().cue(0);
            getAudioPlayer().play();

        }
        if (key == '1') {
            visual = 0;
=======
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
>>>>>>> f2b76e82cd1a4e68fe4ce36b5fe9e56b892b65a5
        }
    }

    public void draw() {
        background(0);
        calculateAverageAmplitude();
        smoothed = getSmoothedAmplitude();

<<<<<<< HEAD
        
        jt.render();
        

=======
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
                jt.render();
                break;
        }
>>>>>>> f2b76e82cd1a4e68fe4ce36b5fe9e56b892b65a5
    }

    public float[] getLerpedBuffer() {
        return lerpedBuffer;
    }

    public void setLerpedBuffer(float[] lerpedBuffer) {
        this.lerpedBuffer = lerpedBuffer;
    }
}


