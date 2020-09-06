package com.blacksystem.automation.module.meetme.utils;

import com.blacksystem.automation.application.utils.DatesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RatesCalculation {
    public static Logger logger = LogManager.getLogger();

    private Date startDate;
    private Date endDate;
    private double agentRatePerMinute;
    private double agentRatePerSecond;
    private long timeStampDifference;
    private long timeStampSeconds;

    private double days;
    private double hours;
    private double minutes;
    private double seconds;
    private double totalMinutes;

    private String sDays;
    private String sHours;
    private String sMinutes;
    private String sSeconds;

    private String duration;

    public static void main (String[] args){
        RatesCalculation ratesCalculation = new RatesCalculation("09-06-2020 5:11:31 pm","09-06-2020 5:13:26 pm",0.5);

        ratesCalculation.getRatePerSecond();
        ratesCalculation.getRatePerMinute();
        ratesCalculation.getDuration();
        ratesCalculation.getDurationInMilliseconds();
        ratesCalculation.getTotalInMinutes();
        ratesCalculation.getDurationInSeconds();
        ratesCalculation.getTotalAgent();
        ratesCalculation.getTotalService();
        ratesCalculation.getTotalCoins();

    }

    public RatesCalculation(String startDate, String endDate, double agentRatePerMinute) {

        logger.info("/-----------------------------------[Rates Calculation]-------------------------------------/");
        logger.info("Start Date: "+startDate);
        logger.info("End Date: "+endDate);

        this.startDate = DatesUtils.convertStringToDate(startDate,DatesUtils.US_DATE_TIME);
        this.endDate = DatesUtils.convertStringToDate(endDate,DatesUtils.US_DATE_TIME);
        this.agentRatePerMinute = agentRatePerMinute;
        this.agentRatePerSecond = agentRatePerMinute/60;

        logger.info("Agent Rate x Minute: "+this.agentRatePerMinute);
        logger.info("Agent Rate x Second: "+this.agentRatePerSecond);

        //Determine Time Duration
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        long difference = this.endDate.getTime() - this.startDate.getTime();
        this.timeStampDifference = difference;
        this.timeStampSeconds = difference/1000;

        this.seconds = difference / 1000 % 60;
        this.minutes = difference / (60 * 1000) % 60;
        this.hours = difference / (60 * 60 * 1000) % 24;
        this.days = difference / (24 * 60 * 60 * 1000);

        this.hours = this.hours + (this.days*24);

        this.sSeconds = this.seconds>9? String.valueOf(this.seconds).replace(".0","") : "0"+ String.valueOf(this.seconds).replace(".0","");
        this.sMinutes = this.minutes>9? String.valueOf(this.minutes).replace(".0","") : "0"+ String.valueOf(this.minutes).replace(".0","");
        this.sHours   = this.hours>9? String.valueOf(this.hours).replace(".0","") : "0"+ String.valueOf(this.hours).replace(".0","");
        this.sDays   = this.days>9? String.valueOf(this.days).replace(".0","") : "0"+ String.valueOf(this.days).replace(".0","");

        this.duration = this.sHours+":"+this.sMinutes+":"+this.sSeconds;

        double hoursInMinutes = this.hours * 60;
        double minutesInMinutes = this.minutes * 1;
        double secondsInMinutes = Math.round((this.seconds / 60)*100.0)/100.0;

        this.totalMinutes = hoursInMinutes+minutesInMinutes+secondsInMinutes;

    }

    public double getRatePerSecond(){
        logger.info("Rate x Second: "+this.agentRatePerSecond);
        return this.agentRatePerSecond;
    }

    public double getRatePerMinute(){
        logger.info("Rate x Minute: "+this.agentRatePerMinute);
        return this.agentRatePerMinute;
    }

    public String getDuration() {
        logger.info("Duration: "+ this.duration);
        return this.duration;
    }

    public long getDurationInMilliseconds(){
        logger.info("Milliseconds: "+this.timeStampDifference);
        return this.timeStampDifference;
    }

    public long getDurationInSeconds(){
        logger.info("Seconds: "+this.timeStampSeconds);
        return timeStampSeconds;
    }

    public double getTotalInMinutes(){
        logger.info("Total Minutes: "+this.totalMinutes );
        return this.totalMinutes ;
    }

    public double getTotalPerSeconds(){
        logger.info("Total x Seconds: "+this.agentRatePerSecond * timeStampSeconds);
        return this.agentRatePerSecond * timeStampSeconds;
    }

    public double getTotalPerMinutes(){
        logger.info("Total x Minutes: "+this.agentRatePerMinute * this.totalMinutes);
        return this.agentRatePerMinute * this.totalMinutes;
    }

    public double getTotalAgent(){
        double totalCall = (this.agentRatePerSecond * timeStampSeconds)*0.65;
        double val = Math.round(totalCall*100.0)/100.0;
        logger.info("Total x Agent 65%: "+val);
        return val;
    }

    public double getTotalService(){
        double totalCall = (this.agentRatePerSecond * timeStampSeconds)*0.35;
        double val = Math.round(totalCall*100.0)/100.0;
        logger.info("Total x Service 35%: "+val);
        return val;
    }

    public double getTotalCall(){
        double totalCall = (this.agentRatePerSecond * timeStampSeconds);
        double val = Math.round(totalCall*100.0)/100.0;
        logger.info("Total x Call: "+val);
        return val;
    }

    public int getTotalCoins(){
        double coins = ((this.agentRatePerSecond * timeStampSeconds) / this.agentRatePerMinute);
        double val = Math.round((Math.round(coins*100.00)/100.00)*2);
        String c = String.valueOf((int) val).replace(".0","");
        logger.info("Coins: "+c );
        return Integer.valueOf(c);
    }

}
