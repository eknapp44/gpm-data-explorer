package com.knapptown.gpmdataexplorer.exceptions;

import com.knapptown.gpmdataexplorer.entities.SongId;

public class SongNotFoundException extends RuntimeException {

    private static final String errorMessage = "Song '%d'  by '%d' on album '%d' not found";

    public SongNotFoundException(SongId id) {
        super(String.format(errorMessage, id.getTitle(), id.getArtist(), id.getAlbum()));
    }
}
