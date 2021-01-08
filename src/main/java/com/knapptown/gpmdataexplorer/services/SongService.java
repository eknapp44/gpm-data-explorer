package com.knapptown.gpmdataexplorer.services;

import java.util.List;

import javax.transaction.Transactional;

import com.knapptown.gpmdataexplorer.entities.Song;
import com.knapptown.gpmdataexplorer.repositories.SongRepository;

import org.springframework.stereotype.Service;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Transactional
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Transactional
    public Song getSong(Long id) {
        return songRepository.getOne(id);
    }

    @Transactional
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }
    
}
