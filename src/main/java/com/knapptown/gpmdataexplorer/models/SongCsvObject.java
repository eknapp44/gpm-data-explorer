package com.knapptown.gpmdataexplorer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SongCsvObject {
    private String title;
    private String album;
    private String artist;
    @JsonProperty("Duration (ms)")
    private String durationMs;
    private String rating;
    @JsonProperty("Play Count")
    private String playCount;
    private String removed;
    @JsonProperty("Playlist Index")
    private String playlistIndex;
}