package com.voxcast.activity;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.voxcast.R;
import com.voxcast.constant.Constant;
import com.voxcast.view.HorizontalListView;

public class CreatePostActivityOld extends BaseActivity {

	private int setIndexImage = 0;
	private ImageView iv = null;
	private HorizontalListView mHlvCustomList;

	private ArrayList<Bitmap> imageBitmap = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_activity);
		setIndexImage = 0;
		imageBitmap = new ArrayList<Bitmap>();
	//	mHlvCustomList = (HorizontalListView) findViewById(R.id.hlvCustomList);

	}

	/*
	 * public Bitmap getResizedBitmap(int targetW, int targetH, String
	 * imagePath) {
	 * 
	 * // Get the dimensions of the bitmap BitmapFactory.Options bmOptions = new
	 * BitmapFactory.Options(); // inJustDecodeBounds = true <-- will not load
	 * the bitmap into memory bmOptions.inJustDecodeBounds = true;
	 * BitmapFactory.decodeFile(imagePath, bmOptions);
	 * 
	 * int photoW = bmOptions.outWidth; int photoH = bmOptions.outHeight;
	 * 
	 * // Determine how much to scale down the image int scaleFactor =
	 * Math.min(photoW / targetW, photoH / targetH);
	 * 
	 * // Decode the image file into a Bitmap sized to fill the View
	 * bmOptions.inJustDecodeBounds = false; bmOptions.inSampleSize =
	 * scaleFactor; bmOptions.inPurgeable = true;
	 * 
	 * Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions); return
	 * (bitmap); }
	 */

	public void onClickVideo(View v) {

		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("video/*"); //
		startActivityForResult(photoPickerIntent,
				Constant.RESULT_GALLERY_VIDEOIMAGE);
	}

	private void setupCustomLists() {
		// Make an array adapter using the built in android layout to render a
		// list of strings
		CustomArrayAdapter adapter = new CustomArrayAdapter(this, imageBitmap);
		// Assign adapter to HorizontalListView
		mHlvCustomList.setAdapter(adapter);

	}

	public void onClickGallary(View v) {

		/*
		 * Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
		 * startActivityForResult(i, Constant.RESULT_GALLERY_MULTIPLEIMAGE);
		 */

		if (Environment.getExternalStorageState().equals("mounted")) {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_PICK);
			startActivityForResult(
					Intent.createChooser(intent, "Select Picture:"),
					Constant.RESULT_GALLERY_MULTIPLEIMAGE);
		}

	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public Bitmap getResizedBitmap(String fileName) {
		File image = new File(fileName);

		BitmapFactory.Options bounds = new BitmapFactory.Options();
		bounds.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(image.getPath(), bounds);
		if ((bounds.outWidth == -1) || (bounds.outHeight == -1)) {
			return null;
		}
		int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
				: bounds.outWidth;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inSampleSize = originalSize / 64;
		return BitmapFactory.decodeFile(image.getPath(), opts);
	}

	private boolean setVideoFlag;
	private boolean setRemoveFlag;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (resultCode == Activity.RESULT_OK) {

				System.out.println("aaaaaaaaaaaa ok ");

				if (requestCode == Constant.RESULT_GALLERY_MULTIPLEIMAGE) {

					Uri selectedImageUri = data.getData();

					String type = data.getAction();

					System.out.println("aaaaaaaaaa  type " + type);

					String selectedImagePath = getPath(selectedImageUri);
					System.out.println("aaaaaaaaaaaa  path  "
							+ selectedImagePath);

					System.out.println("aaaaaaaaaa  aaaasize  "
							+ imageBitmap.size());
					Bitmap photo = getResizedBitmap(selectedImagePath);

					imageBitmap.add(photo);

					setRemoveFlag = true;

				}

				if (requestCode == Constant.RESULT_GALLERY_VIDEOIMAGE
						&& resultCode == Activity.RESULT_OK) {
					setVideoFlag = true;

					String s = getPath(data.getData());
					Bitmap videoThumb = ThumbnailUtils.createVideoThumbnail(s,
							MediaStore.Images.Thumbnails.MINI_KIND);

					System.out.println("aaaaaaaaaa  size  "
							+ imageBitmap.size());

					if (imageBitmap.size() > 0 ) {
						if(!setRemoveFlag)
						imageBitmap.remove(0);
					}

					imageBitmap.add(0, videoThumb);
					setRemoveFlag = false;
				}
				setupCustomLists();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class CustomArrayAdapter extends ArrayAdapter<Bitmap> {
		private LayoutInflater mInflater;
		ArrayList<Bitmap> values;

		public CustomArrayAdapter(Context context, ArrayList<Bitmap> values) {
			super(context, R.layout.custom_data_view, values);
			mInflater = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			this.values = values;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.custom_data_view,
						parent, false);
				holder = new Holder();
				holder.textView = (ImageView) convertView
						.findViewById(R.id.iv_gallary_image_4);
				holder.ib_play_icon = (ImageButton) convertView
						.findViewById(R.id.ib_play_icon);

				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			System.out.println("aaaaaaaaaa  position  " + position);

			if (setVideoFlag) {
				holder.ib_play_icon.setVisibility(View.VISIBLE);
			} else {
				holder.ib_play_icon.setVisibility(View.INVISIBLE);
			}
			holder.textView.setImageBitmap(values.get(position));

			return convertView;
		}
	}

	private static class Holder {
		public ImageView textView;
		public ImageButton ib_play_icon;
	}
}
