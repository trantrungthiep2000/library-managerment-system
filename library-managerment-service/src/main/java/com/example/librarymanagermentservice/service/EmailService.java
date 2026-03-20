package com.example.librarymanagermentservice.service;

/**
 * Information about interface email service.
 */
public interface EmailService {

    /**
     * Send email.
     * @param to String.
     * @param subject String.
     * @param body String.
     * @return Boolean.
     */
    Boolean sendEmail(String to, String subject, String body);
}
