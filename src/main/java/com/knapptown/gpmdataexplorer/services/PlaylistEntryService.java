package com.knapptown.gpmdataexplorer.services;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntity;
import com.knapptown.gpmdataexplorer.mappers.PlaylistEntryMapper;
import com.knapptown.gpmdataexplorer.models.PlaylistEntry;
import com.knapptown.gpmdataexplorer.repositories.PlaylistEntryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlaylistEntryService {

    private final PlaylistEntryRepository playlistEntryRepository;
    private final PlaylistEntryMapper playlistEntryMapper;

    public PlaylistEntryService(PlaylistEntryRepository playlistEntryRepository,
                                PlaylistEntryMapper playlistEntryMapper) {
        this.playlistEntryRepository = playlistEntryRepository;
        this.playlistEntryMapper = playlistEntryMapper;
    }

    @Transactional
    public List<PlaylistEntry> getPlaylistEntries() {
        return playlistEntryMapper.mapPlaylistEntryEntitiesToPlaylistEntries(playlistEntryRepository.findAll());
    }

    @Transactional
    public PlaylistEntry savePlaylistEntry(PlaylistEntry playlistEntry) {
        PlaylistEntryEntity playlistEntryEntity = playlistEntryMapper.mapPlaylistEntryToPlaylistEntryEntity(playlistEntry);
        return playlistEntryMapper.mapPlaylistEntryEntityToPlaylistEntry(playlistEntryRepository.save(playlistEntryEntity));
    }

    @Transactional
    public List<PlaylistEntry> savePlaylistEntries(List<PlaylistEntry> playlistEntries) {
        List<PlaylistEntryEntity> entities = playlistEntryMapper.mapPlaylistEntriesToPlaylistEntryEntities(playlistEntries);
        return playlistEntryMapper.mapPlaylistEntryEntitiesToPlaylistEntries(playlistEntryRepository.saveAll(entities));
    }

}
