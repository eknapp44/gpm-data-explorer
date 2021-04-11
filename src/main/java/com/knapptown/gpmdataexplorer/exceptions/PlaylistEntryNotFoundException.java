package com.knapptown.gpmdataexplorer.exceptions;

import com.knapptown.gpmdataexplorer.entities.PlaylistEntryEntityId;
import com.knapptown.gpmdataexplorer.models.PlaylistEntry;

public class PlaylistEntryNotFoundException extends RuntimeException {

    private static final String  errorMessage = "Playlist Entry for playlist '%s', song: '%s', at index: '%d' not found.";

    public PlaylistEntryNotFoundException(PlaylistEntry playlistEntry) {
        super(String.format(errorMessage, playlistEntry.getPlaylist().getTitle(),
                playlistEntry.getSong().getTitle(), playlistEntry.getPlaylistIndex()));
    }

    public PlaylistEntryNotFoundException(PlaylistEntryEntityId playlistEntryId) {
        super(String.format(errorMessage, playlistEntryId.getPlaylist(),
                playlistEntryId.getSong(), playlistEntryId.getPlaylistIndex()));
    }

}
