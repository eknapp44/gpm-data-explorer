package com.knapptown.gpmdataexplorer.models;

import lombok.Data;

@Data
public class Playlist {

    private Long id;
    private String title;
    private String owner;
    private String description;
    private boolean shared;
    private boolean deleted;

}
