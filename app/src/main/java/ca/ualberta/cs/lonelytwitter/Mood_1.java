package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by linkai on 9/15/16.
 */
public class Mood_1 extends Mood{

    public  Mood_1(Date date){
        super(date);
    }

    @Override
    public String return_mood() {
        return "mood1_happy!";
    }
}
