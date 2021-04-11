package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.TestDataCreator;
import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.models.Playlist;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistMapperTest {

    private final PlaylistMapper playlistMapper = Mappers.getMapper(PlaylistMapper.class);

    @Test
    public void testMapPlaylistToPlaylistEntity() {
        Playlist playlist = TestDataCreator.createTestPlaylist();
        PlaylistEntity entity = playlistMapper.mapModelToEntity(playlist);

        assertEquals(TestDataCreator.TEST_PLAYLIST_ID, entity.getId());
        assertEquals(TestDataCreator.TEST_PLAYLIST_TITLE, entity.getTitle());
        assertEquals(TestDataCreator.TEST_OWNER, entity.getOwner());
        assertEquals(TestDataCreator.TEST_DESCRIPTION, entity.getDescription());
        assertEquals(TestDataCreator.TEST_DELETED, entity.isDeleted());
        assertEquals(TestDataCreator.TEST_SHARED, entity.isShared());
    }

    @Test
    public void testMapPlaylistEntityToPlaylist() {
        PlaylistEntity entity = TestDataCreator.createTestPlaylistEntity();
        Playlist playlist = playlistMapper.mapEntityToModel(entity);

        assertEquals(TestDataCreator.TEST_PLAYLIST_ID, playlist.getId());
        assertEquals(TestDataCreator.TEST_PLAYLIST_TITLE, playlist.getTitle());
        assertEquals(TestDataCreator.TEST_OWNER, playlist.getOwner());
        assertEquals(TestDataCreator.TEST_DESCRIPTION, playlist.getDescription());
        assertEquals(TestDataCreator.TEST_DELETED, playlist.isDeleted());
        assertEquals(TestDataCreator.TEST_SHARED, playlist.isShared());
    }

    @Test
    public void testMapPlaylistsToPlaylistEntities() {
        List<Playlist> playlists = TestDataCreator.createTestPlaylistList();
        List<PlaylistEntity> entities = playlistMapper.mapModelsToEntities(playlists);

        assertEquals(2, entities.size());
    }

    @Test
    public void testMapPlaylistEntitiesToPlaylists() {
        List<PlaylistEntity> entities = TestDataCreator.createTestPlaylistEntityList();
        List<Playlist> playlists = playlistMapper.mapEntitiesToModels(entities);

        assertEquals(2, playlists.size());
    }

}
