import wyjatki.TooManyPatientException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient("Jakub", "Dąbrowski", new BigInteger("99087666341"), 1123.0,"Zdrowy"));
        patientList.add(new Patient("Mikołaj", "Romanowski", new BigInteger("12345678910"), 320,"Chory"));
        patientList.add(new Patient("Jan", "Kowalski", new BigInteger("82345678910"), 700,"Chory"));

        ApachePOIExcelWrite apachePOIExcelWrite = new ApachePOIExcelWrite();

        apachePOIExcelWrite.createExcel(patientList);


    }
}