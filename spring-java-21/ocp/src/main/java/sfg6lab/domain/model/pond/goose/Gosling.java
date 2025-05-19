//: sfg6lab.domain.model.pond.goose.Gosling.java

package sfg6lab.domain.model.pond.goose;


import sfg6lab.domain.model.pond.shore.Bird;


public class Gosling extends Bird {

    public void swim() {
        floatInWater();
        System.out.println(text);
    }
    
    public static void main(String[] args) {
        new Gosling().swim();
    }
    
} /// :~