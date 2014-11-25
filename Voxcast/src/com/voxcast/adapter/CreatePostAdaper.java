package com.voxcast.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.voxcast.R;
import com.voxcast.fragment.CreatePostFragment;
import com.voxcast.model.CreatePostModel;

public class CreatePostAdaper extends BaseAdapter {

	private LayoutInflater inflater;
	ArrayList<String> imageBitmapArray;
	ArrayList<String> videoImageBitmapArray;

	public CreatePostAdaper(Context context,
			ArrayList<String> imageBitmapArray,
			ArrayList<String> videoImageBitmap) {
		inflater = LayoutInflater.from(context);
		this.imageBitmapArray = imageBitmapArray;
		this.videoImageBitmapArray = videoImageBitmap;
	}

	@Override
	public int getCount() {
		return imageBitmapArray.size() + videoImageBitmapArray.size();
	}

	@Override
	public Object getItem(int i) {
		if (i < imageBitmapArray.size())
			return imageBitmapArray.get(i);
		else
			return videoImageBitmapArray.get(0);

	}

	@Override
	public View getView(final int position, View convertView,
			ViewGroup viewGroup) {
		View vi = convertView;
		Holder holder;

		if (vi == null) {
			vi = inflater.inflate(R.layout.layout_row_image_createpost, null);

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

		ImageLoader
				.getInstance()
				.displayImage(
						position < imageBitmapArray.size() ? imageBitmapArray.get(position)
								: videoImageBitmapArray.get(0),
						holder.iv_post_activity_image);

		if (position < imageBitmapArray.size()) {
			holder.ib_postactivity_play_icon.setVisibility(View.INVISIBLE);
		} else {
			holder.ib_postactivity_play_icon.setVisibility(View.VISIBLE);
		}

		holder.ib_postactivity_cross.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String imageType = imageBitmapArray.get(position);

				if (position < imageBitmapArray.size()) {
					imageBitmapArray.remove(position);
				} else {
					videoImageBitmapArray.remove(0);
				}
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

	public static class Holder {
		public ImageView iv_post_activity_image;
		public ImageButton ib_postactivity_play_icon;
		public ImageButton ib_postactivity_cross;
	}

}
