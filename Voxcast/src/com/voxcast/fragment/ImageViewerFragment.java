package com.voxcast.fragment;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.voxcast.R;
import com.voxcast.activity.HomeActivity;

@SuppressLint("InflateParams")
public class ImageViewerFragment extends BaseFragment {

	private static final String KEY_ISVIDEO = "ISVIDEO";
	private static final String KEY_PATH = "Path";

	public static ImageViewerFragment getInstanceOf(String uri, boolean isVideo) {
		ImageViewerFragment fragment = new ImageViewerFragment();
		Bundle bundle = new Bundle();
		bundle.putString(KEY_PATH, uri);
		bundle.putBoolean(KEY_ISVIDEO, isVideo);
		fragment.setArguments(bundle);
		return fragment;
	}

	private String path;
	private boolean isVideo;
	private OrientationEventListener orientationEventListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments();
		if (arguments != null) {
			path = arguments.getString(KEY_PATH);
			isVideo = arguments.getBoolean(KEY_ISVIDEO);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_imageviewer, null);
		view.findViewById(R.id.img_showImage).setVisibility(
				!isVideo ? View.VISIBLE : View.GONE);
		view.findViewById(R.id.videoView).setVisibility(
				isVideo ? View.VISIBLE : View.GONE);
		if (isVideo) {
			VideoView videoView = (VideoView) view.findViewById(R.id.videoView);
			if (TextUtils.isEmpty(path)) {
				popCurrentFragmentOut();
				return view;
			}
			Uri uri = Uri.parse(path);
			videoView.setVideoURI(uri);
			MediaController mediaController = new MediaController(getActivity());
			mediaController.setAnchorView(videoView);
			videoView.setMediaController(mediaController);

			videoView.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					mp.start();
				}

			});
			videoView.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					popCurrentFragmentOut();
				}
			});
		} else {
			ImageView imageView = (ImageView) view
					.findViewById(R.id.img_showImage);
			ImageLoader.getInstance().displayImage(path, imageView,
					HomeActivity.getOptions());
		}
		if (getActivity() instanceof HomeActivity) {
			orientationEventListener = ((HomeActivity) getActivity())
					.getOrientationEventListener();
		}

		if (orientationEventListener != null)
			orientationEventListener.disable();
		return view;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (orientationEventListener != null)
			orientationEventListener.enable();
	}
}
