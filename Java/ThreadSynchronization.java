import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSynchronizationExample {

    private static final int NUM_THREADS = 10;

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.incrementAndGet();
                }
            });
            thread.start();
        }

        try {
            for (Thread thread : Thread.getAllStackTraces().keySet()) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value: " + counter.get());
    }
}
