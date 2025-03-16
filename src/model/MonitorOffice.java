package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

// Monitor Office - Manages the shared resources and synchronization
public class MonitorOffice {
    private static final int CORRIDOR_CHAIRS = 3;
    private Semaphore monitorAvailable = new Semaphore(1);
    private Semaphore studentsWaiting = new Semaphore(0);
    private Semaphore corridorChairs = new Semaphore(CORRIDOR_CHAIRS);
    private Queue<Integer> waitingQueue = new LinkedList<>();
    private boolean isMonitorSleeping = true;

    public MonitorOffice() {
        System.out.println("Monitor's office is open. There are " + CORRIDOR_CHAIRS + " chairs in the corridor.");
    }

    // Called by students to request help
    public boolean requestHelp(int studentId) {
        System.out.println("Student " + studentId + " arrives at the monitor's office.");

        try {
            // Check if the monitor is sleeping
            if (isMonitorSleeping) {
                System.out.println("Student " + studentId + " found the monitor sleeping and wakes them up.");
                isMonitorSleeping = false;

                // Student wakes up monitor and gets help directly
                monitorAvailable.acquire();
                System.out.println("Monitor is now helping Student " + studentId + ".");
                return true;
            }
            // Monitor is busy, try to get a chair
            else if (!monitorAvailable.tryAcquire()) {
                // Try to get a corridor chair
                if (!corridorChairs.tryAcquire()) {
                    System.out.println("No chairs available. Student " + studentId + " leaves to program elsewhere.");
                    return false;
                }

                // Got a chair, wait for monitor
                System.out.println("Student " + studentId + " sits in the corridor.");
                synchronized (waitingQueue) {
                    waitingQueue.add(studentId);
                }
                studentsWaiting.release(); // Signal monitor that a student is waiting

                // Wait until it's this student's turn
                while (true) {
                    synchronized (waitingQueue) {
                        if (!waitingQueue.isEmpty() && waitingQueue.peek() == studentId) {
                            monitorAvailable.acquire();
                            waitingQueue.poll();
                            break;
                        }
                    }
                    Thread.sleep(100); // Small delay to prevent CPU hogging
                }

                corridorChairs.release(); // Free up the corridor chair
                System.out.println("Student " + studentId + " is now getting help from the monitor.");
                return true;
            } else {
                // Monitor was available directly
                System.out.println("Monitor is now helping Student " + studentId + ".");
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Called by students after receiving help
    public void finishHelp(int studentId) {
        System.out.println("Student " + studentId + " finished receiving help and leaves.");
        monitorAvailable.release();
    }

    // Used by monitor to check if there are waiting students
    public boolean hasWaitingStudents() {
        synchronized (waitingQueue) {
            return !waitingQueue.isEmpty();
        }
    }

    // Used by monitor to check if students waiting and take next one
    public void checkWaitingStudents() {
        try {
            if (hasWaitingStudents()) {
                // Wait for the signal that there's a student
                studentsWaiting.acquire();

                // The student will acquire the monitorAvailable semaphore
                // No need to do anything here as the student manages the queue
            } else {
                if (isMonitorSleeping) {
                    System.out.println("Monitor is sleeping.");
                } else {
                    System.out.println("No students waiting. Monitor goes to sleep.");
                    isMonitorSleeping = true;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
