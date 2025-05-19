//: sfg6lab.domain.model.pond.swan.Swan.java

package sfg6lab.domain.model.pond.swan;


import sfg6lab.domain.model.pond.shore.Bird;


public class Swan extends Bird {

    public void swim() {
        floatInWater();
        System.out.println(text);
    }
    
    public void helpOtherSwanSwim() {
        Swan other = new Swan();
        other.floatInWater();
        System.out.println(other.text);
    }
    
    public void helpOtherBirdSwim() {
        
        Bird other = new Bird();
        
        // floatInWater method of Bird is protected, and cannot be accessed
        // from any other package than pond.shore
        // other.floatInWater();
        
        // text field of Bird is protected and cannot be accessed
        // from any other package than pond.shore
        // System.out.println(other.text);
    }

} /// :~