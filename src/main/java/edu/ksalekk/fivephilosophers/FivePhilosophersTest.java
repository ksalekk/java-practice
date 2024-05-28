package edu.ksalekk.fivephilosophers;

public class FivePhilosophersTest {
    public static void main(String[] args) {
        int n = args.length==0 ? 5 : Integer.parseInt(args[0]);
        Thread[] philosophers = new Thread[n];
        Fork[] forks = new Fork[n];
        Waiter waiter = new Waiter();

        for(int i=0; i<n; i++) {
            forks[i] = new Fork();
        }

        for (int i=0; i<n; i++) {
            Philosopher philosopher = new Philosopher(i, forks[i], forks[(i+1) % n], waiter);
            philosophers[i] = new Thread(philosopher);
        }

        for(Thread philosopher : philosophers) {
            philosopher.start();
        }

    }
}
