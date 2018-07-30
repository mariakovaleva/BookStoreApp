package com.example.mariakovaleva.bookstoreapp.database;

import android.provider.BaseColumns;

public class BookContract {

    //Utility class cannot be instantiated
    private BookContract(){}

    public static final class BookEntry implements BaseColumns {

        //Database table name for books
        public static final String TABLE_NAME = "books";
        //Unique ID number for the book, type INTEGER
        public static final String _ID = BaseColumns._ID;
        //Name of the book, type TEXT
        public static final String COLUMN_PRODUCT_NAME = "name";
        //Price of the book, type REAL
        public static final String COLUMN_PRICE = "price";
        //Number of books in stock, type INTEGER
        public static final String COLUMN_QUANTITY = "quantity";
        //Name of the book supplier, type TEXT
        public static final String COLUMN_SUPPLIER_NAME = "supplier";
        //Phone number of the supplier, type TEXT
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "phone";



    }//end BookEntry

}
