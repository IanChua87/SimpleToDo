package sg.edu.rp.c346.id22024905.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etMovie;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvMovies;
    Spinner spnAddRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMovie = findViewById(R.id.editTextMovie);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        lvMovies = findViewById(R.id.listViewMovies);
        spnAddRemove = findViewById(R.id.spinnerAddDelete);

        ArrayList<String> alMovies = new ArrayList<String>();
        ArrayAdapter aaMovie = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alMovies);
        lvMovies.setAdapter(aaMovie);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    etMovie.setHint("Type in a new task here");
                    btnAdd.setEnabled(true);
                    btnDelete.setEnabled(false);
                }
                else{
                    etMovie.setHint("Type in the index of the stuff to be removed");
                    btnDelete.setEnabled(true);
                    btnAdd.setEnabled(false);
                }
                btnClear.setEnabled(true);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie = etMovie.getText().toString();
                alMovies.add(movie);
                aaMovie.notifyDataSetChanged();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alMovies.isEmpty()) {
                    Toast.makeText(MainActivity.this,"You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else {
                    String sIndex = etMovie.getText().toString();
                    int index = Integer.parseInt(sIndex);
                    if(index >=0 && index<alMovies.size()) {
                        alMovies.remove(index);
                        aaMovie.notifyDataSetChanged();
                    } else{
                        Toast.makeText(MainActivity.this,"Wrong index number" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alMovies.clear();
                aaMovie.notifyDataSetChanged();
            }
        });

       lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener(){
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String colour = alMovies.get(position);
               Toast.makeText(MainActivity.this, colour, Toast.LENGTH_SHORT).show();
           }
       });

    }
}