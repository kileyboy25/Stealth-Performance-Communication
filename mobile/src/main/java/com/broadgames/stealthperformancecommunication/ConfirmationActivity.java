package com.broadgames.stealthperformancecommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.broadgames.stealthperformancecommunication.session.Session;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        TextView strategyTextView = (TextView)findViewById(R.id.finalStrategyTextView);
        String message = "";
        for(int i = 0;i<Session.message.size();i++){
            if(i<=1) {
                message += Session.message.elementAt(i);
            }else{
                message +="<font color="+Session.message.elementAt(i).toUpperCase()+">"+ Session.message.elementAt(i)+"</font>";
            }
        }
        strategyTextView.setText(Html.fromHtml(message));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirmation, menu);
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

    public void onConfirmSelection(View view){
        //Send the message to clients and flush
        StealthPerformanceApplication.addData("MESSAGE", Session.message);
        Session.message.clear();
        Intent intent = new Intent(this, ChooseLetterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Session.message.pop();
    }

    public void onStartOver(View view){
        //Flush the message
        Session.message.clear();
        Intent intent = new Intent(this, ChooseLetterActivity.class);
        startActivity(intent);
    }
}