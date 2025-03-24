# 🛋️ The Sleepy Monitor

## Team-workers:
- Esteban Gaviria Zambrano - A00396019
- Miguel González - A00395687
- Santiago Valencia García - A00395902

---

This project simulates a classic Java thread synchronization problem. A monitor (assistant) helps students with their programming tasks in an office with limited space. Students wait in a corridor with 3 chairs if the monitor is busy. If no chairs are available, they leave and return later.

## Program logic

- **Monitor**: Serves students on a first-come, first-served basis. If there are no students, sleeps.
- **Students**: Alternate between scheduling and seeking help from the monitor. If the monitor is busy, they wait in the hallway or leave if no chairs are available.
- **Synchronization**: Semaphores are used to control access to monitor and corridor chairs.

## Implementation

- **Main Classes**:
  - `SleepingMonitorProblem`: Start the simulation.
  - `Student`: Represents a student. Student Thread alternates between working and seeking help from the monitor. If the monitor is busy, the student waits in the corridor. If there are no chairs available, the student leaves.
  - `Monitor`: Represents the monitor. The monitor Thread just check if there are students in the queue and if so, it serves them. If there are no students, the monitor goes to sleep.
  - `MonitorOffice`: Handle the shared resources. It contains the monitor and the corridor chairs. It also contains the semaphores to control access to the monitor and the corridor chairs. It also contains the queue of students waiting to be served. The methods that expose are:
      - `requestHelp(int id)`: A student requests help from the monitor.
      - `finishHelp()`: The monitor finishes helping a student.
      - `checkWaitingStudents()`: The monitor checks if there are students waiting to be served.


- **Technologies**: Java, `Thread` and `Semaphores`.
