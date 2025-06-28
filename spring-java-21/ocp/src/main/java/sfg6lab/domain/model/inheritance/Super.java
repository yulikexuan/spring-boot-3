//: sfg6lab.domain.model.inheritance.Super.java

package sfg6lab.domain.model.inheritance;


public class Super {

    /*
     * Constructors must not invoke overridable methods, directly or indirectly
     */
    public Super() {
        System.out.println("[Super] >>> Constructing Super ... ");
        this.overrideMe();
    }

    public void overrideMe() {
        System.out.println("[Super] >>> Doing super.overrideMe() ... ");
    }

} /// :~