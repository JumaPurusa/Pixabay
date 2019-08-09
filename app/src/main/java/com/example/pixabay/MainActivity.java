package com.example.pixabay;

import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.pixabay.Adapters.MainPagerAdapter;
import com.example.pixabay.Fragments.ImagesFragment;
import com.example.pixabay.Fragments.VideosFragment;
import com.example.pixabay.Utils.NetworkHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    public static CoordinatorLayout coordinatorLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainPagerAdapter adapter;

    //private HttpURLConnection urlConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        assert  actionBar != null;
        actionBar.setHomeButtonEnabled(true);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabLayout();

        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        Toast.makeText(MainActivity.this,
                                "Fragment " + tab.getPosition(), Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );

        if(NetworkHelper.isOnline(MainActivity.this)){
           // urlConnection = null;
        }else{
            Snackbar.make(coordinatorLayout,
                    "No Connection",
                    Snackbar.LENGTH_LONG)
                    .show();
        }

    }

    private void setupViewPager(ViewPager viewPager){
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFragments(new ImagesFragment(), "IMAGES");
        adapter.addFragments(new VideosFragment(), "VIDEO");
        viewPager.setAdapter(adapter);
    }

    private void setupTabLayout(){
        tabLayout.getTabAt(0).setText(MainPagerAdapter.fragmentTitles.get(0));
        tabLayout.getTabAt(1).setText(MainPagerAdapter.fragmentTitles.get(1));
    }

    private URL createURL(String search){
        String apiKey = getString(R.string.api_key);
        String baseURL = getString(R.string.web_service_url);

        try {
            String stringURL = baseURL + apiKey + "&q=" + URLEncoder.encode(search, "UTF-8");

            return new URL(stringURL);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public class GetImagePostsTask extends AsyncTask<URL, Void, JSONObject>{
//
//        @Override
//        protected JSONObject doInBackground(URL... urls) {
//            try {
//                urlConnection = (HttpURLConnection)urls[0].openConnection();
//                int response = urlConnection.getResponseCode();
//
//                if(response == HttpURLConnection.HTTP_OK){
//
//                    InputStream inputStream = urlConnection.getInputStream();
//                    BufferedReader reader = new BufferedReader(
//                            new InputStreamReader(inputStream)
//                    );
//
//                    StringBuilder builder = new StringBuilder();
//                    String line = "";
//
//                    while((line = reader.readLine()) != null){
//                        builder.append(line);
//                    }
//
//                    inputStream.close();
//                    reader.close();
//                    return new JSONObject(builder.toString());
//                }else{
//                    Snackbar.make(findViewById(R.id.coordinatorLayout)
//                    , R.string.connect_error, Snackbar.LENGTH_LONG);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }finally {
//                if(urlConnection != null)
//                    urlConnection.disconnect();
//            }
//
//            return null;
//        }

//        @Override
//        protected void onPostExecute(JSONObject jsonObject) {
//            super.onPostExecute(jsonObject);
//        }
//    }

}
