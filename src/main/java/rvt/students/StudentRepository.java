package rvt.students;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Vienkāršs repozitorijs studentu datu pārvaldībai.
 *
 * Uzglabā studentus atmiņā (sarakstā) un nodrošina persistance caur
 * {@link FileHandler}. Šeit ir metodes pievienošanai, meklēšanai,
 * dzēšanai un atjaunināšanai.
 */
public class StudentRepository {
    private final FileHandler fileHandler;
    private final List<Student> students = new ArrayList<>();

    /**
     * Inicializē repo un ielādē datus no norādītā CSV ceļa.
     * @param csvPath CSV faila ceļš
     */
    public StudentRepository(String csvPath) {
        this.fileHandler = new FileHandler(csvPath);
        try {
            students.addAll(fileHandler.loadAll());
        } catch (IOException e) {
            System.err.println("Neizdevās ielādēt CSV: " + e.getMessage());
        }
    }

    /** Atgriež jaunu saraksta kopiju ar visiem studentiem. */
    public List<Student> getAll() { return new ArrayList<>(students); }

    /**
     * Pievieno jaunu studentu un saglabā CSV.
     * @param s Student objekts
     */
    public void addStudent(Student s) throws IOException {
        students.add(s);
        fileHandler.saveAll(students);
    }

    /** Meklē studentu pēc personas koda. */
    public Student findByPersonalCode(String code) {
        for (Student s : students) {
            if (s.getPersonalCode().equals(code)) return s;
        }
        return null;
    }

    /** Pārbauda, vai e-pasts jau ir reģistrēts (case-insensitive). */
    public boolean isEmailTaken(String email) {
        for (Student s : students) if (s.getEmail().equalsIgnoreCase(email)) return true;
        return false;
    }

    /** Pārbauda, vai personas kods jau eksistē. */
    public boolean isPersonalCodeTaken(String code) {
        for (Student s : students) if (s.getPersonalCode().equals(code)) return true;
        return false;
    }

    /**
     * Dzēš studentu pēc personas koda un saglabā izmaiņas CSV.
     * @return true, ja dzēšana izdevās
     */
    public boolean removeByPersonalCode(String code) throws IOException {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getPersonalCode().equals(code)) {
                it.remove();
                fileHandler.saveAll(students);
                return true;
            }
        }
        return false;
    }

    /**
     * Atjaunina studenta laukus (ja nav tukšs parametris, tas tiek nomainīts).
     * Reģistrācijas laiks tiek atjaunināts uz pašreizējo brīdi.
     * @return true, ja atjaunināšana izdevās
     */
    public boolean updateStudent(String code, String newFirstName, String newLastName, String newEmail, String newPersonalCode) throws IOException {
        Student s = findByPersonalCode(code);
        if (s == null) return false;
        if (newFirstName != null && !newFirstName.isEmpty()) s.setFirstName(newFirstName);
        if (newLastName != null && !newLastName.isEmpty()) s.setLastName(newLastName);
        if (newEmail != null && !newEmail.isEmpty()) s.setEmail(newEmail);
        if (newPersonalCode != null && !newPersonalCode.isEmpty()) s.setPersonalCode(newPersonalCode);
        s.setRegisteredAt(LocalDateTime.now());
        fileHandler.saveAll(students);
        return true;
    }
}