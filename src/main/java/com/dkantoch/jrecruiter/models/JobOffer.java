package com.dkantoch.jrecruiter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "job_offers")
@Getter
@Setter
public class JobOffer implements Comparable<JobOffer>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Size(max = 30)
    String positionTitle;

    @NotBlank
    @Size(max = 1000)
    String positionDescription;

    @NotNull
    private Double lowEndPaymentRange;

    @NotNull
    private Double highEndPaymentRange;

    @NotBlank
    @Size(max = 50)
    private String contractType;

    @Size(max = 50)
    private String leadingTechnology;

    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;

    @JsonManagedReference
    @OneToMany(mappedBy = "jobOffer")
    private Set<RecruitmentProcess> recruitmentProcesses;

    @ManyToMany
    @JoinTable(
            name = "job_offer_requirements",
            joinColumns = @JoinColumn(name = "joboffer_id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id"))
    private Set<Requirement> requirements;

    @NotNull
    @CreatedDate
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date creationDate;

    @NotNull
    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modificationDate;

    @Override
    public String toString() {
        return "JobOffer{" +
                "id=" + id +
                ", positionTitle='" + positionTitle + '\'' +
                ", positionDescription='" + positionDescription + '\'' +
                ", lowEndPaymentRange=" + lowEndPaymentRange +
                ", highEndPaymentRange=" + highEndPaymentRange +
                ", contractType='" + contractType + '\'' +
                ", company=" + company +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }

    @Override
    public int compareTo(JobOffer o)
    {
        return getCreationDate().compareTo(o.getCreationDate());
    }
}
