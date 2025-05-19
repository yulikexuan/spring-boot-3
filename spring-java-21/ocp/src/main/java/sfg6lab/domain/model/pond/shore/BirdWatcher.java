//: sfg6lab.domain.model.pond.shore.BirdWatcher.java

package sfg6lab.domain.model.pond.shore;


public class BirdWatcher {

    public void watchBird() {
        Bird bird = new Bird();
        bird.floatInWater();
        System.out.println(bird.text);
    }
    
} /// :~