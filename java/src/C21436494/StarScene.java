package C21436494; //package declaration

import ie.tudublin.*; //import required libraries

//class declaration
public class StarScene extends Visual{
    MainVisual mv; //instance of MainVisual class

    Star[] stars; //array of Star objects

    //constructor
    public StarScene(MainVisual mv) {
        this.mv = mv; //initialise instance of MainVisual class

        stars = new Star[800]; //initialise array of Star objects

        //loop through array of Star objects and initialise each object
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(); //creating new Star object and assigning it to array
        }
    }

    //render method
    public void render() {
        mv.colorMode(RGB); //set colour mode to RGB
        mv.background(0); //set background colour to black
        mv.translate(mv.width / 2, mv.height / 2); //translate to centre of screen

        mv.beginShape(); //start shape
        mv.fill(255); //set fill colour to white
        float volume = mv.getAudioPlayer().mix.level(); // Get the current amplitude

        //loop through stars array
        for (Star s : stars) {
            s.update(volume); //update each star
            s.show(); //show each star
        }
        mv.endShape(); //end shape

        float average = 0; //initialise average variable
        float sum = 0; //initialise sum variable

        //looping through the mix array
        for(int i = 0; i < mv.getAudioPlayer().mix.size(); i++)
        {
            sum += Math.abs(mv.getAudioPlayer().mix.get(i)); //adding the absolute value of the current mix value to the sum
            mv.lerpedBuffer[i] = MainVisual.lerp(mv.lerpedBuffer[i], mv.getAudioPlayer().mix.get(i), 0.1f); //lerp the current lerpedBuffer value to the current mix value
        }

        average = sum / mv.getAudioPlayer().mix.size(); //calculate the average
        mv.smoothedAmplitude = MainVisual.lerp(mv.smoothedAmplitude, average, 0.1f); //lerp the smoothedAmplitude to the average

        mv.beginShape(); //start shape
        mv.noFill(); //disable fill
        mv.strokeWeight(2); //set stroke weight to 2

        //loop through the mix array
        for (int i = 0; i < mv.getAudioPlayer().mix.size(); i++) {
            float c = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, 255); //mapping the value of i to white
            mv.stroke(c, 255, 255); //setting the stroke colour to white
            float f = mv.lerpedBuffer[i] * 400; //calculating the size of the shape based on the lerpedBuffer value
            float angle = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, TWO_PI); //mapping the value of i to an angle
            float x = cos(angle) * (250 + f); //calculating the x position of the vertex
            float y = sin(angle) * (250 + f); //calculating the y position of the vertex
            mv.vertex(x, y); //adding the vertex to the shape
        }
        mv.endShape(); //end shape

        mv.beginShape(); //start shape
        mv.noFill(); //disable fill

        //loop through the mix array
        for (int i = 0; i < mv.getAudioPlayer().mix.size(); i++) {
            float c = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, 255); //mapping the value of i to white
            mv.stroke(c, 255, 255); //setting the stroke colour to white
            float f = mv.lerpedBuffer[i] * 200; //calculating the size of the shape based on the lerpedBuffer value
            float angle = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, TWO_PI); //mapping the value of i to an angle
            float x = cos(angle) * (350 + f); //calculating the x position of the vertex
            float y = sin(angle) * (350 + f); //calculating the y position of the vertex
            mv.vertex(x, y); //adding the vertex to the shape
        }
        mv.endShape(); //end shape  

    } //end render method

    //Star class
    class Star {

        float x, y, z; //position variables
        float px, py, pz; //previous position variables

        //constructor
        Star() {
            x = random(-mv.width / 2, mv.width / 2); //random x position
            y = random(-mv.height / 2, mv.height / 2); //random y position
            z = random(mv.width / 2); //random z position
            pz = z; //set previous z position to current z position
        }

        //update method
        void update(float volume) {
            z = z - 2 - volume * 100; //updating z position based on volume

            //if z position is less than 1
            if (z < 1) {
                z = mv.width / 2; //resetting the z-coordinate to the maximum value
                x = random(-mv.width / 2, mv.width / 2); //randomly resetting the x-coordinate
                y = random(-mv.height / 2, mv.height / 2); //randomly resetting the y-coordinate
                pz = z; //setting the previous z-coordinate to the current z-coordinate
            }
        } //end update method

        // show method 
        void show() {
            float sx = MainVisual.map(x / z, 0, 1, 0, mv.width); //mapping the x-coordinate 
            float sy = MainVisual.map(y / z, 0, 1, 0, mv.height); //mapping the y-coordinate 
            float r = MainVisual.map(z, 0, mv.width / 2, 16, 0); //mapping the z-coordinate

            mv.ellipse(sx / 2, sy / 2, r / 2, r / 2); //drawing the ellipse representing the star

            float px = MainVisual.map(x / pz, 0, 1, 0, mv.width); //mapping the previous x-coordinate 
            float py = MainVisual.map(y / pz, 0, 1, 0, mv.height); //mapping the previous y-coordinate

            mv.stroke(255, 128); //setting the stroke colour to white

            mv.line(sx, sy, px, py); //drawing the line connecting the current and previous positions

            px = sx; //updating the previous x-coordinate
            py = sy; //updating the previous y-coordinate
            pz = z; //updating the previous z-coordinate
        } //end show method

    } //end Star class

} //end Stars class