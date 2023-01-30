
import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private final int VISIT_TIME = 100;  //millis
    private String doctorName;
    private List<Patient> allPatients;

    public Doctor() {
    }

    public Doctor(String doctorName) {
        this.doctorName = doctorName;
        this.allPatients = new ArrayList<>();
    }


    public void visit(Patient patient) {
        try {
            addPatient(patient);
            patient.sleep(VISIT_TIME);
            System.out.println("\033[0;35m" + "Dr " + this.doctorName + " Visit Patient " + patient.getPatientName() + "\u001B[0m");
        } catch (InterruptedException ex) {
            System.err.println("Thread-" + this.doctorName + " Interrupted Exception");
        }
    }

    public void addPatient(Patient patient) {
        this.allPatients.add(patient);
    }

    /*
    getter & setter
     */
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public List<Patient> getAllPatients() {
        return allPatients;
    }

    public void setAllPatients(List<Patient> allPatients) {
        this.allPatients = allPatients;
    }

}
