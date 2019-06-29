package com.weather.weatherdoppler;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoibmlja2FsYXMiLCJhIjoiY2p2eTJkOXI2MGJmdDQ4cGMxMHllZzQxYSJ9.MGT4wEduz99RGkCIwKTbCw");
        setContentView(R.layout.activity_main);


        ImageView myImageView = findViewById(R.id.dopplerimageView);
        UrlBuilder myUrlBuilder = new UrlBuilder();
        //https://tilecache.rainviewer.com/v2/radar/1561737000/1024/2/39.290386/-76.612190/2/1_1.png
        String Url = myUrlBuilder.buildUrl("1561737000","1024","2","51.50550","-0.07520","2","1_1");

        myImageView.setTag(Url);



        ReturnDopplerImg myDop = new ReturnDopplerImg();

        myDop.execute(myImageView);
        //myDop.onPostExectute();
       //myImageView.setImageDrawable(myDop.LoadImageFromWebOperations(""));




        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {


                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(51.50550, -0.07520))
                        .zoom(2)
                        .tilt(0)
                        .build();




                //LatLng latLng = new LatLng(39.290386,-76.612190);
                //CameraPosition camera = new CameraPosition(latLng,2,0);
                mapboxMap.setCameraPosition(position);
                //mapboxMap.getCameraPosition();
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {


                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

// Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}

