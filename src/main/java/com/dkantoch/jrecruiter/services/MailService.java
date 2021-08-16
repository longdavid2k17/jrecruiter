package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.MailingEntity;
import com.dkantoch.jrecruiter.models.Newsletter;
import com.dkantoch.jrecruiter.repositories.MailingRepository;
import com.dkantoch.jrecruiter.repositories.NewsletterRepository;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MailService
{
    private final Logger logger = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender javaMailSender;
    private final MailingRepository mailingRepository;
    private final NewsletterRepository newsletterRepository;

    public MailService(JavaMailSender javaMailSender,MailingRepository mailingRepository,NewsletterRepository newsletterRepository)
    {
        this.javaMailSender = javaMailSender;
        this.mailingRepository = mailingRepository;
        this.newsletterRepository = newsletterRepository;
    }

    public void sendMail(String to, String subject, String text, boolean isHtmlContent) throws MessagingException
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, isHtmlContent);
        javaMailSender.send(mimeMessage);
    }

    public ResponseEntity<?> validateAndSave(String email) throws MessagingException
    {
        Optional<MailingEntity> entityOptional = mailingRepository.findByEmail(email);
        if(entityOptional.isEmpty())
        {
            sendMail(email,"Witaj w newsletterze JRecruiter","Zapisałeś się właśnie do listy mailingowej portalu JRecruiter. Będziesz dostawać informacje na temat ciekawych ofert pracy! Obiecujemy nie spamować :)",false);
            mailingRepository.save(new MailingEntity(email,new Date(),true));
            return ResponseEntity.ok().body(ToJsonString.toJsonString("Zapisano"));
        }
        else
        {
            MailingEntity mailingEntity = entityOptional.get();
            if(mailingEntity.getActive())
            {
                return ResponseEntity.ok().body(ToJsonString.toJsonString("Jesteś już na naszej liście mailingowej!"));
            }
            else
            {
                mailingEntity.setActive(true);
                mailingRepository.save(mailingEntity);
                return ResponseEntity.ok().body(ToJsonString.toJsonString("Reaktywowaliśmy twoją subskrypcję!"));
            }
        }
    }

    public ResponseEntity<?> getAllMailingEntities()
    {
        List<MailingEntity> mailingEntities = mailingRepository.findAll();
        if(mailingEntities.size()>0)
        {
            return ResponseEntity.ok().body(mailingEntities);
        }
        else
            return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> getAllMailingEntitiesPageable(Pageable pageable)
    {
        Page<MailingEntity> mailingEntities = mailingRepository.findAll(pageable);
        return ResponseEntity.ok().body(mailingEntities);
    }

    @Scheduled(cron = "0 0 6 * * *",zone = "Europe/Warsaw")
    public void sendNewsletter()
    {
        Optional<Newsletter> newsletterOptional = newsletterRepository.findBySentDateAndIsSentEquals(new Date(),false);
        if(newsletterOptional.isPresent())
        {
            int sentCounter = 0;
            Newsletter newsletter = newsletterOptional.get();
            List<MailingEntity> mailingEntities = mailingRepository.findAllByIsActive(true);
            if(mailingEntities.size()>0)
            {
                try
                {
                    for(MailingEntity mailingEntity : mailingEntities)
                    {
                        sendMail(mailingEntity.getEmail(),newsletter.getSubject(), newsletter.getMessage(), false);
                        sentCounter++;
                    }
                    newsletter.setTargetEmailCount(sentCounter);
                    newsletter.setSent(true);
                    newsletterRepository.save(newsletter);
                }
                catch (MessagingException e)
                {
                    logger.error(e.getMessage());
                }
            }
        }

    }
}
