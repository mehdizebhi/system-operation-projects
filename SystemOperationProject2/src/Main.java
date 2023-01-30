
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        List<Doctor> doctors = generateDoctors(3);
        DoctorsBuilding doctorsBuilding = new DoctorsBuilding(doctors);
        
        System.out.print("Patients Number: ");
        int numberOfPatients = input.nextInt();
        
        List<Patient> patients = generatePatients(numberOfPatients, doctorsBuilding);
        
        for(Patient patient : patients){
            patient.start();
        }
        
    }
    
    public static List<Doctor> generateDoctors(int numberOfDoctors){
        List<Doctor> doctors = new ArrayList<>();
        for(int i = 1; i <= numberOfDoctors; i++){
            doctors.add(new Doctor("No." + String.valueOf(i)));
        }
        return doctors;
    }
    
    public static List<Patient> generatePatients(int numberOfPatients, DoctorsBuilding doctorsBuilding){
        List<Patient> patients = new ArrayList<>();
        for(int i = 1; i <= numberOfPatients; i++){
            patients.add(new Patient("No." + String.valueOf(i), doctorsBuilding));
        }
        return patients;
    } 
    
    
}
