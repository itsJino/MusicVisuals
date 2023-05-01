package C21352183;

import ie.tudublin.*;

public class terrain {
    MainVisual mv;

    private int rows;
    private int cols;
    private int terrainMax;
    private int scale;

    public float[][] terrainMap = new float[cols][rows];

    public terrain

    public terrain(int rows, int cols, int terrainMax, int scale) {
        this.rows = rows;
        this.cols = cols;
        this.terrainMax = terrainMax;
        this.scale = scale;
    }

    public void createTerrain(float[][] terrain, float yoff) {
        for (int y = 0; y < cols; y++) {
            float xoff = 0;
            for (int x = 0; x < rows; x++) {
                terrain[y][x] = map(noise(xoff, yoff), 0, 1, -terrainMax - smoothedAmplitude, terrainMax + smoothedAmplitude);
                xoff += 0.4f;
            }
            yoff += 0.4f;   
        }
    }

    public void render() {
        
    }
}
