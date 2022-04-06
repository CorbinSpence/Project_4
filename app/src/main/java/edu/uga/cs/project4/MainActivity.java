package edu.uga.cs.project4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Context context;
    public Country[] readCountries = new Country[195];
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;
        Resources res = getResources();

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(R.drawable.exquizit);

        txt = findViewById(R.id.textView);
        InputStream in = res.openRawResource(R.raw.splash);

        // read and display splash screen text
        byte[] a = new byte[0];
        try {
            a = new byte[in.available()];
            in.read(a);
            txt.setText(new String(a));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // async task to load database
        new MyAsyncTask(context).execute();

        // button to go to quiz
        final Button button1 = findViewById(R.id.button);
        button1.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ) {
                Intent intent = new Intent( getApplicationContext(), QuizActivity.class );
                startActivity(intent);
            }
        });

        // button to go to results
        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ) {
                Intent intent = new Intent( getApplicationContext(), ResultsActivity.class );
                startActivity(intent);
            }
        });

    }

    // method to setup database( COUNTRIES TABLE )
    public void databaseSetup() {

        //opens csv file and inserts values to Country array
        try {
            InputStream in_s = getAssets().open("country_continent.csv");

            CSVReader reader = new CSVReader( new InputStreamReader( in_s ) );

            String[] nextRow;
            int i = 0;
            while( (nextRow = reader.readNext() ) != null) {
                readCountries[i] = new Country();
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
        while (count<195) {
            ContentValues cv = new ContentValues();
            cv.put(QuizDBHelper.COUNTRY_NAME, readCountries[count].name);
            cv.put(QuizDBHelper.COUNTRY_CONTINENT, readCountries[count].continent);

            long id = writeDB.insert(QuizDBHelper.TABLE_COUNTRIES, null, cv);
            //str = str + "" + readCountries[count].name + "|" + readCountries[count].continent + "\n";
            readCountries[count].setID(id);
            count++;
        }
        //return str;
    }

    private class MyAsyncTask extends AsyncTask<String, String, String> {
        Context context;
        String result;

        public MyAsyncTask (Context context) {
            this.context = context;
        }

        //@Override
        protected String doInBackground(String... args) {
            databaseSetup();
            return "1";
        }

        protected void onPostExecute(String result) {
            // implement
        }


    }

}