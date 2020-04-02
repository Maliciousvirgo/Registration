import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PatientService {

    private List<Patient> patientList;

    public boolean isRegistered(BigInteger pesel) {
        boolean isRegistered = false;

        for (Patient patient : patientList) {
            if (patient.getPesel().equals(pesel)) {
                isRegistered = Boolean.TRUE;
            }
        }
        return isRegistered;
    }

    public boolean isRegistered(String name, String surname) {
        boolean isRegistered = false;

        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) {
                isRegistered = Boolean.TRUE;
            }
        }
        return isRegistered;
    }

    public Patient findPatientOrNull(String name, String surname) {
        Patient patientTemp = null;

        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) {
                patientTemp = patient;
            }
        }
        return patientTemp;
    }

    public List<Patient>  findPatientlist(String name, String surname) {
        List<Patient> listaNowa = new ArrayList<>();
        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) {
                {
                    listaNowa.add(patient);
                    System.out.print(listaNowa.size() + ". ");
                    patient.wyswietlPodstawoweDane();
                    patient.wypiszDane();
                }
            }
        }
        return listaNowa;
    }

    public List<Patient>  findPatientlist(BigInteger pesel) {
        List<Patient> listaNowa = new ArrayList<>();
        for (Patient patient : patientList) {
            if (patient.getPesel().equals(pesel)) {
                {
                    listaNowa.add(patient);
                    System.out.print(listaNowa.size() + ". ");
                    patient.wyswietlPodstawoweDane();
                    patient.wypiszDane();
                }
            }
        }
        return listaNowa;
    }


    public Patient findPatientOrNull(BigInteger pesel) {
        Patient patientTemp = null;

        for (Patient patient : patientList) {
            if (patient.getPesel().equals(pesel)) {
                patientTemp = patient;
            }
        }

        return patientTemp;
    }

}