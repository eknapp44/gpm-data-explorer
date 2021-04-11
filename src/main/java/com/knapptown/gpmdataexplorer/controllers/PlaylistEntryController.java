package com.knapptown.gpmdataexplorer.controllers;

import com.knapptown.gpmdataexplorer.models.PlaylistEntry;
import com.knapptown.gpmdataexplorer.services.PlaylistEntryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("api/playlists")
public class PlaylistEntryController {

    private final PlaylistEntryService playlistEntryService;

    /**
     * Instantiate a Playlist Entry Controller.
     * @param playlistEntryService A {@link PlaylistEntryService} instance.
     */
    public PlaylistEntryController(PlaylistEntryService playlistEntryService) {
        this.playlistEntryService = playlistEntryService;
    }

    /**
     * Get all of the songs in a playlist.
     * @param playlistId A playlist ID.
     * @return A list of {@link PlaylistEntry} instances.
     */
    @GetMapping("/{playlistId}/songs")
    public @ResponseBody List<PlaylistEntry> getPlaylistSongs(@PathVariable @Positive Long playlistId) {
        return playlistEntryService.getForPlaylist(playlistId);
    }

    /**
     * Get a playlist entry information for a song.
     * @param playlistId A playlist id.
     * @param songId A song id.
     * @return A list of {@link PlaylistEntry} instances for the given song in a playlist.
     */
    @GetMapping("/{playlistId}/songs/{songId}")
    public @ResponseBody List<PlaylistEntry> getPlaylistSongs(@PathVariable @Positive Long playlistId,
                                                              @PathVariable @Positive Long songId) {
        return playlistEntryService.getForPlaylistAndSong(playlistId, songId);
    }

    /**
     * Get the song indexes of a song in a playlist.
     * @param playlistId A playlist id.
     * @param songId A song id.
     * @return A list of integers representing the indexes of the song in the playlist.
     */
    @GetMapping("/{playlistId}/songs/{songId}/index")
    public @ResponseBody List<Integer> getSongIndexInPlaylist(@PathVariable @Positive Long playlistId,
                                                              @PathVariable @Positive Long songId) {
        return playlistEntryService.getIndexesForPlaylistAndSong(playlistId, songId);
    }

    /**
     * Get a playlist entry information for a song at a specified index..
     * @param playlistId A playlist id.
     * @param songId A song id.
     * @param index A index number.
     * @return A {@link PlaylistEntry} instance for the given song in a playlist at an index.
     */
    @GetMapping("/{playlistId}/songs/{songId}/index/{index}")
    public @ResponseBody PlaylistEntry getPlaylistSongAtIndex(@PathVariable @Positive Long playlistId,
                                                              @PathVariable @Positive Long songId,
                                                              @PathVariable @PositiveOrZero Integer index) {
        return playlistEntryService.getForPlaylistAndSongAndIndex(playlistId, songId, index);
    }

    /**
     * Add a specified song to a playlist.
     * @param playlistId A playlist id.
     * @param songId A song id.
     * @return A {@link PlaylistEntry} instance.
     */
    @PostMapping("/{playlistId}/songs/{songId}")
    public @ResponseBody PlaylistEntry addSongToPlaylist(@PathVariable @Positive Long playlistId,
                                                         @PathVariable @Positive Long songId) {
        return playlistEntryService.create(playlistId, songId);
    }

    /**
     * Add a specified song to a playlist at a specific index..
     * @param playlistId A playlist id.
     * @param songId A song id.
     * @param index A index number.
     * @return A {@link PlaylistEntry} instance.
     */
    @PostMapping("/{playlistId}/songs/{songId}/index/{index}")
    public @ResponseBody PlaylistEntry addSongToPlaylistAtIndex(@PathVariable @Positive Long playlistId,
                                                                @PathVariable @Positive Long songId,
                                                                @PathVariable @PositiveOrZero Integer index) {
        return playlistEntryService.create(playlistId, songId, index);
    }

    /**
     * Update a song's index in a playlist.
     * @param playlistId A playlist id.
     * @param songId A song id.
     * @param index A index number.
     * @param entry A {@link PlaylistEntry} instance.
     * @return A {@link PlaylistEntry} instance with the updated index.
     */
    @PutMapping("/{playlistId}/songs/{songId}/index/{index}")
    public @ResponseBody PlaylistEntry updatePlaylistSongIndex(@PathVariable @Positive Long playlistId,
                                                               @PathVariable @Positive Long songId,
                                                               @PathVariable @PositiveOrZero Integer index,
                                                               @RequestBody @Valid PlaylistEntry entry) {

        return playlistEntryService.update(playlistId, songId, index, entry);
    }

    /**
     * Remove all instances of a song from a playlist.
     * @param playlistId A playlist id.
     * @param songId A song id.
     */
    @DeleteMapping("/{playlistId}/songs/{songId}")
    public void removeSongFromPlaylist(@PathVariable @Positive Long playlistId,
                                       @PathVariable @Positive Long songId) {
        playlistEntryService.remove(playlistId, songId);
    }

    /**
     * Remove a instance of a song from a playlist for a given index.
     * @param playlistId A playlist id.
     * @param songId A song id.
     */
    @DeleteMapping("/{playlistId}/songs/{songId}/index/{index}")
    public void removeSongFromPlaylistAtIndex(@PathVariable @Positive Long playlistId,
                                              @PathVariable @Positive Long songId,
                                              @PathVariable @PositiveOrZero Integer index) {
        playlistEntryService.remove(playlistId, songId, index);
    }

}
