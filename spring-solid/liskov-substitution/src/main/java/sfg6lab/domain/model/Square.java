//: sfg6lab.domain.model.Square.java


package sfg6lab.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;


@Data
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper=true)
public class Square extends Rectangle {

    private int side;

    Square() {
    }

    Square(int side) {

        super(side, side);

        Assert.isTrue(side >= 0,
                "Size must be greater than or equal to 0");

        this.side = side;
        this.height = side;
        this.width = side;
    }

    private void changeSide(int newSide) {

        Assert.isTrue(newSide >= 0,
                ">>> Size must be greater than or equal to 0");

        this.side = newSide;
        this.width = newSide;
        this.height = newSide;
    }

    public Square side(int newSide) {
        this.changeSide(newSide);
        return this;
    }

    @Override
    public Square width(int newWidth) {
        this.changeSide(newWidth);
        return this;
    }

    @Override
    public Square height(int newHeight) {
        this.changeSide(newHeight);
        return this;
    }

    public static boolean validate(@NonNull Square square) {
        return square.width == square.height;
    }

}///:~