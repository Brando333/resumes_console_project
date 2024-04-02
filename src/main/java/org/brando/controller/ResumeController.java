package org.brando.controller;

import org.brando.data.DBConnection;
import org.brando.exceptions.ExistingTittleException;
import org.brando.model.Resume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ResumeController {

    private static final Connection connection = DBConnection.getConnection();
    private static Resume resume;
    private final String GET_ID_QUERY = "SELECT id FROM resume WHERE  id_user = ? AND tittle = ?";

    public ResumeController(Resume resume) {
        ResumeController.resume = resume;
    }

    /**
     * Returns a list with all the resumes of a user
     *
     * @param idUser: the id of the user to show all of the belonging resumes
     * @return {@code List<Resume>}
     */
    public static List<Resume> listResumes(int idUser) {
        List<Resume> resumeList = new ArrayList<>();
        try {
            String SELECT_RESUMES_QUERY = "SELECT * FROM resume WHERE  id_user = ?";
            PreparedStatement pst = connection.prepareStatement(SELECT_RESUMES_QUERY);
            pst.setInt(1, idUser);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tittle = rs.getString("tittle");
                String fullName = rs.getString("fullName");
                String address = rs.getString("address");
                String description = rs.getString("description");
                String rawCertificationsString = rs.getString("certifications");
                String rawSkillsString = rs.getString("skills");
                String rawSocialNetworksString = rs.getString("socialNetworks");

                int user_id = rs.getInt("id_user");

                resumeList.add(
                        new Resume(
                                id, tittle, fullName, address, description, toList(
                                rawCertificationsString
                        ), toMap(
                                rawSocialNetworksString
                        ), toList(
                                rawSkillsString
                        ), user_id
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resumeList;
    }

    private static List<String> toList(String raw) {
        /*removes the "[" & "]" of the list string formatted*/
        String cleaned = raw.substring(1, raw.length() - 1);
        return new ArrayList<>(Arrays.asList(cleaned.split(", ")));
    }


    private static Map<String, String> toMap(String raw) {
        /*removes the "{" & "}" of the map string formatted*/
        String cleaned = raw.substring(1, raw.length() - 1);
        return Arrays.stream(cleaned.split(", ")).map(kv -> kv.split("="))
                .filter(kv -> kv.length == 2) //just to assure we have a key and value in each array, and no enter incorrect data to the db
                .collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));
    }

    /**
     * Returns the resume instance which this controller was created
     */
    public Resume getResume() {

        Resume resume;
        try {
            String SELECT_RESUME_QUERY = "SELECT * FROM resume WHERE  id_user = ? AND tittle = ?";
            PreparedStatement pst = connection.prepareStatement(SELECT_RESUME_QUERY);
            pst.setInt(1, ResumeController.resume.getUserId());
//            System.out.println("ResumeController.resume.getUserId() = " + ResumeController.resume.getUserId());
            pst.setString(2, ResumeController.resume.getTittle());
//            System.out.println("ResumeController.resume.getTittle() = " + ResumeController.resume.getTittle());

            ResultSet rs = pst.executeQuery();
            rs.next();

            int id = rs.getInt("id");
            String tittle = rs.getString("tittle");
            String fullName = rs.getString("fullName");
            String address = rs.getString("address");
            String description = rs.getString("description");
            String rawCertificationsString = rs.getString("certifications");
            String rawSocialNetworksString = rs.getString("socialNetworks");
            String rawSkillsString = rs.getString("skills");
            resume = new Resume(id, tittle, fullName, address, description, toList(rawCertificationsString), toMap(rawSocialNetworksString), toList(rawSkillsString), ResumeController.resume.getUserId());
            return resume;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create() throws ExistingTittleException {
        try {
            String tittle = resume.getTittle();
            int idUser = resume.getUserId();
            if (!isTittleRepeatedForUser(tittle, idUser)) {
                String CREATE_QUERY = "INSERT INTO resume (tittle, fullName, address, description,certifications, socialNetworks, skills, id_user) VALUES (?, ?, ?, ?,?,?,?,?)";

                String fullName = UserController.getUser(idUser).getFullName();
                resume.setFullName(fullName);

                PreparedStatement pst = connection.prepareStatement(CREATE_QUERY);
                pst.setString(1, tittle);
                pst.setString(2, resume.getFullName());
                pst.setString(3, resume.getFullName()); //
                pst.setString(4, resume.getDescription());
                pst.setString(5, Optional.ofNullable(resume.getCertifications()).orElse(new ArrayList<>()).toString());
                pst.setString(6, Optional.ofNullable(resume.getSocialNetworks()).orElse(new HashMap<>()).toString());
                pst.setString(7, Optional.ofNullable(resume.getSkills()).orElse(new ArrayList<>()).toString());
                pst.setInt(8, idUser);
                pst.executeUpdate();
            } else {
                System.out.println("The tittle : " + tittle + " already belong to a resume");
                throw new ExistingTittleException("This tittle already exists for user" + idUser);
            }


        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    /*
     * Return true if the tittle already exists in the table(which we shouldn´t allow)
     * Return false if the tittle does not exist already for a user
     */

    private boolean isTittleRepeatedForUser(String tittle, int idUser) {
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM resume WHERE tittle COLLATE utf8mb4_bin = ? AND id_user = ?");
            pst.setString(1, tittle);
            pst.setInt(2, idUser);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            throw new RuntimeException(e);
        }
    }


    public int updateTittle(String newTittle, int id) {
        try {

            String UPDATE_RESUME_TITTLE = "UPDATE resume SET tittle = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_TITTLE);
            pst.setString(1, newTittle);
            pst.setInt(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Updates the full name field of the resume in the data base
     *
     * @param newFullName: the new value of the full name.
     * @param id:          the id of the target resume
     * @return the numbers of rows updated, the result should be 1 if updated and 0 if not.
     **/
    public int updateFullName(String newFullName, int id) {
        try {
            String UPDATE_RESUME_FULL_NAME = "UPDATE resume SET fullName = ? WHERE id = ? AND id_user = ?";
            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_FULL_NAME);
            pst.setString(1, newFullName);
            pst.setInt(2, id);
            pst.setInt(3, resume.getUserId());
            return pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateAddress(String newAddress, int id) {
        try {
            String UPDATE_RESUME_ADDRESS = "UPDATE resume SET address = ? WHERE id = ? AND id_user = ?";
            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_ADDRESS);
            pst.setString(1, newAddress);
            pst.setInt(2, id);
            pst.setInt(3, resume.getUserId());
            return pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateDescription(String newDescription, int id) {
        try {
            String UPDATE_RESUME_DESCRIPTION = "UPDATE resume SET description = ? WHERE id = ? AND id_user = ?";
            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_DESCRIPTION);
            pst.setString(1, newDescription);
            pst.setInt(2, id);
            pst.setInt(3, resume.getUserId());
            return pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateCertifications(String newCertifications) {
        try {
            String UPDATE_RESUME_CERTIFICATIONS = "UPDATE resume SET certifications = ? WHERE id = ? AND id_user = ?";
            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_CERTIFICATIONS);
            pst.setString(1, newCertifications);
            System.err.println("resume.getId() = " + resume.getId());
            System.err.println("resume.getUserId() = " + resume.getUserId());

            pst.setInt(2, resume.getId());
            pst.setInt(3, resume.getUserId());
            return pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateSocialNetworks(String newSocialNetworks) {
        try {
            String UPDATE_RESUME_SOCIAL_NETWORKS = "UPDATE resume SET socialNetworks = ? WHERE id = ? AND id_user = ?";
            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_SOCIAL_NETWORKS);
            pst.setString(1, newSocialNetworks);
            pst.setInt(2, resume.getId());
            pst.setInt(3, resume.getUserId());

            System.err.println(newSocialNetworks);

            System.err.println(resume.getUserId());

            return pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateSkills(String newSkills) {
        try {
            String UPDATE_RESUME_SKILLS = "UPDATE resume SET skills = ? WHERE id = ? AND id_user = ?";
            PreparedStatement pst = connection.prepareStatement(UPDATE_RESUME_SKILLS);
            pst.setString(1, newSkills);
            pst.setInt(2, resume.getId());
            pst.setInt(3, resume.getUserId());
            return pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteResume() {
        try {
            String DELETE_RESUME = "DELETE FROM resume WHERE  id = ?";
            PreparedStatement pst = connection.prepareStatement(DELETE_RESUME);
            pst.setInt(1, resume.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
