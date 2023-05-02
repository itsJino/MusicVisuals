package C21755919;

import ie.tudublin.*;

public class Scene4 extends Visual{
    MainVisual mv;
    float rot = 0;
    
    public Scene4(MainVisual mv){
        this.mv = mv;
    }
    
    public void render() {
        mv.colorMode(HSB);
        mv.beginShape();
        mv.camera(0, 0, 200, 0, 0, 0, 1, 0, 0);
        mv.translate(0, 0, -100);

        mv.rotate(MainVisual.radians(rot));

        for(float i = 0; i < TWO_PI; i += 0.1f) {

            float colour = MainVisual.map(i, 0, TWO_PI, 0, 255);
            mv.strokeWeight(7.5f);
            mv.stroke(colour, 255, 255);
            
            float r = 75 * cos(4f * i);
            float x = r * cos(i);
            float y = r * sin(i);
            mv.point(20 *(x * mv.lerpedAverage), 20 * (y * mv.lerpedAverage));
            rot += 0.15f;
        }

        
        mv.endShape();
    }
}


