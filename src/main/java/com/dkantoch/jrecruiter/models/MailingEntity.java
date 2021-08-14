package com.dkantoch.jrecruiter.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "mailing_list",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class MailingEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    @CreatedDate
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date subscriptionStartDate;

    @NotNull
    private Boolean isActive;

    public MailingEntity()
    {

    }

    public MailingEntity(Long id, String email, Date subscriptionStartDate, Boolean isActive)
    {
        this.id = id;
        this.email = email;
        this.subscriptionStartDate = subscriptionStartDate;
        this.isActive = isActive;
    }

    public MailingEntity(String email, Date subscriptionStartDate, Boolean isActive)
    {
        this.email = email;
        this.subscriptionStartDate = subscriptionStartDate;
        this.isActive = isActive;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getSubscriptionStartDate()
    {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Date subscriptionStartDate)
    {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public Boolean getActive()
    {
        return isActive;
    }

    public void setActive(Boolean active)
    {
        isActive = active;
    }

    @Override
    public String toString() {
        return "MailingEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", subscriptionStartDate=" + subscriptionStartDate +
                ", isActive=" + isActive +
                '}';
    }
}
