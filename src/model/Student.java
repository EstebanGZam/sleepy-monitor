package model;

import java.util.Random;

// Student class
public class Student implements Runnable {
    private int id;
    private MonitorOffice office;
    private Random random = new Random();

    public Student(int id, MonitorOffice office) {
        this.id = id;
        this.office = office;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Student programs for a while in the computer lab
                System.out.println("Student " + id + " is programming in the computer lab.");
                Thread.sleep(random.nextInt(15000) + 1000);

                // Student tries to get help from the monitor
                boolean receivedHelp = office.requestHelp(id);

                if (receivedHelp) {
                    // Student is getting help for a random time
                    Thread.sleep(random.nextInt(2000) + 1000);
                    office.finishHelp(id);
                } else {
                    // If student couldn't get help, go back to programming and try again later
                    System.out.println("Student " + id + " couldn't get help and goes back to programming.");
                    Thread.sleep(random.nextInt(10000) + 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
