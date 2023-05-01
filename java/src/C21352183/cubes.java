package C21352183;

import ie.tudublin.*;

public class cubes {
    private float x;
    private float y;
    private float z;
    private float size;
    private float r;

    public cubes(float x, float y, float z, float size, float r)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
        this.r = r;
    }

    public void render(MainVisual mv) {
        mv.pushMatrix();
        mv.translate(x, y, z);
        mv.rotateY(r);
        mv.rotateX(r);
        mv.box(size);
        mv.popMatrix();
    }

    
}
