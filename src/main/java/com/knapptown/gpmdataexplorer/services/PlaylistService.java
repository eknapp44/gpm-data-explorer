package com.knapptown.gpmdataexplorer.services;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.exceptions.PlaylistExistsException;
import com.knapptown.gpmdataexplorer.exceptions.PlaylistNotFoundException;
import com.knapptown.gpmdataexplorer.mappers.PlaylistMapper;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.repositories.PlaylistRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PlaylistService implements CrudService<Playlist> {

    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;

    public PlaylistService(PlaylistRepository playlistRepository,
                           PlaylistMapper playlistMapper) {
        this.playlistRepository = playlistRepository;
        this.playlistMapper = playlistMapper;
    }

    @Override
    @Transactional
    public List<Playlist> getAll() {
        return playlistMapper.mapEntitiesToModels(playlistRepository.findAll());
    }

    @Override
    @Transactional
    public Playlist getById(@Positive Long id) {
        PlaylistEntity playlistEntity = playlistRepository.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
        return playlistMapper.mapEntityToModel(playlistEntity);
    }

    @Transactional
    public Playlist getByTitleAndOwner(@NotBlank String title,
                                       @NotBlank String owner) {
        PlaylistEntity playlistEntity = playlistRepository.findByTitleAndOwner(title, owner);
        return playlistMapper.mapEntityToModel(playlistEntity);
    }

    @Transactional
    public boolean existsForTitleAndOwner(@NotBlank String title,
                                          @NotBlank String owner) {
        return playlistRepository.existsByTitleAndOwner(title, owner);
    }

    @Override
    @Transactional
    public Playlist create(@Valid Playlist playlist) {
        if (existsForTitleAndOwner(playlist.getTitle(), playlist.getOwner())) {
            throw new PlaylistExistsException(playlist.getTitle(), playlist.getOwner());
        }
        return savePlaylist(playlist);
    }

    @Override
    @Transactional
    public Playlist update(@Positive Long id, @Valid Playlist playlist) {
        if (existsForTitleAndOwner(playlist.getTitle(), playlist.getOwner())) {
            throw new PlaylistExistsException(playlist.getTitle(), playlist.getOwner());
        }

        Playlist original = getById(id);
        playlist.setId(original.getId());

        return savePlaylist(playlist);
    }

    @Override
    @Transactional
    public void delete(@Positive Long id) {
        playlistRepository.deleteById(id);
    }

    private Playlist savePlaylist(@Valid Playlist playlist) {
        PlaylistEntity playlistEntity = playlistMapper.mapModelToEntity(playlist);
        return playlistMapper.mapEntityToModel(playlistRepository.save(playlistEntity));
    }
    
}
