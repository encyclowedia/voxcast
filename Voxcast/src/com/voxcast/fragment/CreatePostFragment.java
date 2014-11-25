package com.voxcast.fragment;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.voxcast.R;
import com.voxcast.activity.HomeActivity;
import com.voxcast.adapter.CreatePostAdaper;
import com.voxcast.constant.Constant;
import com.voxcast.model.Comment;
import com.voxcast.model.Poster;
import com.voxcast.model.Result;
import com.voxcast.utilities.Utils;
import com.voxcast.view.HorizontalListView;

public class CreatePostFragment extends BaseFragment implements OnClickListener {

	private HorizontalListView mHlvCustomList;
	private int hashButtonWidth;
	private ArrayList<String> imageBitmapArrayList = new ArrayList<String>();
	private ArrayList<String> videoImageBitmapArrayList = new ArrayList<String>();

	private TextView tv_post_activity_200, tv_post_activity_hash;
	private EditText et_post_activity_msg;
	private int et_lenght;
	private CreatePostAdaper adapter;
	private Uri uri;
	private CheckBox chk_postAsAnonymous;
	private OrientationEventListener orientationEventListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_createpost, container, false);
		setUI(view);
		initListenerOnEditText();
		hashButtonWidth = getResources().getDrawable(R.drawable.hash)
				.getMinimumWidth();
		setupCustomLists();
		orientationEventListener = ((HomeActivity) getActivity())
				.getOrientationEventListener();

		if (orientationEventListener != null) {
			orientationEventListener.disable();
		}

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

		v.findViewById(R.id.ibtn_Post).setOnClickListener(this);

		chk_postAsAnonymous = (CheckBox) v
				.findViewById(R.id.chk_postAsAnonymous);

		et_post_activity_msg = (EditText) v
				.findViewById(R.id.et_post_activity_msg);

		tv_post_activity_200 = (TextView) v
				.findViewById(R.id.tv_post_activity_200);
	}

	private void initListenerOnEditText() {

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

				int line = et_post_activity_msg.getLineCount();
				if (line <= 5)
					et_post_activity_msg.setPadding(10, 0,
							hashButtonWidth + 20, 0);

				tv_post_activity_200.setText(String.valueOf(et_lenght));
			}
		});
	}

	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * setContentView(R.layout.post_activity); }
	 */

	private void setupCustomLists() {
		// Make an array adapter using the built in android layout to render a
		// list of strings

		adapter = new CreatePostAdaper(getActivity(), imageBitmapArrayList,
				videoImageBitmapArrayList);
		// Assign adapter to HorizontalListView
		mHlvCustomList.setAdapter(adapter);

	}

	private Uri getUri(boolean isVideo) {

		ContentValues values = new ContentValues();
		values.put(MediaStore.Images.Media.TITLE, "Vox_"
				+ Calendar.getInstance().getTimeInMillis());
		if (isVideo) {
			return (Uri) getActivity().getContentResolver().insert(
					MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
		} else {
			return (Uri) getActivity().getContentResolver().insert(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		}

	}

	private void onOpenCamera() {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		uri = getUri(false);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(cameraIntent,
				Constant.RESULT_GALLERY_CAMERA_IMAGE);
	}

	private void onOpenVideo() {
		Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
		uri = getUri(true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, Constant.RESULT_GALLERY_VIDEOCAPTURE);
	}

	private void onOpenGallery() {

		if (Environment.getExternalStorageState().equals("mounted")) {
			Intent intent = new Intent();

			intent.setType("image/*,video/*");
			intent.setAction(Intent.ACTION_PICK);

			startActivityForResult(intent, Constant.RESULT_GALLERY_VIDEO_IMAGE);
		}
	}

	private String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = getActivity().getContentResolver().query(uri,
				projection, null, null, null);

		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	private boolean isImage(String selectImage) {

		if (selectImage.startsWith("image")) {
			return true;
		}
		return false;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (resultCode == Activity.RESULT_OK) {
				if (requestCode == Constant.RESULT_GALLERY_VIDEO_IMAGE
						|| requestCode == Constant.RESULT_GALLERY_CAMERA_IMAGE
						|| requestCode == Constant.RESULT_GALLERY_VIDEOCAPTURE) {

					Uri selectedImageUri = null;
					if (data != null) {
						selectedImageUri = data.getData();
					}

					if (selectedImageUri == null) {
						selectedImageUri = uri;
					}

					String selectedImagePath = getPath(selectedImageUri);
					boolean isImage = false;
					if (requestCode == Constant.RESULT_GALLERY_VIDEO_IMAGE) {
						ContentResolver cr = getActivity().getContentResolver();
						String mime = cr.getType(selectedImageUri);
						isImage = isImage(mime);
					} else if (requestCode == Constant.RESULT_GALLERY_CAMERA_IMAGE) {
						isImage = true;
					}

					if (isImage) {

						if (!checkImageCount()) {
							imageBitmapArrayList.add("file://"
									+ selectedImagePath);
						} else {
							Toast.makeText(getActivity(),
									"Image  already selected...",
									Toast.LENGTH_LONG).show();
						}
					} else {
						if (videoImageBitmapArrayList.size() < 1) {
							videoImageBitmapArrayList.add("file://"
									+ selectedImagePath);
						} else {
							Toast.makeText(getActivity(),
									"Video already selected...",
									Toast.LENGTH_LONG).show();
						}
					}
				}

				adapter.notifyDataSetChanged();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean checkImageCount() {
		return (imageBitmapArrayList.size() >= Constant.MAX_IMAGE_COUNT);
	}

	private boolean checkVideoCount() {
		return (videoImageBitmapArrayList.size() >= Constant.MAX_VIDEO_COUNT);
	}

	@Override
	public void onClick(View v) {

		// checkImageCount();
		if (v.getId() != R.id.tv_post_activity_hash)
			Utils.hideKeyBoard(getActivity(), v.getWindowToken());
		switch (v.getId()) {
		case R.id.ibtn_Post:

			String string = et_post_activity_msg.getText().toString();
			boolean isHashTagPresent = string.contains("#");

			if (!isHashTagPresent) {
				showErrorMessage("Please type a hashtag for your post");
				return;
			}

			if (string.length() > 200) {
				showErrorMessage("Maximum length of post should be less than 200 characters.");
				return;
			}

			ArrayList<Result> arrayList = ((HomeActivity) getActivity())
					.getArrayList();
			Poster poster;
			if (chk_postAsAnonymous.isChecked()) {
				poster = new Poster("-1", "", "Anonymous");
			} else {
				poster = new Poster("" + 1, "", "Schnider Rose");
			}
			ArrayList<Poster> upVoters = new ArrayList<Poster>();
			ArrayList<Poster> downVoters = new ArrayList<Poster>();
			ArrayList<String> pics = imageBitmapArrayList;
			ArrayList<String> vid = videoImageBitmapArrayList;
			ArrayList<Comment> comments = new ArrayList<Comment>();
			Result result = new Result(arrayList.size() + "", poster, string,
					"Noida", upVoters, downVoters, "Just Now", pics, vid,
					comments);

			arrayList.add(0, result);

			popCurrentFragmentOut();

			if (getOnDataChangeListener() != null) {
				getOnDataChangeListener().refreshData();
			}

			break;
		case R.id.ib_post_activity_closebutton:

			popCurrentFragmentOut();
			Utils.hideKeyBoard(getActivity(), v.getWindowToken());

			break;
		case R.id.ib_post_activity_gallary:
			if (!checkVideoCount() || !checkImageCount()) {
				onOpenGallery();
			} else {

				Toast.makeText(getActivity(),
						"One Video, Four Image Gallary already selected...",
						Toast.LENGTH_LONG).show();
			}

			break;
		case R.id.ib_post_activity_video_capture:

			if (!checkVideoCount()) {
				onOpenVideo();
			} else {
				Toast.makeText(getActivity(),
						"One Video Capture already selected...",
						Toast.LENGTH_LONG).show();
			}

			break;
		case R.id.ib_post_activity_image_capture:
			if (!checkImageCount()) {
				onOpenCamera();
			} else {
				Toast.makeText(getActivity(),
						"Four Camera Image already selected...",
						Toast.LENGTH_LONG).show();
			}
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

	@Override
	public void onDetach() {
		super.onDetach();
		if (orientationEventListener != null) {
			orientationEventListener.enable();
		}
	}
}
