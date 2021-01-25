package com.knapptown.gpmdataexplorer.services;

import java.util.List;

import javax.transaction.Transactional;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.exceptions.PlaylistNotFoundException;
import com.knapptown.gpmdataexplorer.mappers.PlaylistMapper;
import com.knapptown.gpmdataexplorer.mappers.SongMapper;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.repositories.PlaylistRepository;

import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final SongMapper songMapper;

    public PlaylistService(PlaylistRepository playlistRepository,
                           PlaylistMapper playlistMapper,
                           SongMapper songMapper) {
        this.playlistRepository = playlistRepository;
        this.playlistMapper = playlistMapper;
        this.songMapper = songMapper;
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

    @Transactional
    public Playlist addSongsToPlaylist(Playlist playlist, List<Song> songs) {
        return addSongsToPlaylist(playlist.getId(), songs);
    }

    @Transactional
    public Playlist addSongsToPlaylist(Long id, List<Song> songs) {
        PlaylistEntity playlistEntity = playlistRepository.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
        List<SongEntity> songEntities = songMapper.mapSongsToSongEntities(songs);
        playlistEntity.addSongs(songEntities);
        playlistEntity = playlistRepository.save(playlistEntity);
        return playlistMapper.mapPlaylistEntityToPlaylist(playlistEntity);
    }
    
}
