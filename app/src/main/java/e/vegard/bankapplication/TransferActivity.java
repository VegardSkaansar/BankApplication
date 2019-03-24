package e.vegard.bankapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TransferActivity extends AppCompatActivity implements RecyclerViewAdapter.OnNoteListener {

    //RecyclerView adapter and layout
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //text
    String[] persons = new String[]{"guy1", "guy2", "guy3", "guy4", "guy5", "guy6"};

    // this variable will remember the last recipient clicked
    private String mPerson;

    // this variable will be an bool default false
    // and when a person choose one of the list this will be true
    private boolean choosed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        //variable from the different components
        final TextView header = findViewById(R.id.display_balance);
        final Button back = findViewById(R.id.btn_back);
        final EditText transaction = findViewById(R.id.txt_amount);
        final Button add = findViewById(R.id.btn_pay);
        final TextView error = findViewById(R.id.lbl_amount_check);
        mRecyclerView = (RecyclerView) findViewById(R.id.transfer_recycleview);

        //setting fixed size for recyclerview
        mRecyclerView.setHasFixedSize(true);

        //use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //adapter
        mAdapter = new RecyclerViewAdapter(persons, this);
        mRecyclerView.setAdapter(mAdapter);

        //sets the button to disable until we click a person
        add.setEnabled(false);

        //get the Bundle
        Bundle bundle = getIntent().getExtras();

        //extract data from the mainactivity
        final int balanceValue = bundle.getInt("BALANCE");

        //setting the text to be the balance from mainactivity
        header.setText("" + balanceValue);
        error.setText("");


        //onclick back to get to main activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //onclick subtract to the balance
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                String strDate = dateFormat.format(currentTime);
                    Bundle bundle = new Bundle();
                    bundle.putString("PAYTRANSACTION", transaction.getText().toString());
                    bundle.putString("DATETRANSACTION", strDate);
                    bundle.putString("NAMETRANSACTION", mPerson);
                    final Intent i = new Intent(TransferActivity.this, MainActivity.class);
                    i.putExtras(bundle);
                    setResult(RESULT_OK, i);
                    finish();
            }
        });

        transaction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(transaction.getText().toString().matches("")) {
                    add.setEnabled(false);
                } else {
                   int payment = Integer.parseInt(transaction.getText().toString());
                    Log.i("",payment+" is a number and balancevalue"+balanceValue);
                    if (choosed == true) {
                        error.setText("");
                        if (balanceValue < payment || 1 > payment) {
                            Log.i("", "YAY");
                            add.setEnabled(false);
                            error.setText("Amount is outside of bounds");
                        } else {
                            Log.i("", "NAY");
                            add.setEnabled(true);
                            error.setText("");
                        }
                    } else {
                        add.setEnabled(false);
                        error.setText("You have not choose a person");
                    }
                }
            }
        });


    }

    @Override
    public void onNoteClick(int position) {
        // button reference to pay button is disabled when activity launches
        // so when one of the recipient is selected we will enable the button
        final Button pay = findViewById(R.id.btn_pay);
        // choosed true
        choosed = true;


        // setting the global variable to be the person last clicked
        mPerson = persons[position];

        //pay.setEnabled(true);
        Toast.makeText(getApplicationContext(),persons[position],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoteLongClick(int position) {
        // nothing will happend here
    }
}
