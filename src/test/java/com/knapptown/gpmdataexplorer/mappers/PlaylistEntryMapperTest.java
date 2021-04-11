package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.TestApplicationConfiguration;
import com.knapptown.gpmdataexplorer.TestDataCreator;
import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntity;
import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.models.PlaylistEntry;
import com.knapptown.gpmdataexplorer.models.Song;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TestApplicationConfiguration.class)
public class PlaylistEntryMapperTest {

    @Autowired
    private PlaylistEntryMapper songMapper;

    @Test
    public void testMapPlaylistEntryToPlaylistEntryEntity() {
        PlaylistEntry playlistEntry = TestDataCreator.createTestPlaylistEntry();
        PlaylistEntryEntity entity = songMapper.mapModelToEntity(playlistEntry);

        assertEquals(TestDataCreator.TEST_PLAYLIST_INDEX, entity.getPlaylistIndex());

        SongEntity song  = entity.getSong();
        assertEquals(TestDataCreator.TEST_SONG_ID, song.getId());
        assertEquals(TestDataCreator.TEST_ALBUM, song.getAlbum());
        assertEquals(TestDataCreator.TEST_ARTIST, song.getArtist());
        assertEquals(TestDataCreator.TEST_SONG_TITLE, song.getTitle());
        assertEquals(TestDataCreator.TEST_DURATION, song.getDurationMs());
        assertEquals(TestDataCreator.TEST_RATING, song.getRating());
        assertEquals(TestDataCreator.TEST_PLAY_COUNT, song.getPlayCount());
        assertEquals(TestDataCreator.TEST_REMOVED, song.isRemoved());

        PlaylistEntity playlist = entity.getPlaylist();
        assertEquals(TestDataCreator.TEST_PLAYLIST_ID, playlist.getId());
        assertEquals(TestDataCreator.TEST_PLAYLIST_TITLE, playlist.getTitle());
        assertEquals(TestDataCreator.TEST_OWNER, playlist.getOwner());
        assertEquals(TestDataCreator.TEST_DESCRIPTION, playlist.getDescription());
        assertEquals(TestDataCreator.TEST_DELETED, playlist.isDeleted());
        assertEquals(TestDataCreator.TEST_SHARED, playlist.isShared());
    }

    @Test
    public void testMapPlaylistEntryEntityToPlaylistEntry() {
        PlaylistEntryEntity entity = TestDataCreator.createTestPlaylistEntryEntity();
        PlaylistEntry playlistEntry = songMapper.mapEntityToModel(entity);

        assertEquals(TestDataCreator.TEST_PLAYLIST_INDEX, playlistEntry.getPlaylistIndex());

        Song song = playlistEntry.getSong();
        assertEquals(TestDataCreator.TEST_SONG_ID, song.getId());
        assertEquals(TestDataCreator.TEST_ALBUM, song.getAlbum());
        assertEquals(TestDataCreator.TEST_ARTIST, song.getArtist());
        assertEquals(TestDataCreator.TEST_SONG_TITLE, song.getTitle());
        assertEquals(TestDataCreator.TEST_DURATION, song.getDurationMs());
        assertEquals(TestDataCreator.TEST_RATING, song.getRating());
        assertEquals(TestDataCreator.TEST_PLAY_COUNT, song.getPlayCount());
        assertEquals(TestDataCreator.TEST_REMOVED, song.isRemoved());

        Playlist playlist = playlistEntry.getPlaylist();
        assertEquals(TestDataCreator.TEST_PLAYLIST_ID, playlist.getId());
        assertEquals(TestDataCreator.TEST_PLAYLIST_TITLE, playlist.getTitle());
        assertEquals(TestDataCreator.TEST_OWNER, playlist.getOwner());
        assertEquals(TestDataCreator.TEST_DESCRIPTION, playlist.getDescription());
        assertEquals(TestDataCreator.TEST_DELETED, playlist.isDeleted());
        assertEquals(TestDataCreator.TEST_SHARED, playlist.isShared());
    }

    @Test
    public void testMapPlaylistEntriesToPlaylistEntryEntities() {
        List<PlaylistEntry> playlistEntries = TestDataCreator.createTestPlaylistEntries();
        List<PlaylistEntryEntity> entities = songMapper.mapModelsToEntities(playlistEntries);

        assertEquals(2, entities.size());
    }

    @Test
    public void testMapPlaylistEntryEntitiesToPlaylistEntries() {
        List<PlaylistEntryEntity> entities = TestDataCreator.createTestPlaylistEntryEntities();
        List<PlaylistEntry> playlistEntries = songMapper.mapEntitiesToModels(entities);

        assertEquals(2, playlistEntries.size());
    }

}
