package C21352183;

import ie.tudublin.*;

public class JoshuaTerrain {
    MainVisual mv;
    public int rows = 5000;
    public int cols = 2000;
    public float[][] terrainMap = new float[cols][rows];

    public JoshuaTerrain(MainVisual mv) {
        this.mv = mv;
    }

    public void createTerrain(float yoff) {
        int terrainMax = 50;
        
        for (int y = 0; y < cols; y++) {
            float xoff = 0;
            for (int x = 0; x < rows; x++) {
                terrainMap[y][x] = MainVisual.map(mv.noise(xoff, yoff), 0, 1, -terrainMax - mv.getSmoothedAmplitude(), terrainMax + mv.getSmoothedAmplitude());
                xoff += 0.4f;
            }
            yoff += 0.4f;   
        }
    }

    public void drawTerrain() {
        float scale = 30;
        int rows = 5000;
        int cols = 2000;
        for (int y = 10; y < cols - 1; y++) {
            mv.beginShape();
            for (int x = 0; x < rows; x++) {
                mv.vertex(x * scale, y * scale, terrainMap[y][x]);
                mv.vertex(x * scale, (y + 1) * scale, terrainMap[y + 1][x]);
            }
            mv.endShape();
        }
    }

    public void render() {
        mv.translate(mv.width / 6 + 200, (mv.height / 2) + 15, -400);
        mv.rotateX(mv.PI / 2);
        mv.translate(-mv.width / 2, -mv.height / 2);
        drawTerrain();
    }

    //getters and setters
    public float[][] getTerrainMap() {
        return terrainMap;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}        