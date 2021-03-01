package com.knapptown.gpmdataexplorer.repositories;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntity;
import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistEntryRepository extends JpaRepository<PlaylistEntryEntity, PlaylistEntryEntityId> {
}
