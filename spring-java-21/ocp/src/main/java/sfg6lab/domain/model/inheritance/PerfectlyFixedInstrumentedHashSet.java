//: sfg6lab.domain.model.inheritance.PerfectlyFixedInstrumentedHashSet.java

package sfg6lab.domain.model.inheritance;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ForwardingSet;


class PerfectlyFixedInstrumentedHashSet<E>
        extends ForwardingSet<E> implements Set<E> {

    private int addCount = 0;

    private final Set<E> delegate;

    public PerfectlyFixedInstrumentedHashSet() {
        this.delegate = new HashSet<>();
    }

    public PerfectlyFixedInstrumentedHashSet(Collection<E> collection) {
        this.delegate = new HashSet<>(collection);
    }

    @Override
    protected Set<E> delegate() {
        return delegate;
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

} /// :~