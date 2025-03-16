package model;

// Monitor class (the teaching assistant)
public class Monitor implements Runnable {
    private MonitorOffice office;

    public Monitor(MonitorOffice office) {
        this.office = office;
    }

    @Override
    public void run() {
        System.out.println("Monitor has started their shift.");

        while (true) {
            // Check if there are waiting students
            office.checkWaitingStudents();

            // If students are waiting, we're helping them now
            // If no students, we're sleeping until woken up

            // Small pause to make the simulation more readable
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
