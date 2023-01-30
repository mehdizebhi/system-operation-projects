
import java.util.concurrent.Semaphore;

public class Lobby {

    private final int MAXIMUM_CAPACITY = 2;
    private Semaphore semaphore;
    
    public Clinic clinic;        // one to one

    public Lobby(Clinic clinic) {
        this.semaphore = new Semaphore(MAXIMUM_CAPACITY, true);
        this.clinic = clinic;
    }
    
    public void entry(Patient patient) throws InterruptedException{
        semaphore.acquire();
        patient.sleep(1);
//        System.out.println("Patient " + patient.getPatientName() + " Entered Clinic Lobby Of Storey No." + clinic.storey.getStoreyName());
    }
    
    public void exit(Patient patient){
        semaphore.release();
//        System.out.println("Patient " + patient.getPatientName() + " Left The Clinic Lobby Of Storey No." + clinic.storey.getStoreyName());
    }
    
    public int available(){
        return semaphore.availablePermits();
    }

}
