package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.models.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaylistMapper extends BasicMapper<Playlist, PlaylistEntity> {

    @Override
    @Mapping(target = "playlistEntries", ignore = true)
    PlaylistEntity mapModelToEntity(Playlist playlist);

}
