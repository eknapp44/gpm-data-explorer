package com.knapptown.gpmdataexplorer.exceptions;

public class PlaylistNotFoundException extends RuntimeException {

    private static final String  errorMessage = "Playlist '%d' not found.";

    public PlaylistNotFoundException(Long playlistId) {
        super(String.format(errorMessage, playlistId));
    }

}
