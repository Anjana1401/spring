package com.torryharris.JwelleryListingApp.service;

import com.torryharris.JwelleryListingApp.configuration.TwilioProperties;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneVerificationService {

    private final TwilioProperties twilioProperties;

    @Autowired
    public PhoneVerificationService(TwilioProperties twilioProperties){

        this.twilioProperties = twilioProperties;
    }
    public VerificationResults startVerification(String phone){
        try{
            Verification verification= Verification.creator(twilioProperties.getServiceId(),phone,"sms").create();
            if("approved".equals(verification.getStatus())|| "pending".equals(verification.getStatus())){
                return new VerificationResults(verification.getSid());
            }
        } catch (ApiException exception){
            return new VerificationResults(new String[] {exception.getMessage()});
        }
        return null;
    }
    public VerificationResults checkVerification(String phone, String code){
        try{
            VerificationCheck verification= VerificationCheck.creator(twilioProperties.getServiceId(), code).setTo(phone).create();
            if("approved".equals(verification.getStatus())){
                return new VerificationResults(verification.getSid());
            }
            return new VerificationResults(new String[]{"Invalid code."});
        } catch (ApiException exception){
            return new VerificationResults(new String[] {exception.getMessage()});
        }

    }
}
