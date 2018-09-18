package com.example.mariakovaleva.bookstoreapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mariakovaleva.bookstoreapp.database.BookContract.BookEntry;

/**
 * {@link BookCursorAdapter} is an adapter for a list view
 * that uses a {@link Cursor} of book data as its datasource.
 */
public class BookCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link BookCursorAdapter}.
     */
    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * Binds the pet data (in the current row pointed to by cursor) to the given list item layout.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView quantityTextView = view.findViewById(R.id.quantity_text_view);
        TextView nameTextView = view.findViewById(R.id.name_text_view);
        TextView priceTextView = view.findViewById(R.id.price_text_view);
        Button saleButton = view.findViewById(R.id.sale_button);

        // Find the columns of book attributes that we're interested in
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);

        // Read the book attributes from the Cursor for the current book
        final Integer quantity = cursor.getInt(quantityColumnIndex);
        String productName = cursor.getString(nameColumnIndex);
        Double price = cursor.getDouble(priceColumnIndex);
        final int id = cursor.getInt(cursor.getColumnIndex(BookEntry._ID));

        // Update the TextViews with the attributes for the current book
        quantityTextView.setText(Integer.toString(quantity));
        nameTextView.setText(productName);
        priceTextView.setText(Double.toString(price));

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity <= 0) {
                    Toast.makeText(v.getContext(), context.getString(R.string.quantity_negative_alert), Toast.LENGTH_SHORT).show();
                } else {
                    Uri productUri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, id);

                    ContentValues values = new ContentValues();
                    values.put(BookEntry.COLUMN_QUANTITY, quantity - 1);

                    int rowsUpdated = context.getContentResolver()
                            .update(productUri, values, null, null);

                    if (rowsUpdated != 0) {
                        Toast.makeText(context, R.string.quantity_reduced_alert, Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
    }

}//end class
