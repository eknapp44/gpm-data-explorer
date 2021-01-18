package com.knapptown.gpmdataexplorer.models;

import lombok.Data;

@Data
public class Song {

    private String title;
    private String album;
    private String artist;
    private int durationMs;
    private int rating;
    private int playCount;
    private boolean removed;
    private int playlistIndex;

}