package C21755919;

import ie.tudublin.*;
import processing.core.PConstants;

public class Scene4 extends Visual {
    MainVisual mv;
    float r = 300;
    float LAngle = 0;
    int num = 7;
    float len = 100;
    float lineThickness = 10;
    float[] LAng;
    float[] spd;
    float[] os;
    float spdIn;
    float rot = 0;

    public Scene4(MainVisual mv) {
        this.mv = mv;
        LAng = new float[num];
        spd = new float[num];
        os = new float[num];
        float LAngleIncrement = PConstants.TWO_PI / (float) num;
        spdIn = 0.05f;

        for (int i = 0; i < num; i++) {
            LAng[i] = i * LAngleIncrement;
            spd[i] = i * spdIn;
        }
    }

    public void render() {
        mv.colorMode(HSB);
        mv.pushMatrix();

        float amplitude = mv.getSmoothedAmplitude();
        float size1 = MainVisual.map(amplitude, 0, 1, 0.5f, 20.0f);
        r = 300 * size1;
        len = 100 * size1;

        mv.lights();
        mv.noFill();

        // Update LAngles of lines
        float LAngleIncrement = MainVisual.map(amplitude, 0, 1, 0, 0.05f);
        for (int i = 0; i < num; i++) {
            LAng[i] -= spdIn + LAngleIncrement;
        }

        // Draw lines
        for (int i = 0; i < num; i++) {
            float x = MainVisual.sin(LAng[i] + os[i]) * r;
            float y = MainVisual.cos(LAng[i] + os[i]) * r;
            float z = MainVisual.map(amplitude, 0, 1, -r, r);
            float thickness = MainVisual.map(amplitude, 0, 1, 8, lineThickness);

            // Increase thickness and length gradually with amplitude
            thickness *= amplitude * 12.5f;
            len *= amplitude / 1.5f;

            // Set stroke color and weight
            mv.strokeWeight(thickness);
            mv.stroke(MainVisual.map(i, 0, num, 0, 255), 255, 255);

            // Draw line
            mv.line(mv.width / 2, mv.height / 2, z, x + mv.width / 2, y + mv.height / 2, z);

        }
        mv.popMatrix();

        mv.noStroke();
        mv.fill(0);
        mv.circle(mv.width / 2, mv.height / 2, mv.smoothedAmplitude * 200);
        
        mv.noFill();
        mv.beginShape();
        mv.camera(0, 0, 200, 0, 0, 0, 1, 0, 0);
        mv.translate(0, 0, -100);

        mv.rotate(MainVisual.radians(rot));

        for (float i = 0; i < TWO_PI; i += 0.1f) {

            float colour = MainVisual.map(i, 0, TWO_PI, 0, 255);
            mv.strokeWeight(7.5f);
            mv.stroke(colour, 255, 255);

            float r = 200 * cos(4f);
            float x = r * cos(i);
            float y = r * sin(i);
            mv.point(20 * (x * mv.lerpedAverage / 1.525f), 20 * (y * mv.lerpedAverage / 1.525f));
            mv.point(21 * (x * mv.lerpedAverage / 1.525f), 21 * (y * mv.lerpedAverage / 1.525f));
            mv.point(22 * (x * mv.lerpedAverage / 1.525f), 22 * (y * mv.lerpedAverage / 1.525f));
            rot += 0.2f;
        }

        mv.endShape();
    }
    }
