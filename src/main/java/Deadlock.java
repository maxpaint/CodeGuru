public class Deadlock {
    private Object process = new Object();
    private boolean stopped = true;

    public static void main(String args[]) throws InterruptedException {
        Deadlock deadlock = new Deadlock();

        Thread threadA = new Thread(() -> {
            deadlock.run();
            System.out.println("Running...");
        });
        Thread threadB = new Thread(() -> {
            deadlock.stopProcess();
            System.out.println("Stopped...");
        });
        threadA.start();
        threadA.join();
        threadB.start();
    }

    public void run() {
        synchronized (process) {
            isStopped();
        }
    }

    private synchronized boolean isStopped() {
        return stopped;
    }

    public synchronized void stopProcess() {
        synchronized (process) {
            System.out.println("Stopping...");
        }
    }
}

