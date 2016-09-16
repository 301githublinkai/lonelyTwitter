package ca.ualberta.cs.lonelytwitter;


import java.util.ArrayList;
import java.util.Date;

/**
 * Created by linkai on 9/15/16.
 */
public abstract class Tweet {
    private String message;
    private Date date;
    //public String s;
    //protected String p;
    //String nothing;
    private ArrayList<Mood> moodList;

    public Tweet(String message) {
        this.message = message;
        this.date = new Date();
        this.moodList = new ArrayList<Mood>();
    }

    public Tweet(String message, Date date){
        this.message = message;
        this.date = date;
        this.moodList = new ArrayList<Mood>();
    }

    public abstract Boolean isImportant();

    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() > 140) {
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage(){
        return message;
    }

    public Date getDate(){
        return date;
    }

    public void addMood(int moodInt){
        if (moodInt == 1) {
            Mood newMood = new Mood_1();
            moodList.add(newMood);
        } else {
            Mood newMood = new Mood_2();
            moodList.add(newMood);
        }
    }


}
