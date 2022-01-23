package com.example.testtask2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.onesignal.OneSignal;

public class FirstActivity extends AppCompatActivity {

    Intent intent;

    public static final String IS_PUSH_LOADED = "boolean_key";

    SharedPreferences sPref;

    private final static String ONESIGNAL_APP_ID = "05763373-a478-418b-92ae-6119375ccc3a";
    final static String intentKeyCategory = "keycategory";
    final static String intentKeyPushUrl = "keypushurl";

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // подключение пуша
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


        // проверка на открытие ранее через пуш

        OneSignal.setNotificationOpenedHandler(osNotificationOpenedResult -> {

            // если открывали через пуш, сохраняем открытие

            SharedPreferences.Editor ed = sPref.edit();
            ed.putBoolean(IS_PUSH_LOADED, true);
            ed.apply();
            loadWebView();
        });

        sPref = getPreferences(MODE_PRIVATE);

        if( sPref.getBoolean(IS_PUSH_LOADED, false)) {
            loadWebView();
        } else {
            gotoJokes();
        }

    }

    private void loadWebView(){

        // ссылку можно брать с пуша и передавать здесь

        intent = new Intent(this, FourthActivity.class);
        intent.putExtra(intentKeyPushUrl, "https://github.com/VladiAvram");
        startActivity(intent);
    }

    private void gotoJokes(){

        intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
