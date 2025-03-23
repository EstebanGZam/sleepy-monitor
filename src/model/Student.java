package model;

import java.util.Random;

// Student class
public class Student implements Runnable {
    private int id;
    private MonitorOffice office;
    private Random random = new Random();
    private int maxHelpRequests;
    private int helpRequests = 0;

    public Student(int id, MonitorOffice office, int maxHelpRequests) {
        this.id = id;
        this.office = office;
        this.maxHelpRequests = maxHelpRequests;
    }

    @Override
    public void run() {
        while (helpRequests < maxHelpRequests) {
            try {
                // Student programs for a while in the computer lab
                System.out.println("Student " + id + " is programming in the computer lab.");
                Thread.sleep(random.nextInt(3000) + 2000);

                // Student tries to get help from the monitor
                boolean receivedHelp = office.requestHelp(id);

                if (receivedHelp) {
                    helpRequests++;
                    System.out.println("Student " + id + " has received help " + helpRequests + " times.");
                    // Student is getting help for a random time
                    Thread.sleep(random.nextInt(1000) + 1000);
                    office.finishHelp(id);
                } else {
                    // If student couldn't get help, go back to programming and try again later
                    System.out.println("Student " + id + " couldn't get help and goes back to programming.");
                    Thread.sleep(random.nextInt(4000) + 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Student " + id + " has finished all help requests and leaves.");
    }
}
