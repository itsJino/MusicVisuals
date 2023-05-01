package C21755919;

public class Cstar {
    //Variables
    private int numStars;
    private float starX;
    private float starY;
    private float starReact;

    //setters
    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }

    public void setStarX(float starX) {
        this.starX = starX;
    }

    public void setStarY(float starY) {
        this.starY = starY;
    }

    public void setStarReact(float starReact) {
        this.starReact = starReact;
    }

    //getters   
    public int getNumStars() {
        return numStars;
    }

    public float getStarX() {
        return starX;
    }

    public float getStarY() {
        return starY;
    }

    public float getStarReact() {
        return starReact;
    }

    

    //constructor
    public Cstar(int numStars, float starX, float starY, float starReact) {
        this.numStars = numStars;
        this.starX = starX;
        this.starY = starY;
        this.starReact = starReact;
    }
}
