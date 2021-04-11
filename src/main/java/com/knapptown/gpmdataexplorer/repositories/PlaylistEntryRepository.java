package com.knapptown.gpmdataexplorer.repositories;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntity;
import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistEntryRepository extends JpaRepository<PlaylistEntryEntity, PlaylistEntryEntityId> {

    List<PlaylistEntryEntity> findByPlaylistId(Long id);

    List<PlaylistEntryEntity> findByPlaylistIdAndSongId(Long playlistId, Long songId);

    boolean existsPlaylistEntryEntitiesByPlaylistIdAndPlaylistIndex(Long playlistId, int playlistIndex);

}
