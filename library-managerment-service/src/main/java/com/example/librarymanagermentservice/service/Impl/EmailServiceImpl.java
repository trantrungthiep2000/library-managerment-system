package com.example.librarymanagermentservice.service.Impl;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Information about email service implement.
 */
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final Log logger = LogFactory.getLog(EmailServiceImpl.class);
    private final JavaMailSender javaMailSender;

    /**
     * Send email.
     * @param to String.
     * @param subject String.
     * @param body String.
     * @return Boolean.
     */
    @Override
    public Boolean sendEmail(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, Constant.UTF_8);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setTo(to);
            javaMailSender.send(mimeMessage);
        } catch (Exception ex) {
            logger.error("Send mail error: " + ex.getMessage());
            return false;
        }

        return true;
    }
}
