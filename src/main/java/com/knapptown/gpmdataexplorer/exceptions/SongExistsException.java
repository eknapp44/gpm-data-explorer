package com.knapptown.gpmdataexplorer.exceptions;

public class SongExistsException extends RuntimeException {

    private static final String MESSAGE = "Song '%s' by '%s' on '%s' already exists.";

    public SongExistsException(String title, String artist, String album) {
        super(String.format(MESSAGE, title, artist, album));
    }

}
