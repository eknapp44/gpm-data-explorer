package com.knapptown.gpmdataexplorer.repositories;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    
}
