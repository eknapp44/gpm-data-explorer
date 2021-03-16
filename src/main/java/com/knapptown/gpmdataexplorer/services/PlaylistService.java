package com.knapptown.gpmdataexplorer.services;

import java.util.List;

import javax.transaction.Transactional;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.exceptions.PlaylistExistsException;
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
    public Playlist getPlaylistForTitleAndOwner(String title, String owner) {
        PlaylistEntity playlistEntity = playlistRepository.findByTitleAndOwner(title, owner);
        return playlistMapper.mapPlaylistEntityToPlaylist(playlistEntity);
    }

    @Transactional
    public Playlist createPlaylist(Playlist playlist) {
        if (playlistRepository.existsByTitleAndOwner(playlist.getTitle(), playlist.getOwner())) {
            throw new PlaylistExistsException(playlist.getTitle(), playlist.getOwner());
        }
        return savePlaylist(playlist);
    }

    @Transactional
    public Playlist updatePlaylist(Playlist playlist) {
        return savePlaylist(playlist);
    }

    private Playlist savePlaylist(Playlist playlist) {
        PlaylistEntity playlistEntity = playlistMapper.mapPlaylistToPlaylistEntity(playlist);
        return playlistMapper.mapPlaylistEntityToPlaylist(playlistRepository.save(playlistEntity));
    }
    
}
