package com.example.pixabay.Fragments;


import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pixabay.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {

    public final static String TAG = VideosFragment.class.getName();
    public final static String ARG_PAGE = "ARG_PAGE";

    private int mPage;


    public VideosFragment() {
        // Required empty public constructor
    }

    public static VideosFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        VideosFragment videosFragment = new VideosFragment();
        videosFragment.setArguments(args);
        return videosFragment;
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
        return inflater.inflate(R.layout.fragment_videos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }
}
