
import java.util.concurrent.Semaphore;

public class Office {

    private final int MAXIMUM_CAPACITY = 1;  //patient capacity
    private Semaphore semaphore;
    public Doctor doctor;

    public Clinic clinic;   // one to one

    public Office(Doctor doctor, Clinic clinic) {
        this.semaphore = new Semaphore(MAXIMUM_CAPACITY, true);
        this.doctor = doctor;
        this.clinic = clinic;
    }

    public void entry(Patient patient) throws InterruptedException {
        semaphore.acquire();
        patient.sleep(1);
        System.out.println("\033[0;34m" + "Patient " + patient.getPatientName() + " Entered Dr " + doctor.getDoctorName() + " Office" + "\033[0m");
        doctor.visit(patient);     //doctor visited patient
    }

    public void exit(Patient patient) {
        semaphore.release();
        System.out.println("\033[0;33m" + "Patient " + patient.getPatientName() + " Left The Dr " + doctor.getDoctorName() + " Office" + "\033[0m");
    }

    public int available() {
        return semaphore.availablePermits();
    }
}
