package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.models.Song;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SongMapper {

    public List<SongEntity>  mapSongsToSongEntities(List<Song> songs) {
        return songs.stream().map(this::mapSongToSongEntity).collect(Collectors.toList());
    }

    public SongEntity mapSongToSongEntity(Song song) {
        return SongEntity.builder()
                .id(song.getId())
                .title(song.getTitle())
                .album(song.getAlbum())
                .artist(song.getArtist())
                .durationMs(song.getDurationMs())
                .playCount(song.getPlayCount())
                .playlistIndex(song.getPlaylistIndex())
                .rating(song.getRating())
                .removed(song.isRemoved())
                .build();
    }

    public List<Song> mapSongEntitiesToSongs(List<SongEntity> songEntities) {
        return songEntities.stream().map(this::mapSongEntityToSong).collect(Collectors.toList());
    }

    public Song mapSongEntityToSong(SongEntity songEntity) {
        Song song = new Song();
        song.setId(songEntity.getId());
        song.setTitle(songEntity.getTitle());
        song.setArtist(songEntity.getArtist());
        song.setAlbum(songEntity.getAlbum());
        song.setDurationMs(songEntity.getDurationMs());
        song.setPlayCount(songEntity.getPlayCount());
        song.setPlaylistIndex(songEntity.getPlaylistIndex());
        song.setRating(songEntity.getRating());
        song.setRemoved(songEntity.isRemoved());
        return song;
    }

}
