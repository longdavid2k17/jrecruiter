package com.dkantoch.jrecruiter.services;

import com.dkantoch.jrecruiter.models.MailingEntity;
import com.dkantoch.jrecruiter.repositories.MailingRepository;
import com.dkantoch.jrecruiter.utils.ToJsonString;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Optional;

@Service
public class MailService
{
    private final JavaMailSender javaMailSender;
    private final MailingRepository mailingRepository;

    public MailService(JavaMailSender javaMailSender,MailingRepository mailingRepository)
    {
        this.javaMailSender = javaMailSender;
        this.mailingRepository = mailingRepository;
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
        Optional<MailingEntity> entityOptional = mailingRepository.findByEmailAndIsActive(email,true);
        if(entityOptional.isEmpty())
        {
            sendMail(email,"Witaj w newsletterze JRecruiter","Zapisałeś się właśnie do listy mailingowej portalu JRecruiter. Będziesz dostawać informacje na temat ciekawych ofert pracy! Obiecujemy nie spamować :)",false);
            mailingRepository.save(new MailingEntity(email,new Date(),true));
            return ResponseEntity.ok().body(ToJsonString.toJsonString("Zapisano"));
        }
        else
        {
            return ResponseEntity.ok().body(ToJsonString.toJsonString("Jesteś już na naszej liście mailingowej!"));
        }
    }
}
