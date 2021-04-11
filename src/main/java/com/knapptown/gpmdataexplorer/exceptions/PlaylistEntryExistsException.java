package com.knapptown.gpmdataexplorer.exceptions;

public class PlaylistEntryExistsException extends RuntimeException {

    private static final String MESSAGE = "Playlist Entry for playlist '%s' with index '%d' already exists.";

    public PlaylistEntryExistsException(String playlist, Integer index) {
        super(String.format(MESSAGE, playlist, index));
    }

}
