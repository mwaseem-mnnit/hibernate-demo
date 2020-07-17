package com.example.hibernatedemo.client;

import com.example.hibernatedemo.dto.YoutubeSearchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mohd.waseem on 16/06/20.
 */
@Component
@FeignClient(name = "youtubeClient", url = "${youtube.url}")
public interface YoutubeClient {
    @GetMapping(value = "search")
    ResponseEntity<YoutubeSearchDTO> searchVideo(
            @RequestHeader("Authorization") String token,
            @RequestParam("key") String key,
            @RequestParam(value = "part") String part,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "maxResults") Integer maxResults,
            @RequestParam(value = "pageToken", required = false) String pageToken);

}
