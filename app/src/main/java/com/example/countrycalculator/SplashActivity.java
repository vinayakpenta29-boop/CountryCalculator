package com.example.countrycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.countrycalculator.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView appLogo;
    private TextView appName, createdBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appLogo = findViewById(R.id.appLogo);
        appName = findViewById(R.id.appName);
        createdBy = findViewById(R.id.createdBy);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        appLogo.startAnimation(fadeIn);
        appName.startAnimation(fadeIn);
        createdBy.startAnimation(fadeIn);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}
