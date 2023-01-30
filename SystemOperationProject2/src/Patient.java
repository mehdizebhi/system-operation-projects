
public class Patient extends Thread {

    private String patientName;
    private DoctorsBuilding doctorsBuilding;

    public Patient() {
    }

    public Patient(String patientName, DoctorsBuilding doctorsBuilding) {
        this.patientName = patientName;
        this.doctorsBuilding = doctorsBuilding;
    }

    public void run() {
        try {
            visitingDoctor();
        } catch (InterruptedException ex) {
            System.err.println("Thread-" + this.patientName + " Interrupted Exception");
        }
    }

    private void visitingDoctor() throws InterruptedException {

        this.doctorsBuilding.parking.entry(this);
        Storey selectedStorey = choosingDoctor();
        if (selectedStorey != null) {

            selectedStorey.corridor.entry(this);
            selectedStorey.clinic.lobby.entry(this);
            selectedStorey.clinic.office.entry(this);
            
            selectedStorey.clinic.office.exit(this);
            selectedStorey.clinic.lobby.exit(this);
            selectedStorey.corridor.exit(this);
        }
        this.doctorsBuilding.parking.exit(this);
        
    }

    private Storey choosingDoctor() {
        int max = -999999;
        Storey mostSecludedStorey = null;
        for (Storey storey : doctorsBuilding.storeies) {
            if (storey.clinic.lobby.available() + storey.corridor.available() > max) {
                max = storey.clinic.lobby.available() + storey.corridor.available();
                mostSecludedStorey = storey;
            }
        }
        return mostSecludedStorey;
    }


    /*
    getter & setter
     */
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public DoctorsBuilding getDoctorsBuilding() {
        return doctorsBuilding;
    }

    public void setDoctorsBuilding(DoctorsBuilding doctorsBuilding) {
        this.doctorsBuilding = doctorsBuilding;
    }

}
