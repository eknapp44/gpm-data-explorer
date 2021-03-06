package com.knapptown.gpmdataexplorer.exceptions;

import com.knapptown.gpmdataexplorer.entities.SongEntityId;

public class SongNotFoundException extends RuntimeException {

    private static final String errorMessage = "Song '%s' by '%s' on '%s' not found";

    public SongNotFoundException(String title, String artist, String album) {
        super(String.format(errorMessage, title, artist, album));
    }

    public SongNotFoundException(SongEntityId id) {
        super(String.format(errorMessage, id.getTitle(), id.getArtist(), id.getArtist()));
    }
}
