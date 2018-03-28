package com.example.uzairkhan.mkhan208cw;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Created by Uzairkhan on 20/03/2018.
 */

public class FeedItem implements Serializable {
    String title;
    String link;
    String description;
    String pubDate;
    String coordinate;
    String startDate;
    String endDate;
    String works;
    String management;
    String diversion;
    Date stDate;
    Date fnDate;

    public long getTimeBetweenDates()
    {
        if(startDate !=null && endDate !=null)
        {
            long diffInMilliseconds = fnDate.getTime() - stDate.getTime();
            return TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
        }
        else
        {
            return 0;
        }
    }
    public String getWorks() {return this.works;}
    public String getManagement() {return this.management;}
    public String getDiversion() {return  this.diversion;}
    public void setWorks(String works) {this.works = works;}
    public void setManagement(String management) {this.management = management;}
    public void setDiversion(String diversion) {this.diversion = diversion;}


    public String getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link=link;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getPubDate(){
        return pubDate;
    }

    public void setPubDate(String pubDate){
        this.pubDate=pubDate;
    }
}
