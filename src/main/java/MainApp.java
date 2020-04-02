import wyjatki.TooManyPatientException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static Scanner scanner;
    private static PatientService patientService;
    private static List<Patient> patientList;
    private static ApachePOIExcelWrite apachePOIExcelWrite;

    public static void main(String[] args) {
        ApachePOIExcelRead apachePOIExcelRead = new ApachePOIExcelRead();
        apachePOIExcelWrite = new ApachePOIExcelWrite();
        patientList = apachePOIExcelRead.getPatientList();
        patientService = new PatientService(patientList);
        scanner = new Scanner(System.in);
        System.out.println("Wybierz akcje: \n0 - Zakończ działanie \n1 - Sprawdź czy pacjent jest zarejestrowany \n2 - Zarejestruj pacjenta \n3 - Usuń pacjenta \n4 - Wyszukaj pacjenta \n5 - Zakończ program");
        Integer action = scanner.nextInt();
        chooseTypeSearching(action);
    }

    private static void chooseTypeSearching(Integer action) {
        switch (action) {
            case 0:
                break;
            case 1:
                isRegistered();
                break;
            case 2:
                try {
                    registerPatient();
                } catch (TooManyPatientException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                deletingPatient();
                break;
            case 4:
                findPatientlist();
                break;
            case 5:
                System.exit(0);

        }
    }

    private static void deletingPatient() {
        System.out.println(patientList);
        Patient patientTemp = findPatient();
        patientList.remove(patientTemp);
        System.out.println(patientList);
        apachePOIExcelWrite.createExcel(patientList);
    }

    private static void registerPatient() throws TooManyPatientException {
        System.out.println("Podaj imię: ");
        String name = scanner.next();
        System.out.println("Podaj nazwisko: ");
        String surname = scanner.next();
        System.out.println("Podaj PESEL: ");
        BigInteger pesel = scanner.nextBigInteger();
        System.out.println("Podaj stan portfela: ");
        Double wallet = scanner.nextDouble();

        if (patientService.isRegistered(pesel)) {
            throw new TooManyPatientException();
        }
        patientList.add(new Patient(name, surname, pesel, wallet));
        apachePOIExcelWrite.createExcel(patientList);

        System.out.println("Udało się zarejestrować nowego pacjenta");
        System.out.println(patientList);
    }

    private static void isRegistered() {
        System.out.println("Sprawdź czy pacjent jest zarejestrowany po: \n0 - Zakończ działanie \n1 - imieniu i nazwisku \n2 - numerze PESEL");
        Integer action = scanner.nextInt();

        switch (action) {
            case 0:
                break;
            case 1:
                System.out.println("Podaj imię: ");
                String name = scanner.next();
                System.out.println("Podaj nazwisko: ");
                String surname = scanner.next();
                System.out.println(patientService.isRegistered(name, surname));
                break;
            case 2:
                System.out.println("Podaj pesel: ");
                BigInteger pesel = scanner.nextBigInteger();
                System.out.println(patientService.isRegistered(pesel));
                break;
            default:
                break;
        }
    }

    private static void findPatientlist() {
        System.out.println("Wyszukaj pacjenta po: \n0 - Zakończ działanie \n1 - imieniu i nazwisku \n2 - numerze PESEL");
        Integer action = scanner.nextInt();

        switch (action) {
            case 0:
                break;
            case 1:
                System.out.println("Podaj imię: ");
                String name = scanner.next();
                System.out.println("Podaj nazwisko: ");
                String surname = scanner.next();
                patientList = patientService.findPatientlist(name, surname);
                break;
            case 2:
                System.out.println("Podaj pesel: ");
                String pesel = scanner.next();
                patientList = patientService.findPatientlist(new BigInteger(pesel));
                break;

            System.out.println("Wybierz pacjenta : ");
            action = scanner.nextInt();
            Patient p = patientList.get(action - 1);
            edytujPacjenta(p);
        }
    }

    private static Patient findPatient() {
        System.out.println("Wyszukaj pacjenta po: \n0 - Zakończ działanie \n1 - imieniu i nazwisku \n2 - numerze PESEL");
        Integer action = scanner.nextInt();

        Patient patientTemp = null;

        switch (action) {
            case 0:
                break;
            case 1:
                System.out.println("Podaj imię: ");
                String name = scanner.next();
                System.out.println("Podaj nazwisko: ");
                String surname = scanner.next();
                patientTemp = patientService.findPatientOrNull(name, surname);
                break;
            case 2:
                System.out.println("Podaj pesel: ");
                String pesel = scanner.next();
                patientTemp = patientService.findPatientOrNull(new BigInteger(pesel));
                break;
            default:
                break;
        }
        return patientTemp;
    }

    private static void coronaTest() {
        System.out.println("Wyszukaj pacjenta po: \n0 - Zakończ działanie  \n1 - numerze PESEL");
        Integer action1 = scanner.nextInt();

        Patient patientTemp1 = null;

        switch (action1) {
            case 0:
                break;
            case 1:
                System.out.println("Podaj pesel: ");
                String pesel = scanner.next();
                patientTemp1 = patientService.findPatientOrNull(new BigInteger(pesel));
                break;
            default:
                break;
        }

    }

     public static void edytujPacjenta(Patient patient) {
         patient.wyswietlPodstawoweDane();
         System.out.println("Czy chcesz przeprowadzić test na koronawirusa ? ");
         System.out.println("1. Tak");
         System.out.println("2. Nie");
         int action = scanner.nextInt();
         if (action == 1) {
             coronaTest(patient);
         } else {
             chooseTypeSearching();
         }

     }
    }
