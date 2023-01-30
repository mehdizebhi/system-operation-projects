
import java.util.concurrent.Semaphore;


public class Parking {
    
    private final int MAXIMUM_CAPACITY = 8;
    private Semaphore semaphore;

    public Parking() {
        this.semaphore = new Semaphore(MAXIMUM_CAPACITY, true);
    }
    
    public void entry(Patient patient) throws InterruptedException{
        System.out.println("Patient " + patient.getPatientName() + " Wants To Enter Parking");
        semaphore.acquire();
        patient.sleep(1);
        System.out.println("\u001B[32m" + "Patient " + patient.getPatientName() + " Entered Parking" + "\u001B[0m");
    }
    
    public void exit(Patient patient){
        semaphore.release();
        System.out.println("\033[0;31m" + "Patient " + patient.getPatientName() + " Left The Parking" + "\u001B[0m");
    }
    
    public int available(){
        return semaphore.availablePermits();
    }
}
