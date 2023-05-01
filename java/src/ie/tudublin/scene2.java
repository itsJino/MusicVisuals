package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class scene2 extends PApplet{
    Star[] stars;

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    float lerpedBuffer[] = new float[1024];
    float smoothedAmplitude = 0;

    public void settings() {
        size(1920, 1080, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("java_bin_starryeyed.mp3", 1024);
        ap.play();
        ab = ap.mix;

        background(0);
        stars = new Star[800];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    public void draw() {
        background(0);
        translate(width / 2, height / 2);

        beginShape();
        fill(255);
        float volume = ap.mix.level(); // Get the current amplitude
        for (Star s : stars) {
            s.update(volume);
            s.show();
        }
        endShape();

        float average = 0;
        float sum = 0;;

        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));

            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        beginShape();
        noFill();
        for (int i = 0; i < ab.size(); i++) {
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);
            float f = lerpedBuffer[i] * 400;
            float angle = map(i, 0, ab.size(), 0, TWO_PI);
            float x = cos(angle) * (250 + f);
            float y = sin(angle) * (250 + f);
            vertex(x, y);
        }
        endShape();

        beginShape();
        noFill();
        for (int i = 0; i < ab.size(); i++) {
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);
            float f = lerpedBuffer[i] * 200;
            float angle = map(i, 0, ab.size(), 0, TWO_PI);
            float x = cos(angle) * (350 + f);
            float y = sin(angle) * (350 + f);
            vertex(x, y);
        }
        endShape();

        // draw a sphere
        // beginShape();
        // pushMatrix();
        // rotateX(frameCount * 0.01f);
        // rotateY(frameCount * 0.01f);
        // noFill();
        // sphere(100);
        // popMatrix();
        // endShape();
    }

    class Star {

        float x, y, z;
        float px, py, pz;

        Star() {
            x = random(-width / 2, width / 2);
            y = random(-height / 2, height / 2);
            z = random(width / 2);
            pz = z;
        }

        void update(float volume) {
            z = z - 2 - volume * 100;
            if (z < 1) {
                z = width / 2;
                x = random(-width / 2, width / 2);
                y = random(-height / 2, height / 2);
                pz = z;
            }
        }

        void show() {
            float sx = map(x / z, 0, 1, 0, width);
            float sy = map(y / z, 0, 1, 0, height);
            float r = map(z, 0, width / 2, 16, 0);

            ellipse(sx / 2, sy / 2, r / 2, r / 2);

            float px = map(x / pz, 0, 1, 0, width);
            float py = map(y / pz, 0, 1, 0, height);

            stroke(255, 128);

            line(sx, sy, px, py);

            px = sx;
            py = sy;
            pz = z;
        }

    }

}
