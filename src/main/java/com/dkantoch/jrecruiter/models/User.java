package com.dkantoch.jrecruiter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "websiteUrl"),
        @UniqueConstraint(columnNames = "githubUrl"),
        @UniqueConstraint(columnNames = "twitterUrl")
})
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Size(max = 100000)
    private String profileImgUrl;

    @Size(max = 500)
    private String bio;

    @NotBlank
    @Size(max = 12)
    private String phoneNumber;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 100)
    private String websiteUrl;

    @Size(max = 100)
    private String githubUrl;

    @Size(max = 100)
    private String twitterUrl;

    @NotBlank
    @Size(max = 50)
    private String surname;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<RecruitmentProcess> recruitmentProcesses;

    public User()
    {

    }

    public User(Long id, String username, String email, String password, String profileImgUrl, String bio, String phoneNumber, String name, String websiteUrl, String githubUrl, String twitterUrl, String surname, Set<Role> roles, Set<RecruitmentProcess> recruitmentProcesses)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileImgUrl = profileImgUrl;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.githubUrl = githubUrl;
        this.twitterUrl = twitterUrl;
        this.surname = surname;
        this.roles = roles;
        this.recruitmentProcesses = recruitmentProcesses;
    }

    public User(String username, String email, String password, String name, String surname, String phoneNumber)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<RecruitmentProcess> getRecruitmentProcesses() {
        return recruitmentProcesses;
    }

    public void setRecruitmentProcesses(Set<RecruitmentProcess> recruitmentProcesses) {
        this.recruitmentProcesses = recruitmentProcesses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profileImgUrl='" + profileImgUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", twitterUrl='" + twitterUrl + '\'' +
                ", surname='" + surname + '\'' +
                ", roles=" + roles +
                ", recruitmentProcesses=" + recruitmentProcesses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(profileImgUrl, user.profileImgUrl) && Objects.equals(bio, user.bio) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(name, user.name) && Objects.equals(websiteUrl, user.websiteUrl) && Objects.equals(githubUrl, user.githubUrl) && Objects.equals(twitterUrl, user.twitterUrl) && Objects.equals(surname, user.surname) && Objects.equals(roles, user.roles) && Objects.equals(recruitmentProcesses, user.recruitmentProcesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, profileImgUrl, bio, phoneNumber, name, websiteUrl, githubUrl, twitterUrl, surname, roles, recruitmentProcesses);
    }
}
