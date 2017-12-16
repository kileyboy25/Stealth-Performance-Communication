package com.broadgames.stealthperformancecommunication;

import android.app.Application;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.broadgames.stealthperformancecommunication.session.Session;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.List;

/**
 * Created by Sugandanb on 19-05-2016.
 */


public class StealthPerformanceApplication extends Application {

    static Firebase myFirebaseRef;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        //DATABASE URL used here
        myFirebaseRef = new Firebase("https://stealthperformancecommunication.firebaseio.com/");
       /* myFirebaseRef.createUser("mayur.arge@gmail.com","mayur",new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                //Toast.makeText(MainActivity.this, "User created", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", "Username created : " + result.get("uid"));
                //goToNextScreen();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d("MainActivity", "User creation failed" + firebaseError.getMessage());
            }
        });*/
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Session.message.clear();
        StealthPerformanceApplication.addData("MESSAGE", Session.message);
    }

    public static void addData(String key, Object value){
        StealthPerformanceApplication.getFirebase().child(key).setValue(value);
    }

    public static Firebase getFirebase(){
        return myFirebaseRef;
    }

}
