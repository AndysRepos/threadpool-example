import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * The class demonstrates the use of the Executor framework to
 * manage a fixed number of threads and assign a list of jobs to the thread pool for execution.
 * The application takes two command-line arguments; the first specifies the number of jobs,
 * and the second the number of threads in the pool.
 *
 * Usage: java ThreadPoolApp [number of jobs] [number of threads]
 *
 * Both command-line arguments must be positive integers where:
 *
 * The first argument 'numberOfJobs' denotes the number of jobs to be executed.
 * The second argument 'numberOfThreads' is the number of threads to maintain in the pool.
 *
 * The class has a single static method 'error' which displays an error message
 * if the input arguments are missing or are invalid and terminates the application.
 *
 * Within the main method, a fixed thread pool is created with a number of threads
 * specified by the user. Jobs are submitted to the pool and executed. Once all jobs are submitted,
 * the pool is shut down, allowing all submitted jobs to finish without accepting new ones.
 *
 * The Job class, which is not defined within this snippet, is assumed to implement
 * the Runnable interface, representing the jobs to be executed by the thread pool.
 *
 */
public class ThreadPoolApp {
    
    public static void main (String [] args) {
        if (args.length < 2)
            ThreadPoolApp.error();
        try {
            int numberOfJobs = Integer.parseInt(args[0]);
            int numberOfThreads = Integer.parseInt(args[1]);
            if ((numberOfJobs < 1) || (numberOfThreads < 1))
                ThreadPoolApp.error();
            ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

            Job [] jobs = new Job [numberOfJobs];
            for (int i = 0; i < numberOfJobs; i++) {
                jobs[i] = new Job (i);
                pool.execute(jobs[i]); // executes the command at future time.
            }
            pool.shutdown(); // Shutdown: previously submitted tasks are executed,
            // but no new tasks will be accepted.
            System.out.println ("Last line " + Thread.currentThread().getName());
        } catch (NumberFormatException e) {
            ThreadPoolApp.error();
        }
    }

    /**
     * Prints an error message to the standard output and exits the application.
     * This method is called when there is an error with the input arguments.
     */
    private static void error() {
        System.out.println("ThreadPoolApp must be run with two positive valued " +
                "integer arguments. The first detailing the number of jobs " +
                "the second the number of processing threads in the pool");
        System.exit(0); // exit program
    }
}
