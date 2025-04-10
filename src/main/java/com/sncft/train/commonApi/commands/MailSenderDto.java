package com.sncft.train.commonApi.commands;
import lombok.Data;

@Data
public class MailSenderDto {
    private String to;
    private String subject;
    private String body;
}