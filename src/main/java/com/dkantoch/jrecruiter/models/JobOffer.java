package com.dkantoch.jrecruiter.models;

import lombok.Getter;
import lombok.Setter;
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
public class JobOffer
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
    //List<String> requirements;
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

    @OneToMany(mappedBy = "jobOffer")
    private Set<RecruitmentProcess> recruitmentProcesses;

    @NotNull
    @CreatedDate
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date creationDate;

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
                '}';
    }
}
