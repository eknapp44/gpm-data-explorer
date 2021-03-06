package com.knapptown.gpmdataexplorer.repositories;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, PlaylistEntityId> {
    
}
