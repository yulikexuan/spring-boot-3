//: sfg6lab.domain.model.inheritance.InstrumentedHashSet.java

package sfg6lab.domain.model.inheritance;


import java.util.Collection;
import java.util.HashSet;


/*
 * Double-Counted implementation of HashSet
 *
 * If you use inheritance where composition is appropriate,
 * you needlessly expose implementation details.
 * The resulting API ties you to the original implementation,
 * forever limiting the performance of your class.
 *
 * More seriously, by exposing the internals, you let clients access them directly.
 *
 * At the very least, it can lead to confusing semantics and chaos.
 */
class InstrumentedHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    public InstrumentedHashSet() {}

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    /*
     * When calling addAll(List.of("1", "2", "3")):
     * Internally, HashSet’s addAll method is implemented on top of its add method,
     * although HashSet, quite reasonably, does not document this implementation detail.
     * The addAll method in this class added three to addCount and then
     * invoked HashSet’s addAll implementation using super::addAll
     * This in turn invoked the add method, as overridden in InstrumentedHashSet,
     * once for each element. Each of these three invocations added one more to addCount,
     * for a total increase of six:
     * each element added with the addAll method is double-counted.
     *
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

} /// :~