//: sfg6lab.domain.service.TryLockBankService.java

package sfg6lab.domain.service;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;


class TryLockBankService {

    private Lock vault = new ReentrantLock();
    private int total = 0;
    
    public void deposit(int value) {
        
        try {
            vault.tryLock();
            total += value;
        } finally {
            vault.unlock();
        }
    }
    
    public static void main(String[] args) {
        var bank = new TryLockBankService();
        IntStream.range(0, 10).parallel().forEach(i -> bank.deposit(i));
        System.out.println("Total: " + bank.total);
    }
    
} /// :~