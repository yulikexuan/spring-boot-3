//: sfg6lab.domain.model.inheritance.FragileFixedInstrumentedHashSet.java

package sfg6lab.domain.model.inheritance;


import java.util.Collection;
import java.util.HashSet;


/*
 *
 */
class FragileFixedInstrumentedHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    public FragileFixedInstrumentedHashSet() {}

    public FragileFixedInstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    /*
     * Fix the subclass by eliminating its override of the addAll method.
     * While the resulting class would work, it would depend for its proper
     * function on the fact that HashSet’s addAll method is implemented on top
     * of its add method.
     * This “self-use” is an implementation detail, not guaranteed to hold in
     * all implementations of the Java platform and subject to change from
     * release to release.
     * Therefore, the resulting InstrumentedHashSet class would be fragile.
     */
//    @Override
//    public boolean addAll(Collection<? extends E> c) {
//        addCount += c.size();
//        return super.addAll(c);
//    }

    public int getAddCount() {
        return addCount;
    }

} /// :~