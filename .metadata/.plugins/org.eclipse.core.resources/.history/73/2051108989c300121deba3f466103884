package com.utk.helloweather;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView listView;

	private AsyncHttpClient client = new AsyncHttpClient();

	private ArrayList<String> items = new ArrayList<String>();
	private ArrayAdapter<String> aa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);

		aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		listView.setAdapter(aa);

		showWeather();
	}

	private void showWeather() {
		String url = "http://www.makathon.com/weather/?weather=bangkok";
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				try {
					JSONObject jObj = new JSONObject(response);
					JSONObject jData = jObj.getJSONObject("data");
					JSONArray jWeather = jData.getJSONArray("weather");
					for (int i = 0; i < jWeather.length(); i++) {
						JSONObject jItem = jWeather.getJSONObject(i);
						
						String date = jItem.getString("date");
						String maxC = jItem.getString("tempMaxC");
						String minC = jItem.getString("tempMinC");
						
						String weatherMsg = String.format("%s: Max %s, Min %s", date, maxC, minC);
						items.add(weatherMsg);
					}
					
					aa.notifyDataSetChanged();

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable thr) {

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
