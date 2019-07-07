package com.example.pixabay;

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

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        ActionBar actionBar = getSupportActionBar();
        assert  actionBar != null;
        actionBar.setHomeButtonEnabled(true);

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
}
