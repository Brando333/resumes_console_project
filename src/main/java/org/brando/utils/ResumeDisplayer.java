package org.brando.utils;

import org.brando.model.Resume;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ResumeDisplayer {
    private Resume resume;

    public ResumeDisplayer(Resume resume) {
        this.resume = resume;
    }

    public void display() {
        try {
            File file = ResumeWriter.write(resume);
            String command = STR."gedit \{file.getPath()}";

            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
