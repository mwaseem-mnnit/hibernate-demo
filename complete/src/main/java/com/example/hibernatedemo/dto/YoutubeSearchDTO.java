package com.example.hibernatedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by mohd.waseem on 16/07/20.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YoutubeSearchDTO {
    String kind;
    String regionCode;
    String nextPageToken;
    List<Items> items;
    public static class Items {
        Id id;
        Snippet snippet;
        public class Snippet {
            String channelId;
            String title;
        }
        public class Id {
            String videoId;
        }
    }
}
