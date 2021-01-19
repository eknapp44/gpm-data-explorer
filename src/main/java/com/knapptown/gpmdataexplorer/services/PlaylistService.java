package com.knapptown.gpmdataexplorer.services;

import java.util.List;

import javax.transaction.Transactional;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.exceptions.PlaylistNotFoundException;
import com.knapptown.gpmdataexplorer.mappers.PlaylistMapper;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.repositories.PlaylistRepository;

import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;

    public PlaylistService(PlaylistRepository playlistRepository,
                           PlaylistMapper playlistMapper) {
        this.playlistRepository = playlistRepository;
        this.playlistMapper = playlistMapper;
    }

    @Transactional
    public List<Playlist> getAllPlaylists() {
        return playlistMapper.mapPlaylistEntitiesToPlaylists(playlistRepository.findAll());
    }

    @Transactional
    public Playlist getPlaylist(Long id) {
        PlaylistEntity playlistEntity = playlistRepository.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
        return playlistMapper.mapPlaylistEntityToPlaylist(playlistEntity);
    }

    @Transactional
    public Playlist savePlaylist(Playlist playlist) {
        PlaylistEntity playlistEntity = playlistMapper.mapPlaylistToPlaylistEntity(playlist);
        return playlistMapper.mapPlaylistEntityToPlaylist(playlistRepository.save(playlistEntity));
    }
    
}
