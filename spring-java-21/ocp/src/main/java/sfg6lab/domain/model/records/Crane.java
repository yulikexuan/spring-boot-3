//: sfg6lab.domain.model.records.Crane.java

package sfg6lab.domain.model.records;


import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;


public record Crane(int numberOfEggs, String name) {

    public Crane {
        if (numberOfEggs < 0) {
            throw new IllegalArgumentException("Number of eggs must not be negative.");
        }
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name must not be blank.");
        }
        name = name.trim().toUpperCase();
    }

    public Crane(int numberOfEggs, String firstName, String lastName) {
        this(numberOfEggs, firstName + "-" + lastName);
        numberOfEggs = -1;
        // this.name = this.name.toUpperCase();
    }

//    public Crane(int numberOfEggs) {
//        this("");
//    }
//
//    public Crane(String name) {
//        this(1);
//    }

    public static void main(String[] args) {
        Crane crane = new Crane(1, "red-crowned", "crane");
        Assert.isTrue(crane.name().equals(
                "RED-CROWNED-CRANE"), ">>> Name must be capitalized.");
    }
}
