package com.example.helloandroid.activities;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.helloandroid.R;

public class BasicViewsActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_ACTIVITY";
    private static final String NOTIFICATION_CHANNEL = "notification_test";

    private NotificationManager manager;
    private Notification notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views);
        setupButtonFunc();
        setupNotification();
        setupToolBar();
    }

    private void setupToolBar() {
        Toolbar toolBar = findViewById(R.id.tool_bar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("toolBar", "click");
            }
        });
    }

    private void setupButtonFunc() {
        Button btn = findViewById(R.id.btn_a);

//        btn.setOnClickListener(view -> Log.e(TAG, "onClick"));

        btn.setOnLongClickListener(view -> {
            Log.e(TAG, "onLongClick");
            // 如果此处返回true, 则不会执行onClick方法
            return false;
        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e(TAG, "onTouch"+ motionEvent.toString());
                return false;
            }
        });
    }

    private void setupNotification(){
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, "测试",
                    NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
                .setContentTitle("通知")
                .setContentText("哈哈哈哈哈")
                .setSmallIcon(R.drawable.ic_baseline_account_box_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.test))
                .setColor(0xFF784839) //设置小图标颜色
                .setContentIntent(pendingIntent) //设置点击通知后动作
                .setAutoCancel(true) //点击通知后自动取消
                //.setWhen() 设置创建时间
                .build();
    }

    public void onBtnClick(View view) {
        Log.e(TAG, "onClick");
    }

    public void login(View view) {
        EditText userName = findViewById(R.id.user_name);
        EditText password = findViewById(R.id.password);
        Log.e(TAG,
                "userName: " + userName.getText().toString() + "\n" +
                        "password: " + password.getText().toString());
    }

    public void sendNotification(View view) {
        manager.notify(1, notification);
    }

    public void cancelNotification(View view) {
        manager.cancel(1);
    }

    public void showDialog(View view) {
        View dialog_view = getLayoutInflater().inflate(R.layout.dialog_view, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setIcon(R.drawable.ic_baseline_security_24)
                .setTitle("dialog Title")
                .setMessage("dialog content")
                .setView(dialog_view)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("dialog", "确认点击");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("dialog", "取消点击");
                    }
                })
                .setNeutralButton("信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("dialog", "信息按钮点击");
                    }
                })
                .create();
        dialog.show();
    }

    public void showPopupWindow(View view) {
        View popup_view = getLayoutInflater().inflate(R.layout.popup_window, null);

        PopupWindow popupWindow = new PopupWindow(popup_view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAsDropDown(view);

        popup_view.findViewById(R.id.pop_btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("popupWindow", "Btn1 Click");
                popupWindow.dismiss();
            }
        });
        popup_view.findViewById(R.id.pop_btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("popupWindow", "Btn2 Click");
                popupWindow.dismiss();
            }
        });
    }
}