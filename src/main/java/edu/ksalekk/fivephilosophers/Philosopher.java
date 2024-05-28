package edu.ksalekk.fivephilosophers;

public class Philosopher implements Runnable {
    public final int id;
    public final Fork leftFork;
    public final Fork rightFork;
    public final Waiter waiter;

    public Philosopher(int id, Fork leftFork, Fork rightFork, Waiter waiter) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.waiter = waiter;
    }

    public void doAction(String action) throws InterruptedException {
        System.out.println("PHILOSOPHER_" + id + action);
        Thread.sleep((int) (Math.random() * 5) * 1000);
    }

    @Override
    public void run() {
        try {
            while(true) {
                doAction(" is thinking");
                long start = System.currentTimeMillis();
                this.waiter.pickUpForks(this.leftFork, this.rightFork);
                long time = (System.currentTimeMillis() - start) / 1000;
                doAction(" is eating [waiting time: " + time + "s]");
                this.waiter.putDownForks(this.leftFork, this.rightFork);
            }

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
