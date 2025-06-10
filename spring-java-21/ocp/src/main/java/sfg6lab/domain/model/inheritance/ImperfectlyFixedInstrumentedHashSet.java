//: sfg6lab.domain.model.inheritance.FixedInstrumentedHashSet.java

package sfg6lab.domain.model.inheritance;


import java.util.Collection;
import java.util.HashSet;

import lombok.NonNull;


class ImperfectlyFixedInstrumentedHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    public ImperfectlyFixedInstrumentedHashSet() {}

    public ImperfectlyFixedInstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    /*
     * It would be slightly better to override the addAll method to iterate over
     * the specified collection, calling the add method once for each element.
     * This would guarantee the correct result, whether HashSet’s addAll method
     * was implemented atop its add method because HashSet’s addAll implementation
     * would no longer be invoked.
     *
     * This technique, however, does not solve all our problems:
     * It amounts to (相當於) reimplementing superclass methods that may or may
     * not result in self-use, which can be difficultly changing, time-consuming,
     * error-prone, and may reduce performance.
     * Additionally, it isn't always possible because some methods cannot be
     * implemented without access to private fields inaccessible to the subclass.
     */
    @Override
    public boolean addAll(@NonNull final Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    public int getAddCount() {
        return addCount;
    }

} /// :~