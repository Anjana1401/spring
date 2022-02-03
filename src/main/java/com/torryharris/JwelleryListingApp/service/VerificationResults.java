package com.torryharris.JwelleryListingApp.service;

import org.springframework.stereotype.Service;


public class VerificationResults {

    private final String id;

    private final String[] error;

    private final boolean valid;

    public VerificationResults(String id) {
        this.id = id;
        this.error = new String[]{};
        this.valid = true;
    }

    public VerificationResults(String[] error) {
        this.error = error;
        this.id = "";
        this.valid = false;
    }

    public String getId() {
        return id;
    }

    public String[] getError() {
        return error;
    }

    public boolean isValid() {
        return valid;
    }
}
