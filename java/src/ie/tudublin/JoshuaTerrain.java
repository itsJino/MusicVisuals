package ie.tudublin;

import ddf.minim.AudioBuffer;
// import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import ie.tudublin.joshuaSphere;

public class JoshuaTerrain extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    FFT fft;

    int scl = 30;
    int terrainMax = 50;
    int w = width;
    int h = height;
    int rows = 5000 / scl;
    int cols = 2000 / scl;
    float flying;

    float[][] terrain = new float[cols][rows];

    int numSpheres = 70;
    float[] sphereX = new float[numSpheres];
    float[] sphereY = new float[numSpheres];
    float[] sphereZ = new float[numSpheres];
    float[] sphereReact = new float[numSpheres];

    public void settings() {
        size(1920,1080, P3D);
    }

    public void setup() {
        colorMode(RGB);
        background(0);

        minim = new Minim(this);

        ap = minim.loadFile("bridge.mp3", 1024);
        ap.play();
        ab = ap.mix;

        fft = new FFT(1024, 44100);

        System.out.println("setup");
        System.out.println(rows);
        System.out.println(cols);

        createSpheres(numSpheres);
    }

    float lerpedBuffer[] = new float[1024];

    float smoothedAmplitude = 0;

    float halfWidth = width / 2;
    float halfHeight = height / 2;

    float moveSphere;

    public void createSpheres(int numSpheres) {
        for(int i = 0; i < numSpheres; i++)
        {
            sphereX[i] = random(-1000, width + 100);
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
                noFill();
                strokeWeight(2);
                translate(sphereX[i], sphereY[i], z);
                rotateX(frameCount * 0.01f);
                rotateY(frameCount * 0.01f);
                box(20 + smoothedAmplitude * 200);
                popMatrix();
            }
            else
            {
                sphereZ[i] = random(-2000, 1000);
            }
            
        }
    }

    public void draw() {
        background(0);
        stroke(255);
        noFill();
        float average = 0;
        float sum = 0;
        rotateY(-smoothedAmplitude / 2);
        rotateX(-smoothedAmplitude / 5);


        translate(halfWidth, halfHeight);

        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));

            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        moveSphere += 2;

        float zoff = moveSphere;

        pushMatrix();
        rotateX(frameCount * 0.01f);
        sphere(2000); 
        popMatrix();

        drawSpheres(zoff);

        // Waves
        beginShape();
        flying -= smoothedAmplitude / 2;

        float yoff = flying;

        for (int y = 0; y < cols; y++) {
            float xoff = 0;
            for (int x = 0; x < rows; x++) {
                terrain[y][x] = map(noise(xoff, yoff), 0, 1, -terrainMax - smoothedAmplitude, terrainMax + smoothedAmplitude);
                xoff += 0.4f;
            }
            yoff += 0.4f;   
        }
        
        translate(width / 6 + 200, (height / 2) + 15, -400);
        rotateX(PI / 2);
        translate(-width / 2, -height / 2);
        fill(0);
        stroke(236, 221, 14);
        strokeWeight(1);
        for (int y = 10; y < cols - 1; y++) {
            beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < rows; x++) {
                vertex(x * scl, y * scl, terrain[y][x]);
                vertex(x * scl, (y + 1) * scl, terrain[y + 1][x]);
            }
            endShape();
        }
        endShape();
        

        translate(+0, +0, +200);
        fill(0);
        stroke(236, 221, 14);
        strokeWeight(1);
        for (int y = 10; y < cols - 1; y++) {
            beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < rows; x++) {
                vertex(x * scl, y * scl, terrain[y][x]);
                vertex(x * scl, (y + 1) * scl, terrain[y + 1][x]);
            }
            endShape();
        }
        endShape();
        
        /* 
        translate(+0, +0, +130);
        beginShape();
        noFill();
        fill(0, 0, 0);
        stroke(255);
        for (int y = 10; y < cols - 1; y++) {
           beginShape(TRIANGLE_STRIP);
           for (int x = 0; x < rows; x++) {
               vertex(x * scl, y * scl, terrain[y][x]);
               vertex(x * scl, (y + 1) * scl, terrain[y + 1][x]);
           }
           endShape();
        }
        endShape();
        */
    }
}