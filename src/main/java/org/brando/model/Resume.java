package org.brando.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Resume {

  private int id;
  private String tittle;
  private String fullName;
  private String address;
  private String description;
  private List<String> certifications;
  private Map<String, String> socialNetworks;
  private List<String> skills;
  private int userId;


  //this constructor is used for retrieving resumes
  public Resume(int id, String tittle, String fullName, String address, String description, List<String> certifications, Map<String, String> socialNetworks, List<String> skills, int userId) {
    this.id = id;
    this.tittle = tittle;
    this.fullName = fullName;
    this.address = address;
    this.description = description;
    this.certifications = certifications;
    this.socialNetworks = socialNetworks;
    this.skills = skills;
    this.userId = userId;
  }

  public Resume(String tittle, String fullName, String address, String description, List<String> certifications, Map<String, String> socialNetworks, List<String> skills, int userId) {
    this.tittle = tittle;
    this.fullName = fullName;
    this.address = address;
    this.description = description;
    this.certifications = certifications;
    this.socialNetworks = socialNetworks;
    this.skills = skills;
    this.userId = userId;
  }

  public Resume(String tittle, int userId) {
    this.tittle = tittle;
    this.userId = userId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTittle() {
    return tittle;
  }

  public void setTittle(String tittle) {
    this.tittle = tittle;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getCertifications() {
    if (certifications == null) {
      System.err.println("certifications is null, but now returning an empty arrayList");
      return new ArrayList<>();
    }
    return certifications;
  }

  public void setCertifications(List<String> certifications) {

    this.certifications = certifications;
  }

  public Map<String, String> getSocialNetworks() {
    return socialNetworks;
  }

  public void setSocialNetworks(Map<String, String> socialNetworks) {
    this.socialNetworks = socialNetworks;
  }

  public List<String> getSkills() {
    return skills;
  }

  public void setSkills(List<String> skills) {
    this.skills = skills;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return STR."Resume{id=\{id}, tittle='\{tittle}\{'\''}, fullName='\{fullName}\{'\''}, address='\{address}\{'\''}, description='\{description}\{'\''}, certifications=\{certifications}, socialNetworks=\{socialNetworks}, skills=\{skills}, userId=\{userId}\{'}'}";
  }
}
