package com.dkantoch.jrecruiter.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "newsletter",uniqueConstraints = {
        @UniqueConstraint(columnNames = "sentDate")
})
public class Newsletter
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @Size(min = 3, max = 100)
    private String subject;

    @Column(length = 1500)
    @Size(min = 3, max = 1500)
    private String message;

    @NotNull
    @CreatedDate
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date sentDate;

    @NotNull
    private Boolean isSent;

    @NotNull
    private int targetEmailCount;

    public Newsletter()
    {

    }

    public Newsletter(String subject, String message, Date sentDate, Boolean isSent, int targetEmailCount)
    {
        this.subject = subject;
        this.message = message;
        this.sentDate = sentDate;
        this.isSent = isSent;
        this.targetEmailCount = targetEmailCount;
    }

    public Newsletter(Long id, String subject, String message, Date sentDate, Boolean isSent, int targetEmailCount)
    {
        this.id = id;
        this.subject = subject;
        this.message = message;
        this.sentDate = sentDate;
        this.isSent = isSent;
        this.targetEmailCount = targetEmailCount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Boolean getSent() {
        return isSent;
    }

    public void setSent(Boolean sent) {
        isSent = sent;
    }

    public int getTargetEmailCount() {
        return targetEmailCount;
    }

    public void setTargetEmailCount(int targetEmailCount) {
        this.targetEmailCount = targetEmailCount;
    }

    @Override
    public String toString() {
        return "Newsletter{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", sentDate=" + sentDate +
                ", isSent=" + isSent +
                ", targetEmailCount=" + targetEmailCount +
                '}';
    }
}
