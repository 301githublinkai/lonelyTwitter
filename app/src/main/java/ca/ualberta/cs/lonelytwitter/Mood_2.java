package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by linkai on 9/15/16.
 */
public class Mood_2 extends Mood{
    public  Mood_2(Date date){
        super(date);
    }

    public  Mood_2(){
        super();
    }

    @Override
    public String return_mood() {
        return "mood2_sad";
    }
}
