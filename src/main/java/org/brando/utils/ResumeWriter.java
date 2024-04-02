package org.brando.utils;

import org.brando.model.Resume;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResumeWriter {


    public static File write(Resume resume) {

        try {

            String tittleNoWhiteSpaces = resume.getTittle().replaceAll("\\s+", "");
            String fileSeparator = File.separator;
            String extension = ".txt";
            String path = STR."resumeTemp\{fileSeparator}\{tittleNoWhiteSpaces}\{extension}";

            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String resumeInfo = getInfo(resume);

            bufferedWriter.write(resumeInfo);
            bufferedWriter.newLine();

            bufferedWriter.close();
            return file;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static String getInfo(Resume resume) {
        return STR."""
                                 \{resume.getTittle()}

                    Nombre:     \{resume.getFullName()}
                    _____________________________________________________________
                    \{resume.getDescription()}
                    =============================================================
                    Dirección:  \{resume.getAddress()}

                    Contacto:   \{resume.getSocialNetworks()}

                    Certificaciones:   \{resume.getCertifications()}

                    Habilidades:   \{resume.getSkills()}

                """;
    }
}
