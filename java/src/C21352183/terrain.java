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
}
