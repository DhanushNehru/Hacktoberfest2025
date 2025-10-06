public class ThreadPriorityExample {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 with priority " + Thread.MAX_PRIORITY + " is running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.setPriority(Thread.MAX_PRIORITY);

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2 with priority " + Thread.MAX_PRIORITY + " is running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.setPriority(Thread.MAX_PRIORITY);

        Thread thread3 = new Thread(() -> {
            System.out.println("Thread 3 with priority " + Thread.NORM_PRIORITY + " is running...");
        });
        thread3.setPriority(Thread.NORM_PRIORITY);

        Thread thread4 = new Thread(() -> {
            System.out.println("Thread 4 with priority " + Thread.NORM_PRIORITY + " is running...");
        });
        thread4.setPriority(Thread.NORM_PRIORITY);

        Thread thread5 = new Thread(() -> {
            System.out.println("Thread 5 with priority " + Thread.MIN_PRIORITY + " is running...");
        });
        thread5.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
