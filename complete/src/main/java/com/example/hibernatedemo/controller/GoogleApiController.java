package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.client.YoutubeClient;
import com.example.hibernatedemo.dto.YoutubeSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mohd.waseem on 16/07/20.
 */
@RestController
@RequestMapping("google")
public class GoogleApiController {

    final YoutubeClient youtubeClient;
    static final String token = "Bearer ya29.a0AfH6SMA0nDVrMxOPlfQ7o_qfXMmILtyUGldI5yNBvF898akxriB69dR3QPxHTBWC8_-2PtWQ2tm1gXwRsWgYM83sM6jZtjNMaOlIurnpbrSTc-NW-_PpBA66q8OB6EOdZhevioSPMZxe3xglPf-_RCcppbA4bRY8alc";
    static final String key = "AIzaSyCnriu-JRRzBLwvMQkrUzHmJmqyVCOJLzU";
    static final String part = "snippet";
    static final String type = "video";
    static final Integer maxResult = 50;

    @Autowired
    public GoogleApiController(YoutubeClient youtubeClient) {
        this.youtubeClient = youtubeClient;
    }

    @GetMapping("test")
    public ResponseEntity<String> testYoutubeApi(@RequestParam Boolean isToken) {
        String npt = null;
        for(int i=0; i<11000;i++) {
            try{
                ResponseEntity<YoutubeSearchDTO> dto;
                if(isToken) {
                    dto = youtubeClient.searchVideo(token, null, part, type, maxResult, npt);
                }
                else {
                    dto = youtubeClient.searchVideo(null, key, part, type, maxResult, npt);
                }
                if(HttpStatus.FORBIDDEN.equals(dto.getStatusCode())) {
                    System.out.println("Bad request!!! "+dto.toString());
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("FAILED!!!");
                }
                System.out.println(String.format("Number: %s, kind: %s, regionCode: %s, nextToken: %s",
                        i,
                        dto.getBody() != null ? dto.getBody().getKind() : "empty",
                        dto.getBody() != null ? dto.getBody().getRegionCode() : "empty",
                        dto.getBody() != null ? dto.getBody().getNextPageToken() : "empty"));
                npt = (dto.getBody() != null && dto.getBody().getNextPageToken() != null) ?  dto.getBody().getNextPageToken() : null;
            } catch (Exception ex) {
                System.out.println("Exception Occurred!!!! ");
                ex.printStackTrace();
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("FAILED!!!");
            }
        }

        return ResponseEntity.ok().body("Success");
    }
}
