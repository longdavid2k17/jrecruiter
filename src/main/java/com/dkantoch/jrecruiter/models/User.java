package com.dkantoch.jrecruiter.models;

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
        @UniqueConstraint(columnNames = "email")
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

    @Size(max = 150)
    private String profileImgUrl;

    @Size(max = 150)
    private String cvPath;

    @NotBlank
    @Size(max = 12)
    private String phoneNumber;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String surname;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<RecruitmentProcess> recruitmentProcesses;

    public User()
    {

    }

    public User(Long id, String username, String email, String password, String profileImgUrl, String cvPath, String phoneNumber, String name, String surname, Set<Role> roles, Set<RecruitmentProcess> recruitmentProcesses)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileImgUrl = profileImgUrl;
        this.cvPath = cvPath;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
        this.recruitmentProcesses = recruitmentProcesses;
    }

    public User(String username, String email, String password, String name, String surname,String phoneNumber)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
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

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
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
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profileImgUrl='" + profileImgUrl + '\'' +
                ", cvPath='" + cvPath + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
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
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(profileImgUrl, user.profileImgUrl) && Objects.equals(cvPath, user.cvPath) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(roles, user.roles) && Objects.equals(recruitmentProcesses, user.recruitmentProcesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, profileImgUrl, cvPath, phoneNumber, name, surname, roles, recruitmentProcesses);
    }
}
