package com.example.myfirstapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class EditTextMain extends AppCompatActivity {

    private static final String CHANNEL_ID = "my_channel_id";
    private EditText editTextDisplay;
    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text_main);

        editTextDisplay = findViewById(R.id.editTextDisplay);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        createNotificationChannel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            showEditDialog();
            return true;
        } else if (id == R.id.action_notify) {
            sendNotification();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showEditDialog() {
        final EditText input = new EditText(this);
        input.setHint("Введите текст");
        input.setText(editTextDisplay.getText().toString());
        input.setSelection(input.getText().length());

        new AlertDialog.Builder(this)
                .setTitle("Редактировать текст")
                .setView(input)
                .setPositiveButton("ОК", (dialog, which) -> {
                    String text = input.getText().toString();
                    editTextDisplay.setText(text);
                })
                .setNegativeButton("ОТМЕНА", (dialog, which) -> dialog.cancel())
                .show();
    }

    private void sendNotification() {
        String text = editTextDisplay.getText().toString().trim();
        Log.d("NOTIF_DEBUG", "Текст: '" + text + "'");

        if (text.isEmpty()) {
            Toast.makeText(this, "Текстовое поле пустое!", Toast.LENGTH_SHORT).show();
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Моё приложение")
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            notificationManager.notify(notificationId++, builder.build());
            Toast.makeText(this, "Уведомление отправлено!", Toast.LENGTH_SHORT).show();
            Log.d("NOTIF_DEBUG", "Отправлено с ID: " + (notificationId - 1));
        } else {
            Toast.makeText(this, "Нет разрешения на уведомления", Toast.LENGTH_SHORT).show();
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Мой канал";
            String description = "Канал для оповещений";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

}
