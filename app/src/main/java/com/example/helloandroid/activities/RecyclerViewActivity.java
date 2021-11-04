package com.example.helloandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.helloandroid.R;
import com.example.helloandroid.adapters.RecyclerListAdapter;
import com.example.helloandroid.models.ListItemBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        List<ListItemBean> titleList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            titleList.add(new ListItemBean("item" + i));
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        // ListView
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // GridView
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        // Water Fall
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerListAdapter adapter = new RecyclerListAdapter(titleList, this);

        //设置点击事件
        adapter.setRecyclerItemClickListener(new RecyclerListAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                Log.e("RecyclerItemClick:", "" + position);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}