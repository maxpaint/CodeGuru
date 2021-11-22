public class Deadlock {
    private volatile Object process = new Object();
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
        threadB.start();
    }

    public void run() {
        synchronized (process) {
            isStopped();
        }
    }

    private boolean isStopped() {
        return stopped;
    }

    public void stopProcess() {
        synchronized (process) {
            System.out.println("Stopping...");
        }
    }
}

