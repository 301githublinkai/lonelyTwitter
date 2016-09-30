package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by administrator on 2016-09-29.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2{

    public TweetListTest(){
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }
    public void testAddTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("adding tweet");
        tweets.addTweet(tweet);
        assertTrue(tweets.hasTweet(tweet));
    }

    public void testHasTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        assertTrue(list.hasTweet(tweet));
    }

    public void testDelete(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.addTweet(tweet);
        list.delete(tweet);
        assertFalse(list.hasTweet(tweet));
    }

    public void testGetTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test");
        tweets.addTweet(tweet);
        Tweet returnedTweet = tweets.getTweet(0);
        assertEquals(returnedTweet.getMessage(), tweet.getMessage());
    }

    public void testGetCount(){
        TweetList tweets = new TweetList();
        Tweet tweet1 = new NormalTweet("test1");
        tweets.addTweet(tweet1);
        Tweet tweet2 = new NormalTweet("test2");
        tweets.addTweet(tweet2);
        assertTrue(tweets.getCount()==2);
    }

    public void testGetTweets(){
        TweetList tweets = new TweetList();
        Tweet tweet1 = new NormalTweet("test1", new Date());
        tweets.addTweet(tweet1);
        Tweet tweet2 = new NormalTweet("test2", new Date(99, 2, 18));
        tweets.addTweet(tweet2);
        Tweet tweet3 = new NormalTweet("test3");
        tweets.addTweet(tweet3);
        ArrayList<Tweet> myList;
        myList = tweets.getTweets();

        for(int i=0; i < tweets.getCount()-1; i++){
            if(myList.get(i).getDate().after(myList.get(i+1).getDate())){
                assertTrue(Boolean.FALSE);
            }
        }



    }

    public void testAddTweetDuplicate(){
        TweetList tweets = new TweetList();
        Tweet tweet1 = new NormalTweet("test");
        tweets.addTweet(tweet1);
        Tweet tweet2 = new NormalTweet("test");
        try{
            tweets.addTweet(tweet2);
            assertTrue(Boolean.FALSE);
        }catch(IllegalArgumentException e){
            assertTrue(Boolean.TRUE);
        }
    }
}
