# 🛋️ El Monitor Dormilón

## Integrantes:
- Esteban Gaviria Zambrano - A00396019
- Miguel González - A00395687
- Santiago Valencia García - A00395902

---

Este proyecto simula un problema clásico de sincronización de hilos en Java. Un monitor (asistente) ayuda a estudiantes con sus tareas de programación en una oficina con espacio limitado. Los estudiantes esperan en un corredor con 3 sillas si el monitor está ocupado. Si no hay sillas disponibles, se van y regresan más tarde.

## Lógica del programa

- **Monitor**: Atiende a los estudiantes en orden de llegada. Si no hay estudiantes, duerme.
- **Estudiantes**: Alternan entre programar y buscar ayuda del monitor. Si el monitor está ocupado, esperan en el corredor o se van si no hay sillas disponibles.
- **Sincronización**: Se usan semáforos para controlar el acceso al monitor y las sillas del corredor.

## Implementación

- **Clases principales**:
  - `SleepingMonitorProblem`: Inicia la simulación.
  - `Student`: Representa a un estudiante.
  - `Monitor`: Representa al asistente.
  - `MonitorOffice`: Gestiona los recursos compartidos.

- **Tecnologías**: Java, hilos (`Thread`) y semáforos (`Semaphore`).

## Condición de parada

Cada estudiante solicita ayuda un número determinado de veces (`maxHelpRequests`). El programa termina cuando todos los estudiantes han recibido ayuda.