package com.knapptown.gpmdataexplorer.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playlist {

    private String title;
    private String owner;
    private String description;
    private boolean shared;
    private boolean deleted;

}
