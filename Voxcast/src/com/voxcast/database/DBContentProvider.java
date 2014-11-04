package com.voxcast.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.voxcast.database.Data.DB;

public class DBContentProvider extends ContentProvider {
	private CustomSqliteDB db;

	private static final UriMatcher sUriMatcher;

	private static final int ID_ZGALLARYIMAGE = 0;

	static {
		sUriMatcher = new UriMatcher(0);
		sUriMatcher.addURI(Data.AUTHORITY, Data.Table.ZGALLARYIMAGE,
				ID_ZGALLARYIMAGE);

	}

	public class CustomSqliteDB extends SQLiteOpenHelper {

		public CustomSqliteDB(Context context, String name) {
			super(context, name, null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}

	@Override
	public boolean onCreate() {
		db = new CustomSqliteDB(getContext(), DB.DB_NAME);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor resultCursor = null;
		SQLiteDatabase database = db.getReadableDatabase();

		switch (sUriMatcher.match(uri)) {
		case ID_ZGALLARYIMAGE:
			/*
			 * resultCursor = database.query(Data.Table.ZPROFILE, projection,
			 * selection, selectionArgs, null, null, sortOrder);
			 * resultCursor.setNotificationUri
			 * (getContext().getContentResolver(), uri);
			 */
			break;

		}

		return resultCursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase localSQLiteDatabase = db.getWritableDatabase();
		localSQLiteDatabase.beginTransaction();
		long result = 0;
		switch (sUriMatcher.match(uri)) {
		case ID_ZGALLARYIMAGE:
			result = localSQLiteDatabase.insert(Data.Table.ZGALLARYIMAGE, null,
					values);
			break;
		}

		localSQLiteDatabase.setTransactionSuccessful();
		localSQLiteDatabase.endTransaction();
		localSQLiteDatabase.close();
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.withAppendedPath(uri, result + "");
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		int numImages = values == null ? -1 : values.length;

		return numImages;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase writableDatabase = db.getWritableDatabase();
		int result = -1;

		return result;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int result = -1;
		SQLiteDatabase database = db.getWritableDatabase();
		switch (sUriMatcher.match(uri)) {
		case ID_ZGALLARYIMAGE:
			result = database.update(Data.Table.ZGALLARYIMAGE, values,
					selection, selectionArgs);
			break;

		}
		return result;
	}

}
