package com.broadgames.stealthperformancecommunication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.broadgames.stealthperformancecommunication.session.Session;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.List;

public class ShowDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_description);
        displayDescription();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_description, menu);
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

    public void displayDescription(){
        StealthPerformanceApplication.getFirebase().child(Session.clientMessage).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Session.clientMessage="";
                try {
                    setTextView(snapshot.getValue().toString());
                } catch (Exception e) {
                    Log.e("GetData", e.toString());
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
    }

    private void setTextView(String clientMessage) {
        TextView showStrategyText = (TextView)findViewById(R.id.showDescription);
        showStrategyText.setText(clientMessage);
    }
}
