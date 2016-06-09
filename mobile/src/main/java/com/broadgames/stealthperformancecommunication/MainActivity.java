package com.broadgames.stealthperformancecommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.broadgames.stealthperformancecommunication.session.Session;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    Session user_Session;
    boolean loginSuccess = false;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Start the background Firebase activity
        serviceIntent = new Intent(this, SendNotificationService.class);
        this.stopService(serviceIntent);

        //Attempt to show keyboard
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        relativeLayout.clearFocus();

        // Clear userSession
        user_Session = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkLogin(View view){
        EditText loginEditText = (EditText)findViewById(R.id.loginText);
        EditText passwordEditText = (EditText)findViewById(R.id.passwordText);
        login(loginEditText.getText().toString(), passwordEditText.getText().toString());
        /*if(loginSuccess){
            Toast.makeText(this, loginEditText.getText().toString()+" logged in as Super-User",
                    Toast.LENGTH_LONG).show();
            if(user_Session.getUser_session()==Session.CLIENT_QUATERBACK){
                Intent intent = new Intent(this, ChooseLetterActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, ClientStrategyActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Incorrect Login Credentials",
                    Toast.LENGTH_LONG).show();
            //loginEditText.setText("");
            //passwordEditText.setText("");
        }*/
    }
    public void login(final String username, final String password) {
        StealthPerformanceApplication.getFirebase().authWithPassword(username, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Log.d("MainActivity", "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                if (authData.getUid().equalsIgnoreCase("2b6d4e49-6054-4169-b5d8-813715fc59ec")) {
                    Session.user_session = Session.CLIENT_QUATERBACK;
                    gotoQuaterbackActivity();
                } else {
                    Session.user_session = Session.CLIENT_PLAYER;
                    gotoPlayerActivity();
                }
                loginSuccess = true;
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.d("MainActivity", "Error while logging in : " + firebaseError.getMessage());
                passwordFail();
                //createNewUser(username, password);
                loginSuccess = false;
            }
        });
    }

    private void gotoPlayerActivity() {
        EditText loginEditText = (EditText)findViewById(R.id.loginText);
        Toast.makeText(this, loginEditText.getText().toString()+" logged in as Super-User",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ClientStrategyActivity.class);
        this.startService(serviceIntent);
        startActivity(intent);
    }

    private void gotoQuaterbackActivity() {
        EditText loginEditText = (EditText)findViewById(R.id.loginText);
        Toast.makeText(this, loginEditText.getText().toString()+" logged in as Super-User",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ChooseLetterActivity.class);
        startActivity(intent);
    }

    private void passwordFail(){
        Toast.makeText(this, "login failed",Toast.LENGTH_LONG).show();
    }
}
