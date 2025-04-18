package com.example.myclickbot;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning = false;
    private Button toggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleBtn = findViewById(R.id.toggleButton);
    }

    public void toggleService(View view) {
        isRunning = !isRunning;
        MyAccessibilityService.setRunning(isRunning);
        toggleBtn.setText(isRunning ? "Tắt chế độ tìm số 1" : "Bật chế độ tìm số 1");
    }

    public void openAccessibilitySettings(View view) {
        startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
    }
}