package rvt.students;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Studenta datu modelis.
 *
 * Šī klase satur visu nepieciešamo informāciju par studentu: vārdu, uzvārdu,
 * e-pastu, personas kodu un reģistrācijas laiku. Ir nodrošinātas metodes
 * CSV rindu ģenerēšanai un objekta atjaunošanai no CSV rindas.
 *
 * Komentāri latviešu valodā: metodes nosaukumi un lauki ir pašsaprotami,
 * papildus validācija jāveic ārpus šīs klases (piem., Validator klasē).
 */
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String personalCode;
    private LocalDateTime registeredAt;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Konstruktoru izmanto, lai izveidotu Student objektu ar visiem laukiem.
     * @param firstName Studenta vārds
     * @param lastName Studenta uzvārds
     * @param email Studenta e-pasts
     * @param personalCode Studenta personas kods
     * @param registeredAt Reģistrācijas datums un laiks
     */
    public Student(String firstName, String lastName, String email, String personalCode, LocalDateTime registeredAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.personalCode = personalCode;
        this.registeredAt = registeredAt;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPersonalCode() { return personalCode; }
    public LocalDateTime getRegisteredAt() { return registeredAt; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPersonalCode(String personalCode) { this.personalCode = personalCode; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }

    /**
     * Pārveido studentu uz CSV rindu. Lai izvairītos no papildu komatiem,
     * komati laukos tiek aizvietoti ar atstarpi.
     * @return CSV rinda ar laukiem atdalītiem ar komatu
     */
    public String toCSV() {
        // Aizvieto komatus laukos, lai vienkāršotu CSV formātu
        String f = firstName.replace(',', ' ');
        String l = lastName.replace(',', ' ');
        String e = email.replace(',', ' ');
        String p = personalCode.replace(',', ' ');
        return String.join(",", f, l, e, p, registeredAt.format(FORMATTER));
    }

    /**
     * Izveido Student objektu no CSV rindas. Sagaida formātu ar 5 laukiem:
     * firstName,lastName,email,personalCode,registeredAt
     * @param line CSV rinda
     * @return Student objekts vai null, ja rinda nav derīga
     */
    public static Student fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length < 5) return null;
        String f = parts[0].trim();
        String l = parts[1].trim();
        String e = parts[2].trim();
        String p = parts[3].trim();
        String r = parts[4].trim();
        java.time.LocalDateTime dt = LocalDateTime.parse(r, FORMATTER);
        return new Student(f, l, e, p, dt);
    }
}