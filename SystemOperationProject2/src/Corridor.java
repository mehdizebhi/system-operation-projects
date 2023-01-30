
import java.util.concurrent.Semaphore;

public class Corridor {
 
    private final int MAXIMUM_CAPACITY = 10;
    private Semaphore semaphore;
    
    public Storey storey;    //one to one

    public Corridor(Storey storey) {
        this.semaphore = new Semaphore(MAXIMUM_CAPACITY, true);
        this.storey = storey;
    }
    
    public void entry(Patient patient) throws InterruptedException{
        semaphore.acquire();
        patient.sleep(1);
//        System.out.println("Patient " + patient.getPatientName() + " Entered Corridor Of Storey No." + storey.getStoreyName());
    }
    
    public void exit(Patient patient){
        semaphore.release();
//        System.out.println("Patient " + patient.getPatientName() + " Left The Corridor Of Storey No." + storey.getStoreyName());
    }
    
    public int available(){
        return semaphore.availablePermits();
    }
}
