package com.example.hungo.jsonn.control;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hungo.jsonn.R;
import com.example.hungo.jsonn.constant.Constant;
import com.example.hungo.jsonn.model.VideoItem;
import com.example.hungo.jsonn.until.DownloadImage;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hungo on 7/10/2016.
 */
public class VideoAdapter extends BaseAdapter{

    private ArrayList<VideoItem> lstData = new ArrayList<VideoItem>();
    Context context;
    LayoutInflater inflater;

    public VideoAdapter(Context context, ArrayList<VideoItem> lstData){
        this.context = context;
        this.lstData = lstData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lstData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        View v = convertView;
        holder = new Holder();
        if(v == null){
            Log.d("H-O", position + "");
            v = inflater.inflate(R.layout.item_layout,  null);
            holder.tvStt = (TextView) v.findViewById(R.id.tvStt);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            holder.btnEdit = (ImageView) v.findViewById(R.id.btnEdit);

            v.setTag(holder);
        } else {
            holder = (Holder) v.getTag();
        }
        VideoItem t = lstData.get(position);
//        holder.tvStt.setText(position +"");
        holder.tvName.setText(t.getTiltle());

        String urlImage = t.getUrl();
            new DownloadImage(holder.btnEdit).execute(urlImage);


//        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editItem(position, lstData.get(position));
//            }
//        });
//
//        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteItem(position);
//            }
//        });


        return v;
    }

    class Holder{
        TextView tvStt;
        TextView tvName;
        ImageView btnDelete;
        ImageView btnEdit;
    }

    public void editItem(int stt, VideoItem item ){
        VideoItem temp = lstData.get(stt);
        // Do something

        notifyDataSetChanged();
    }
    public  void deleteItem(int stt){
        lstData.remove(stt);
        notifyDataSetChanged();
    }
}
