package com.dkantoch.jrecruiter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "requirement")
@Getter
@Setter
public class Requirement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private Integer scaleIndicator;

    @NotNull
    @CreatedDate
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date creationDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "requirements")
    private Set<JobOffer> jobOfferSet;

    @Override
    public String toString() {
        return "Requirement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scaleIndicator=" + scaleIndicator +
                ", creationDate=" + creationDate +
                ", jobOfferSet=" + jobOfferSet +
                '}';
    }
}
