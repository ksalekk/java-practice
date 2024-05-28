package edu.ksalekk.fivephilosophers;

public class Waiter {
    // synchronized pickUpForks() =>  only one philosopher is served by waiter at once
    public synchronized void pickUpForks(Fork leftFork, Fork rightFork) throws InterruptedException {
        // only one philosopher is served by waiter, so fork state not will change between "check time" and "use time"
        while(leftFork.isBusy() || rightFork.isBusy()) {
            wait();
        }

        // if both forks are free, then lock them for current philosopher
        leftFork.setBusy(true);
        rightFork.setBusy(true);
    }

    // synchronized putDownForks() =>  only one philosopher is served by waiter at once
    public synchronized void putDownForks(Fork leftFork, Fork rightFork) {
        leftFork.setBusy(false);
        rightFork.setBusy(false);
        this.notifyAll();
    }
}
