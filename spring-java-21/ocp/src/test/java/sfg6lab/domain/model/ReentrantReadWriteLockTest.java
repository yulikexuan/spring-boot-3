//: sfg6lab.domain.model.ReentrantReadWriteLockTest.java

package sfg6lab.domain.model;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ReentrantReadWriteLockTest {
    
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    
    @BeforeEach
    void setUp() {
        rwLock = new ReentrantReadWriteLock();
    }
    
    @Test
    void not_Able_To_Get_Write_Lock_After_Acquiring_Read_Lock() throws Exception {
    
        boolean acquired = false;
        try {
            rwLock.readLock().lock();
            acquired = rwLock.writeLock().tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println(">>> Not be able to get write lock after acquiring read lock");
        } finally {
            rwLock.readLock().unlock();
        }
        System.out.println(acquired);
    }
    
} /// :~