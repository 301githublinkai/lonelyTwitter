package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by administrator on 2016-09-29.
 */
public class TweetList {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public TweetList(){

    }

    public boolean hasTweet(Tweet tweet){
        return tweets.contains(tweet);
    }

    public void addTweet(Tweet tweet) {
        for(Tweet myTweet:tweets){
            if (myTweet.getMessage().equals(tweet.getMessage())){
                throw new IllegalArgumentException();
            }
        }
        tweets.add(tweet);
    }

    public void delete(Tweet tweet) {
        tweets.remove(tweet);
    }

    public Tweet getTweet(int index) {
        return tweets.get(index);
    }

    public int getCount() {
        return tweets.size();
    }

    public ArrayList<Tweet> getTweets() {
        ArrayList<Tweet> cpyTweet = tweets;
        ArrayList<Tweet> returnList = new ArrayList<Tweet>();
        Tweet min;

        while (cpyTweet.size()>0) {
            min = cpyTweet.get(0);
            for (int i = 0; i < cpyTweet.size(); i++) {
                if ((cpyTweet.get(i).getDate()).before(min.getDate())) {
                    min = cpyTweet.get(i);
                }
            }
            returnList.add(min);
            cpyTweet.remove(min);
        }
        return returnList;
    }
}
