package com.example.felipe.desenvolvimentomobile;

/**
 * Created by felipe on 28/03/2015.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AboutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void botaoExibirNovaTela(View view) {

        Intent intent = new Intent();
        intent.setClass(AboutActivity.this,
                MainActivity.class);

        startActivity(intent);

        finish();
    }


}
