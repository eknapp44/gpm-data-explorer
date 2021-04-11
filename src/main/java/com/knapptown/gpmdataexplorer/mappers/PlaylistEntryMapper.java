package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntity;
import com.knapptown.gpmdataexplorer.models.PlaylistEntry;
import com.knapptown.gpmdataexplorer.models.PlaylistEntryCsvObject;
import com.knapptown.gpmdataexplorer.models.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PlaylistMapper.class, SongMapper.class})
public interface PlaylistEntryMapper extends BasicMapper<PlaylistEntry, PlaylistEntryEntity> {

    @Mapping(target = "playlist", ignore = true)
    @Mapping(target = "song", expression = "java(mapPlaylistEntryCsvObjectToSong(playlistEntryCsvObject))")
    PlaylistEntry mapPlaylistEntryCsvObjectToPlaylistEntry(PlaylistEntryCsvObject playlistEntryCsvObject);

    @Mapping(target = "id", ignore = true)
    Song mapPlaylistEntryCsvObjectToSong(PlaylistEntryCsvObject playlistEntryCsvObject);

}
