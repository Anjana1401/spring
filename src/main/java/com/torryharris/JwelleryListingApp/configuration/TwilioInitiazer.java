package com.torryharris.JwelleryListingApp.configuration;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;


@EnableAutoConfiguration
public class TwilioInitiazer {

    private final TwilioProperties twilioProperties;

    @Autowired
    public TwilioInitiazer(TwilioProperties twilioProperties)
    {
        this.twilioProperties=twilioProperties;
        Twilio.init(twilioProperties.getAccountSid(),twilioProperties.getAuthToken());
        System.out.println("Twilio initialized with account-"+twilioProperties.getAccountSid());
    }
}
