package strathmore.com.sqlitelab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert: ", "Inserting ...");
        db.addContact(new Contact( "Ravi", "9100000000"));
        db.addContact(new Contact( "Srinivaa", "919999999"));
        db.addContact(new Contact( "Ravi", "95333333333"));


        Log.d("Insert: ", "Inserting ...");
        db.addStudents(new Students("BBIT", "third"));
        db.addStudents(new Students( "DBIT", "third"));
        db.addStudents(new Students( "BTC", "third"));

        //Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.get_id()+ " ,Name:" + cn.get_name() + " ,Phone: " + cn.get_phone_number();
            //Write Contact to log
            Log.d("Name:", log);

        }
        //Reading all Student
        Log.d("Reading: ", "Reading all contacts..");
        List<Students> students = db.getAllStudents();

        for (Students st : students) {
            String log = "Id: " + st.getStd_id()+ " ,Name:" + st.getCourse_name() + " ,Phone: " + st.get_year();
            //Write Contact to log
            Log.d("Name:", log);

        }


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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

}
