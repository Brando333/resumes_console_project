package org.brando.controller;

import org.brando.data.DBConnection;
import org.brando.model.Resume;
import org.brando.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResumeController {
    private final String CREATE_QUERY = "INSERT INTO resume (tittle, fullName, address, description,certifications, socialNetworks, skills, id_user) VALUES (?, ?, ?, ?,?,?,?,?)";
    private final String SELECT_RESUMES_QUERY = "SELECT * FROM resume WHERE  id = ?";
    private final String UPDATE_RESUME_TITTLE = "UPDATE resume SET tittle = ? WHERE id = ?";
    private final String UPDATE_RESUME_FULL_NAME = "UPDATE resume SET fullName = ? WHERE id = ?";
    private final String UPDATE_RESUME_ADDRESS = "UPDATE resume SET address = ? WHERE id = ?";
    private final String UPDATE_RESUME_DESCRIPTION = "UPDATE resume SET description = ? WHERE id = ?";
    private final String UPDATE_RESUME_CERTIFICATIONS = "UPDATE resume SET certifications = ? WHERE id = ?";
    private final String UPDATE_RESUME_SOCIAL_NETWORKS = "UPDATE resume SET socialNetworks = ? WHERE id = ?";
    private final String UPDATE_RESUME_SKILLS = "UPDATE resume SET skills = ? WHERE id = ?";
    private final String DELETE_RESUME = "DELETE FROM resume WHERE  id = ?";

    private Resume resume;
    private Connection connection = DBConnection.getConnection();

    public ResumeController(Resume resume) {
        this.resume = resume;
    }

    public void create() {
        try {
            PreparedStatement pst = connection.prepareStatement(CREATE_QUERY);
            pst.setString(1, resume.getTittle());
            pst.setString(2, resume.getFullName());
            pst.setString(3, resume.getAddress());
            pst.setString(4, resume.getDescription());
            pst.setString(5, resume.getCertifications().toString());
            pst.setString(6, resume.getSocialNetworks().toString());
            pst.setString(7, resume.getSkills().toString());
            pst.setInt(8, resume.getId_user());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTittle(String newTittle) {
        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_TITTLE);
            pst.setString(1, newTittle);
            pst.setInt(2, resume.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFullName(String newFullName) {
        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_FULL_NAME);
            pst.setString(1, newFullName);
            pst.setInt(2, resume.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAddress(String newAddress) {
        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_ADDRESS);
            pst.setString(1, newAddress);
            pst.setInt(2, resume.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDescription(String newDescription) {
        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_DESCRIPTION);
            pst.setString(1, newDescription);
            pst.setInt(2, resume.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCertifications(String newCertifications) {

        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_CERTIFICATIONS);
            pst.setString(1, newCertifications);
            pst.setInt(2, resume.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSocialNetworks(String newSocialNetworks) {
        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_SOCIAL_NETWORKS);
            pst.setString(1, newSocialNetworks);
            pst.setInt(2, resume.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSkills(String newSkills) {
        try {

            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_SKILLS);
            pst.setString(1, newSkills);
            pst.setInt(2, resume.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteResume() {
        try {

            PreparedStatement pst = connection.prepareStatement(DELETE_RESUME);
            pst.setInt(1, resume.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
