package com.weather.weatherdoppler;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.mapbox.api.staticmap.v1.MapboxStaticMap;
import com.mapbox.api.staticmap.v1.StaticMapCriteria;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.api.staticmap.*;

import java.time.Instant;


public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



final double latitude =  27.6648;
final double longitude = -81.5158;
long timeStamp = Instant.now().getEpochSecond();
timeStamp = timeStamp - (timeStamp % 600);
int zoom = 2;


        MapboxStaticMap staticImage = MapboxStaticMap.builder()
                .accessToken("pk.eyJ1Ijoibmlja2FsYXMiLCJhIjoiY2p2eTJkOXI2MGJmdDQ4cGMxMHllZzQxYSJ9.MGT4wEduz99RGkCIwKTbCw")
                .styleId(StaticMapCriteria.LIGHT_STYLE)
                .cameraPoint(Point.fromLngLat(longitude, latitude)) // Image's centerpoint on map
                .cameraZoom(zoom)
                .width(512) // Image width
                .height(512) // Image height
                .retina(true) // Retina 2x image will be returned
                .build();



        Mapbox.getInstance(this, "pk.eyJ1Ijoibmlja2FsYXMiLCJhIjoiY2p2eTJkOXI2MGJmdDQ4cGMxMHllZzQxYSJ9.MGT4wEduz99RGkCIwKTbCw");
        setContentView(R.layout.activity_main);


        ImageView myImageView = findViewById(R.id.staticmapimageView);
        UrlBuilder myUrlBuilder = new UrlBuilder();
        //https://tilecache.rainviewer.com/v2/radar/1561737000/1024/2/39.290386/-76.612190/2/1_1.png


        myImageView.setTag(staticImage.url().toString());
        ReturnDopplerImg myDop = new ReturnDopplerImg();
        myDop.execute(myImageView);
        //myDop.onPostExectute();
       //myImageView.setImageDrawable(myDop.LoadImageFromWebOperations(""));

try {
    ImageView myDopImageView = findViewById(R.id.dopplerimageView);
    UrlBuilder myDopUrlBuilder = new UrlBuilder();
    //https://tilecache.rainviewer.com/v2/radar/1561737000/1024/2/39.290386/-76.612190/2/1_1.png
    String DopUrl = myUrlBuilder.buildUrl(Long.toString(timeStamp),"512",Integer.toString(zoom),Double.toString(latitude),Double.toString(longitude),"2","1_1");
    myDopImageView.setTag(DopUrl);
    ReturnDopplerImg myDopImg = new ReturnDopplerImg();
    myDopImg.execute(myDopImageView);
}
catch(Exception ex) {
    ex.printStackTrace();
}



        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {


                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(latitude, longitude))
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

