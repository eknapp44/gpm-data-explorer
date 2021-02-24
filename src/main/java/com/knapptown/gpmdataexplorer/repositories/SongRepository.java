package com.knapptown.gpmdataexplorer.repositories;

import com.knapptown.gpmdataexplorer.entities.SongEntity;

import com.knapptown.gpmdataexplorer.entities.SongId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongEntity, SongId> {
    
}
