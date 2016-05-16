package com.broadgames.stealthperformancecommunication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.broadgames.stealthperformancecommunication.session.Session;

public class MainActivity extends AppCompatActivity {

    Session user_Session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if(loginEditText.getText().toString().equalsIgnoreCase("login") &&
                passwordEditText.getText().toString().equalsIgnoreCase("password")){
            Toast.makeText(this, loginEditText.getText().toString()+" logged in as Super-User",
                    Toast.LENGTH_LONG).show();
            user_Session = new Session(Session.CLIENT_QUATERBACK);
            if(user_Session.getUser_session()==Session.CLIENT_QUATERBACK){
                
            }
        } else {
            Toast.makeText(this, "Incorrect Login Credentials",
                    Toast.LENGTH_LONG).show();
            loginEditText.setText("");
            passwordEditText.setText("");
        }
    }
}
