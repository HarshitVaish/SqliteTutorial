package com.example.harshitvaish.sqlitetutorial;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryyViewHOlder> {
    private Context mcontext;
    private Cursor mCursor;

    public GroceryAdapter(Context mcontext, Cursor mCursor) {
        this.mcontext = mcontext;
        this.mCursor = mCursor;
    }

    public class GroceryyViewHOlder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView countText;


        public GroceryyViewHOlder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
        }
    }

    @NonNull
    @Override
    public GroceryyViewHOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.grocery_item, viewGroup, false);
        return new GroceryyViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryyViewHOlder groceryyViewHOlder, int i) {
        if (!mCursor.move(i)) {
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_AMOUNT));
        groceryyViewHOlder.countText.setText(amount + "");
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
            mCursor=newCursor;
        if (newCursor!=null){
            notifyDataSetChanged();
        }

    }
}
