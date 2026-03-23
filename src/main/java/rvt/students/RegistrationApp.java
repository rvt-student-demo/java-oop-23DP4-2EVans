package rvt.students;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Galvenā CLI lietotne studentu reģistrācijai.
 *
 * Nodrošina izvēlni un darbības: reģistrēt, rādīt, dzēst, rediģēt un iziet.
 * Komunikācija ar lietotāju notiek caur standarta ievadi/izvadi (console).
 */
public class RegistrationApp {
    private static final String CSV_PATH = "students.csv";

    /** Programmas sākumpunkts. */
    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository(CSV_PATH);
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Izvēle: ");
            String choice = sc.nextLine().trim().toLowerCase();
            switch (choice) {
                case "register":
                case "r":
                    handleRegister(repo, sc);
                    break;
                case "show":
                case "s":
                    handleShow(repo);
                    break;
                case "remove":
                case "rm":
                    handleRemove(repo, sc);
                    break;
                case "edit":
                case "e":
                    handleEdit(repo, sc);
                    break;
                case "exit":
                case "q":
                    running = false;
                    break;
                default:
                    System.out.println("Neatpazīta komanda. Izmanto: register/show/remove/edit/exit");
            }
        }
        sc.close();
        System.out.println("Programma apturēta.");
    }

    /** Izvada programmas izvēlni. */
    private static void printMenu() {
        System.out.println("\n=== Studentu reģistrācija ===");
        System.out.println("register (r) - reģistrēt jaunu lietotāju");
        System.out.println("show     (s) - rādīt visus lietotājus");
        System.out.println("remove   (rm) - dzēst lietotāju pēc personas koda");
        System.out.println("edit     (e) - rediģēt lietotāja datus pēc personas koda");
        System.out.println("exit     (q) - iziet");
    }

    /**
     * Apstrādā jauna studenta reģistrāciju: iekļauj validāciju un unikāluma pārbaudes.
     */
    private static void handleRegister(StudentRepository repo, Scanner sc) {
        try {
            System.out.print("Vārds: ");
            String first = sc.nextLine().trim();
            Validator.validateFirstName(first);

            System.out.print("Uzvārds: ");
            String last = sc.nextLine().trim();
            Validator.validateLastName(last);

            System.out.print("E-pasts: ");
            String email = sc.nextLine().trim();
            Validator.validateEmail(email);
            if (repo.isEmailTaken(email)) {
                System.out.println("Kļūda: šāds e-pasts jau ir reģistrēts.");
                return;
            }

            System.out.print("Personas kods: ");
            String pcode = sc.nextLine().trim();
            Validator.validatePersonalCode(pcode);
            if (repo.isPersonalCodeTaken(pcode)) {
                System.out.println("Kļūda: šāds personas kods jau eksistē.");
                return;
            }

            LocalDateTime now = LocalDateTime.now();
            Student s = new Student(first, last, email, pcode, now);
            repo.addStudent(s);
            System.out.println("Student reģistrēts: " + first + " " + last);
        } catch (IllegalArgumentException ex) {
            System.out.println("Ievades kļūda: " + ex.getMessage());
        } catch (IOException io) {
            System.out.println("Kļūda saglabājot datus: " + io.getMessage());
        }
    }

    /**
     * Izdrukā visus reģistrētos studentus tabulas formātā.
     */
    private static void handleShow(StudentRepository repo) {
        List<Student> list = repo.getAll();
        if (list.isEmpty()) {
            System.out.println("Nav reģistrētu studentu.");
            return;
        }
        String fmt = "%-15s %-15s %-25s %-12s %-20s\n";
        System.out.printf(fmt, "Vārds", "Uzvārds", "E-pasts", "Pers.kods", "Reģistrēts");
        System.out.println(new String(new char[90]).replace('\0', '-'));
        for (Student s : list) {
            System.out.printf(fmt,
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getPersonalCode(),
                s.getRegisteredAt().toString());
        }
    }

    /** Dzēš studentu pēc personas koda (ja atrasts). */
    private static void handleRemove(StudentRepository repo, Scanner sc) {
        try {
            System.out.print("Ievadi personas kodu, ko dzēst: ");
            String code = sc.nextLine().trim();
            if (repo.removeByPersonalCode(code)) {
                System.out.println("Lietotājs ar personas kodu " + code + " izdzēsts.");
            } else {
                System.out.println("Lietotājs ar personas kodu netika atrasts.");
            }
        } catch (IOException e) {
            System.out.println("Kļūda dzēšot: " + e.getMessage());
        }
    }

    /**
     * Rediģē lietotāja datus. Lietotājs var atstāt lauku tukšu, lai to nemainītu.
     */
    private static void handleEdit(StudentRepository repo, Scanner sc) {
        try {
            System.out.print("Ievadi personas kodu, kuru rediģēt: ");
            String code = sc.nextLine().trim();
            Student s = repo.findByPersonalCode(code);
            if (s == null) {
                System.out.println("Lietotājs nav atrasts.");
                return;
            }
            System.out.println("Atstāj tukšu lai nemainītu lauku.");
            System.out.print("Jauns vārds (" + s.getFirstName() + "): ");
            String first = sc.nextLine().trim();
            if (!first.isEmpty()) Validator.validateFirstName(first);

            System.out.print("Jauns uzvārds (" + s.getLastName() + "): ");
            String last = sc.nextLine().trim();
            if (!last.isEmpty()) Validator.validateLastName(last);

            System.out.print("Jauns e-pasts (" + s.getEmail() + "): ");
            String email = sc.nextLine().trim();
            if (!email.isEmpty()) {
                Validator.validateEmail(email);
                if (!email.equalsIgnoreCase(s.getEmail()) && repo.isEmailTaken(email)) {
                    System.out.println("Kļūda: e-pasts jau aizņemts.");
                    return;
                }
            }

            System.out.print("Jauns personas kods (" + s.getPersonalCode() + "): ");
            String pcode = sc.nextLine().trim();
            if (!pcode.isEmpty()) {
                Validator.validatePersonalCode(pcode);
                if (!pcode.equals(s.getPersonalCode()) && repo.isPersonalCodeTaken(pcode)) {
                    System.out.println("Kļūda: personas kods jau eksistē.");
                    return;
                }
            }

            boolean ok = repo.updateStudent(code,
                    first.isEmpty() ? null : first,
                    last.isEmpty() ? null : last,
                    email.isEmpty() ? null : email,
                    pcode.isEmpty() ? null : pcode);
            if (ok) System.out.println("Dati atjaunināti.");
            else System.out.println("Neizdevās atjaunināt.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Ievades kļūda: " + ex.getMessage());
        } catch (IOException io) {
            System.out.println("Kļūda saglabājot datus: " + io.getMessage());
        }
    }
}
