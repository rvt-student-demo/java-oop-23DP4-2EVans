package rvt.students;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Rūpējas par studentu datu saglabāšanu un ielādi no CSV faila.
 *
 * - Ja CSV fails neeksistē, tas tiks izveidots ar galveni.
 * - CSV rinda paredzēta kā: firstName,lastName,email,personalCode,registeredAt
 */
public class FileHandler {
    private final Path csvPath;
    private static final String HEADER = "firstName,lastName,email,personalCode,registeredAt";

    /**
     * @param filePath Ceļš līdz CSV failam (var būt relatīvs vai absolūts)
     */
    public FileHandler(String filePath) {
        this.csvPath = Paths.get(filePath);
    }

    /**
     * Nolasa visus studentus no CSV. Pirmā rinda tiek uzskatīta par galveni.
     * Ja faila nav, izveido jaunu ar galveni un atgriež tukšu sarakstu.
     * @return saraksts ar Student objektiem
     */
    public List<Student> loadAll() throws IOException {
        List<Student> list = new ArrayList<>();
        if (!Files.exists(csvPath)) {
            // Ja nav direktorijas, izveido tās
            if (csvPath.getParent() != null) Files.createDirectories(csvPath.getParent());
            try (BufferedWriter bw = Files.newBufferedWriter(csvPath, StandardCharsets.UTF_8)) {
                bw.write(HEADER);
                bw.newLine();
            }
            return list;
        }

        List<String> lines = Files.readAllLines(csvPath, StandardCharsets.UTF_8);
        boolean first = true;
        for (String line : lines) {
            if (first) { first = false; continue; } // izlaiž header rindu
            if (line.trim().isEmpty()) continue;    // izlaiž tukšas rindas
            Student s = Student.fromCSV(line);
            if (s != null) list.add(s);
        }
        return list;
    }

    /**
     * Saglabā visu studentu sarakstu CSV failā. Esošais saturs tiek pārrakstīts.
     * @param students saraksts ar Student objektiem
     */
    public void saveAll(List<Student> students) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(csvPath, StandardCharsets.UTF_8)) {
            bw.write(HEADER);
            bw.newLine();
            for (Student s : students) {
                bw.write(s.toCSV());
                bw.newLine();
            }
        }
    }
}

