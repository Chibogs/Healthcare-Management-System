import java.util.*;
public class Medical_InformationSystem {
    public static User login(String role) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (role.equalsIgnoreCase("Patient")) {
            return new Patient(id, name, age);
        } else if (role.equalsIgnoreCase("Doctor")) {
            return new Doctor(id, name, age);
        } else if (role.equalsIgnoreCase("Nurse")) {
            return new Nurse(id, name, age);
        } else {
            throw new IllegalArgumentException("Invalid role");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Medical Information System");
        System.out.print("Login as (Patient/Doctor/Nurse): ");
        String role = scanner.nextLine();
        User user = login(role);

        if (user instanceof Patient) {
            Patient patient = (Patient) user;
            while (true) {
                System.out.println("\n1. View Test Results");
                System.out.println("2. View Prescriptions");
                System.out.println("3. View Billing Info");
                System.out.println("4. View Schedule");
                System.out.print("Enter choice: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        patient.viewTestResults();
                        break;
                    case "2":
                        patient.viewPrescriptions();
                        break;
                    case "3":
                        patient.viewBillingInfo();
                        break;
                    case "4":
                        patient.viewSchedule();
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } else if (user instanceof Doctor) {
            Doctor doctor = (Doctor) user;
            while (true) {
                System.out.println("\n1. Add Patient");
                System.out.println("2. Choose Patient");
                System.out.print("Enter choice: ");
                String choice = scanner.nextLine();

                if (choice.equals("1")) {
                    System.out.print("Patient ID: ");
                    String patientId = scanner.nextLine();
                    System.out.print("Patient Name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Patient Age: ");
                    int patientAge = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    Patient patient = new Patient(patientId, patientName, patientAge);
                    doctor.addPatient(patient);
                } else if (choice.equals("2")) {
                    try{
                        Patient selectedPatient = doctor.choosePatient();
                        while (true) {
                            System.out.println("\n1. Create Prescription");
                            System.out.println("2. Create Test Result");
                            System.out.println("3. View Test Results");
                            System.out.println("4. View Prescriptions");
                            System.out.println("5. Compute Billing");
                            System.out.println("6. Schedule Appointment");
                            System.out.print("Enter choice: ");
                            String subChoice = scanner.nextLine();

                            switch (subChoice) {
                                case "1":
                                    doctor.createPrescription(selectedPatient);
                                    break;
                                case "2":
                                    doctor.createTestResult(selectedPatient);
                                    break;
                                case "3":
                                    doctor.viewTestResults(selectedPatient);
                                    break;
                                case "4":
                                    doctor.viewPrescriptions(selectedPatient);
                                    break;
                                case "5":
                                    doctor.computeBilling(selectedPatient);
                                    break;
                                case "6":
                                    doctor.scheduleAppointment(selectedPatient);
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Non existing Patient!");
                    }
                } else {
                    System.out.println("Invalid choice");
                }
            }
        } else if (user instanceof Nurse) {
            Nurse nurse = (Nurse) user;
            while (true) {
                System.out.println("\n1. Add Patient");
                System.out.println("2. Choose Patient");
                System.out.print("Enter choice: ");
                String choice = scanner.nextLine();

                if (choice.equals("1")) {
                    System.out.print("Patient ID: ");
                    String patientId = scanner.nextLine();
                    System.out.print("Patient Name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Patient Age: ");
                    int patientAge = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    Patient patient = new Patient(patientId, patientName, patientAge);
                    nurse.addPatient(patient);
                } else if (choice.equals("2")) {
                    try {
                        Patient selectedPatient = nurse.choosePatient();
                        while (true) {
                            System.out.println("\n1. View Test Results");
                            System.out.println("2. View Prescriptions");
                            System.out.println("3. Compute Billing");
                            System.out.println("4. Schedule Appointment");
                            System.out.print("Enter choice: ");
                            String subChoice = scanner.nextLine();

                            switch (subChoice) {
                                case "1":
                                    nurse.viewTestResults(selectedPatient);
                                    break;
                                case "2":
                                    nurse.viewPrescriptions(selectedPatient);
                                    break;
                                case "3":
                                    nurse.computeBilling(selectedPatient);
                                    break;
                                case "4":
                                    nurse.scheduleAppointment(selectedPatient);
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                            }
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Non existing Patient!");
                    }
                } else {
                    System.out.println("Invalid choice");
                }
            }
        }
    }
}