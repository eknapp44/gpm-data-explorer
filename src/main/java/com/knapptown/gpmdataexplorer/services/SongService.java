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
public class SongService implements CrudService<Song> {

    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository,
                       SongMapper songMapper) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    @Override
    @Transactional
    public List<Song> getAll() {
        return songMapper.mapEntitiesToModels(songRepository.findAll());
    }

    @Override
    @Transactional
    public Song getById(@Positive Long id) {
        SongEntity songEntity = songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException(id));
        return songMapper.mapEntityToModel(songEntity);
    }

    @Transactional
    public Song getSongByTitleAndArtistAndAlbum(@NotBlank String title,
                                                @NotBlank String artist,
                                                @NotBlank String album) {
        SongEntity songEntity = songRepository.findByTitleAndAlbumAndArtist(title, album, artist);
        return songMapper.mapEntityToModel(songEntity);
    }

    @Transactional
    public boolean existsForTitleAndArtistAndAlbum(@NotBlank String title,
                                                   @NotBlank String artist,
                                                   @NotBlank String album) {
        return songRepository.existsByTitleAndAlbumAndArtist(title, artist, album);
    }

    @Override
    @Transactional
    public Song create(@Valid Song song) {
        if (existsForTitleAndArtistAndAlbum(song.getTitle(), song.getAlbum(), song.getArtist())) {
            throw new SongExistsException(song.getTitle(), song.getArtist(), song.getAlbum());
        }
        return saveSong(song);
    }

    @Override
    @Transactional
    public Song update(@Positive Long id, @Valid Song song) {
        if (existsForTitleAndArtistAndAlbum(song.getTitle(), song.getAlbum(), song.getArtist())) {
            throw new SongExistsException(song.getTitle(), song.getArtist(), song.getAlbum());
        }

        Song original = getById(id);
        song.setId(original.getId());

        return saveSong(song);
    }

    @Override
    @Transactional
    public void delete(@Positive Long id) {
        songRepository.deleteById(id);
    }

    private Song saveSong(@Valid Song song) {
        SongEntity songEntity = songMapper.mapModelToEntity(song);
        return songMapper.mapEntityToModel(songRepository.save(songEntity));
    }
    
}
