package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.models.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    List<SongEntity> mapSongsToSongEntities(List<Song> songs);

    @Mapping(target = "playlistEntries", ignore = true)
    SongEntity mapSongToSongEntity(Song song);

    List<Song> mapSongEntitiesToSongs(List<SongEntity> songEntities);

    Song mapSongEntityToSong(SongEntity songEntity) ;

}
