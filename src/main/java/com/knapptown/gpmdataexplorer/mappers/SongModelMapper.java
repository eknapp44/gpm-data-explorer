package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.models.SongCsvObject;
import org.springframework.stereotype.Component;

@Component
public class SongModelMapper {

    public Song mapSongCsvObjectToSong(SongCsvObject songCsvObject) {
       return Song.builder()
               .title(songCsvObject.getTitle())
               .artist(songCsvObject.getArtist())
               .album(songCsvObject.getAlbum())
               .durationMs(Integer.parseInt(songCsvObject.getDurationMs()))
               .playCount(Integer.parseInt(songCsvObject.getPlayCount()))
               .rating(Integer.parseInt(songCsvObject.getRating()))
               .removed(Boolean.parseBoolean(songCsvObject.getRemoved()))
               .playlistIndex(Integer.parseInt(songCsvObject.getPlaylistIndex()))
               .build();
    }
}
