package com.weather.weatherdoppler;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class ReturnDopplerImg extends AsyncTask<ImageView, Drawable, Drawable> {

    ImageView imageView = null;

    @Override
    protected Drawable doInBackground(ImageView... imageViews) {
        this.imageView = imageViews[0];
        return LoadImageFromWebOperations((String)imageView.getTag());
    }

    @Override
    protected void onPostExecute(Drawable result) {
        imageView.setImageDrawable(result);
    }
    //uses this api: https://www.rainviewer.com/api.html
    //url format: https://tilecache.rainviewer.com/v2//radar/{ts}/{size}/{z}/{latitude}/{longitude}/{color}/{options}.png

    //example of accepted timestamps (last 2 hours): https://tilecache.rainviewer.com/api/maps.json
    //example of url returning doppler data over baltimore: https://tilecache.rainviewer.com/v2/radar/1561737000/1024/2/39.290386/-76.612190/2/1_1.png

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            //TODO: remove below line, for testing only. Build method to return url.

            //url = "https://tilecache.rainviewer.com/v2/radar/1561750800/1024/2/39.290386/-76.612190/2/1_1.png";
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
