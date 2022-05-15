package com.example.eartquake.service;

import com.example.eartquake.model.Feature;
import com.example.eartquake.model.UsgsResponse;
import com.example.eartquake.model.request.EarthQuakeListRequest;
import com.example.eartquake.model.response.EarthQuakeInfo;
import com.example.eartquake.model.response.EarthQuakeListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EarthQuakeService {

    //method used for Post Rest API
    public EarthQuakeListResponse getEarthQuakeList(EarthQuakeListRequest earthQuakeListRequest) {
        LocalDate today = LocalDate.now();
        LocalDate beforeDate = today.plusDays(-earthQuakeListRequest.getCountOfPastDays());
        String countryName = earthQuakeListRequest.getCountryName();

        return getEarthQuakeListResponse(today, beforeDate, countryName);
    }
    //method used for Get Rest API
    public EarthQuakeListResponse getEarthQuakeList(String countryName, Integer countOfPastDays) {
        LocalDate today = LocalDate.now();
        LocalDate beforeDate = today.plusDays(-countOfPastDays);

        return getEarthQuakeListResponse(today, beforeDate, countryName);
    }

    private EarthQuakeListResponse getEarthQuakeListResponse(LocalDate today, LocalDate beforeDate, String countryName) {
        RestTemplate restTemplate = new RestTemplate();
        //url is the default url to get response json object from API
        String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson";
        //this is dynamically created request url
        String requestUrl = url + "&starttime=" + beforeDate + "&endtime=" + today;
        //for mapping json object to usgsResponse ResponseEntity class is used
        ResponseEntity<UsgsResponse> response = restTemplate.getForEntity(requestUrl, UsgsResponse.class);
        //date formats for seperating the date and time
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

        EarthQuakeListResponse earthQuakeListResponse = new EarthQuakeListResponse();
        List<EarthQuakeInfo> earthQuakeInfoList = new ArrayList<>();
        //used null control for avoiding any null pointer exceptions
        if (response.getBody() != null && response.getBody().getFeatures() != null) {
            for (Feature feature : response.getBody().getFeatures()) {
                //from place attribute from the json object, string comparison is made via contain method
                //thus if a country name is mentioned in place attribute the object added to the earthquake info list created above
                //used toUppercase method to avoid case insensivity
                if (feature.getProperties() != null && feature.getProperties().getPlace() != null && feature.getProperties().getPlace().toUpperCase().contains(countryName.toUpperCase())) {
                    //created object named earthQuakeInfo from response json object
                    EarthQuakeInfo earthQuakeInfo = new EarthQuakeInfo();
                    //setting earthquake info from the json response object
                    earthQuakeInfo.setMagnitude(feature.getProperties().getMag());
                    earthQuakeInfo.setPlace(feature.getProperties().getPlace());
                    earthQuakeInfo.setCountryName(feature.getProperties().getPlace().substring(feature.getProperties().getPlace().lastIndexOf(",") + 2));
                    //Instant.ofEpochMilli is used to convert long data type into date
                    String date = dateFormat.format(Date.from(Instant.ofEpochMilli(feature.getProperties().getTime())));
                    String time = timeFormat.format(Date.from(Instant.ofEpochMilli(feature.getProperties().getTime())));
                    earthQuakeInfo.setOccurredDate(date);
                    earthQuakeInfo.setOccurredTime(time);
                    earthQuakeInfoList.add(earthQuakeInfo);
                }
            }
        }

        earthQuakeListResponse.setEarthQuakeInfoList(earthQuakeInfoList);
        return earthQuakeListResponse;
    }
}
