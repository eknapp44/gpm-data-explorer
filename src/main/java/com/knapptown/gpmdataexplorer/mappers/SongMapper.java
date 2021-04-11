package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.models.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongMapper extends BasicMapper<Song, SongEntity> {

    @Override
    @Mapping(target = "playlistEntries", ignore = true)
    SongEntity mapModelToEntity(Song song);

}
