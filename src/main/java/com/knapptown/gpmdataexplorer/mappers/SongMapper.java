package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.entities.SongId;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.models.SongCsvObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    List<SongEntity> mapSongsToSongEntities(List<Song> songs);

    @Mapping(target = "playlists", ignore = true)
    @Mapping(target = "id", expression = "java(mapSongToSongId(song))")
    SongEntity mapSongToSongEntity(Song song);

    List<Song> mapSongEntitiesToSongs(List<SongEntity> songEntities);

    @Mapping(target = "title", source = "songEntity.id.title")
    @Mapping(target = "artist", source = "songEntity.id.artist")
    @Mapping(target = "album", source = "songEntity.id.album")
    Song mapSongEntityToSong(SongEntity songEntity) ;

    @Mapping(target = "durationMs", expression = "java(Integer.parseInt(songCsvObject.getDurationMs()))")
    @Mapping(target = "playCount", expression = "java(Integer.parseInt(songCsvObject.getPlayCount()))")
    @Mapping(target = "rating", expression = "java(Integer.parseInt(songCsvObject.getRating()))")
    @Mapping(target = "playlistIndex", expression = "java(Integer.parseInt(songCsvObject.getPlaylistIndex()))")
    @Mapping(target = "removed", expression = "java(Boolean.parseBoolean(songCsvObject.getRemoved()))")
    Song mapSongCsvObjectToSong(SongCsvObject songCsvObject);

    SongId mapSongToSongId(Song song);

}
