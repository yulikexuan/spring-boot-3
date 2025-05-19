//: sfg6lab.domain.model.pond.goose.Goose.java

package sfg6lab.domain.model.pond.goose;


import sfg6lab.domain.model.pond.shore.Bird;


public class Goose extends Bird {

    public void helpGooseSwim() {
        Goose other = new Goose();
        other.floatInWater();
        System.out.println(other.text);
    }
    
    public void helpOtherGooseSwim() {
        
        Bird other = new Goose();
        
        // Cannot call floatInWater method of Bird here
        // other.floatInWater();
        
        // Cannot access text field of Bird here
        // System.out.println(other.text);
    }
    
} /// :~