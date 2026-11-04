package com.example.myfirstapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.core.Counter;


public class MainActivity extends AppCompatActivity {

    private Counter counter;
    private static final String CHANNEL_ID = "my_channel_id";
    private TextView textView;
    private TextView textViewCounter;
    private Button button;
    private Button buttonAdd;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = new Counter(0, 1);


        textView = findViewById(R.id.textView);
        textViewCounter = findViewById(R.id.textViewCounter);
        button = findViewById(R.id.button);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonReset = findViewById(R.id.buttonReset);

        buttonAdd.setOnClickListener(v -> {
            counter.increment();
            updateUi();
        });

        buttonReset.setOnClickListener(v -> {
            counter.reset();
            updateUi();
        });





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        createNotificationChannel();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Кнопка нажата!");
            }
        });



    }

    private void updateUi() {
        textViewCounter.setText(String.valueOf(counter.getCount()));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_info) {
            showDialog();
            return true;
        } else if (id == R.id.action_notify) {
            sendNotification();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Внимание!");
        builder.setMessage("Это диалоговое окно?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Вы нажали ДА", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Моё первое приложение!")
                .setContentText("Это оповещающее сообщение")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        notificationManagerCompat.notify(1, builder.build());
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Мой канал";
            String description = "Канал для оповещений";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}