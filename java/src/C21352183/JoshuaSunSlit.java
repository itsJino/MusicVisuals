package C21352183;

import ie.tudublin.*;

public class JoshuaSunSlit extends Visual {
    MainVisual mv;

    int rows = 5000 / 30;
    int cols = 2000 / 30;
    float[][] terrain = new float[cols][rows];

    int w = 1920;
    int ht = 1080;

    public JoshuaSunSlit(MainVisual mv) {
        this.mv = mv;

        topSlitY = ht / 2 - (sunRadius / 4);

        slits = new Rectangle[6];

        float y = topSlitY;
        float h = 1.0f;
        for (int i = 0; i < slits.length; i++) {
            slits[i] = new Rectangle(y, h);

            y += ((h / 2 + sunRadius) - topSlitY) / slits.length;
            h = MainVisual.map(y, topSlitY, h / 2 + sunRadius, 1.0f, 40.0f);
        }
    }

    float lerpedBuffer[] = new float[1024];
    float smoothedAmplitude = 0;

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

    int bgColor = color(0,0,30);
    float sunRadius = 300;
    float topSlitY;
    Rectangle[] slits;

    class Rectangle {
        float x, y, w, h;

        Rectangle(float y, float h) {
            this.x = 960 - sunRadius;
            this.w = 2 * sunRadius;
            this.y = y;
            this.h = h;
        }

        void render() {
            mv.fill(255);
            mv.rect(x, y, w, h);
        }

        void update() {
            y -= 0.5;
      
            if (y < topSlitY) {
              y = h / 2 + sunRadius;
            }
      
            h = MainVisual.map(y, topSlitY, h / 2 + sunRadius, 1.0f, 40.0f);
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

    public void createTerrain(float yoff) {
        for (int y = 0; y < cols; y++) {
            float xoff = 0;
            for (int x = 0; x < rows; x++) {
                terrain[y][x] = 0;
                xoff += 0.4f;
            }
            yoff += 0.4f;   
        }
    }

    public void drawTerrain() {
        for (int y = 10; y < cols - 1; y++) {
            mv.beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < rows; x++) {
                mv.vertex(x * 30, y * 30, terrain[y][x]);
                mv.vertex(x * 30, (y + 1) * 30, terrain[y + 1][x]);
            }
            mv.endShape();
        }
    }

    public void drawSun(float sum) {
        mv.noStroke();
        mv.fill(sunColors[0]);
        mv.ellipse(w / 2, ht / 2, 2 * sunRadius, 2 * sunRadius);

        mv.loadPixels();

        for (int i = 0; i < mv.pixels.length; i++) {

            if (mv.pixels[i] == sunColors[0]) {
                int y = i / w;

                float step = MainVisual.map(y, ht / 2 - sunRadius, ht / 2 + sunRadius, 0, 1.0f);

                mv.pixels[i] = interpolateColor(sunColors, step);
            }
        }

        mv.updatePixels();

        for (Rectangle r : slits) {
            r.update();
            r.render();
        }
    }

    float flying = 0;
    float enlarge = 0;

    public void render() {
        mv.colorMode(RGB);
        mv.background(bgColor);

        float sum = 0;

        for(int i = 0; i < mv.getAudioPlayer().mix.size(); i++)
        {
            sum += Math.abs(mv.getAudioPlayer().mix.get(i));
            mv.lerpedBuffer[i] = MainVisual.lerp(mv.lerpedBuffer[i], mv.getAudioPlayer().mix.get(i), 0.1f);
        }

        enlarge += 0.5f;

        float zoff = enlarge;
        
        mv.beginShape();
        mv.translate(0, 0, -500);
        drawSun(sum);
        mv.endShape();


        // mv.beginShape();
        // flying -= mv.smoothedAmplitude * 5;

        // float yoff = flying;

        // for (int y = 0; y < cols; y++) {
        //     float xoff = 0;
        //     for (int x = 0; x < 25; x++) {
        //         terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -30,150);
        //         xoff += 0.4f;
        //     }
        //     for (int x = 26; x < 45; x++) {
        //         terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -30,150);
        //         xoff += 0.4f;
        //     }
        //     for (int x = 45; x < 50; x++) {
        //         terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -30,80);
        //         xoff += 0.4f;
        //     }
        //     for (int x = 51; x < 56; x++) {
        //         terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -30,25);
        //         xoff += 0.4f;
        //     }
        //     for (int x = 57; x < 61; x++) {
        //         terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -30,80);
        //         xoff += 0.4f;
        //     }
        //     for (int x = 61; x < 80; x++) {
        //         terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -30,150);
        //         xoff += 0.4f;
        //     }
        //     for (int x = 80; x < rows; x++) {
        //         terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -30,150);
        //         xoff += 0.4f;
        //     }
        //     yoff += 0.4f;   
        // }

        // for (int y = 0; y < 10; y++) {
        //     float xoff = 0;
        //     for (int x = 0; x < rows; x++) {
        //         terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -30,100);
        //         xoff += 0.4f;
        //     }
        // }

        // mv.translate(w / 6 + 10, (h / 2) + 70);
        // mv.rotateX(PI / 2);
        // mv.translate(-w / 2, -h / 2);
        // mv.fill(0,0,20);
        // mv.stroke(255,20,147);
        // mv.strokeWeight(1);
        // for (int y = 10; y < cols - 1; y++) {
        //     mv.beginShape(TRIANGLE_STRIP);
        //     for (int x = 0; x < rows; x++) {
        //         mv.vertex(x * 30, y * 30, terrain[y][x]);
        //         mv.vertex(x * 30, (y + 1) * 30, terrain[y + 1][x]);
        //     }
        //     mv.endShape();
        // }
        // mv.endShape();
    }
}