package e.vegard.bankapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecycleViewHolder> {

    private String[] mData;
    private OnNoteListener mOnNoteListener;

    // Provides a reference to the views for each data item
    public static class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView mTextView;
        OnNoteListener OnNoteListener;

        public RecycleViewHolder(TextView v, OnNoteListener OnNoteListener) {
            super(v);
            mTextView = v;
            this.OnNoteListener = OnNoteListener;

            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OnNoteListener.onNoteClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            OnNoteListener.onNoteLongClick(getAdapterPosition());
            return true;
        }

    }


    // constructor for adapter
    public RecyclerViewAdapter(String[] mDataset, OnNoteListener onNoteListener) {
        this.mData = mDataset;
        this.mOnNoteListener = onNoteListener;
    }

    // creating a new views
    @Override
    public RecyclerViewAdapter.RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // creating a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        RecycleViewHolder vh = new RecycleViewHolder(v, mOnNoteListener);
        return vh;
    }

    // replace the contents of the view
    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        holder.mTextView.setText(mData[position]);
    }

    // returning the size of the dataset
    @Override
    public int getItemCount() {
        return mData.length;
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
        void onNoteLongClick(int position);
    }
}
