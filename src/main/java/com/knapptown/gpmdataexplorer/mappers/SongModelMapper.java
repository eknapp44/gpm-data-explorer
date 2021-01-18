package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.models.SongCsvObject;
import org.springframework.stereotype.Component;

@Component
public class SongModelMapper {

    public Song mapSongCsvObjectToSong(SongCsvObject songCsvObject) {
        Song song = new Song();
        song.setTitle(songCsvObject.getTitle());
        song.setArtist(songCsvObject.getArtist());
        song.setAlbum(songCsvObject.getAlbum());
        song.setDurationMs(Integer.parseInt(songCsvObject.getDurationMs()));
        song.setPlayCount(Integer.parseInt(songCsvObject.getPlayCount()));
        song.setRating(Integer.parseInt(songCsvObject.getRating()));
        song.setRemoved(Boolean.parseBoolean(songCsvObject.getRemoved()));
        song.setPlaylistIndex(Integer.parseInt(songCsvObject.getPlaylistIndex()));
        return song;
    }
}
