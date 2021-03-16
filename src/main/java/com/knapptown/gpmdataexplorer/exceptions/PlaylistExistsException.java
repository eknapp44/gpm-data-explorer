package com.knapptown.gpmdataexplorer.exceptions;

public class PlaylistExistsException extends RuntimeException {

    private static final String MESSAGE = "Playlist '%s' owned by '%s' already exists.";

    public PlaylistExistsException(String title, String owner) {
        super(String.format(MESSAGE, title, owner));
    }

}
