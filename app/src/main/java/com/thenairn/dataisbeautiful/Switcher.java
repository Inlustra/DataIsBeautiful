package com.thenairn.dataisbeautiful;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class Switcher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialActivity(this);
    }

    public static void initialActivity(Context context) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            context.startActivity(new Intent(context, DashboardActivity.class));
        } else {
            context.startActivity(new Intent(context, OnboardingActivity.class));
        }
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).finish();
        }
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }
}
