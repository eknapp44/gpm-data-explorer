package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.models.Playlist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaylistMapper {

    public List<PlaylistEntity> mapPlaylistsToPlaylistEntities(List<Playlist> playlists) {
        return playlists.stream().map(this::mapPlaylistToPlaylistEntity).collect(Collectors.toList());
    }

    public PlaylistEntity mapPlaylistToPlaylistEntity(Playlist playlist) {
        return PlaylistEntity.builder()
                .id(playlist.getId())
                .title(playlist.getTitle())
                .owner(playlist.getOwner())
                .descriptions(playlist.getDescription())
                .deleted(playlist.isDeleted())
                .shared(playlist.isShared())
                .build();
    }

    public List<Playlist> mapPlaylistEntitiesToPlaylists(List<PlaylistEntity> playlistEntities) {
        return playlistEntities.stream().map(this::mapPlaylistEntityToPlaylist).collect(Collectors.toList());
    }

    public Playlist mapPlaylistEntityToPlaylist(PlaylistEntity playlistEntity) {
        return Playlist.builder()
                .id(playlistEntity.getId())
                .title(playlistEntity.getTitle())
                .description(playlistEntity.getDescriptions())
                .owner(playlistEntity.getOwner())
                .shared(playlistEntity.isShared())
                .deleted(playlistEntity.isDeleted())
                .build();
    }

}
