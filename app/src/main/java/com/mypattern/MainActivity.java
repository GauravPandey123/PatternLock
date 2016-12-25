package com.mypattern;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.mylibrary.PatternLockLibrary;

public class MainActivity extends ActionBarActivity {

    private PatternLockLibrary lock9View;

    private static String MY_PREFS_NAME = "PatternLock";
    private static String PATTERN_KEY;
    
    SharedPreferences prefs;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        lock9View = (PatternLockLibrary) findViewById(R.id.lock_9_view);
        
        lock9View.setCallBack(new PatternLockLibrary.CallBack() {

            @Override
            public void onFinish(String password) {
            	PATTERN_KEY = prefs.getString("Pattern", "invalid");
            	
            	if(PATTERN_KEY.equals("invalid")){
            		Toast.makeText(MainActivity.this, "Options --> Create new Pattern", Toast.LENGTH_LONG).show();
            	}else{
            		if(password.equals(PATTERN_KEY)){
                		Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                		startActivity(intent);
                		finish();
                		Toast.makeText(MainActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                	}else{
                		Toast.makeText(MainActivity.this, "Pattern incorrect!", Toast.LENGTH_SHORT).show();
                	}
            	}  
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contact:
                new AlertDialog.Builder(this)
                        .setTitle("Android-Lock9View")
                        .setMessage(
                                "Email : gaurav.iec12@gmail.com\n" +
                                "Blog  : https://medium.com/me/stories/public/\n")
                        .setPositiveButton("OK", null)
                        .show();
                return true;
                
            case R.id.create_new_pattern:
            	Intent intent = new Intent(MainActivity.this, ChangeActivity.class);
            	startActivity(intent);
            	finish();
                return true;
                
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
