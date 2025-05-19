//: sfg6lab.domain.model.StaticImporting.java

package sfg6lab.domain.model;


// This static import can be removed as there is a same one defined in this class
import static java.util.Arrays.asList;

import java.util.List;

// Importing two classes with the same name gives a compiler error.
// import java.awt.List;


class BadZooParking {
    
    static boolean usingMyAsList = false;
    
    public static void main(String[] args) {
        System.out.println(makeList());
    }
    
    static List<String> makeList() {
        // Arrays.asList("one", "two");
        return asList("one", "two");
    }
    
    private static <T> List<T> asList(T... strings) {
        usingMyAsList = true;
        return List.of(strings);
    }
    
} /// :~