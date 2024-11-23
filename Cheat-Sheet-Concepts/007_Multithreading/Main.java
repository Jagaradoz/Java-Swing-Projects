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
        // ========= CREATION =========
        // Runnable     -> interface used for run in Thread
        // Callable     -> interface used for returning values (Future<T>)
        Runnable runnable = () -> System.out.println("Runnable");
        Callable<Integer> callable = () -> 1;


        // ========= THREAD METHODS =========
        // Thread.sleep()       -> freezes a thread
        // thread.join()        -> waits for thread to finish
        // thread.setDaemon()   -> changes user thread to daemon thread
        Thread.sleep(1000);
        new Thread(runnable).join();
        new Thread(runnable).setDaemon(true);


        // ========= SYNCHRONIZATION =========
        // synchronized method          -> synchronized(this){}
        // static synchronized method   -> synchronized(MyClass.class){}
        // NOTHING EQUIVALENT           -> synchronized(myObject){}
        synchronized (this) {
        }
        synchronized (Main.class) {
        }
        synchronized (new Object()) {
        }


        // ========= VOLATILE =========
        // write variables directly to main memory (RAM)
        private volatile boolean flag = false;
        public static void method () {
            x = 0;
            y = 0;
            flag = true; // prevent reordering
        }


        // ========= VIRTUAL THREAD =========
        // updated version of traditional thread (java 19)
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println("Running in a virtual thread");
        });


        // ========= THREAD LOCAL =========
        // give variables for each thread (cannot see each other)
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("Hello");
        threadLocal.get();
        threadLocal.remove();


        // ========= REENTRANT LOCK =========
        // .lock()                  -> locks for a thread (ignore interruption and continue)
        // .lockInterruptibly()     -> locks for a thread (stop waiting and throws exception)
        // .lock()                  -> unlocks for a thread
        // .tryLock()               -> tries to acquire a lock instantly not waiting (return boolean)
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.lockInterruptibly();
        lock.unlock();
        boolean getLock = lock.tryLock(10, TimeUnit.SECONDS);


        // ========= EXECUTOR SERVICE =========
        // newFixedThreadPool                   -> fixed amount of threads in pool
        // newCachedThreadPool                  -> amount of threads exceeds for needed threads
        // newScheduledThreadPool               -> assigns amount of threads , delay and period of each
        // newSingleThreadExecutor              -> executes threads sequentially
        // newVirtualThreadPerTaskExecutor      -> amount of threads exceeds for needed threads but for visual threads
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService virtualThreadPerTaskExecutor = Executors.newVirtualThreadPerTaskExecutor();


        // ========= BLOCKING QUEUE =========
        // Queue data structure
        // .put()       -> waits for adding if queue is full
        // .take()      -> waits for taking if queue is empty
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);


        // ========= ATOMIC =========
        // prevent non-thread-safe effectively
        AtomicInteger counter = new AtomicInteger(0);
        AtomicLong bigCounter = new AtomicLong(0L);
        AtomicBoolean checkCounter = new AtomicBoolean(false);

        // ========= COMPARE AND SWAP =========
        // if old value == expected value then change to new value
        boolean changed = counter.compareAndSet(1, 2);


        // ========= RECURSIVE TASK OR RECURSIVE ACTION =========
        class Task extends RecursiveTask<String> {
            int count = 0;

            @Override
            protected String compute() {
                if (count < 16) {
                    // Base case
                    return "Hello";
                } else {
                    // Recursive case

                    Task leftTask = new Task();
                    Task rightTask = new Task();

                    leftTask.fork(); // execute asynchronously
                    rightTask.join(); // wait for task finish
                    return "";
                }
            }
        }

        // ========= FORK JOIN POOL =========
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String msg = forkJoinPool.invoke(new Task());
    }
}
