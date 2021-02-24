package com.knapptown.gpmdataexplorer.services;

import java.util.List;

import javax.transaction.Transactional;

import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.entities.SongId;
import com.knapptown.gpmdataexplorer.exceptions.SongNotFoundException;
import com.knapptown.gpmdataexplorer.mappers.SongMapper;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.repositories.SongRepository;

import org.springframework.stereotype.Service;

@Service
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
    public Song getSong(SongId id) {
        SongEntity songEntity = songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
        return songMapper.mapSongEntityToSong(songEntity);
    }

    @Transactional
    public Song saveSong(Song song) {
        SongEntity songEntity = songMapper.mapSongToSongEntity(song);
        return songMapper.mapSongEntityToSong(songRepository.save(songEntity));
    }

    @Transactional
    public boolean songExists(Song song) {
        SongId id = songMapper.mapSongToSongId(song);
        return songRepository.existsById(id);
    }
    
}
