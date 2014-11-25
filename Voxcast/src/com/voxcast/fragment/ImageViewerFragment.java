package com.voxcast.fragment;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.voxcast.R;
import com.voxcast.activity.HomeActivity;
import com.voxcast.adapter.ImageAdapter;

@SuppressLint("InflateParams")
public class ImageViewerFragment extends BaseFragment {

	private static final String KEY_ISVIDEO = "ISVIDEO";
	private static final String KEY_PATHS = "Paths";
	private static final String KEY_POSITION = "position";

	public static ImageViewerFragment getInstanceOf(ArrayList<String> uri,
			int position, boolean isVideo) {
		ImageViewerFragment fragment = new ImageViewerFragment();
		Bundle bundle = new Bundle();
		bundle.putStringArrayList(KEY_PATHS, uri);
		bundle.putInt(KEY_POSITION, position);
		bundle.putBoolean(KEY_ISVIDEO, isVideo);
		fragment.setArguments(bundle);
		return fragment;
	}

	private int position;
	private ArrayList<String> paths;
	private boolean isVideo;
	private OrientationEventListener orientationEventListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle arguments = getArguments();
		if (arguments != null) {
			isVideo = arguments.getBoolean(KEY_ISVIDEO);
			position = arguments.getInt(KEY_POSITION);
			paths = arguments.getStringArrayList(KEY_PATHS);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		String path = paths.get(position);
		View view = inflater.inflate(R.layout.fragment_imageviewer, null);
		view.findViewById(R.id.vp_images).setVisibility(
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
			ViewPager pager = (ViewPager) view.findViewById(R.id.vp_images);
			pager.setAdapter(new ImageAdapter(getActivity(), paths));
			pager.setCurrentItem(position);
			// ImageLoader.getInstance().displayImage(path, imageView,
			// HomeActivity.getOptions());
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
