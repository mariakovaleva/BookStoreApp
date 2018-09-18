package com.example.mariakovaleva.bookstoreapp.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class BookContract {

    /**
     * Name for the content provider
     */
    public static final String CONTENT_AUTHORITY = "com.example.mariakovaleva.bookstoreapp";
    /**
     * The base of all URI's which apps will use to contact the content provider
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    /**
     * Possible path for selecting all book data
     */
    public static final String PATH_BOOKS = "books";

    /**
     * Utility class cannot be instantiated
     */
    private BookContract() {
    }

    /**
     * Inner class that defines constant values for the pets database table
     * 1 entry - 1 book
     */
    public static final class BookEntry implements BaseColumns {

        /**
         * The content URI to access the book data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of books.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single book.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        /**
         * Database table name for books
         */
        public static final String TABLE_NAME = "books";
        /**
         * Unique ID number for the book, type INTEGER
         */
        public static final String _ID = BaseColumns._ID;
        /**
         * Name of the book, type TEXT
         */
        public static final String COLUMN_PRODUCT_NAME = "name";
        /**
         * Price of the book, type REAL
         */
        public static final String COLUMN_PRICE = "price";
        /**
         * Number of books in stock, type INTEGER
         */
        public static final String COLUMN_QUANTITY = "quantity";
        /**
         * Name of the book supplier, type TEXT
         */
        public static final String COLUMN_SUPPLIER_NAME = "supplier";
        /**
         * Phone number of the supplier, type TEXT
         */
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "phone";


    }//end BookEntry

}//end class
