package e.vegard.bankapplication;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //the constant for transfer activity
    final public static int PARAM = 1;
    //the constant for transaction activity
    final public static int PARAM2 = 2;
    //private balance int we will use to display and edit our money
    private int txtBalance = 0;
    //int that takes care of the last inserted id in arraylist
    private int lastInsertedId = 1;

    //using java list to store all my data in memory because we do not
    // know the length of the transactions
    public static ArrayList<TransferData> store = new ArrayList<TransferData>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //here we will get the textviews we want to update and buttons
        final TextView balance = findViewById(R.id.balance_amount);
        final Button btnTransaction = findViewById(R.id.btn_transaction);
        final Button btnTransfer = findViewById(R.id.btn_transfer);

        //when oncreate happends we need to update the view to the right
        //balance
        txtBalance = (int) (Math.random()*20+91);
        balance.setText(""+txtBalance);

        //current time and setting it
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String strDate = dateFormat.format(currentTime);

        store.add(new TransferData(lastInsertedId, "guy1", strDate, txtBalance, txtBalance));

        //making the button clickable
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we need to send the balance amount over to the new activity
                //from there we can use that value to update and send back the new value
                Bundle bundle = new Bundle();
                //adding the balance into the bundle as Int;
                bundle.putInt("BALANCE",txtBalance);
                //we making a new intent so we can use this to send within a bundle
                final Intent i = new Intent(MainActivity.this, TransferActivity.class);
                //lets now put the bundle into the intent
                i.putExtras(bundle);

                startActivityForResult(i, PARAM);
            }
        });

        btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we will send the list with all the transactions to transaction activit
                //declaring the intent
                final Intent i = new Intent(MainActivity.this, TransactionsActivity.class);
                //lets now send over to the transaction activity
                startActivity(i);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final TextView balance = findViewById(R.id.balance_amount);
           super.onActivityResult(requestCode, resultCode, data);
           // if (requestCode == PARAM) {

                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();

                    // gets the pay money spended when finish from TransferActivity
                    final String pay = bundle.getString("PAYTRANSACTION");
                    int result = Integer.parseInt(pay);

                    // gets the date of that transaction
                    final String date = bundle.getString("DATETRANSACTION");

                    // gets the selected recipient from the list
                    final String mPerson = bundle.getString("NAMETRANSACTION");

                    // store the variable in memory
                    store.add(new TransferData(lastInsertedId, mPerson, date, result, txtBalance));

                    //incrementing the lastinsertedId
                    lastInsertedId++;
                    // after added into list we need to update the balance variable
                    txtBalance = txtBalance - result;
                    balance.setText(""+txtBalance);

                }
            }

    //}

}



