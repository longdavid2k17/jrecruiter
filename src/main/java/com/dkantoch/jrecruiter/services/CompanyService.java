package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.Company;
import com.dkantoch.jrecruiter.models.RecruiterAccount;
import com.dkantoch.jrecruiter.models.User;
import com.dkantoch.jrecruiter.repositories.CompanyRepository;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService
{
    private final Logger logger = LoggerFactory.getLogger(CompanyService.class);
    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final RecruiterAccountService recruiterAccountService;

    public CompanyService(CompanyRepository companyRepository,UserService userService,RecruiterAccountService recruiterAccountService)
    {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.recruiterAccountService = recruiterAccountService;
    }

    public ResponseEntity<?> getAllCompanies()
    {
        List<Company> companyList = companyRepository.findAll();
        if(companyList.size()>0)
        {
            return ResponseEntity.ok().body(companyList);
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Brak pozycji!"));
    }

    public ResponseEntity<?> getById(Long id)
    {
        if(id!=null)
        {
            Optional<Company> optionalCompany = companyRepository.findById(id);
            if(optionalCompany.isPresent())
            {
                return ResponseEntity.ok().body(optionalCompany.get());
            }
            else
                return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Nie znaleziono firmy o takim ID"));
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Błędny parametr!"));
    }

    public ResponseEntity<?> createNewCompany(Company company, Long recruiterId)
    {
        if(company!=null && recruiterId!=null)
        {
            Optional<User> optionalUser = userService.getUserById(recruiterId);
            if(optionalUser.isPresent())
            {
                User user = optionalUser.get();
                if(user.getRecruiter())
                {
                    if(company.getId()!=null)
                        company.setId(null);
                    if(company.getCompanyName()==null)
                        return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Brak nazwy firmy"));
                    if(company.getDescription()==null)
                        return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Brak opisu firmy"));
                    if(company.getLocalization()==null)
                        return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Brak lokalizacji"));
                    company.setJobOffers(null);
                    try
                    {
                        Company savedCompany = companyRepository.save(company);

                        RecruiterAccount recruiterAccount = recruiterAccountService.saveAccount(new RecruiterAccount(savedCompany));
                        user.setRecruiter_account_id(recruiterAccount.getId());
                        userService.updateUser(String.valueOf(user.getId()),user);
                        savedCompany.getConnectedRecruiters().add(recruiterAccount);
                        companyRepository.save(savedCompany);
                        return ResponseEntity.ok().body(savedCompany);
                    }
                    catch (Exception e)
                    {
                        logger.error(e.getMessage());
                        return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Wystąpił błąd podczas zapisywania danych!"));
                    }
                }
                else
                    return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Przesłany użytkownik nie jest rekruterem. Proces nie może być kontynuowany!"));
            }
            else
                return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Nie znaleziono powiązanego konta użytkownika!"));
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Podane wartości są nieprawidłowe!"));
    }

    public ResponseEntity<?> save(Company company)
    {
        if(company!=null)
        {
            if(company.getId()!=null)
            {
                if(company.getCompanyName()==null)
                    return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Brak nazwy!"));
                if(company.getDescription()==null)
                    return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Brak opisu!"));
                if(company.getLocalization()==null)
                    return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Brak lokalizacji!"));
                Company savedCompany = companyRepository.save(company);
                return ResponseEntity.ok().body(savedCompany);
            }
            else
            {
                logger.warn("Zapis nowego wpisu firmy");
                Company savedCompany = companyRepository.save(company);
                return ResponseEntity.ok().body(savedCompany);
            }
        }
        else
            return ResponseEntity.badRequest().body(ToJsonString.toJsonString("Przekazane parametry są niepoprawne!"));
    }
}
