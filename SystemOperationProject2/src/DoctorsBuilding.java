
import java.util.ArrayList;
import java.util.List;

public class DoctorsBuilding {
    
    public Parking parking;
    public List<Storey> storeies;

    public DoctorsBuilding(List<Doctor> doctors) {
        this.parking = new Parking();
        this.storeies = new ArrayList<>();
        int count = 1;
        for(Doctor doctor : doctors){
            this.storeies.add(new Storey(doctor, String.valueOf(count)));
            count++;
        }
    }
    
}
