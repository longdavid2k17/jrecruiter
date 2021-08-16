package com.dkantoch.jrecruiter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
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
@Data
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

    public JobOffer(Long id, String positionTitle, String positionDescription, Double lowEndPaymentRange, Double highEndPaymentRange, String contractType, String leadingTechnology, Company company, Set<RecruitmentProcess> recruitmentProcesses, Date creationDate)
    {
        this.id = id;
        this.positionTitle = positionTitle;
        this.positionDescription = positionDescription;
        this.lowEndPaymentRange = lowEndPaymentRange;
        this.highEndPaymentRange = highEndPaymentRange;
        this.contractType = contractType;
        this.leadingTechnology = leadingTechnology;
        this.company = company;
        this.recruitmentProcesses = recruitmentProcesses;
        this.creationDate = creationDate;
    }

    public JobOffer()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public Double getLowEndPaymentRange() {
        return lowEndPaymentRange;
    }

    public void setLowEndPaymentRange(Double lowEndPaymentRange) {
        this.lowEndPaymentRange = lowEndPaymentRange;
    }

    public Double getHighEndPaymentRange() {
        return highEndPaymentRange;
    }

    public void setHighEndPaymentRange(Double highEndPaymentRange) {
        this.highEndPaymentRange = highEndPaymentRange;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getLeadingTechnology() {
        return leadingTechnology;
    }

    public void setLeadingTechnology(String leadingTechnology) {
        this.leadingTechnology = leadingTechnology;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<RecruitmentProcess> getRecruitmentProcesses() {
        return recruitmentProcesses;
    }

    public void setRecruitmentProcesses(Set<RecruitmentProcess> recruitmentProcesses) {
        this.recruitmentProcesses = recruitmentProcesses;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

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
