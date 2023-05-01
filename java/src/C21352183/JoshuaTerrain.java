package C21352183;

import ie.tudublin.*;
import processing.core.PConstants;

public class JoshuaTerrain {
    MainVisual mv;
    int scl = 30;
    int terrainMax = 50;
    int rows = 5000 / scl;
    int cols = 2000 / scl;
    float flying;
    float moveSphere;

    float[][] terrain = new float[cols][rows];

    public JoshuaTerrain(MainVisual mv) {
        this.mv = mv;
    }

    public void render() {
        
        mv.background(0);
        mv.stroke(255);
        mv.noFill();
        mv.rotateY(-mv.getSmoothedAmplitude() / 2);
        mv.rotateX(-mv.getSmoothedAmplitude()  / 5);

        moveSphere += 2;

        float zoff = moveSphere;

        // Waves
        mv.beginShape();
        flying -= mv.getSmoothedAmplitude()  / 2;

        float yoff = flying;

        for (int y = 0; y < cols; y++) {
            float xoff = 0;
            for (int x = 0; x < rows; x++) {
                terrain[y][x] = MainVisual.map(mv.noise(xoff, yoff), 0, 1, -terrainMax - mv.getSmoothedAmplitude(), terrainMax + mv.getSmoothedAmplitude());
                xoff += 0.4f;
            }
            yoff += 0.4f;   
        }
        
        mv.translate(mv.width / 6 + 200, (mv.height / 2) + 15, -400);
        mv.rotateX(mv.PI / 2);
        mv.translate(-mv.width / 2, -mv.height / 2);
        mv.fill(0);
        mv.stroke(236, 221, 14);
        mv.strokeWeight(1);
        for (int y = 10; y < cols - 1; y++) {
            mv.beginShape();
            for (int x = 0; x < rows; x++) {
                mv.vertex(x * scl, y * scl, terrain[y][x]);
                mv.vertex(x * scl, (y + 1) * scl, terrain[y + 1][x]);
            }
            mv.endShape();
        }
        mv.endShape();
        

        mv.translate(+0, +0, +200);
        mv.fill(0);
        mv.stroke(236, 221, 14);
        mv.strokeWeight(1);
        for (int y = 10; y < cols - 1; y++) {
            mv.beginShape();
            for (int x = 0; x < rows; x++) {
                mv.vertex(x * scl, y * scl, terrain[y][x]);
                mv.vertex(x * scl, (y + 1) * scl, terrain[y + 1][x]);
            }
            mv.endShape();
        }
        mv.endShape();
    }
}        