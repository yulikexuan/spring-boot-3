//: sfg6lab.controller.data.DataMaper.java

package sfg6lab.controller.data;


public interface DataMaper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);
}
