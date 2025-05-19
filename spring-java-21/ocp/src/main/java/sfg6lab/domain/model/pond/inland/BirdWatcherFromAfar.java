//: sfg6lab.domain.model.pond.inland.BirdWatcherFromAfar.java

package sfg6lab.domain.model.pond.inland;


import sfg6lab.domain.model.pond.shore.Bird;


public class BirdWatcherFromAfar {

    public void watchBird() {
        
        Bird bird = new Bird();
        
        // Cannot access protected method floatInWater() of Bird
        // Different package, so can't access it.
        // bird.floatInWater();
        
        // Cannot access protected field text of Bird
        // Different package, so can't access it.'
        // System.out.println(bird.text);
    }
    
} /// :~