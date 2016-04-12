package me.thenpush.rest.models;

/**
 * Created by pappacena on 12/04/16.
 */
public class Device {
    private String registrationId;

    public Device(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
