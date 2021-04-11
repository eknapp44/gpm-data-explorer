package com.knapptown.gpmdataexplorer.services;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntity;
import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntityId;
import com.knapptown.gpmdataexplorer.exceptions.PlaylistEntryExistsException;
import com.knapptown.gpmdataexplorer.exceptions.PlaylistEntryNotFoundException;
import com.knapptown.gpmdataexplorer.mappers.PlaylistEntryMapper;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.models.PlaylistEntry;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.repositories.PlaylistEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class PlaylistEntryService {

    private final PlaylistEntryRepository playlistEntryRepository;
    private final PlaylistEntryMapper playlistEntryMapper;
    private final PlaylistService playlistService;
    private final SongService songService;

    public PlaylistEntryService(PlaylistEntryRepository playlistEntryRepository,
                                PlaylistEntryMapper playlistEntryMapper,
                                PlaylistService playlistService,
                                SongService songService) {
        this.playlistEntryRepository = playlistEntryRepository;
        this.playlistEntryMapper = playlistEntryMapper;
        this.playlistService = playlistService;
        this.songService = songService;
    }

    /**
     * GET Related methods.
     */

    @Transactional
    public List<PlaylistEntry> getForPlaylist(@Positive Long id) {
        List<PlaylistEntryEntity> entities = playlistEntryRepository.findByPlaylistId(id);
        return playlistEntryMapper.mapEntitiesToModels(entities);
    }

    @Transactional
    public List<PlaylistEntry> getForPlaylistAndSong(@Positive Long playlistId, @Positive Long songId) {
        List<PlaylistEntryEntity> entities = playlistEntryRepository.findByPlaylistIdAndSongId(playlistId, songId);
        return playlistEntryMapper.mapEntitiesToModels(entities);
    }

    @Transactional
    public List<Integer> getIndexesForPlaylistAndSong(@Positive Long playlistId, @Positive Long songId) {
        List<PlaylistEntryEntity> entities = playlistEntryRepository.findByPlaylistIdAndSongId(playlistId, songId);
        return entities.stream().map(PlaylistEntryEntity::getPlaylistIndex).collect(Collectors.toList());
    }

    @Transactional
    public PlaylistEntry getForPlaylistAndSongAndIndex(@Positive Long playlistId,
                                                       @Positive Long songId,
                                                       @PositiveOrZero Integer playlistIndex) {
        PlaylistEntryEntityId id = getEntityId(playlistId, songId, playlistIndex);
        PlaylistEntryEntity entity = playlistEntryRepository.findById(id)
                .orElseThrow(() -> new PlaylistEntryNotFoundException(id));
        return playlistEntryMapper.mapEntityToModel(entity);
    }

    /**
     * CREATE Related methods.
     */

    @Transactional
    public PlaylistEntry create(@Valid  PlaylistEntry playlistEntry) {
        PlaylistEntryEntity entity = playlistEntryMapper.mapModelToEntity(playlistEntry);
        return playlistEntryMapper.mapEntityToModel(playlistEntryRepository.save(entity));
    }

    @Transactional
    public List<PlaylistEntry> create(@Valid List<PlaylistEntry> playlistEntries) {
        List<PlaylistEntryEntity> entities = playlistEntryMapper.mapModelsToEntities(playlistEntries);
        return playlistEntryMapper.mapEntitiesToModels(playlistEntryRepository.saveAll(entities));
    }

    @Transactional
    public PlaylistEntry create(@Positive Long playlistId, @Positive Long songId) {
        return create(playlistId, songId, getNextPlaylistIndex(playlistId));
    }

    @Transactional
    public PlaylistEntry create(@Valid Playlist playlist, @Valid Song song) {
        return create(playlist, song, getNextPlaylistIndex(playlist));
    }

    @Transactional
    public PlaylistEntry create(@Positive Long playlistId, @Positive Long songId, @PositiveOrZero Integer playlistIndex) {
        Playlist playlist = playlistService.getById(playlistId);
        Song song = songService.getById(songId);
        return create(playlist, song, playlistIndex);
    }

    @Transactional
    public PlaylistEntry create(@Valid Playlist playlist, @Valid Song song, @PositiveOrZero Integer playlistIndex) {
        PlaylistEntry entry = PlaylistEntry.builder()
                .playlist(playlist)
                .song(song)
                .playlistIndex(playlistIndex)
                .build();
        return create(entry);
    }

    /**
     * UPDATE Related methods.
     */

    @Transactional
    public PlaylistEntry update(@Positive Long playlistId,
                                @Positive Long songId,
                                @PositiveOrZero Integer index,
                                @Valid PlaylistEntry newEntry) {
        // Argument validation.
        if (!newEntry.getPlaylist().getId().equals(playlistId)) {
            throw new IllegalArgumentException("Invalid playlist entry update argument. Playlist ID: " +
                    playlistId + " does not match input playlist entry playlist id: " + newEntry.getPlaylist().getId());
        }
        if (!newEntry.getSong().getId().equals(songId)) {
            throw new IllegalArgumentException("Invalid playlist entry update argument. Song ID: " +
                    songId + " does not match input playlist entry song id: " + newEntry.getSong().getId());
        }

        if (playlistEntryRepository.existsPlaylistEntryEntitiesByPlaylistIdAndPlaylistIndex(playlistId, index)) {
            throw new PlaylistEntryExistsException(newEntry.getPlaylist().getTitle(), index);
        }

        // Retrieve the existing playlist entry.
        PlaylistEntryEntityId id = getEntityId(playlistId, songId, index);
        PlaylistEntryEntity entity = playlistEntryRepository.findById(id)
                .orElseThrow(() -> new PlaylistEntryNotFoundException(id));

        // If the index values are different, update the index value and save.
        if (!entity.getPlaylistIndex().equals(newEntry.getPlaylistIndex())) {
            entity.setPlaylistIndex(newEntry.getPlaylistIndex());
            entity = playlistEntryRepository.save(entity);
        }

        // Finally save.
        return playlistEntryMapper.mapEntityToModel(entity);
    }

    /**
     * REMOVE Related methods.
     */

    @Transactional
    public void remove(@Positive Long playlistId, @Positive Long songId, @PositiveOrZero Integer playlistIndex) {
        PlaylistEntryEntityId id = getEntityId(playlistId, songId, playlistIndex);
        PlaylistEntryEntity entity = playlistEntryRepository.findById(id)
                .orElseThrow(() -> new PlaylistEntryNotFoundException(id));
        playlistEntryRepository.delete(entity);
    }

    @Transactional
    public void remove(@Positive Long playlistId, @Positive Long songId) {
        List<PlaylistEntryEntity> entities = playlistEntryRepository.findByPlaylistIdAndSongId(playlistId, songId);
        playlistEntryRepository.deleteAll(entities);
    }

    /**
     * Helper/Utility methods.
     */

    private PlaylistEntryEntityId getEntityId(Long playlistId, Long songId, int playlistIndex) {
        return PlaylistEntryEntityId.builder()
                .playlist(playlistId)
                .song(songId)
                .playlistIndex(playlistIndex)
                .build();
    }

    private Integer getNextPlaylistIndex(Playlist playlist) {
        return getNextPlaylistIndex(playlist.getId());
    }

    private Integer getNextPlaylistIndex(Long playlistId) {
        List<PlaylistEntryEntity> entities = playlistEntryRepository.findByPlaylistId(playlistId);
        Integer currentMax = entities.stream()
                .map(PlaylistEntryEntity::getPlaylistIndex)
                .max(Integer::compare)
                .orElseThrow();
        return currentMax + 1;
    }

}
