package com.voxcast.database;

import android.net.Uri;

public class Data {

	public static final String SCHEME = "content";

	public static final String AUTHORITY = "com.voxcast";

	public static final Uri CONTENT_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

	public static final String MIME_TYPE_ROWS = "vnd.android.cursor.dir/vnd.com.voxcast";

	public static final String MIME_TYPE_SINGLE_ROW = "vnd.android.cursor.item/vnd.com.voxcast";

	public class Column {

		public static final String ZGALLARYIMAGEPATH = "ZGALLARYIMAGEPATH";
		public static final String ZPK = "ZPK";
		public static final String ZGALLARYINDEX = "ZGALLARYINDEX";

	}

	public static class Table {
		public static final String ZGALLARYIMAGE = "ZGALLARYIMAGE";

	}

	public static class Uris {
		public final static Uri GALLARY_URI = Uri.withAppendedPath(CONTENT_URI,
				Table.ZGALLARYIMAGE);

	}

	public static class DB {
		public static final String DB_NAME = "Voxcast.sqlite";
	}
}
