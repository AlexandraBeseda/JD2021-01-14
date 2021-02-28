package by.it.nakhankov.jd02_02;

import java.util.Objects;

public class  Cashier implements Runnable {

    private final int number;

    public Cashier(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(this + "opened");
        while (!Dispatcher.marketIsClosed()) {
            Customer customer = QueueCustomers.poll();
            if (Objects.nonNull(customer)) {
                System.out.println(this + "started service for " + customer);
                int timeout = Utils.random(2000, 5000);
                Utils.timeout(timeout);
                synchronized (customer.getMonitorWaiting()) {
                    customer.setWaitFlag(false);
                    customer.notify();
                }
                System.out.println(this + "finished service for " + customer);
            } else {

                Thread.onSpinWait();
            }
        }

        System.out.println(this + "closed");
    }

    @Override
    public String toString() {
        return "-- Cashier №" + number + " ";
    }
}
