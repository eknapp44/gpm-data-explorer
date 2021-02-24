package com.knapptown.gpmdataexplorer.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    private int playlistIndex;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Song) {
            Song song = (Song) obj;
            return this.title.equals(song.getTitle()) &&
                    this.artist.equals(song.getArtist()) &&
                    this.album.equals(song.getAlbum());
        }
        return false;
    }

    @Override
    public int  hashCode() {
        return title.hashCode() + this.artist.hashCode() + this.album.hashCode();
    }

}