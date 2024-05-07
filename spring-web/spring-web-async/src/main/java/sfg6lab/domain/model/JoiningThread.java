//: sfg6lab.domain.model.JoiningThread.java

package sfg6lab.domain.model;


import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.List;


class JoiningThread {

    public static void main(String[] args) {

        List<Long> inputNums = List.of(1000000000L, 34L, 35L, 23L, 46L, 23L, 55L);

        List<FactorialThread> threads = Lists.newArrayList();
        for (Long num : inputNums) {
            FactorialThread thread = FactorialThread.of(num);
            threads.add(thread);
        }

        for (FactorialThread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        for (FactorialThread thread : threads) {
            try {
                thread.join(1000L);
            } catch (InterruptedException e) {
                System.out.println(">>> Thread %s was interrupted");
            }
        }

        for (int i = 0; i < inputNums.size(); i++) {
            var factorialThread = threads.get(i);
            Long num = inputNums.get(i);
            var msg = factorialThread.finished() ?
                    ">>> Factorial of %d is %d".formatted(
                            num, factorialThread.result())
                    : ">>> The calculation for %d is still in progress".formatted(
                    num);
            System.out.println(msg);
        }
    }

    @RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
    static class FactorialThread extends Thread {

        private final long input;

        private BigInteger result = BigInteger.ZERO;
        private boolean finished = false;

        @Override
        public void run() {

            this.result = factorial(this.input);
            this.finished = true;
        }

        private static BigInteger factorial(long num) {

            BigInteger tmp = BigInteger.ONE;
            for (long i = num; i > 0; i--) {
                tmp = tmp.multiply(BigInteger.valueOf(i));
            }

            return tmp;
        }

        boolean finished() {

            return finished;
        }

        BigInteger result() {

            return this.result;
        }

    }

} ///:~