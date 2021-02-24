package com.knapptown.gpmdataexplorer.mappers;

import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.entities.SongId;
import com.knapptown.gpmdataexplorer.models.Song;
import com.knapptown.gpmdataexplorer.models.SongCsvObject;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongMapperTest {

    private final SongMapper songMapper = Mappers.getMapper(SongMapper.class);

    private static final String TEST_TITLE = "Song Title";
    private static final String TEST_ALBUM = "Album Title";
    private static final String TEST_ARTIST = "Artist Name";
    private static final int TEST_DURATION = 122333;
    private static final int TEST_RATING = 3;
    private static final int TEST_PLAY_COUNT = 44;
    private static final boolean TEST_REMOVED = false;
    private static final int TEST_PLAYLIST_INDEX = 4;

    @Test
    public void testMapSongToSongEntity() {
        Song song = createTestSong();
        SongEntity entity = songMapper.mapSongToSongEntity(song);

        assertEquals(TEST_ALBUM, entity.getId().getAlbum());
        assertEquals(TEST_ARTIST, entity.getId().getArtist());
        assertEquals(TEST_TITLE, entity.getId().getTitle());
        assertEquals(TEST_DURATION, entity.getDurationMs());
        assertEquals(TEST_RATING, entity.getRating());
        assertEquals(TEST_PLAY_COUNT, entity.getPlayCount());
        assertEquals(TEST_REMOVED, entity.isRemoved());
        assertEquals(TEST_PLAYLIST_INDEX, entity.getPlaylistIndex());
    }

    @Test
    public void testMapSongEntitiesToSong() {
        SongEntity entity = createTestSongEntity();
        Song song = songMapper.mapSongEntityToSong(entity);

        assertEquals(TEST_ALBUM, song.getAlbum());
        assertEquals(TEST_ARTIST, song.getArtist());
        assertEquals(TEST_TITLE, song.getTitle());
        assertEquals(TEST_DURATION, song.getDurationMs());
        assertEquals(TEST_RATING, song.getRating());
        assertEquals(TEST_PLAY_COUNT, song.getPlayCount());
        assertEquals(TEST_REMOVED, song.isRemoved());
        assertEquals(TEST_PLAYLIST_INDEX, song.getPlaylistIndex());
    }

    @Test
    public void testMapSongsToSongEntities() {
        List<Song> songs = createTestSongList();
        List<SongEntity> entities = songMapper.mapSongsToSongEntities(songs);

        assertEquals(2, entities.size());
    }

    @Test
    public void testMapSongEntitiesToSongs() {
        List<SongEntity> entities = createTestSongEntityList();
        List<Song> songs = songMapper.mapSongEntitiesToSongs(entities);

        assertEquals(2, songs.size());
    }

    @Test
    public void testMapSongCsvObjectToSong() {
        SongCsvObject songCsvObject = createTestSongCsvObject();
        Song song = songMapper.mapSongCsvObjectToSong(songCsvObject);

        assertEquals(TEST_ALBUM, song.getAlbum());
        assertEquals(TEST_ARTIST, song.getArtist());
        assertEquals(TEST_TITLE, song.getTitle());
        assertEquals(TEST_DURATION, song.getDurationMs());
        assertEquals(TEST_RATING, song.getRating());
        assertEquals(TEST_PLAY_COUNT, song.getPlayCount());
        assertEquals(TEST_REMOVED, song.isRemoved());
        assertEquals(TEST_PLAYLIST_INDEX, song.getPlaylistIndex());
    }

    private Song createTestSong() {
        return Song.builder()
                .title(TEST_TITLE)
                .album(TEST_ALBUM)
                .artist(TEST_ARTIST)
                .durationMs(TEST_DURATION)
                .rating(TEST_RATING)
                .playCount(TEST_PLAY_COUNT)
                .removed(TEST_REMOVED)
                .playlistIndex(TEST_PLAYLIST_INDEX)
                .build();
    }

    private SongEntity createTestSongEntity() {
        SongId id  = SongId.builder()
                .title(TEST_TITLE)
                .artist(TEST_ARTIST)
                .album(TEST_ALBUM)
                .build();

        return SongEntity.builder()
                .id(id)
                .durationMs(TEST_DURATION)
                .rating(TEST_RATING)
                .playCount(TEST_PLAY_COUNT)
                .removed(TEST_REMOVED)
                .playlistIndex(TEST_PLAYLIST_INDEX)
                .build();
    }

    private List<Song> createTestSongList() {
        return List.of(createTestSong(), createTestSong());
    }

    private List<SongEntity> createTestSongEntityList() {
        return List.of(createTestSongEntity(), createTestSongEntity());
    }

    private SongCsvObject createTestSongCsvObject() {
        SongCsvObject songCsvObject = new SongCsvObject();
        songCsvObject.setTitle(TEST_TITLE);
        songCsvObject.setAlbum(TEST_ALBUM);
        songCsvObject.setArtist(TEST_ARTIST);
        songCsvObject.setDurationMs(String.valueOf(TEST_DURATION));
        songCsvObject.setPlayCount(String.valueOf(TEST_PLAY_COUNT));
        songCsvObject.setPlaylistIndex(String.valueOf(TEST_PLAYLIST_INDEX));
        songCsvObject.setRating(String.valueOf(TEST_RATING));
        songCsvObject.setRemoved(String.valueOf(TEST_REMOVED));
        return  songCsvObject;
    }

}
