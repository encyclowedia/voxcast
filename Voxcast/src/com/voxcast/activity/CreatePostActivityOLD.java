package com.voxcast.activity;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.voxcast.R;
import com.voxcast.constant.Constant;

public class CreatePostActivityOLD extends BaseActivity {/*implements OnClickListener {

	private int setIndexImage = 0;
	private ImageView iv = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_activity);
		setIndexImage = 0;
		// RelativeLayout l = (RelativeLayout) findViewById(R.id.iiii);

		setUI();
	}

	private void setUI() {
		findViewById(R.id.ib_postactivity_cross_1).setOnClickListener(this);
		findViewById(R.id.ib_postactivity_cross_2).setOnClickListener(this);
		findViewById(R.id.ib_postactivity_cross_3).setOnClickListener(this);
		findViewById(R.id.ib_postactivity_cross_4).setOnClickListener(this);
	}

	public Bitmap getResizedBitmap(int targetW, int targetH, String imagePath) {

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		// inJustDecodeBounds = true <-- will not load the bitmap into memory
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imagePath, bmOptions);

		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
		return (bitmap);
	}

	public void onClickCamera(View v) {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, Constant.CAMERA_REQUEST);
	}

	public void onClickVideo(View v) {

		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("video/*"); //
		startActivityForResult(photoPickerIntent,
				Constant.RESULT_GALLERY_VIDEOIMAGE);
	}

	public void onClickGallary(View v) {

		
		 * Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
		 * startActivityForResult(i, Constant.RESULT_GALLERY_MULTIPLEIMAGE);
		 

		if (Environment.getExternalStorageState().equals("mounted")) {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_PICK);
			startActivityForResult(intent,
					Constant.RESULT_GALLERY_MULTIPLEIMAGE);
		}

	}

	public String getRealPathFromURI(Context context, Uri contentUri) {
		Cursor cursor = null;
		try {
			String[] proj = { MediaStore.Images.Media.DATA };
			cursor = context.getContentResolver().query(contentUri, proj, null,
					null, null);
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (resultCode == Activity.RESULT_OK) {

				if (requestCode == Constant.RESULT_GALLERY_MULTIPLEIMAGE) {

					
					 * String[] all_path = data.getStringArrayExtra("all_path");
					 * 
					 * ArrayList<CustomGallery> dataT = new
					 * ArrayList<CustomGallery>();
					 * 
					 * for (String string : all_path) { CustomGallery item = new
					 * CustomGallery(); item.sdcardPath = string;
					 * dataT.add(item);
					 * 
					 * Bitmap imageBitmap = getResizedBitmap(140, 140,
					 * item.sdcardPath);
					 * 
					 * if (setIndexImage == 0) { iv = (ImageView)
					 * findViewById(R.id.iv_gallary_image_2); } else if
					 * (setIndexImage == 1) { iv = (ImageView)
					 * findViewById(R.id.iv_gallary_image_3); } else if
					 * (setIndexImage == 2) { iv = (ImageView)
					 * findViewById(R.id.iv_gallary_image_4); } if
					 * (setIndexImage != 2) setIndexImage++;
					 * 
					 * iv.setImageBitmap(imageBitmap); }
					 

					String s = getRealPathFromURI(this, data.getData());
					Bitmap imageBitmap = getResizedBitmap(140, 140, s);

					setBitmapImage(imageBitmap);
				}

				if (requestCode == Constant.RESULT_GALLERY_VIDEOIMAGE) {

					String s = getRealPathFromURI(this, data.getData());

					Bitmap videoThumb = ThumbnailUtils.createVideoThumbnail(s,
							MediaStore.Images.Thumbnails.MINI_KIND);
					findViewById(R.id.rl_post_activity_1).setVisibility(
							View.VISIBLE);
					iv = (ImageView) findViewById(R.id.iv_gallary_image_1);
					iv.setImageBitmap(videoThumb);

				}
				if (requestCode == Constant.CAMERA_REQUEST) {
					Bitmap photo = (Bitmap) data.getExtras().get("data");
					setBitmapImage(photo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setBitmapImage(Bitmap bitmap) {

		if (setIndexImage == 0) {
			findViewById(R.id.rl_post_activity_2).setVisibility(View.VISIBLE);
			iv = (ImageView) findViewById(R.id.iv_gallary_image_2);
		} else if (setIndexImage == 1) {
			findViewById(R.id.rl_post_activity_3).setVisibility(View.VISIBLE);
			iv = (ImageView) findViewById(R.id.iv_gallary_image_3);
		} else if (setIndexImage == 2) {
			findViewById(R.id.rl_post_activity_4).setVisibility(View.VISIBLE);
			iv = (ImageView) findViewById(R.id.iv_gallary_image_4);
		}
		
		System.out.println("aaaaaaaaaa  set  " + setIndexImage);
		
		if (setIndexImage != 2)
			setIndexImage++;	
		iv.setImageBitmap(bitmap);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_postactivity_cross_1:
			findViewById(R.id.rl_post_activity_1).setVisibility(View.GONE);
			setIndexImage--;
			break;
		case R.id.ib_postactivity_cross_2:
			findViewById(R.id.rl_post_activity_2).setVisibility(View.GONE);
			setIndexImage--;
			break;
		case R.id.ib_postactivity_cross_3:
			findViewById(R.id.rl_post_activity_3).setVisibility(View.GONE);
			setIndexImage--;
			break;
		case R.id.ib_postactivity_cross_4:
			findViewById(R.id.rl_post_activity_4).setVisibility(View.GONE);
			setIndexImage--;
			break;

		default:
			break;
		}
	}*/
}
