package com.voxcast.database;

import android.net.Uri;

public final class Data {

	public static final String SCHEME = "content";

	public static final String AUTHORITY = "com.voxcast.activity";

	public static final Uri CONTENT_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

	public static final String MIME_TYPE_ROWS = "vnd.android.cursor.dir/vnd.com.voxcast";

	public static final String MIME_TYPE_SINGLE_ROW = "vnd.android.cursor.item/vnd.com.voxcast";

	public static class Column {

		public static class GET_NOTIFICATION {
			public static final String NOTIFICATION_CNT = "NOTIFICATION_CNT";
			public static final String USER_ID = "USER_ID";
			public static final String USER_IMAGE = "USER_IMAGE";
			public static final String NOTIFICATION_MSG = "NOTIFICATION_MSG";

		}

		public static class GET_USER_LIST {

			public static final String USER_ID = "USER_ID";
			public static final String USER_IMAGE = "USER_IMAGE";
			public static final String USER_NAME = "USER_NAME";

		}

		public static class Z_POST_VIDEO {
			public static final String VIDEO_ID = "VIDEO_ID";

		}

		public static class Z_POST_IMAGE {
			public static final String IMAGE_ID = "IMAGE_ID";

		}

		public static class Z_CREATE_POST {
			public static final String POST_ID = "POST_ID";
			public static final String POST_MSG = "POST_MSG";
			public static final String ANONYMOUS_POST = "ANONYMOUS_POST";
		}

		public static class Z_EDIT_POST {
			public static final String USER_ID = "USER_ID";
			public static final String USER_IMAGE = "USER_IMAGE";

		}

		public static class Z_GET_PROFILE {
			public static final String USER_ID = "USER_ID";
			public static final String USER_IMAGE = "USER_IMAGE";
			public static final String USER_NAME = "USER_NAME";
			public static final String MSG_CNT = "MSG_CNT";
			public static final String USER_UP_CNT = "USER_UP_CNT";
			public static final String USER_DOWN_CNT = "USER_DOWN_CNT";
			public static final String POST_ID = "POST_ID";
			public static final String POST_MSG = "POST_MSG";
			public static final String POST_CITY_NAME = "POST_CITY_NAME";
			public static final String POST_UP_CNT = "POST_UP_CNT";
			public static final String POST_DOWN_CNT = "POST_DOWN_CNT";
			public static final String POST_COMMENT_CNT = "POST_COMMENT_CNT";
			public static final String POST_TIME_STAMP = "POST_TIME_STAMP";
			public static final String POST_IMAGE_IDS = "POST_IMAGE_IDS";
			public static final String POST_VIDEO_ID = "POST_VIDEO_ID";
			public static final String POST_COMMENT = "POST_COMMENT";

		}

		public static class Z_GET_POST {
			public static final String CITY_IMAGE = "CITY_IMAGE";
			public static final String USER_ID = "USER_ID";
			public static final String POST_ID = "POST_ID";
			public static final String USER_IMAGE = "USER_IMAGE";
			public static final String USER_NAME = "USER_NAME";
			public static final String POST_MSG = "POST_MSG";
			public static final String CITY_NAME = "CITY_NAME";
			public static final String UP_CNT = "UP_CNT";
			public static final String DOWN_CNT = "DOWN_CNT";
			public static final String COMMENT_CNT = "COMMENT_CNT";
			public static final String TIME_STAMP = "TIME_STAMP";
			public static final String IMAGE_IDS = "IMAGE_IDS";
			public static final String VIDEO_ID = "VIDEO_ID";
			public static final String COMMENT = "COMMENT";

		}

		public static final String PK = "PK";
		/*
		 * public static final String PK = "PK"; public static final String
		 * NOTIFICATION_CNT = "NOTIFICATION_CNT"; public static final String
		 * USER_ID = "USER_ID"; public static final String USER_IMAGE =
		 * "USER_IMAGE"; public static final String MSG = "MSG"; public static
		 * final String NAME = "NAME"; public static final String VIDEO_ID =
		 * "VIDEO_ID"; public static final String IMAGE_ID = "IMAGE_ID"; public
		 * static final String POST_ID = "POST_ID"; public static final String
		 * ANONYMOUS = "ANONYMOUS"; public static final String MSG_CNT =
		 * "MSG_CNT"; public static final String UP_CNT = "UP_CNT"; public
		 * static final String DOWN_CNT = "DOWN_CNT"; public static final String
		 * COMMENT_CNT = "COMMENT_CNT"; public static final String TIME_STAMP =
		 * "TIME_STAMP"; public static final String COMMENTS = "COMMENTS";
		 * public static final String CITY_IMAGE = "CITY_IMAGE"; public static
		 * final String CITY_NAME = "CITY_NAME";
		 */

	}

	public static class Table {
		public static final String Z_GET_NOTIFICATION = "Z_GET_NOTIFICATION";
		public static final String Z_GET_USER_LIST = "Z_GET_USER_LIST";
		public static final String Z_POST_VIDEO = "Z_POST_VIDEO";
		public static final String Z_POST_IMAGE = "Z_POST_IMAGE";
		public static final String Z_CREATE_POST = "Z_CREATE_POST";
		public static final String Z_EDIT_POST = "Z_EDIT_POST";
		public static final String Z_GET_PROFILE = "Z_GET_PROFILE";
		public static final String Z_GET_POST = "Z_GET_POST";
	}

	public static class Uris {
		public final static Uri Z_GET_NOTIFICATION = Uri.withAppendedPath(
				CONTENT_URI, Table.Z_GET_NOTIFICATION);
	}

	public static class DB {
		public static final String DB_NAME = "Voxcast.sqlite";
	}
}
