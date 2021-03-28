package com.knapptown.gpmdataexplorer.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Song implements Serializable {

    private Long id;

    @NotBlank(message = "Title cannot be empty.")
    @Size(max = 150)
    private String title;

    @NotBlank(message = "Album cannot be blank.")
    @Size(max = 150)
    private String album;

    @NotBlank(message = "Artist cannot be empty.")
    @Size(max = 200)
    private String artist;

    @PositiveOrZero
    private Integer durationMs;

    @Min(0)
    @Max(value = 5)
    private Integer rating;

    @PositiveOrZero
    private Integer playCount;

    private boolean removed;

}