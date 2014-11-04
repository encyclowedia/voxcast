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

import com.voxcast.R;
import com.voxcast.fragment.CreatePostFragment;
import com.voxcast.model.CreatePostModel;

public class CreatePostAdaper extends BaseAdapter {

	private LayoutInflater inflater;
	ArrayList<CreatePostModel> imageBitmapArrayList;

	public CreatePostAdaper(Context context,
			ArrayList<CreatePostModel> imageBitmapArrayList2) {
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

			vi = inflater.inflate(R.layout.custom_create_post_activity, null);

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
		holder.iv_post_activity_image.setImageBitmap(imageBitmapArrayList.get(
				position).getBitmap());

		String imageType = imageBitmapArrayList.get(position).getType();
		if (imageType.equals("video")) {
			holder.ib_postactivity_play_icon.setVisibility(View.VISIBLE);
		}

		else {
			holder.ib_postactivity_play_icon.setVisibility(View.INVISIBLE);
		}
		holder.ib_postactivity_cross.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String imageType = imageBitmapArrayList.get(position).getType();
				if (imageType.equals("video")) {

					CreatePostFragment.isVideoSelect = false;
				}
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

	public static class Holder {
		public ImageView iv_post_activity_image;
		public ImageButton ib_postactivity_play_icon;
		public ImageButton ib_postactivity_cross;
	}

}