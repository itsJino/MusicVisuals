package C21755919;

import ie.tudublin.*;

public class CianVisualizer extends Visual
{
    MainVisual mv;
    float topOffset;
    int rows = 3300 / 30;
    int cols = 3300 / 30;
    float[][] terrain;
    int numShootingStars = 15;
    float[] shootingStarX = new float[numShootingStars];
    float[] shootingStarY = new float[numShootingStars];
    float[] shootingStarSpeed = new float[numShootingStars];
    float[] starReact = new float[numShootingStars];

    public CianVisualizer(MainVisual mv)
    {
        this.mv = mv;
        this.topOffset = mv.height / 0.4f;
        this.terrain = new float[cols][rows];

        for(int i = 0; i < numShootingStars; i++)
        {
            this.shootingStarX[i] = random(mv.width);
            this.shootingStarY[i] = random(mv.height / 1.75f);
            this.shootingStarSpeed[i] = random(5, 500);
            this.starReact[i] = random(20, 100);
        }
    }

    public void render()
    {
        mv.colorMode(HSB);
        //float halfH = mv.height / 2;
        float halfW = mv.width / 2;
        float average = 0;
        float sum = 0;
        
        // Drawing shooting stars
        float trailAlpha = 50;
        for (int i = 0; i < numShootingStars; i++) {
            mv.noStroke();
            mv.fill(255);
            mv.circle(shootingStarX[i], shootingStarY[i], mv.smoothedAmplitude * 30);

            // Drawing trail behind stars
            mv.fill(255, trailAlpha);
            mv.ellipse(shootingStarX[i] + shootingStarSpeed[i], shootingStarY[i],
                    mv.smoothedAmplitude * starReact[i] * 1.25f, mv.smoothedAmplitude  * starReact[i] * 1.5f);

            mv.ellipse(shootingStarX[i] + shootingStarSpeed[i], shootingStarY[i],
                    mv.smoothedAmplitude * starReact[i] * 0.75f, mv.smoothedAmplitude * starReact[i] * 0.75f);

            shootingStarX[i] -= shootingStarSpeed[i] + random(-2, 2);
            shootingStarY[i] += random(0, 1.5f);
            if (shootingStarX[i] < 15) {
                shootingStarX[i] = mv.width;
                shootingStarY[i] = random(mv.height / 2);
                shootingStarSpeed[i] = random(5, 30);
            }
        }
        
        for(int i = 0; i < mv.getAudioPlayer().mix.size(); i++)
        {
            sum += Math.abs(mv.getAudioPlayer().mix.get(i));
            mv.lerpedBuffer[i] = MainVisual.lerp(mv.lerpedBuffer[i], mv.getAudioPlayer().mix.get(i), 0.1f);
        }
        average = sum / mv.getAudioPlayer().mix.size();
    
        //Northern Lights
        for(int i = 0; i < mv.getAudioPlayer().mix.size(); i++)
        {
            float c = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, 255);
            float amplitude = mv.getAudioPlayer().mix.get(i);
            float f = mv.lerpedBuffer[i] * halfW * 1.0f;
            float x = MainVisual.map(i, 0, mv.getAudioPlayer().mix.size(), 0, mv.width);

            //Map amplitude to stroke color in rgb
            float hue = MainVisual.map(amplitude, -1, 1, 100, 120);
            float saturation = MainVisual.map(amplitude, -1, 1, 160, 225);
            float brightness = MainVisual.map(amplitude, -1, 1, 180, 225);

            //Draw lines with varying stroke weight and color
            if(f > 0)
            {
                float weight = MainVisual.map(amplitude, -1, 1, 0.5f, 3);
                mv.strokeWeight(f / 50);
                mv.stroke(hue, saturation, brightness, c);
                mv.line(x, topOffset + f, x, topOffset - f);

                float alpha = MainVisual.map(amplitude, -1, 1, 20, 80);
                mv.strokeWeight(weight * 0.5f);
                mv.stroke(hue, saturation, brightness, alpha);
                mv.line(x, topOffset + (f * 0.5f), x, topOffset - (f * 0.5f));
            }
        }

        // Waves
        mv.beginShape();
        mv.flying -= mv.smoothedAmplitude / 2;

        float yoff = mv.flying;

        for (int y = 0; y < cols; y++) {
            float xoff = 0;
            for (int x = 0; x < rows; x++) {
                terrain[y][x] = MainVisual.map(noise(xoff, yoff), 0, 1, -95, 0);
                xoff += 0.3f;
            }
            yoff += 0.3f;   
        }
        
        mv.translate(mv.width / 6, mv.height / 2 +15);
        mv.rotateX(PI / 2);
        mv.translate(-mv.width / 2, -mv.height / 2);
        mv.fill(0);
        mv.stroke(255, 50);
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