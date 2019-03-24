package e.vegard.bankapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity implements RecyclerViewAdapter.OnNoteListener {

    //RecyclerView adapter and layout
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        // get the recycleview and sets it to a variable
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_list);

        //setting fixed size for recyclerview
        mRecyclerView.setHasFixedSize(true);

        //use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //array
        ArrayList<String> gg = new ArrayList<String>();
        MainActivity.store.forEach(object -> gg.add(object.getDate() + "   |   " + object.getName() + "   |   " + object.getAmount() + "   |   " + object.getBalance()));

        String[] arrayname = gg.toArray(new String[0]);
        //adapter
        mAdapter = new RecyclerViewAdapter(arrayname, this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onNoteClick(int position) {
        // button reference to pay button is disabled when activity launches
        // so when one of the recipient is selected we will enable the button
        //final Button pay = findViewById(R.id.btn_pay);

        // setting the global variable to be the person last clicked


        //pay.setEnabled(true);

    }

    @Override
    public void onNoteLongClick(int position) {
       TransferData getItem = MainActivity.store.get(position);

       Toast.makeText(getApplicationContext(),getItem.getName() + " " + getItem.getAmount() ,Toast.LENGTH_SHORT).show();
    }
}
