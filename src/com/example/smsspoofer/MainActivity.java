package com.example.smsspoofer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends Activity {

	public final static String RES = "com.example.myfirstapp.RES";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void sendSMS(View view) {

		final Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText from_message = (EditText) findViewById(R.id.from_message);
		EditText to_message = (EditText) findViewById(R.id.to_message);
		EditText text_message = (EditText) findViewById(R.id.text_message);

		String from = from_message.getText().toString();
		String to = to_message.getText().toString();
		String text = text_message.getText().toString();
		String api_key = "test1";
		String api_secret = "test2";

		String url = "http://rest.nexmo.com/sms/json?api_key=" + api_key
				+ "&api_secret=" + api_secret + "&from=" + from + "&to=" + to
				+ "&text=" + text;

		AsyncHttpClient client = new AsyncHttpClient();

		client.get(url, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(String response) {
				intent.putExtra(RES, "done");
				startActivity(intent);
				System.out.println(response);
			}

		});
	}
}
