package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.helloandroid.activities.AnimationActivity;
import com.example.helloandroid.activities.BasicViewsActivity;
import com.example.helloandroid.activities.RecyclerViewActivity;
import com.example.helloandroid.adapters.MainListAdapter;
import com.example.helloandroid.models.ListItemBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ListItemBean> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupListItems();
        setupListView();
    }

    private void setupListItems() {
        titleList.add(new ListItemBean("basic Views"));
        titleList.add(new ListItemBean("RecyclerView"));
        titleList.add(new ListItemBean("animation"));

    }

    private void setupListView() {
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new MainListAdapter(titleList, this));
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            handleAction(i);
        });
    }

    private void handleAction(int index) {
        switch (titleList.get(index).getTitle()) {
            case "basic Views":
                startNewActivity(BasicViewsActivity.class);
                break;
            case "RecyclerView":
                startNewActivity(RecyclerViewActivity.class);
                break;
            case "animation":
                startNewActivity(AnimationActivity.class);
                break;
            default:
                break;
        }
    }

    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}