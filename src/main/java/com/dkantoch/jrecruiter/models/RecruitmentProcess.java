package com.dkantoch.jrecruiter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "recruitment_processes")
@Getter
@Setter
public class RecruitmentProcess
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;

    @NotNull
    @CreatedDate
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date processStartDate;

    private String processStatus;

    @Size(max = 500)
    private String feedback;
}
