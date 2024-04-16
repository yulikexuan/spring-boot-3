//: sfg6lab.domain.model.Rectangle.java


package sfg6lab.domain.model;


import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(fluent = true)
public class Rectangle implements Shape {

    int width;
    int height;

    Rectangle() {
    }

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public long area() {
        return (long) width * height;
    }

}///:~