package com.knapptown.gpmdataexplorer.exceptions;

public class SongNotFoundException extends RuntimeException {

    private static final String errorMessage = "Song '%d' not found";

    public SongNotFoundException(Long id) {
        super(String.format(errorMessage, id));
    }
}
