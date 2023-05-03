package C21352183;

import ie.tudublin.*;

public class JoshuaSunSlit2 extends Visual {
    MainVisual mv;

    public JoshuaSunSlit2(MainVisual mv) {
        this.mv = mv;

        createSlits();
    }


    // RGB colors
    int[] sunColors = {
            color(212, 202, 11),
            color(214, 198, 30),
            color(211, 170, 26),
            color(216, 157, 51),
            color(217, 124, 64),
            color(213, 104, 81),
            color(212, 51, 98),
            color(215, 29, 121),
            color(217, 11, 139),
            color(217, 0, 151)
    };

    int bgColor = color(37, 25, 53);
    float sunRadius = 300;
    float topSlitY;
    Rectangle[] slits;

    class Rectangle {
        float x, y, w, h;

        Rectangle(float y, float h) {
            this.x = mv.width / 2 - sunRadius;
            this.w = 2 * sunRadius;
            this.y = y;
            this.h = h;
        }

        void draw() {
            mv.fill(bgColor);
            mv.rect(mv.width / 2 - sunRadius, y, w, h);
        }

        void update() {
            y -= mv.smoothedAmplitude * 100;

            if (y < topSlitY) {
                y = 1080 / 2 + sunRadius;
            }

            h = MainVisual.map(y, topSlitY + 150, 1080 / 2 + sunRadius, 1.0f, 40.0f);
        }
    }

    public void createSlits() {
        topSlitY = mv.height / 2 - (sunRadius / 4);

        slits = new Rectangle[6];

        float y = topSlitY + 1000;
        float h = 1.0f;
        for (int i = 0; i < slits.length; i++) {
            slits[i] = new Rectangle(y, h);

            y += ((mv.height / 2 + sunRadius) - topSlitY) / slits.length;
            h = MainVisual.map(y, topSlitY, mv.height / 2 + sunRadius, 1.0f, 40.0f);
        }
    }

    public void render() {
        mv.colorMode(RGB);
        mv.background(bgColor);

        mv.noStroke();
        mv.fill(sunColors[0]);
        mv.ellipse(mv.width / 2, mv.height / 2, 2 * sunRadius, 2 * sunRadius);

        mv.loadPixels();

        for (int i = 0; i < mv.pixels.length; i++) {

            if (mv.pixels[i] == sunColors[0]) {
                int y = i / mv.width;

                float step = MainVisual.map(y, mv.height / 2 - sunRadius, mv.height / 2 + sunRadius, 0, 1.0f);

                mv.pixels[i] = interpolateColor(sunColors, step);
            }

        }

        mv.updatePixels();

        for (Rectangle r : slits) {
            r.update();
            r.draw();
        }
    }

    public int interpolateColor(int[] arr, float step) {
        int sz = arr.length;

        if (sz == 1 || step <= 0.0f) {
            return arr[0];
        } else if (step >= 1.0f) {
            return arr[sz - 1];
        } else {
            float f = step * (sz - 1);
            int i = (int) f;
            float p = f - i;
            return mv.lerpColor(arr[i], arr[i + 1], p);
        }
    }
}
