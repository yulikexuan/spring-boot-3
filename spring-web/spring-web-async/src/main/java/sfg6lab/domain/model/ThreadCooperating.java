//: sfg6lab.domain.model.ThreadCooperating.java

package sfg6lab.domain.model;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;


class ThreadCooperating {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new LongComputationTask(
                BigInteger.TWO, BigInteger.valueOf(1000000000000L)));

        thread.setDaemon(true);

        thread.start();

        TimeUnit.MILLISECONDS.sleep(1500L);

        thread.interrupt();
    }

    @RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
    static class LongComputationTask implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        public void run() {
            System.out.println(">>> %d ^ %d = %d".formatted(
                    this.base, this.power, power(this.base, this.power)));
        }

        private BigInteger power(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO;
                 i.compareTo(power) < 0;
                 i = i.add(BigInteger.ONE)) {

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(">>> Prematurely interrupted computation.");
                    return BigInteger.ZERO;
                }

                result = result.multiply(base);
            }
            return result;
        }
    }

} ///:~