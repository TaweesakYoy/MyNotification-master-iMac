package com.example.taweesak.mynoti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private String tag = "3NovV1";

    @Override
    public void onReceive(Context context, Intent intent) {

        //test Add********
        String Title = intent.getStringExtra("calendar");;
       // String Title = intent.getStringExtra(context.getString(R.string.titttle));


        Intent intent1 = new Intent(context, ShowNotiActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // intFlag from MainFragment

        //test Add********
        //intent1.putExtra("calendar1", Title);

        context.startActivity(intent1);

        Log.d(tag, "Title get ==> " + Title);

    }


}// Main Class
