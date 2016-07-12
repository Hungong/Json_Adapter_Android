package com.example.hungo.jsonn.until;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.hungo.jsonn.MainActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hungo on 7/12/2016.
 */
public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    public String url;
    ImageView bmImage;
    ProgressDialog mProgressDialog;

    public DownloadImage(ImageView bmImage) {
        this.bmImage = bmImage;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        mProgressDialog = new ProgressDialog(ma);
//        // Set progressdialog title
//        mProgressDialog.setTitle("Download Image Tutorial");
//        // Set progressdialog message
//        mProgressDialog.setMessage("Loading...");
//        mProgressDialog.setIndeterminate(false);
//        // Show progressdialog
//        mProgressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        this.url = urlDisplay;
        Bitmap mImage = null;

        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            mImage = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mImage;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        // Do something
        bmImage.setImageBitmap(result);
    }
}
