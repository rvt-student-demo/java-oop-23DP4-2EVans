package rvt.students;

import java.util.regex.Pattern;

/**
 * Validator klase satur vienkāršas validācijas metodes ievaddatiem.
 *
 * Šeit izmantotas regulārās izteiksmes (RegEx) lai pārbaudītu:
 * - vārda un uzvārda pareizību (tikai burti, min garums 3)
 * - e-pasta formātu (vienkārša pārbaude, ne RFC pilnība)
 * - personas koda formātu (cipari, garums 6-12)
 *
 * Ja ievade nav derīga, metodes izmet IllegalArgumentException ar ziņojumu latviešu valodā.
 */
public class Validator {
    // Atļaut Unicode burtus ar \p{L}, vismaz 3 simboli
    private static final Pattern NAME_PATTERN = Pattern.compile("^[\\p{L}]{3,}$");
    // Vienkārša e-pasta pārbaude: lietotājs@domēns.tld
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    // Personas kods sastāv no cipariem, garums no 6 līdz 12
    private static final Pattern PERSONAL_CODE_PATTERN = Pattern.compile("^[0-9]{6,12}$");

    /** Pārbauda vārdu; ja neder, met IllegalArgumentException. */
    public static void validateFirstName(String name) {
        if (name == null || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Vārds jābūt tikai burtiem un vismaz 3 simboli.");
        }
    }

    /** Pārbauda uzvārdu; ja neder, met IllegalArgumentException. */
    public static void validateLastName(String name) {
        if (name == null || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Uzvārds jābūt tikai burtiem un vismaz 3 simboli.");
        }
    }

    /** Pārbauda e-pasta formātu; ja neder, met IllegalArgumentException. */
    public static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Nederīgs e-pasta formāts.");
        }
    }

    /** Pārbauda personas kodu; ja neder, met IllegalArgumentException. */
    public static void validatePersonalCode(String code) {
        if (code == null || !PERSONAL_CODE_PATTERN.matcher(code).matches()) {
            throw new IllegalArgumentException("Personas kodam jābūt tikai cipariem (6-12 cipari).");
        }
    }
}
