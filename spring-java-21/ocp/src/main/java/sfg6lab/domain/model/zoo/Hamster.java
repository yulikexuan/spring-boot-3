//: sfg6lab.domain.model.zoo.Hamster.java

package sfg6lab.domain.model.zoo;


class Hamster {

    private String color;
    private int weight;
    
    Hamster(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }
    
    Hamster(int weight) {
        
        // Constructors can be called only by writing new before the name of
        // the constructor.
        // They are not like normal methods that you can just call.
        // Hamster(weight, "brown");
        
        // Compiles but creates an extra object;
        // It doesn’t do what we want, though;
        // When this constructor is called, it creates a new object with
        // the default weight and color;
        // It then constructs a different object with the desired weight and color
        // In this manner, we end up with two different objects;
        // One of which is discarded after it's created
        // new Hamster(weight, "brown");
        
        // this() must be the first statement in the constructor
        // there can be only one call to this() in any constructor
        this(weight, "brown");
    }
    
} /// :~