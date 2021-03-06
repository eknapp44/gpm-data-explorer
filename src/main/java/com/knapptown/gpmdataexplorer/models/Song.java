package com.knapptown.gpmdataexplorer.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Song {

    private String title;
    private String album;
    private String artist;
    private int durationMs;
    private int rating;
    private int playCount;
    private boolean removed;

}