package com.example.mariakovaleva.bookstoreapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mariakovaleva.bookstoreapp.database.BookDbHelper;
import com.example.mariakovaleva.bookstoreapp.database.BookContract.BookEntry;

public class InventoryActivity extends AppCompatActivity {

    //Provides us access to the database
    private BookDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //Instantiate BookDbHelper to get access to our database
        mDbHelper = new BookDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseEntries();
    }

    //Method to display our data in the TextView
    private void displayDatabaseEntries(){

        //Create and/or open database to read from it
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        //Used for learning purposes; however, since we select all columns, could be left null
        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_PRODUCT_NAME,
                BookEntry.COLUMN_PRICE,
                BookEntry.COLUMN_QUANTITY,
                BookEntry.COLUMN_SUPPLIER_NAME,
                BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER};

        //Perform a query on the books table
        Cursor cursor = database.query(BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayEntries = (TextView) findViewById(R.id.text_view_books);
        TextView emptyTextView = (TextView) findViewById(R.id.empty);

        if(cursor != null && cursor.getCount() > 0) {
            try {
                emptyTextView.setVisibility(View.GONE);
                displayEntries.setVisibility(View.VISIBLE);
                displayEntries.setText(cursor.getCount() + " books currently in stock: \n\n");
                displayEntries.append(BookEntry._ID + " - " +
                        BookEntry.COLUMN_PRODUCT_NAME + " - " +
                        BookEntry.COLUMN_PRICE + " - " +
                        BookEntry.COLUMN_QUANTITY + " - " +
                        BookEntry.COLUMN_SUPPLIER_NAME + " - " +
                        BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "\n");

                //get the index of each column
                int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
                int productNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
                int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
                int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);
                int supplierNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_NAME);
                int supplierPhoneColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

                //Iterate through returned cursor rows

                while (cursor.moveToNext()) {
                    int currentId = cursor.getInt(idColumnIndex);
                    String currentProductName = cursor.getString(productNameColumnIndex);
                    float currentPrice = cursor.getFloat(priceColumnIndex);
                    int currentQuantity = cursor.getInt(quantityColumnIndex);
                    String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                    String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);
                    //show the current row in our text view
                    displayEntries.append("\n" + currentId + " - " +
                            currentProductName + " - " +
                            currentPrice + " - " +
                            currentQuantity + " - " +
                            currentSupplierName + " - " +
                            currentSupplierPhone);
                }

            } finally {
                //Releases all resources from the cursor when done
                cursor.close();
            }
        //if database is empty, show emptyTextView
        } else {
                displayEntries.setVisibility(View.GONE);
                emptyTextView.setVisibility(View.VISIBLE);
                emptyTextView.setText(getText(R.string.empty_text_view));
            }


    }

    //Insert dummy book data in the database
    private void insertBook() {

        //Get the database to write in it
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_PRODUCT_NAME, "Alice in Wonderland");
        values.put(BookEntry.COLUMN_PRICE, 9.99);
        values.put(BookEntry.COLUMN_QUANTITY, 5);
        values.put(BookEntry.COLUMN_SUPPLIER_NAME, "Penguin Books");
        values.put(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER, "+44-123-456-78");

        long newRowId = database.insert(BookEntry.TABLE_NAME, null, values);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //adds menu items to action bar
        getMenuInflater().inflate(R.menu.menu_inventory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertBook();
                displayDatabaseEntries();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}// end InventoryActivity
