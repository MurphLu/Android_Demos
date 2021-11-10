package com.example.helloandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.helloandroid.R;
import com.example.helloandroid.adapters.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        setupViewPager();
    }

    private void setupViewPager() {
        List<View> list = new ArrayList<>();
        LayoutInflater lf = getLayoutInflater().from(this);
        View view1 = lf.inflate(R.layout.view_pager_01, null);
        View view2 = lf.inflate(R.layout.view_pager_02, null);
        View view3 = lf.inflate(R.layout.view_pager_03, null);
        list.add(view1);
        list.add(view2);
        list.add(view3);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyViewPagerAdapter(list));
    }

}