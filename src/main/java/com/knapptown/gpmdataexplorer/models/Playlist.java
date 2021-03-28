package com.knapptown.gpmdataexplorer.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playlist implements Serializable {

    private Long id;

    @NotBlank(message = "Playlist title cannot be empty.")
    @Size(max = 50)
    private String title;

    @NotBlank(message = "User cannot be empty/")
    @Size(max = 25)
    private String owner;

    @Size(max = 100)
    private String description;

    private boolean shared;

    private boolean deleted;

}
