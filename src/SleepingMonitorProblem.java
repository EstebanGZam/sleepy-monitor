import model.Monitor;
import model.MonitorOffice;
import model.Student;

// Main class
public class SleepingMonitorProblem {
    public static void main(String[] args) {
        int numberOfStudents = 10;
        MonitorOffice office = new MonitorOffice();
        
        // Create and start the monitor thread
        Monitor monitor = new Monitor(office);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        
        // Create and start student threads
        for (int i = 0; i < numberOfStudents; i++) {
            Student student = new Student(i + 1, office);
            Thread studentThread = new Thread(student);
            studentThread.start();
        }
    }
}