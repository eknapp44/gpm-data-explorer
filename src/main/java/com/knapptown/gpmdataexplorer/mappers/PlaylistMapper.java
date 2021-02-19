package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.models.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    List<PlaylistEntity> mapPlaylistsToPlaylistEntities(List<Playlist> playlists);

    @Mapping(target = "songs", ignore = true)
    PlaylistEntity mapPlaylistToPlaylistEntity(Playlist playlist);

    List<Playlist> mapPlaylistEntitiesToPlaylists(List<PlaylistEntity> playlistEntities);

    Playlist mapPlaylistEntityToPlaylist(PlaylistEntity playlistEntity);

}
