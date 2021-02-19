package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.models.Playlist;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistMapperTest {

    private final PlaylistMapper playlistMapper = Mappers.getMapper(PlaylistMapper.class);

    private final static Long TEST_ID = 444L;
    private final static String TEST_TITLE = "Test Title";
    private final static String TEST_OWNER = "John Doe";
    private final static String TEST_DESCRIPTION = "Playlist Description";
    private final static boolean TEST_DELETED = false;
    private final static boolean TEST_SHARED = true;

    @Test
    public void testMapPlaylistToPlaylistEntity() {
        Playlist playlist = createTestPlaylist();
        PlaylistEntity entity = playlistMapper.mapPlaylistToPlaylistEntity(playlist);

        assertEquals(TEST_ID, entity.getId());
        assertEquals(TEST_TITLE, entity.getTitle());
        assertEquals(TEST_OWNER, entity.getOwner());
        assertEquals(TEST_DESCRIPTION, entity.getDescription());
        assertEquals(TEST_DELETED, entity.isDeleted());
        assertEquals(TEST_SHARED, entity.isShared());
    }

    @Test
    public void testMapPlaylistEntityToPlaylist() {
        PlaylistEntity entity = createTestPlaylistEntity();
        Playlist playlist = playlistMapper.mapPlaylistEntityToPlaylist(entity);

        assertEquals(TEST_ID, playlist.getId());
        assertEquals(TEST_TITLE, playlist.getTitle());
        assertEquals(TEST_OWNER, playlist.getOwner());
        assertEquals(TEST_DESCRIPTION, playlist.getDescription());
        assertEquals(TEST_DELETED, playlist.isDeleted());
        assertEquals(TEST_SHARED, playlist.isShared());
    }

    @Test
    public void testMapPlaylistsToPlaylistEntities() {
        List<Playlist> playlists = createTestPlaylistList();
        List<PlaylistEntity> entities = playlistMapper.mapPlaylistsToPlaylistEntities(playlists);

        assertEquals(2, entities.size());
    }

    @Test
    public void testMapPlaylistEntitiesToPlaylists() {
        List<PlaylistEntity> entities = createTestPlaylistEntityList();
        List<Playlist> playlists = playlistMapper.mapPlaylistEntitiesToPlaylists(entities);

        assertEquals(2, playlists.size());
    }

    private Playlist createTestPlaylist() {
        return Playlist.builder()
                .id(TEST_ID)
                .title(TEST_TITLE)
                .owner(TEST_OWNER)
                .description(TEST_DESCRIPTION)
                .deleted(TEST_DELETED)
                .shared(TEST_SHARED)
                .build();
    }

    private PlaylistEntity createTestPlaylistEntity() {
        return PlaylistEntity.builder()
                .id(TEST_ID)
                .title(TEST_TITLE)
                .owner(TEST_OWNER)
                .description(TEST_DESCRIPTION)
                .deleted(TEST_DELETED)
                .shared(TEST_SHARED)
                .build();
    }

    private List<Playlist> createTestPlaylistList() {
        return List.of(createTestPlaylist(), createTestPlaylist());
    }

    private List<PlaylistEntity> createTestPlaylistEntityList() {
        return List.of(createTestPlaylistEntity(), createTestPlaylistEntity());
    }
}
