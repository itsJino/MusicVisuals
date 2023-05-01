package C21436494;

public class Star {

    int num;
    float x, y, z;
    float px, py, pz;

    //getters and setters
    public int getNum() {
        return num;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getZ() {
        return z;
    }
    public float getPx() {
        return px;
    }
    public float getPy() {
        return py;
    }
    public float getPz() {
        return pz;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y= y;
    }
    public void setZ(float z) {
        this.z = z;
    }
    public void setPx(float px) {
        this.px = px;
    }
    public void setPy(float py) {
        this.py = py;
    }
    public void setPz(float pz) {
        this.pz = pz;
    }


    //Constructor
    public Star(int num, float x, float y, float z, float px, float py, float pz) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.z = z;
        this.px = px;
        this.py = py;
        this.pz = pz;
    }

}