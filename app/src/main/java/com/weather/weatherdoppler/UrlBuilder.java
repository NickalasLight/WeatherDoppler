package com.weather.weatherdoppler;

public class UrlBuilder {
    public String buildUrl(String timeStamp, String size, String zoom, String lat, String longitude, String color, String options){
        String url = "";
        url = "https://tilecache.rainviewer.com/v2/radar/" + timeStamp + "/" + size + "/" + zoom + "/" + lat +"/" + longitude + "/" + color +"/"+ options+".png";
        return url;
    }

    public String utcTimestamp(){

        return "";
    }
    //uses this api: https://www.rainviewer.com/api.html
    //url format: https://tilecache.rainviewer.com/v2/radar/{ts}/{size}/{z}/{latitude}/{longitude}/{color}/{options}.png

    //example of accepted timestamps (last 2 hours): https://tilecache.rainviewer.com/api/maps.json
    //example of url returning doppler data over baltimore: https://tilecache.rainviewer.com/v2/radar/1561737000/1024/2/39.290386/-76.612190/2/1_1.png


}
