import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {
    
    // Each fork is represented by a lock
    private final ReentrantLock[] forks;

    public DiningPhilosophers() {
        forks = new ReentrantLock[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        
        // Determine the fork indices for this philosopher
        int leftFork = philosopher;
        int rightFork = (philosopher + 1) % 5;
        
        // Resource ordering: Always pick up the lower-indexed fork first
        int firstFork = Math.min(leftFork, rightFork);
        int secondFork = Math.max(leftFork, rightFork);
        
        // Acquire locks in a strict global order to prevent circular dependency
        forks[firstFork].lock();
        forks[secondFork].lock();
        
        try {
            // Pick up forks, eat, and put them down
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
        } finally {
            // Release locks in reverse order
            forks[secondFork].unlock();
            forks[firstFork].unlock();
        }
    }
}
