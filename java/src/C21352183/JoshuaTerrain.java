package C21352183;

import ie.tudublin.*;

public class JoshuaTerrain extends Visual {
    MainVisual mv;
    int scl = 30;
    int terrainMax = 50;
    int rows = 5000 / scl;
    int cols = 2000 / scl;
    float flying;

    float[][] terrain = new float[cols][rows];

    int numSpheres = 100;
    float[] sphereX = new float[numSpheres];
    float[] sphereY = new float[numSpheres];
    float[] sphereZ = new float[numSpheres];
    float[] sphereReact = new float[numSpheres];

    float halfWidth = width / 2;
    float halfHeight = height / 2;

    float moveSphere;

    public JoshuaTerrain(MainVisual mv) {
        this.mv = mv;
        this.terrain = new float[cols][rows];

        for(int i = 0; i < numSpheres; i++)
        {
            sphereX[i] = random(-1000, 4000);
            sphereY[i] = random(350, 550);
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
                mv.pushMatrix();
                mv.noFill();
                mv.strokeWeight(2);
                mv.translate(sphereX[i], sphereY[i], z);
                mv.rotateX(mv.frameCount * 0.01f);
                mv.rotateY(mv.frameCount * 0.01f);
                mv.box(20 + mv.smoothedAmplitude * 200);
                mv.popMatrix();
            }
            else
            {
                sphereZ[i] = random(-2000, 1000);
            }
            
        }
    }

    public void render() {
        mv.background(0);
        mv.stroke(255);
        mv.noFill();
        mv.rotateY(-mv.smoothedAmplitude / 2);
        mv.rotateX(-mv.smoothedAmplitude / 5);

        mv.translate(halfWidth, halfHeight);

        moveSphere += 2;

        float zoff = moveSphere;

        mv.pushMatrix();
        mv.rotateX(frameCount * 0.01f);
        mv.sphere(2000); 
        mv.popMatrix();

        drawSpheres(zoff);

        // Waves
        mv.beginShape();
        flying -= mv.smoothedAmplitude / 2;

        float yoff = flying;

        for (int y = 0; y < cols; y++) {
            float xoff = 0;
            for (int x = 0; x < rows; x++) {
                terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -terrainMax - mv.smoothedAmplitude, terrainMax + mv.smoothedAmplitude);
                xoff += 0.4f;
            }
            yoff += 0.4f;   
        }
        
        mv.translate(mv.width / 6 + 200, (mv.height / 2) + 15, -400);
        mv.rotateX(PI / 2);
        mv.translate(-mv.width / 2, -mv.height / 2);
        mv.fill(0);
        mv.stroke(236, 221, 14);
        mv.strokeWeight(1);
        for (int y = 10; y < cols - 1; y++) {
            mv.beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < rows; x++) {
                mv.vertex(x * 30, y * 30, terrain[y][x]);
                mv.vertex(x * 30, (y + 1) * 30, terrain[y + 1][x]);
            }
            mv.endShape();
        }
        mv.endShape();
        
        mv.translate(+0, +0, +200);
        mv.fill(0);
        mv.stroke(236, 221, 14);
        mv.strokeWeight(1);
        for (int y = 10; y < cols - 1; y++) {
            mv.beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < rows; x++) {
                mv.vertex(x * 30, y * 30, terrain[y][x]);
                mv.vertex(x * 30, (y + 1) * 30, terrain[y + 1][x]);
            }
            mv.endShape();
        }
        mv.endShape();
    }
}        