//: sfg6lab.domain.model.Matrix.java

package sfg6lab.domain.model;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


class AtomicMetrics {

    public static void main(String[] args) {

        final var metrics = new Metrics();
        final var businessLogicThread_1 = BusinessLogic.of(metrics);
        final var businessLogicThread_2 = BusinessLogic.of(metrics);
        final var metricsPrinterThread = MetricsPrinter.of(metrics);

        businessLogicThread_1.start();
        businessLogicThread_2.start();
        metricsPrinterThread.start();
    }

    @RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
    static class MetricsPrinter extends Thread {

        private final Metrics metrics;

        @Override
        public void run() {
            while (true) {
                printMetricsAverage();
            }
        }

        private void printMetricsAverage() {

            try {
                TimeUnit.MILLISECONDS.sleep(1500L);
            } catch (InterruptedException e) {
                System.out.println(">>> MetricsPrinter interrupted.");
            }

            var average = metrics.average();
            System.out.printf(">>> Current Metrics Average: %.4f%n", average);
        }

    }

    @RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
    static class BusinessLogic extends Thread {

        private final Metrics metrics;

        private ThreadLocalRandom random = ThreadLocalRandom.current();

        @Override
        public void run() {

            while (true) {
                addSampleToMetrics();
            }
        }

        private void addSampleToMetrics() {

            long startTime = System.currentTimeMillis();

            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(0, 10));
            } catch (InterruptedException e) {
                System.out.println(">>> BusinessLogic interrupted.");
            }

            long endTime = System.currentTimeMillis();

            this.metrics.addSample(endTime - startTime);
        }

    }

    static class Metrics {

        private long count;
        private volatile double average;

        synchronized void addSample(final long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        double average() {
            return average;
        }
    }

} ///:~