package edu.neu.madcourse.numad20su_hassankhan;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import edu.neu.madcourse.numad20su_hassankhan.nameAndLinkURL.NameAndLinkURLContent;

public class MainActivity extends AppCompatActivity implements LinkCollectorFragment.OnListFragmentInteractionListener {

    // initialize text stores
    private static String clickyPressedText = "Pressed: ";
    private String linkName_store = "";
    private String linkURL_store = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // floating action button to add new links
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Input Name and Link URL");

                // set up text input boxes
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText linkNameBox = new EditText(MainActivity.this);
                linkNameBox.setHint("Link Name");
                layout.addView(linkNameBox);

                final EditText linkURLBox = new EditText(MainActivity.this);
                linkURLBox.setHint("Link URL (start with 'https://')");
                layout.addView(linkURLBox);

                dialog.setView(layout);

                // Set up buttons
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        linkName_store = linkNameBox.getText().toString();
                        linkURL_store = linkURLBox.getText().toString();

                        // add item to NameAndLinkURL content, if not empty
                        if (linkName_store.equals("") || linkURL_store.equals("")) {
                            Snackbar.make(view, "Please don't leave any fields blank", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        } else {
                            NameAndLinkURLContent
                                    .addItem(NameAndLinkURLContent.createNameAndLinkURLItem(linkName_store, linkURL_store));

                            Snackbar.make(view, "Successfully Added New Link", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
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

    public static String getClickyPressedText() {
        return clickyPressedText;
    }

    public static void setClickyPressedText(String textToSet) {
        clickyPressedText = textToSet;
    }

    @Override
    public void onListFragmentInteraction(NameAndLinkURLContent.NameAndLinkURLItem item) {

    }
}
