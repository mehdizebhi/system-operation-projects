
public class Clinic {
    
    public Office office;
    public Lobby lobby;
    
    public Storey storey;    //one to one

    public Clinic(Doctor doctor, Storey storey) {
        this.office = new Office(doctor, this);
        this.lobby = new Lobby(this);
        this.storey = storey;
    }
}
