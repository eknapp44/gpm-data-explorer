package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.TestDataCreator;
import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.models.Song;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongMapperTest {

    private final SongMapper songMapper = Mappers.getMapper(SongMapper.class);

    @Test
    public void testMapSongToSongEntity() {
        Song song = TestDataCreator.createTestSong();
        SongEntity entity = songMapper.mapSongToSongEntity(song);

        assertEquals(TestDataCreator.TEST_ALBUM, entity.getAlbum());
        assertEquals(TestDataCreator.TEST_ARTIST, entity.getArtist());
        assertEquals(TestDataCreator.TEST_SONG_TITLE, entity.getTitle());
        assertEquals(TestDataCreator.TEST_DURATION, entity.getDurationMs());
        assertEquals(TestDataCreator.TEST_RATING, entity.getRating());
        assertEquals(TestDataCreator.TEST_PLAY_COUNT, entity.getPlayCount());
        assertEquals(TestDataCreator.TEST_REMOVED, entity.isRemoved());
    }

    @Test
    public void testMapSongEntitiesToSong() {
        SongEntity entity = TestDataCreator.createTestSongEntity();
        Song song = songMapper.mapSongEntityToSong(entity);

        assertEquals(TestDataCreator.TEST_ALBUM, song.getAlbum());
        assertEquals(TestDataCreator.TEST_ARTIST, song.getArtist());
        assertEquals(TestDataCreator.TEST_SONG_TITLE, song.getTitle());
        assertEquals(TestDataCreator.TEST_DURATION, song.getDurationMs());
        assertEquals(TestDataCreator.TEST_RATING, song.getRating());
        assertEquals(TestDataCreator.TEST_PLAY_COUNT, song.getPlayCount());
        assertEquals(TestDataCreator.TEST_REMOVED, song.isRemoved());
    }

    @Test
    public void testMapSongsToSongEntities() {
        List<Song> songs = TestDataCreator.createTestSongList();
        List<SongEntity> entities = songMapper.mapSongsToSongEntities(songs);

        assertEquals(2, entities.size());
    }

    @Test
    public void testMapSongEntitiesToSongs() {
        List<SongEntity> entities = TestDataCreator.createTestSongEntityList();
        List<Song> songs = songMapper.mapSongEntitiesToSongs(entities);

        assertEquals(2, songs.size());
    }

}
