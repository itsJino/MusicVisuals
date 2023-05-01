package C21755919;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Scene1 extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    // Stars variables
    int numStars = 250;
    float[] starX = new float[numStars];
    float[] starY = new float[numStars];
    float[] starReact = new float[numStars];

    // Shooting stars variable
    int numShootingStars = 20;
    float[] shootingStarX = new float[numShootingStars];
    float[] shootingStarY = new float[numShootingStars];
    float[] shootingStarSpeed = new float[numShootingStars];

    int scl = 30;
    int w = width;
    int h = height;
    int rows = 3300 / scl;
    int cols = 1800 / scl;
    float flying;

    float[][] terrain = new float[cols][rows];

    /*
     * float fadeAlpha = 0;
     * float fadeSpeed = 0.5f;
     * int boxsize = 100;
     * float elapsedTime = millis();
     */

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings() {
        //size(1920, 1080, P3D);
        fullScreen(P3D);
    }

    public void setup() {
        minim = new Minim(this);

        // And comment the next two lines out
        ap = minim.loadFile("starryeyed.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        for (int i = 0; i < numStars; i++) {
            starX[i] = random(width);
            starY[i] = random(height/ 1.5f);
            // setting a ranom limit between 50 and 250
            starReact[i] = random(20, 100);
        }

        for (int i = 0; i < numShootingStars; i++) {
            shootingStarX[i] = random(width);
            shootingStarY[i] = random(height / 1.75f);
            // setting a ranom limit between 50 and 250
            shootingStarSpeed[i] = random(5, 500);
        }

    }

    float off = 0;

    float lerpedBuffer[] = new float[1024];

    public void draw() {
        // background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        float cx = width / 2;
        float cy = height / 2;

        float halfW = width / 2;
        float topOffset = height * 0.2f;

        colorMode(HSB, 255);
        background(0);

        // Drawing the stars randomly
        for (int i = 0; i < numStars; i++) {
            stroke(0);
            strokeWeight(1);
            fill(255);
            circle(starX[i], starY[i], smoothedAmplitude * starReact[i]);
        }

        // Drawing shooting stars
        float trailAlpha = 50;
        for (int i = 0; i < numShootingStars; i++) {
            noStroke();
            fill(255);
            circle(shootingStarX[i], shootingStarY[i], smoothedAmplitude * starReact[i]);

            // Drawing trail behind stars
            fill(255, trailAlpha);
            ellipse(shootingStarX[i] + shootingStarSpeed[i], shootingStarY[i],
                    smoothedAmplitude * starReact[i] * 1.25f, smoothedAmplitude * starReact[i] * 1.5f);

            ellipse(shootingStarX[i] + shootingStarSpeed[i], shootingStarY[i],
                    smoothedAmplitude * starReact[i] * 0.75f, smoothedAmplitude * starReact[i] * 0.75f);

            shootingStarX[i] -= shootingStarSpeed[i] + random(-2, 2);
            shootingStarY[i] += random(0, 1.5f);
            if (shootingStarX[i] < 4) {
                shootingStarX[i] = width;
                shootingStarY[i] = random(height / 2);
                shootingStarSpeed[i] = random(5, 30);
            }
        }

        // For Northern Lights, to fill the full width of the screen
        for (int i = 0; i < ab.size(); i++) {
            float c = map(i, 0, ab.size(), 50, 255);
            float amplitude = ab.get(i);
            float f = lerpedBuffer[i] * halfW * 1.0f;
            float x = map(i, 0, ab.size(), 0, width);

            // Map amplitude to stroke color in HSB format
            float hue = map(amplitude, -1, 1, 100, 120);
            float saturation = map(amplitude, -1, 1, 200, 255);
            float brightness = map(amplitude, -1, 1, 150, 255);

            // Draw lines with varying stroke weight and color
            if (f > 0) {
                float weight = map(amplitude, -1, 1, 0.5f, 3);
                strokeWeight(f / 50);
                stroke(hue, saturation, brightness, c);
                line(x, topOffset + f, x, topOffset - f);

                float alpha = map(amplitude, -1, 1, 20, 80);
                strokeWeight(weight * 0.5f);
                stroke(hue, saturation, brightness, alpha);
                line(x, topOffset + (f * 0.5f), x, topOffset - (f * 0.5f));
            }
        }

        // Waves
        beginShape();
        flying -= smoothedAmplitude / 2;

        float yoff = flying;

        for (int y = 0; y < cols; y++) {
            float xoff = 0;
            for (int x = 0; x < rows; x++) {
                terrain[y][x] = map(noise(xoff, yoff), 0, 1, -95, 0);
                xoff += 0.3f;
            }
            yoff += 0.3f;   
        }
        
        translate(width / 6, height / 2 +15);
        rotateX(PI / 2);
        translate(-width / 2, -height / 2);
        // noFill();
        fill(0, 170, 0);
        stroke(0, 0, 70);
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
         * if (fadeAlpha < 255)
         * {
         * fadeAlpha += fadeSpeed;
         * }
         * 
         * if (fadeAlpha > 0 && elapsedTime > 1)
         * {
         * fadeAlpha -= fadeSpeed;
         * System.out.println(elapsedTime);
         * }
         * 
         * fill(0, fadeAlpha);
         * 
         * rectMode(CENTER);
         * rect(width / 2, height / 2, width, height);
         */
    }
}
