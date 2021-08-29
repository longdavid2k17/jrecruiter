package com.dkantoch.jrecruiter.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "recruiter_accounts")
@Getter
@Setter
public class RecruiterAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Company connectedCompany;

    public RecruiterAccount(Company connectedCompany)
    {
        this.connectedCompany = connectedCompany;
    }

    public RecruiterAccount()
    {

    }
}
