package com.voxcast.activity;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.voxcast.R;
import com.voxcast.adapter.CreatePostAdaper;
import com.voxcast.constant.Constant;
import com.voxcast.fragment.BaseFragment;
import com.voxcast.model.CreatePostModel;
import com.voxcast.view.HorizontalListView;

public class CreatePostActivity extends BaseFragment implements OnClickListener {

	private HorizontalListView mHlvCustomList;

	private ArrayList<CreatePostModel> imageBitmapArrayList = null;

	private TextView tv_post_activity_200, tv_post_activity_hash;
	private EditText et_post_activity_msg;
	private int et_lenght;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.post_activity, container, false);
		imageBitmapArrayList = new ArrayList<CreatePostModel>();

		setUI(view);

		editText();
		return view;
	}

	private void setUI(View v) {
		mHlvCustomList = (HorizontalListView) v
				.findViewById(R.id.hlvCustomList);
		v.findViewById(R.id.ib_post_activity_closebutton).setOnClickListener(
				this);
		v.findViewById(R.id.ib_post_activity_gallary).setOnClickListener(this);
		v.findViewById(R.id.ib_post_activity_video_capture).setOnClickListener(
				this);
		v.findViewById(R.id.ib_post_activity_image_capture).setOnClickListener(
				this);
		v.findViewById(R.id.tv_post_activity_hash).setOnClickListener(this);

		et_post_activity_msg = (EditText) v
				.findViewById(R.id.et_post_activity_msg);
		tv_post_activity_200 = (TextView) v
				.findViewById(R.id.tv_post_activity_200);

	}

	private void editText() {

		et_post_activity_msg.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				et_lenght = 200 - s.length();

				if (et_lenght < 0) {
					tv_post_activity_200.setTextColor(Color.RED);
				} else {
					tv_post_activity_200.setTextColor(Color
							.parseColor("#C0C0C0"));
				}
				tv_post_activity_200.setText(String.valueOf(et_lenght));
			}
		});
	}

	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * setContentView(R.layout.post_activity);
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	private void setupCustomLists() {
		// Make an array adapter using the built in android layout to render a
		// list of strings

		CreatePostAdaper adapter = new CreatePostAdaper(getActivity(),
				imageBitmapArrayList);
		// Assign adapter to HorizontalListView
		mHlvCustomList.setAdapter(adapter);

	}

	private void onOpenCamera() {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent,
				Constant.RESULT_GALLERY_CAMERA_IMAGE);
	}

	private void onOpenVideo() {

		Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
		// / intent.putExtra("android.intent.extra.durationLimit", 120);
		startActivityForResult(intent, Constant.RESULT_GALLERY_VIDEOCAPTURE);

		/*
		 * Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		 * photoPickerIntent.setType("video/*"); //
		 * photoPickerIntent.putExtra("image", "video");
		 * startActivityForResult(photoPickerIntent,
		 * Constant.RESULT_GALLERY_VIDEOIMAGE);
		 */
	}

	private void onOpenGallary() {

		/*
		 * Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
		 * startActivityForResult(i, Constant.RESULT_GALLERY_MULTIPLEIMAGE);
		 */

		if (Environment.getExternalStorageState().equals("mounted")) {
			Intent intent = new Intent();

			intent.setType("image/*,video/*");
			intent.setAction(Intent.ACTION_PICK);

			startActivityForResult(intent, Constant.RESULT_GALLERY_VIDEO_IMAGE);
		}
	}

	private String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = getActivity().managedQuery(uri, projection, null, null,
				null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	private Bitmap getResizedBitmap(String fileName) {
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

	String typeImage[] = { ".png", ".gif", ".jpeg", ".jpg", ".bmp" };

	private boolean isImage(String selectImage) {

		for (int i = 0; i < typeImage.length; i++) {
			if (selectImage.contains(typeImage[i])) {
				return true;
			}
		}

		return false;
	}

	private boolean isVideoSelect;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		try {

			if (resultCode == Activity.RESULT_OK) {
				if (requestCode == Constant.RESULT_GALLERY_VIDEO_IMAGE) {

					Uri selectedImageUri = data.getData();

					String selectedImagePath = getPath(selectedImageUri);

					if (isImage(selectedImagePath)) {
						if (imageBitmapArrayList.size() <= 3) {
							Bitmap photo = getResizedBitmap(selectedImagePath);
							imageBitmapArrayList.add(new CreatePostModel(photo,
									"image"));
						}
					} else {

						if (!isVideoSelect) {
							Bitmap videoThumb = ThumbnailUtils
									.createVideoThumbnail(
											selectedImagePath,
											MediaStore.Images.Thumbnails.MINI_KIND);
							imageBitmapArrayList.add(new CreatePostModel(
									videoThumb, "video"));
						}

						isVideoSelect = true;
					}
				}
				if (requestCode == Constant.RESULT_GALLERY_VIDEOCAPTURE) {

					String s = getPath(data.getData());
					Bitmap videoThumb = ThumbnailUtils.createVideoThumbnail(s,
							MediaStore.Images.Thumbnails.MINI_KIND);
					imageBitmapArrayList.add(new CreatePostModel(videoThumb,
							"video"));

					isVideoSelect = true;

				}
				if (requestCode == Constant.RESULT_GALLERY_CAMERA_IMAGE) {

					if (imageBitmapArrayList.size() <= 3) {
						Bitmap photo = (Bitmap) data.getExtras().get("data");
						imageBitmapArrayList.add(new CreatePostModel(photo,
								"image"));
					}

				}

				setupCustomLists();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean checkImageCount() {

		for (int i = 0; i < imageBitmapArrayList.size(); i++) {
			String imageType = imageBitmapArrayList.get(i).getType();
			if (imageType.equals("image")) {

				if (i <= 2) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_post_activity_closebutton:

			// Intent i = new Intent(getActivity(), HomeActivity.class);
			// startActivity(i);
			// getActivity().onBackPressed();
			getFragmentManager().popBackStack(
					((HomeActivity) getActivity()).getPreviousTag(),
					getFragmentManager().POP_BACK_STACK_INCLUSIVE);
			break;
		case R.id.ib_post_activity_gallary:

			onOpenGallary();
			break;
		case R.id.ib_post_activity_video_capture:

			if (!isVideoSelect) {
				onOpenVideo();
			} else {
				Toast.makeText(getActivity(), "Video already select...",
						Toast.LENGTH_LONG).show();
			}

			break;
		case R.id.ib_post_activity_image_capture:

			onOpenCamera();
			break;
		case R.id.tv_post_activity_hash:

			et_post_activity_msg.setText(et_post_activity_msg.getText()
					.toString() + "#");
			et_post_activity_msg.setSelection(et_post_activity_msg.getText()
					.toString().length());

			break;

		default:
			break;
		}
	}

}
