package com.example.pixabay.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pixabay.Adapters.PostsAdapter;
import com.example.pixabay.MainActivity;
import com.example.pixabay.Models.Post;
import com.example.pixabay.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment {

    public static final String TAG = ImagesFragment.class.getName();
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private List<Post> imagePostsList;
    private RecyclerView recyclerView;
    private PostsAdapter adapter;

    private HttpURLConnection connection;


    public ImagesFragment() {
        // Required empty public constructor
    }

    public static ImagesFragment newInstance(int page){

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ImagesFragment imagesFragment = new ImagesFragment();
        imagesFragment.setArguments(args);
        return imagesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_images, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        imagePostsList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PostsAdapter(getActivity());

        new GetImagesPostsTask().execute(createURL());
    }

    public URL createURL(){
        String apiKey = getString(R.string.api_key);
        String baseURL = getString(R.string.web_service_url);

        try {
            String stringURL = baseURL + apiKey;  // + "&q=" + URLEncoder.encode(search, "UTF-8");

            return new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public class GetImagesPostsTask extends AsyncTask<URL, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(URL... urls) {

            try {
                connection = (HttpURLConnection)urls[0].openConnection();
                int responseCode = connection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    StringBuilder builder = new StringBuilder();

                    try(BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream())
                    )){

                        String line;

                        while( (line = reader.readLine()) != null){
                            builder.append(line);
                        }

                    }catch (IOException e){
                        Snackbar.make(MainActivity.coordinatorLayout,
                                R.string.connect_error, 2000)
                                .show();
                        e.printStackTrace();
                    }

                    Log.d(TAG, "doInBackground: builder: " + builder.toString());
                    return new JSONObject(builder.toString());
                }else{
                    Snackbar.make(MainActivity.coordinatorLayout,
                            R.string.connect_error, 2000)
                            .show();
                }

            } catch (IOException e) {
                Snackbar.make(MainActivity.coordinatorLayout,
                        R.string.connect_error, 2000)
                        .show();
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally{
                connection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            Log.d(TAG, "onPostExecute: jsonObject: " + jsonObject.toString());
            if(jsonObject != null){
                convertToPostArrayList(jsonObject);
                recyclerView.setAdapter(adapter);
            }else{

                Snackbar.make(MainActivity.coordinatorLayout,
                        "Error Occurred", 2000).show();
            }
        }
    }

    public void convertToPostArrayList(JSONObject jsonObject){

        imagePostsList.clear();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("hits");

            for(int i=0; i<jsonArray.length(); i++){
                Post imagePost = new Post();

                JSONObject postObect = jsonArray.getJSONObject(i);

                imagePost.setPostURL(postObect.getString("webformatURL"));
                imagePost.setUserImaageURL(postObect.getString("userImageURL"));
                imagePost.setUser(postObect.getString("user"));
                imagePost.setDescription(postObect.getString("tags"));
                imagePost.setViews(postObect.getString("views"));
                imagePost.setLikes(postObect.getString("likes"));
                imagePost.setComments(postObect.getString("comments"));

                imagePostsList.add(imagePost);
            }

            adapter.addListItems(imagePostsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
