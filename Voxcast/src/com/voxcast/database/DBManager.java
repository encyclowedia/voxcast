package com.voxcast.database;

import java.util.ArrayList;

import com.voxcast.model.GallaryImageModel;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

public class DBManager {

	// Particular type of methods according to requirement.

	public static void addGallaryImage(Context context , ArrayList<GallaryImageModel> gallaryImageArrayList) {
		ContentValues values;
		Uri coin_uri = Data.Uris.GALLARY_URI;
		context.getContentResolver().delete(coin_uri, null, null);
		values = new ContentValues();
		
		
	//	values.put(Data.Column.ZGALLARYIMAGEPATH, value)
		/*for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			ArrayList<Coins> arrayList = user.getArrayList();
			values = new ContentValues[arrayList.size()];

			for (int j = 0; j < arrayList.size(); j++) {

				values[j] = new ContentValues();
				values[j].put(Data.Column.ZCOIN_ZCOINID, arrayList.get(j)
						.getContentId());
				values[j].put(Data.Column.ZCOIN_ZCONTENTID, arrayList.get(j)
						.getContentId());
				values[j].put(Data.Column.ZCOIN_ZPROFILE, user.getUserId());
			}
			context.getContentResolver().bulkInsert(coin_uri, values);
		}*/
	}
}
