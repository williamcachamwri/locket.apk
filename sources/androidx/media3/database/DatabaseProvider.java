package androidx.media3.database;

import android.database.sqlite.SQLiteDatabase;

public interface DatabaseProvider {
    public static final String TABLE_PREFIX = "ExoPlayer";

    SQLiteDatabase getReadableDatabase();

    SQLiteDatabase getWritableDatabase();
}
