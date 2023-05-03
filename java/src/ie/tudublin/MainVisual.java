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

    public float lerpedAverage = 0;
    float average;
    float sum;

    CianVisualizer cv = new CianVisualizer(this);
    StarPlace sp = new StarPlace(this);
    StarScene ss = new StarScene(this);
    JoshuaTerrain jt = new JoshuaTerrain(this);
    JoshuaSunSlit jss = new JoshuaSunSlit(this);
    Scene4 s4 = new Scene4(this);

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
        if (key == '5') {
            scene = 4;
        }
    }

    // Switch statement to switch between scenes
    public void draw() {
        background(0);
        calculateAverageAmplitude();
        smoothed = getSmoothedAmplitude();

        average = 0;
        sum = 0;
        // Calculate the average of the buffer
        for (int i = 0; i < getAudioBuffer().size(); i++) {
            sum += abs(getAudioBuffer().get(i));
        }
        average = sum / getAudioBuffer().size();
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);

        // 0:00 (Cian Star = 1)
        // 0:36 (Ian Stars = 2)
        // 1:02 (Colour Wheel = 4)
        // 1:30 (Ian Stars = 2)
        // 1:49 (JoshuaTerrain = 3)
        // 2:16 (JoshuaSunSlit = 5)
        // 2:44 (Cian Stars = 1)

        // Switch statement to switch between scenes
        switch (scene) {
            case 0:
                ResetCamera();
                sp.render(sp.stars);
                cv.render();
                break;
            case 1:
                ResetCamera();
                ss.render();
                break;
            case 2:
                ResetCamera();
                jt.render();
                break;
            case 3:
                ResetCamera();
                s4.render();
                translate(0, 0, 0);
                break;
            case 4:
                ResetCamera();
                jss.render();
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
