package org.brando.view;

import org.brando.controller.ResumeController;
import org.brando.exceptions.ExistingTittleException;
import org.brando.model.Resume;
import org.brando.model.User;
import org.brando.utils.ResumeDisplayer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.brando.view.HomeView.showHome;

public class ResumesView {
  private final static Scanner scanner = new Scanner(System.in);

  public static void showResumesPanel(User user) {

    List<Resume> resumesFromUser = ResumeController.listResumes(user.getId());

    System.out.println("Enter the number of resume and the action you want to do with it");
    System.out.println("E.g.: '1 see', '1 edit', '1 delete'");

    int counter = 1;
    for (Resume resume : resumesFromUser) {
      System.out.printf("[%s] %s [see][edit][delete]%n", counter, resume.getTittle());
      counter++;
    }
    String request = scanner.nextLine();

    if (request.contains("delete")) {
      int resumeSelected = Integer.parseInt(request.replaceAll("\\D+", ""));
      int index = resumeSelected - 1;
      Resume resumeTarget = resumesFromUser.get(index);
      ResumeController resumeController = new ResumeController(resumeTarget);
      resumeController.deleteResume();
      System.out.println("Resume deleted successfully");
      showResumesPanel(user);

    } else if (request.contains("edit")) {
      int resumeSelected = Integer.parseInt(request.replaceAll("\\D+", ""));
      int index = resumeSelected - 1;
      Resume resumeTarget = resumesFromUser.get(index);

      showResumesEdit(resumeTarget, user);
    } else if (request.contains("see")) {
      int resumeSelected = Integer.parseInt(request.replaceAll("\\D+", ""));
      int index = resumeSelected - 1;
      Resume resumeTarget = resumesFromUser.get(index);
      //            System.out.println(resumeTarget);
      ResumeDisplayer displayer = new ResumeDisplayer(resumeTarget);
      displayer.display();
      showResumesEdit(resumesFromUser.get(index), user);
    }

  }

  public static void showResumesEdit(Resume resume, User user) {

    System.out.println("Enter the number of field you want to modify");

    System.out.println("tittle\t\t\t[1]:".concat(STR."\t\{resume.getTittle()}"));
    System.out.println("full name\t\t[2]:".concat(STR."\t\{user.getFullName()}"));
    System.out.println("home address\t[3]:".concat(STR."\t\{resume.getAddress()}"));
    System.out.println("description\t\t[4]:".concat(STR."\t\{resume.getDescription()}"));
    System.out.println("certifications\t[5]:".concat(STR."\t\{resume.getCertifications()}"));
    System.out.println("social networks\t[6]:".concat(STR."\t\{resume.getSocialNetworks()}"));
    System.out.println("skills \t\t\t[7]:".concat(STR."\t\{resume.getSkills()}"));
    System.out.println("back to menu [0]:");
    String field = scanner.nextLine();

    ResumeController resumeController = new ResumeController(resume);

    switch (field) {
      case "title", "1" -> {
        String value;
        System.out.println("Enter title");
        value = scanner.nextLine();
        resumeController.updateTittle(value, Objects.requireNonNull(resumeController.getResume()).getId());
        resume.setTittle(value);
      }
      case "full name", "2" -> {
        String value;
        System.out.println("Enter full name");
        value = scanner.nextLine();


        resumeController.updateFullName(value, Objects.requireNonNull(resumeController.getResume()).getId());
        resume.setFullName(value);
      }

      case "home address", "3" -> {
        String value;
        System.out.println("Enter home address");
        value = scanner.nextLine();
        resumeController.updateAddress(value, Objects.requireNonNull(resumeController.getResume()).getId());
        resume.setAddress(value);
      }
      case "description", "4" -> {
        String value;
        System.out.println("Enter description");
        value = scanner.nextLine();
        resumeController.updateDescription(value, Objects.requireNonNull(resumeController.getResume()).getId());
        resume.setDescription(value);
      }
      case "certifications", "5" -> {
        String value;
        List<String> certifications = resume.getCertifications();
        System.out.println("Enter certification");
        value = scanner.nextLine();
        certifications.add(value);

        //for more values
        String answer;
        do {
          System.out.println("[1] add other certification");
          System.out.println("[0] go back");
          answer = scanner.nextLine();
          if (answer.equals("1")) {
            System.out.println("Enter certification");
            value = scanner.nextLine();
            certifications.add(value);
          }
        } while (answer.equals("1"));
        resumeController.updateCertifications(certifications.toString());
        resume.setCertifications(certifications);
      }
      case "social networks", "6" -> {


        List<String> socialNetworkApps = new ArrayList<>(resume.getSocialNetworks().keySet());
        List<String> socialNetworkUserLinks = new ArrayList<>(resume.getSocialNetworks().values());

        System.out.println("Enter social: e.g[LinkedIn, GitHub, WhatsApp, Facebook, etc]");
        String socialApp = scanner.nextLine();
        socialNetworkApps.add(socialApp);

        System.out.println(STR."Enter your\{socialApp} link: ");
        String socialLink = scanner.nextLine();
        socialNetworkUserLinks.add(socialLink);


        //for more values
        String answer;
        do {
          System.out.println("[1] add other social network");
          System.out.println("[0] go back");
          answer = scanner.nextLine();
          if (answer.equals("1")) {
            System.out.println("Enter social: e.g[LinkedIn, GitHub, WhatsApp, Facebook, etc]");
            socialApp = scanner.nextLine();
            socialNetworkApps.add(socialApp);

            System.out.println(STR."Enter your\{socialApp} link: ");
            socialLink = scanner.nextLine();
            socialNetworkUserLinks.add(socialLink);
          }


        } while (answer.equals("1"));

        Map<String, String> socialNetworks = IntStream.range(0, socialNetworkApps.size()).boxed().collect(Collectors.toMap(socialNetworkApps::get, socialNetworkUserLinks::get));


        resumeController.updateSocialNetworks(socialNetworks.toString());
        resume.setSocialNetworks(socialNetworks);
      }
      case "skills", "7" -> {
        List<String> skills = resume.getSkills();

        System.out.println("Enter skill");
        String skill = scanner.nextLine();
        skills.add(skill);
        //for more values
        String answer;
        do {
          System.out.println("[1] add other skill");
          System.out.println("[0] go back");
          answer = scanner.nextLine();
          if (answer.equals("1")) {
            System.out.println("Enter skill");
            skill = scanner.nextLine();
            skills.add(skill);
          }


        } while (answer.equals("1"));

        resumeController.updateSkills(skills.toString());
        resume.setSkills(skills);
      }
      case "0" -> {
        showHome(user);
      }
    }
    showResumesEdit(resume, user);

  }

  public static void showResumesCreate(User user) {
    System.out.println("Enter a tittle for the resume:");
    String tittle = scanner.nextLine();
    Resume resume = new Resume(tittle, user.getId());
    ResumeController resumeController = new ResumeController(resume);
    try {
      resumeController.create();
    } catch (ExistingTittleException e) {
      System.out.println(STR."The tittle \"\{tittle}\" already exists, choose another one");
      showResumesCreate(user);
    }
    showResumesEdit(resume, user);

  }
}
