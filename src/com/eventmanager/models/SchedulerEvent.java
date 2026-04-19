package com.eventmanager.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SchedulerEvent
{
    //Atributos
    private String eventDesc;
    private String fwdEmail;
    private Date date;
    private Frequency frequency;
    private boolean alarm;

    //Metodos
    @Override
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return sdf.format(this.date) + ";"+ getEventDesc() +
                ";" + getFrequency() + ";" + getFwdEmail() + ";"
                + (getAlarm() == true ? "1" : "0");
    }

    //Getters & Setter
    public String getEventDesc() { return eventDesc; }

    public void setEventDesc(String descEvent) { this.eventDesc = descEvent; }

    public String getFwdEmail() { return fwdEmail; }

    public void setFwdEmail(String encEmail) { this.fwdEmail = encEmail; }

    public Date getDate() { return date; }

    public void setDate(Date dat) { this.date = dat; }

    public Frequency getFrequency() { return frequency; }

    public void setFrequency(Frequency frequency) { this.frequency = frequency; }

    public boolean getAlarm() { return alarm; }

    public void setAlarm(boolean alarm) { this.alarm = alarm; }
}
