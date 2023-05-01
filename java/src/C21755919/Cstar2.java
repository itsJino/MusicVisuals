package C21755919;

public class Cstar2 {
    //Variables
    private int shootingStarNum;
    private float shootingStarX;
    private float shootingStarY;
    private float shootingStarSpeed;

    //setters
    public void setShootingStarNum(int shootingStarNum) {
        this.shootingStarNum = shootingStarNum;
    }

    public void setShootingStarX(float shootingStarX) {
        this.shootingStarX = shootingStarX;
    }

    public void setShootingStarY(float shootingStarY) {
        this.shootingStarY = shootingStarY;
    }

    public void setShootingStarSpeed(float shootingStarSpeed) {
        this.shootingStarSpeed = shootingStarSpeed;
    }

    //getters
    public int getShootingStarNum() {
        return shootingStarNum;
    }

    public float getShootingStarX() {
        return shootingStarX;
    }

    public float getShootingStarY() {
        return shootingStarY;
    }

    public float getShootingStarSpeed() {
        return shootingStarSpeed;
    }

    //Constructor
    public Cstar2(int ShootingStarNum, float shootingStarX, float shootingStarY, float shootingStarSpeed) {
        this.shootingStarNum = ShootingStarNum;
        this.shootingStarX = shootingStarX;
        this.shootingStarY = shootingStarY;
        this.shootingStarSpeed = shootingStarSpeed;
    }
}
