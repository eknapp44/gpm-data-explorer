package com.knapptown.gpmdataexplorer.repositories;

import com.knapptown.gpmdataexplorer.entities.SongEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongEntity, Long> {

    SongEntity findByTitleAndAlbumAndArtist(String title, String album, String artist);

    boolean existsByTitleAndAlbumAndArtist(String title, String album, String artist);

}
