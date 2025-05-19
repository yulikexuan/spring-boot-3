//: sfg6lab.domain.model.PassingByValue.java

package sfg6lab.domain.model;


class PassingByValue {
    
    static final int INITIAL_NUMBER = 4;
    static final int NEW_NUMBER = 8;
    
    static final int ORIGINAL_1 = 1;
    static final int ORIGINAL_2 = 2;
    
    static int num_1;
    static int num_2;
    
    public static int number() {
        int num = INITIAL_NUMBER;
        newNumber(num);
        return num;
    }
    
    static void newNumber(int num) {
        num = NEW_NUMBER;
    }
    
    public static int[] doSwap() {
        
        num_1 = ORIGINAL_1;
        num_2 = ORIGINAL_2;
        
        swap(num_1, num_2);
        
        return new int[] {num_1, num_2};
    }
    
    static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

} /// :~