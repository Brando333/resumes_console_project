package org.brando.app;

import org.brando.model.Resume;
import org.mindrot.jbcrypt.BCrypt;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> certifications = Arrays.asList("certificacion java", "certificacion cisco", "certifacion algoritmos");
        Map<String, String> socialNetworks = new HashMap<>();
        socialNetworks.put("Facebook", "Brando Calderon");
        socialNetworks.put("Twitter", "BrandoPalafox123");
        socialNetworks.put("WhatsApp", "(+51)912 880 417");

        Resume resume = new Resume("Brando resume test", "Brando Jeanpier", "Mi casa", "trabajo", certifications, socialNetworks, null, 1);

//        String certificationsString = certifications.toString();
        String socialString = socialNetworks.toString();
//        List<String> certificationsCopy = new ArrayList<>(Arrays.asList(certificationsString.replaceAll("[\\[\\]]", "").split(", ")));
        Map<String, String> socialNetworksCopy = Arrays.stream(socialString.substring(1, socialString.length() - 1).split(", ")).map(i -> i.split("=")).collect(Collectors.toMap(e -> e[0], e -> e[1]));


    }
}