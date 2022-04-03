package edu.uga.cs.project4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.Context;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    public Country[] readCountries = new Country[195];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this test proves pushing works

        Resources res = getResources();

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(R.drawable.exquizit);

        TextView txt = findViewById(R.id.textView);
        InputStream in = res.openRawResource(R.raw.splash);

        byte[] a = new byte[0];

        try {
            a = new byte[in.available()];
            in.read(a);
            txt.setText(new String(a));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //opens csv file and inserts values to Country array
        try {
            InputStream in_s = getAssets().open("country_continent.csv");

            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );

            String[] nextRow;
            int i = 0;
            while( (nextRow = reader.readNext() ) != null) {
                readCountries[i].name = nextRow[0];
                readCountries[i].continent = nextRow[1];
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //creates database from Country Array
        QuizDBHelper db = QuizDBHelper.getInstance(this);
        SQLiteDatabase writeDB = db.getWritableDatabase();
        int count = 0;
        while (count<195){
            ContentValues cv = new ContentValues();
            cv.put(QuizDBHelper.COUNTRY_NAME, readCountries[count].name);
            cv.put(QuizDBHelper.COUNTRY_CONTINENT, readCountries[count].continent);

            long id = writeDB.insert(QuizDBHelper.TABLE_COUNTRIES, null, cv);

            readCountries[count].setID(id);
            count++;
        }

        final Button button1 = findViewById(R.id.button);
        button1.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ) {
                Intent intent = new Intent( getApplicationContext(), QuizActivity.class );
                startActivity(intent);
            }
        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ) {
                Intent intent = new Intent( getApplicationContext(), ResultsActivity.class );
                startActivity(intent);
            }
        });






    }

    private class MyTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }



}