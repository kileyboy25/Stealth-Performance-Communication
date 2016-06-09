package com.broadgames.stealthperformancecommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.broadgames.stealthperformancecommunication.session.Session;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.List;

public class ClientStrategyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_strategy);
        displayStrategy();
        Log.d("Client", Session.clientMessage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_client_strategy, menu);
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

    public void displayStrategy(){
        StealthPerformanceApplication.getFirebase().child("MESSAGE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Session.clientMessage="";
                try {
                    List<String> message = (List<String>) snapshot.getValue();
                    Log.d("GetData",message.toString());
                    for (int i=0;i< message.size();i++) {
                        if(i<=1) {
                            Session.clientMessage += message.get(i);
                        } else {
                            Session.clientMessage += "<font color="+message.get(i).toUpperCase()+">"+message.get(i)+"</font>";
                        }
                    }
                    setTextView(Session.clientMessage);
                } catch (Exception e) {
                    Log.e("GetData", e.toString());
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
    }

    private void setTextView(String message){
        TextView showStrategyText = (TextView)findViewById(R.id.showStrategyText);
        showStrategyText.setText(Html.fromHtml(message));
    }

    public void showDescription(View view){
        Intent intent = new Intent(this, ShowDescriptionActivity.class);
        startActivity(intent);
    }
}