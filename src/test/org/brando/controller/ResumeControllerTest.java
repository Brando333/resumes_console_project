package org.brando.controller;

import org.brando.exceptions.ExistingTittleException;
import org.brando.model.Resume;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResumeControllerTest {


    @Test
    public void update() {
        ResumeController rc = new ResumeController(new Resume("", 2));
        String name = "name updated";
        int updatedRows = rc.updateFullName(name); //4 is the value of an existing test resume in the data base.
        assertEquals(1, updatedRows);

        String address = "address updated";
        updatedRows = rc.updateAddress(address); //4 is the value of an existing test resume in the database.
        assertEquals(1, updatedRows);

        String description = "description updated";
        updatedRows = rc.updateDescription(description); //4 is the value of an existing test resume in the database.
        assertEquals(1, updatedRows);

        String certifications = "certifications updated";
        updatedRows = rc.updateCertifications(certifications); //4 is the value of an existing test resume in the database.
        assertEquals(1, updatedRows);

        String socials = "socials updated";
        updatedRows = rc.updateSocialNetworks(socials); //4 is the value of a existing test resume in the database.
        assertEquals(1, updatedRows);

        String skills = "skills updated";
        updatedRows = rc.updateSkills(skills); //4 is the value of a existing test resume in the database.
        assertEquals(1, updatedRows);


    }

    @Test
    public void listResumes() {

        List<Resume> resumeList = ResumeController.listResumes(2); //2 is the id of the existing test user
        resumeList.forEach(System.out::println);
    }


    @Test
    public void createResume() throws ExistingTittleException {
        Resume resume = new Resume(Utils.getRandomTitle("test"), 2);
        ResumeController resumeController = new ResumeController(resume);
        resumeController.create();
    }

    @Test
    public void createResumeWithTittleExisting() throws ExistingTittleException {
        //todo
        Resume resume = new Resume("test2023-10-04T04:43:32.411Z", 2);
        ResumeController resumeController = new ResumeController(resume);
        resumeController.create();
    }


}