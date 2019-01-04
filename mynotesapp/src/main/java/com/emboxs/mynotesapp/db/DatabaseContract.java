package com.emboxs.mynotesapp.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_NOTE ="note";
    static final class NoteColumns implements BaseColumns{
        //Note title
        static String TITLE = "title";
        //Note description
        static String DESCRIPTION = "description";
        //note date
        static String DATE ="date";
    }
}
