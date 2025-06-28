//: sfg6lab.domain.model.interfaces.Cat.java

package sfg6lab.domain.model.interfaces;


class Cat implements Walk, Run {

    @Override
    public int getSpeed() {
        return 1;
    }

    public int getWalkSpeed() {
        return Walk.super.getSpeed();
    }

    public int getRunSpeed() {
        return Run.super.getSpeed();
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        System.out.println(">>> The cat's speed is : %d km/h".formatted(cat.getSpeed()));
        System.out.println(">>> As a walker, the cat can walk at %d km/h"
                .formatted(cat.getWalkSpeed()));
        System.out.println(">>> As a runner, the cat can run at %d km/h"
                .formatted(cat.getRunSpeed()));
    }

} /// :~