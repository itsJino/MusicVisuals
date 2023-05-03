package C21436494;

import ie.tudublin.*;

public class StarScene extends Visual{
    MainVisual mv;

    Star[] stars;


    public StarScene(MainVisual mv) {
        this.mv = mv;

        stars = new Star[800];

        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }


    public void render() {
        mv.colorMode(RGB);
        mv.background(0);
        mv.translate(mv.width / 2, mv.height / 2);

        mv.beginShape();
        mv.fill(255);
        float volume = mv.getAudioPlayer().mix.level(); // Get the current amplitude
        for (Star s : stars) {
            s.update(volume);
            s.show();
        }
        mv.endShape();

        float average = 0;
        float sum = 0;

        for(int i = 0; i < mv.getAudioPlayer().mix.size(); i++)
        {
            sum += Math.abs(mv.getAudioPlayer().mix.get(i));
            mv.lerpedBuffer[i] = MainVisual.lerp(mv.lerpedBuffer[i], mv.getAudioPlayer().mix.get(i), 0.1f);
        }

        average = sum / mv.getAudioPlayer().mix.size();
        mv.smoothedAmplitude = MainVisual.lerp(mv.smoothedAmplitude, average, 0.1f);

        mv.beginShape();
        mv.noFill();
        mv.strokeWeight(2);
        for (int i = 0; i < mv.getAudioPlayer().mix.size(); i++) {
            float c = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, 255);
            mv.stroke(c, 255, 255);
            float f = mv.lerpedBuffer[i] * 400;
            float angle = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, TWO_PI);
            float x = cos(angle) * (250 + f);
            float y = sin(angle) * (250 + f);
            mv.vertex(x, y);
        }
        mv.endShape();

        mv.beginShape();
        mv.noFill();
        for (int i = 0; i < mv.getAudioPlayer().mix.size(); i++) {
            float c = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, 255);
            mv.stroke(c, 255, 255);
            float f = mv.lerpedBuffer[i] * 200;
            float angle = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, TWO_PI);
            float x = cos(angle) * (350 + f);
            float y = sin(angle) * (350 + f);
            mv.vertex(x, y);
        }
        mv.endShape();

    }

    class Star {

        float x, y, z;
        float px, py, pz;

        Star() {
            x = random(-mv.width / 2, mv.width / 2);
            y = random(-mv.height / 2, mv.height / 2);
            z = random(mv.width / 2);
            pz = z;
        }

        void update(float volume) {
            z = z - 2 - volume * 100;
            if (z < 1) {
                z = mv.width / 2;
                x = random(-mv.width / 2, mv.width / 2);
                y = random(-mv.height / 2, mv.height / 2);
                pz = z;
            }
        }

        void show() {
            float sx = MainVisual.map(x / z, 0, 1, 0, mv.width);
            float sy = MainVisual.map(y / z, 0, 1, 0, mv.height);
            float r = MainVisual.map(z, 0, mv.width / 2, 16, 0);

            mv.ellipse(sx / 2, sy / 2, r / 2, r / 2);

            float px = MainVisual.map(x / pz, 0, 1, 0, mv.width);
            float py = MainVisual.map(y / pz, 0, 1, 0, mv.height);

            mv.stroke(255, 128);

            mv.line(sx, sy, px, py);

            px = sx;
            py = sy;
            pz = z;
        }

    }

}