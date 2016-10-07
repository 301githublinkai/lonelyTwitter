/*
Copyright (C) 2016 Team 20, CMPUT301, University of Alberta - All Rights Reserved.
You  may use, copy or distribution this code under terms and conditions of University of Alberta
and Code of Student Behavior.
Please contact abc@abc.com for more details or questions.
 */
package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This class is the main view class in lonelyTwitter class.
 * It deals with user inputs, saves/loads them in/from the file FILE_NAME (file.sav)
 * <p>you can access this file form android device monitor. </>
 * <pre> pre-formatted	test</>
 * <code>
 *     pseudo-code that is used in this class it as follows:
 *     step1<br>
 *     step2<br>
 * </code>
 * <ol>
 *     <li>first item</li>
 *     <li>second item</li>
 * </ol>
 * <ul>
 *     <li>first item</li>
 *     <li>second item</li>
 * </ul>
 * @author Qi
 * @since 1.4
 * @deprecated
 * @see  NormalTweet
 */
public class LonelyTwitterActivity extends Activity {

	/**
	 * This is the name of the file that is saved in your virtual device.
	 * you can access it through Android Device Monitor by selectiong you app,
	 * then data -> data -> file.sav
	 * @see NormalTweet
	 * @author Linkai
	 */
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		oldTweetsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
										   int pos, long id) {
				// TODO Auto-generated method stub

				Log.v("long clicked","pos: " + pos);

				return true;
			}
		});

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				Tweet newTweet = new NormalTweet(text);
				tweetList.add(newTweet);
				adapter.notifyDataSetChanged();
				saveInFile();
				bodyText.setText("");

			}
		});

		clearButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				setResult(RESULT_OK);
				tweetList.clear();
				bodyText.setText("");
				adapter.notifyDataSetChanged();
				deleteInFile();

			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweetList);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 * this method loads the json file, generates the tweets from its contents.
	 * @throws RuntimeException
	 * @exception FileNotFoundException
	 */
	private void loadFromFile() {
		ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			//Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt September 22, 2016
			Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();
			tweetList = gson.fromJson(in, listType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}

	}
	
	private void saveInFile() {
		try {

			FileOutputStream fos = openFileOutput(FILENAME,0);
			OutputStreamWriter writer = new OutputStreamWriter(fos);
			Gson gson = new Gson();
			gson.toJson(tweetList, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	private void deleteInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0);
			OutputStreamWriter writer = new OutputStreamWriter(fos);
			Gson gson = new Gson();
			gson.toJson(tweetList, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}