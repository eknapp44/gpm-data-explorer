package com.knapptown.gpmdataexplorer.services;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.exceptions.SongExistsException;
import com.knapptown.gpmdataexplorer.exceptions.SongNotFoundException;
import com.knapptown.gpmdataexplorer.mappers.SongMapper;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.repositories.SongRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SongService {

    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository,
                       SongMapper songMapper) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    @Transactional
    public List<Song> getAllSongs() {
        return songMapper.mapSongEntitiesToSongs(songRepository.findAll());
    }

    @Transactional
    public Song getSong(@Positive Long id) {
        SongEntity songEntity = songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException(id));
        return songMapper.mapSongEntityToSong(songEntity);
    }

    @Transactional
    public Song getSongByTitleAndArtistAndAlbum(@NotBlank String title, @NotBlank String artist, @NotBlank String album) {
        SongEntity songEntity = songRepository.findByTitleAndAlbumAndArtist(title, album, artist);
        return songMapper.mapSongEntityToSong(songEntity);
    }

    @Transactional
    public Song createSong(@Valid Song song) {
        if (songRepository.existsByTitleAndAlbumAndArtist(song.getTitle(), song.getAlbum(), song.getArtist())) {
            throw new SongExistsException(song.getTitle(), song.getArtist(), song.getAlbum());
        }
        return saveSong(song);
    }

    @Transactional
    public Song updateSong(@Valid Song song) {
        return saveSong(song);
    }

    private Song saveSong(@Valid Song song) {
        SongEntity songEntity = songMapper.mapSongToSongEntity(song);
        return songMapper.mapSongEntityToSong(songRepository.save(songEntity));
    }
    
}
