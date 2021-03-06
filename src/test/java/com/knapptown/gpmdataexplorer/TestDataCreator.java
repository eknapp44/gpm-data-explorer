package com.knapptown.gpmdataexplorer;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntity;
import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntity;
import com.knapptown.gpmdataexplorer.entities.SongEntity;
import com.knapptown.gpmdataexplorer.models.Playlist;
import com.knapptown.gpmdataexplorer.models.PlaylistEntry;
import com.knapptown.gpmdataexplorer.models.PlaylistEntryCsvObject;
import com.knapptown.gpmdataexplorer.models.Song;

import java.util.List;

public class TestDataCreator {

    public static final String TEST_SONG_TITLE = "Song Title";
    public static final String TEST_ALBUM = "Album Title";
    public static final String TEST_ARTIST = "Artist Name";
    public static final int TEST_DURATION = 122333;
    public static final int TEST_RATING = 3;
    public static final int TEST_PLAY_COUNT = 44;
    public static final boolean TEST_REMOVED = false;
    public static final int TEST_PLAYLIST_INDEX = 4;

    public final static String TEST_PLAYLIST_TITLE = "Test Title";
    public final static String TEST_OWNER = "John Doe";
    public final static String TEST_DESCRIPTION = "Playlist Description";
    public final static boolean TEST_DELETED = false;
    public final static boolean TEST_SHARED = true;

    /**
     * Playlist Entry Object Data.
     */

    public static PlaylistEntry createTestPlaylistEntry() {
        return PlaylistEntry.builder()
                .playlist(createTestPlaylist())
                .song(createTestSong())
                .playlistIndex(TEST_PLAYLIST_INDEX)
                .build();
    }

    public static PlaylistEntryEntity createTestPlaylistEntryEntity() {
        return PlaylistEntryEntity.builder()
                .playlist(createTestPlaylistEntity())
                .song(createTestSongEntity())
                .playlistIndex(TEST_PLAYLIST_INDEX)
                .build();
    }

    public static List<PlaylistEntry> createTestPlaylistEntries() {
        return List.of(createTestPlaylistEntry(), createTestPlaylistEntry());
    }

    public static List<PlaylistEntryEntity> createTestPlaylistEntryEntities() {
        return List.of(createTestPlaylistEntryEntity(), createTestPlaylistEntryEntity());
    }

    /**
     * Song Object Data.
     */

    public static Song createTestSong() {
        return Song.builder()
                .title(TEST_SONG_TITLE)
                .album(TEST_ALBUM)
                .artist(TEST_ARTIST)
                .durationMs(TEST_DURATION)
                .rating(TEST_RATING)
                .playCount(TEST_PLAY_COUNT)
                .removed(TEST_REMOVED)
                .build();
    }

    public static SongEntity createTestSongEntity() {
        return SongEntity.builder()
                .title(TEST_SONG_TITLE)
                .artist(TEST_ARTIST)
                .album(TEST_ALBUM)
                .durationMs(TEST_DURATION)
                .rating(TEST_RATING)
                .playCount(TEST_PLAY_COUNT)
                .removed(TEST_REMOVED)
                .build();
    }

    public static List<Song> createTestSongList() {
        return List.of(createTestSong(), createTestSong());
    }

    public static List<SongEntity> createTestSongEntityList() {
        return List.of(createTestSongEntity(), createTestSongEntity());
    }

    /**
     * Playlist Object Data.
     */

    public static Playlist createTestPlaylist() {
        return Playlist.builder()
                .title(TEST_PLAYLIST_TITLE)
                .owner(TEST_OWNER)
                .description(TEST_DESCRIPTION)
                .deleted(TEST_DELETED)
                .shared(TEST_SHARED)
                .build();
    }

    public static PlaylistEntity createTestPlaylistEntity() {
        return PlaylistEntity.builder()
                .title(TEST_PLAYLIST_TITLE)
                .owner(TEST_OWNER)
                .description(TEST_DESCRIPTION)
                .deleted(TEST_DELETED)
                .shared(TEST_SHARED)
                .build();
    }

    public static List<Playlist> createTestPlaylistList() {
        return List.of(createTestPlaylist(), createTestPlaylist());
    }

    public static List<PlaylistEntity> createTestPlaylistEntityList() {
        return List.of(createTestPlaylistEntity(), createTestPlaylistEntity());
    }

    /**
     * Playlist Entry CSV Object Data.
     */

    public static PlaylistEntryCsvObject createTestPlaylistEntryCsvObject() {
        PlaylistEntryCsvObject playlistEntryCsvObject = new PlaylistEntryCsvObject();
        playlistEntryCsvObject.setTitle(TEST_SONG_TITLE);
        playlistEntryCsvObject.setAlbum(TEST_ALBUM);
        playlistEntryCsvObject.setArtist(TEST_ARTIST);
        playlistEntryCsvObject.setDurationMs(String.valueOf(TEST_DURATION));
        playlistEntryCsvObject.setPlayCount(String.valueOf(TEST_PLAY_COUNT));
        playlistEntryCsvObject.setPlaylistIndex(String.valueOf(TEST_PLAYLIST_INDEX));
        playlistEntryCsvObject.setRating(String.valueOf(TEST_RATING));
        playlistEntryCsvObject.setRemoved(String.valueOf(TEST_REMOVED));
        return playlistEntryCsvObject;
    }

}
