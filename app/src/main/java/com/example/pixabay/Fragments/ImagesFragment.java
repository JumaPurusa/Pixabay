package com.example.pixabay.Fragments;


import android.os.Bundle;
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
public class ImagesFragment extends Fragment {

    public static final String TAG = ImagesFragment.class.getName();
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private RecyclerView recyclerView;

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

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
