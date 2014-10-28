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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.voxcast.R;
import com.voxcast.constant.Constant;
import com.voxcast.view.HorizontalListView;

public class CreatePostActivity extends BaseActivity {

	private HorizontalListView mHlvCustomList;

	private ArrayList<Myimage> imageBitmapArrayList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_activity);

		imageBitmapArrayList = new ArrayList<Myimage>();
		mHlvCustomList = (HorizontalListView) findViewById(R.id.hlvCustomList);

	}

	public void onClickCamera(View v) {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, Constant.CAMERA_REQUEST);
	}

	public void onClickVideo(View v) {

		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("video/*"); //
		photoPickerIntent.putExtra("image", "video");
		startActivityForResult(photoPickerIntent,
				Constant.RESULT_GALLERY_VIDEOIMAGE);
	}

	private void setupCustomLists() {
		// Make an array adapter using the built in android layout to render a
		// list of strings

		CustomArrayAdapter adapter = new CustomArrayAdapter(this,
				imageBitmapArrayList);
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
			intent.setType("image/*,video/*");
			intent.setAction(Intent.ACTION_PICK);
			intent.putExtra("image", "image");
			startActivityForResult(intent,
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		try {

			if (resultCode == Activity.RESULT_OK) {
				if (requestCode == Constant.RESULT_GALLERY_MULTIPLEIMAGE) {
					Uri selectedImageUri = data.getData();

					String selectedImagePath = getPath(selectedImageUri);

					Bitmap photo = getResizedBitmap(selectedImagePath);
					imageBitmapArrayList.add(new Myimage(photo, "image"));

				}
				if (requestCode == Constant.RESULT_GALLERY_VIDEOIMAGE) {

					String s = getPath(data.getData());
					Bitmap videoThumb = ThumbnailUtils.createVideoThumbnail(s,
							MediaStore.Images.Thumbnails.MINI_KIND);
					imageBitmapArrayList.add(new Myimage(videoThumb, "video"));

				}
				if (requestCode == Constant.CAMERA_REQUEST) {
					Bitmap photo = (Bitmap) data.getExtras().get("data");
					imageBitmapArrayList.add(new Myimage(photo, "image"));
				}
				setupCustomLists();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private class CustomArrayAdapter extends BaseAdapter {

		private LayoutInflater inflater;
		ArrayList<Myimage> imageBitmapArrayList;

		public CustomArrayAdapter(Context context,
				ArrayList<Myimage> imageBitmapArrayList2) {
			inflater = LayoutInflater.from(context);
			this.imageBitmapArrayList = imageBitmapArrayList2;

		}

		@Override
		public int getCount() {
			return imageBitmapArrayList.size();
		}

		@Override
		public Object getItem(int i) {
			return imageBitmapArrayList.get(i);
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup viewGroup) {
			View vi = convertView;
			Holder holder;

			if (vi == null) {

				vi = inflater.inflate(R.layout.custom_create_post_activity,
						null);

				holder = new Holder();
				holder.iv_post_activity_image = (ImageView) vi
						.findViewById(R.id.iv_post_activity_image);
				holder.ib_postactivity_cross = (ImageButton) vi
						.findViewById(R.id.ib_postactivity_cross);
				holder.ib_postactivity_play_icon = (ImageButton) vi
						.findViewById(R.id.ib_postactivity_play_icon);

				vi.setTag(holder);
			} else {
				holder = (Holder) vi.getTag();

			}
			holder.iv_post_activity_image.setImageBitmap(imageBitmapArrayList
					.get(position).getBitmap());

			String imageType = imageBitmapArrayList.get(position).getType();
			if (imageType.equals("video")) {
				holder.ib_postactivity_play_icon.setVisibility(View.VISIBLE);
			}

			holder.ib_postactivity_cross
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							imageBitmapArrayList.remove(position);
							notifyDataSetChanged();
						}
					});

			return vi;

		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	private static class Holder {
		public ImageView iv_post_activity_image;
		public ImageButton ib_postactivity_play_icon;
		public ImageButton ib_postactivity_cross;
	}

}

class Myimage {

	Bitmap Bitmap;

	public Myimage(Bitmap photo, String type) {
		// TODO Auto-generated constructor stub
		Bitmap = photo;
		Type = type;
	}

	public Bitmap getBitmap() {
		return Bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		Bitmap = bitmap;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	String Type;
}