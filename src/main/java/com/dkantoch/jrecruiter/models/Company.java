package com.dkantoch.jrecruiter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String companyName;

    @NotBlank
    @Size(max = 120)
    private String localization;

    @Size(max = 1000)
    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<JobOffer> jobOffers = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "connectedCompany")
    private Set<RecruiterAccount> connectedRecruiters = new HashSet<>();

    @Override
    public String toString()
    {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", localization='" + localization + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
