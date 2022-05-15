package com.example.eartquake.controller;

import com.example.eartquake.model.request.EarthQuakeListRequest;
import com.example.eartquake.model.response.EarthQuakeListResponse;
import com.example.eartquake.service.EarthQuakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EarthQuakeRestController {

    private final EarthQuakeService earthQuakeService;

    public EarthQuakeRestController(EarthQuakeService earthQuakeService) {
        this.earthQuakeService = earthQuakeService;
    }

    //Rest Controller takes two parameters which are country name and number of days to return earthquakes according to given parameters
    @GetMapping("/earthquakes")
    public ResponseEntity getEarthQuakeListGet(@RequestParam(value = "countryName") String countryName, @RequestParam(value = "countOfPastDays") Integer countOfPastDays) {
        EarthQuakeListResponse earthQuakeListResponse = earthQuakeService.getEarthQuakeList(countryName, countOfPastDays);
        if (earthQuakeListResponse.getEarthQuakeInfoList().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("No Earthquakes were recorded past " + countOfPastDays + " days");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(earthQuakeListResponse);
        }
    }

    //Rest Controller takes request object which includes country name and number of days to return earthquakes according to given request
    @PostMapping("/earthquakes")
    public ResponseEntity getEarthQuakeListPost(@RequestBody EarthQuakeListRequest earthQuakeListRequest) {
        EarthQuakeListResponse earthQuakeListResponse = earthQuakeService.getEarthQuakeList(earthQuakeListRequest);

        if (earthQuakeListResponse.getEarthQuakeInfoList().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("No Earthquakes were recorded past " + earthQuakeListRequest.getCountOfPastDays() + " days");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(earthQuakeListResponse);

        }
    }

}