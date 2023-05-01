package ie.tudublin;

import ddf.minim.AudioBuffer;
// import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import ie.tudublin.joshuaSphere;

public class Scene3 extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    FFT fft;

    int scl = 30;
    int terrainMax = 80;
    int w = width;
    int h = height;
    int rows = 3300 / scl;
    int cols = 1800 / scl;
    float flying;

    int numSpheres = 70;
    float[] sphereX = new float[numSpheres];
    float[] sphereY = new float[numSpheres];
    float[] sphereZ = new float[numSpheres];
    float[] sphereReact = new float[numSpheres];

    float m = 0;
    float c = 8;

    public void settings() {
        size(1920,1080, P3D);
    }

    public void setup() {
        colorMode(RGB);
        background(0);

        minim = new Minim(this);

        ap = minim.loadFile("verse2.mp3", 1024);
        ap.play();
        ab = ap.mix;

        fft = new FFT(1024, 44100);

        for (int i = 0; i < 10; i++) {
            pushMatrix();
            translate(random(-width, width), random(-height, height), random(-500, 500));
            rotateY(frameCount * 0.01f);
            rotateX(frameCount * 0.01f);
            fill(190, 150, 0);
            sphere(100 + (smoothedAmplitude * 50));
            popMatrix();
        }

        c = 8;
        m = 0;
        
    }

    public void createSpheres(int numSpheres) {
        for(int i = 0; i < numSpheres; i++)
        {
            sphereX[i] = random(100, width - 100);
            sphereY[i] = random(400, 500);
            sphereZ[i] = random(-5000, 1000);
            sphereReact[i] = random(0.1f, 0.5f);   
        }
    }

    public void drawSpheres(float zoff) {
        for(int i = 0; i < numSpheres; i++)
        {
            float z = sphereZ[i] + zoff;
            if(z < 1000)
            {
                pushMatrix();
                fill(255);
                translate(sphereX[i], sphereY[i], z);
                rotateX(frameCount * 0.01f);
                rotateY(frameCount * 0.01f);
                box(20 + smoothedAmplitude * 200);
                popMatrix();
            }
        }
    }

    public void star2() {
        c = 8;
        double angle = m * 137.5;
        double radius = c * sqrt(m);
        double x = radius * cos((float)angle) + width/2;
        double y = radius * sin((float)angle) + height/2;
        fill(200);
        ellipse((float)x,(float)y,9,6);
        m++;
    }

    float lerpedBuffer[] = new float[1024];

    float smoothedAmplitude = 0;

    float halfWidth = width / 2;
    float halfHeight = height / 2;

    public void draw() {
        background(0);
        stroke(255);
        noFill();
        float average = 0;
        float sum = 0;

        translate(halfWidth, halfHeight);

        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));

            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        pushMatrix();
        translate(width / 2, height /2 - 60, +500);
        rotateY(frameCount * 0.01f);
        rotateX(frameCount * 0.01f);
        fill(190, 150, 0);
        sphere(1000 + (smoothedAmplitude * 50));
        popMatrix();

        double angle = m * 137.5;
        double radius = c * sqrt(m);
        double x = radius * cos((float)angle) + width/2 ;
        double y = radius * sin((float)angle) + height/2 ;

        fill(255,255,0);
        ellipse((float)x,(float)y, 64, 64);

        m++;
    }
}