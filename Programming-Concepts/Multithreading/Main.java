import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Creation:
        // Runnable     -> Interface used for run in thread.
        // Callable     -> Interface used for returning values (Future<T>).
        Runnable runnable = () -> System.out.println("Runnable");
        Callable<Integer> callable = () -> 1;


        // Thread Methods:
        // Thread.sleep()       -> Freezes a thread.
        // thread.join()        -> Waits for thread to finish.
        // thread.setDaemon()   -> Changes user thread to daemon thread.
        Thread.sleep(1000);
        new Thread(runnable).join();
        new Thread(runnable).setDaemon(true);


        // Synchronization:
        // Synchronized method          -> synchronized(this){}.
        // Static synchronized method   -> synchronized(MyClass.class){}.
        // Nothing equivalent           -> synchronized(myObject){}.
        synchronized (this) {
        }
        synchronized (Main.class) {
        }
        synchronized (new Object()) {
        }


        // Volatile:
        // Write variables directly to main memory (RAM).
        private volatile boolean flag = false;
        public static void method () {
            x = 0;
            y = 0;
            flag = true; // Prevent reordering.
        }


        // Virtual Thread:
        // Updated version of traditional thread (Java 19).
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println("Running in a virtual thread");
        });


        // Thread Local:
        // Give variables for each thread (cannot see each other).
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("Hello");
        threadLocal.get();
        threadLocal.remove();


        // Reentrant Lock:
        // .lock()                  -> Locks for a thread (ignore interruption and continue).
        // .lockInterruptibly()     -> Locks for a thread (stop waiting and throws exception).
        // .unlock()                -> Unlocks for a thread.
        // .tryLock()               -> Tries to acquire a lock instantly without waiting (returns boolean).
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.lockInterruptibly();
        lock.unlock();
        boolean getLock = lock.tryLock(10, TimeUnit.SECONDS);


        // Executor Service:
        // newFixedThreadPool                   -> Fixed amount of threads in pool.
        // newCachedThreadPool                  -> Amount of threads exceeds for needed threads.
        // newScheduledThreadPool               -> Assigns amount of threads, delay and period of each.
        // newSingleThreadExecutor              -> Executes threads sequentially.
        // newVirtualThreadPerTaskExecutor      -> Amount of threads exceeds for needed threads but for virtual threads.
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService virtualThreadPerTaskExecutor = Executors.newVirtualThreadPerTaskExecutor();


        // Blocking Queue:
        // Queue data structure.
        // .put()       -> Waits for adding if queue is full.
        // .take()      -> Waits for taking if queue is empty.
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);


        // Atomic:
        // Prevent non-thread-safe issues effectively.
        AtomicInteger counter = new AtomicInteger(0);
        AtomicLong bigCounter = new AtomicLong(0L);
        AtomicBoolean checkCounter = new AtomicBoolean(false);

        // Compare and Swap:
        // If old value == expected value then change to new value.
        boolean changed = counter.compareAndSet(1, 2);


        // Recursive task or recursive action:
        class Task extends RecursiveTask<String> {
            int count = 0;

            @Override
            protected String compute() {
                if (count < 16) {
                    // Base case.
                    return "Hello";
                } else {
                    // Recursive case.

                    Task leftTask = new Task();
                    Task rightTask = new Task();

                    leftTask.fork(); // Execute asynchronously.
                    rightTask.join(); // Wait for task to finish.
                    return "";
                }
            }
        }

        // Fork Join Pool:
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String msg = forkJoinPool.invoke(new Task());
    }
}
