package com.knapptown.gpmdataexplorer.exceptions;

public class PlaylistNotFoundException extends RuntimeException {

    private static final String  errorMessage = "Playlist '%s' by '%s' not found.";

    public PlaylistNotFoundException(String owner, String title) {
        super(String.format(errorMessage, title, owner));
    }

}
