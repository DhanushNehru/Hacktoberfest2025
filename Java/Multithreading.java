import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Multithreading {

    public static void main(String[] args) {
        // Create a fixed thread pool with 4 threads
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        
        // List to hold the Future objects
        List<Future<Integer>> futures = new ArrayList<>();
        
        // Submit 10 tasks to the executor
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            Callable<Integer> task = () -> {
                // Simulate some computation
                int result = taskId * 2; // Simple task: double the task ID
                Thread.sleep(1000); // Simulate delay
                System.out.println("Task " + taskId + " completed with result: " + result);
                return result;
            };
            futures.add(executorService.submit(task));
        }

        // Shutdown the executor service
        executorService.shutdown();

        // Retrieve results from Future objects
        for (Future<Integer> future : futures) {
            try {
                // Blocking call to get the result
                Integer result = future.get();
                System.out.println("Result: " + result);
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error occurred while retrieving result: " + e.getMessage());
            }
        }
    }
}
