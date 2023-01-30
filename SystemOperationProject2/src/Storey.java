
public class Storey {
    
    private String storeyName;
    public Clinic clinic;
    public Corridor corridor;

    public Storey(Doctor doctor,String storeyName) {
        this.clinic = new Clinic(doctor, this);
        this.corridor = new Corridor(this);
        this.storeyName = storeyName;
    }
    

    /*
    getter & setter
    */
    public String getStoreyName() {
        return storeyName;
    }

    public void setStoreyName(String storeyName) {
        this.storeyName = storeyName;
    }
    
    
}
