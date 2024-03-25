//: springlab.domain.model.Coffee.java


package springlab.domain.model;


public record Coffee(Water water, CoffeeBeans coffeeBeans, boolean brewed) {

    public Coffee(Water water, CoffeeBeans coffeeBeans) {
        this(water, coffeeBeans, false);
    }

    public static Coffee brewed(Coffee coffee) {
        return new Coffee(coffee.water, coffee.coffeeBeans, true);
    }

}///:~