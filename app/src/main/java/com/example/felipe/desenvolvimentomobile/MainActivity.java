package com.example.felipe.desenvolvimentomobile;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sobre(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Sobre o Grupo")
                .setMessage("Felipe Miyai RA: 20351694")
                .setNeutralButton("OK", null)
                .show();
    }

    public void btnAbout(View view) {

        startActivity(new Intent(this,AboutActivity.class));
        finish();
    }

    public void btnComecar(View view) {

        startActivity(new Intent(this,CameraActivity.class));

        finish();
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    //  getMenuInflater().inflate(R.menu.menu_main, menu);
    //  return true;
    //}
/*
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

*/
}
