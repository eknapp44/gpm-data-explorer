package com.knapptown.gpmdataexplorer.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "song")
@Getter @Setter @Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongEntity implements Serializable {

    private static final long serialVersionUID = -4082590851179640267L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private  Integer rating;

    @PositiveOrZero
    private Integer playCount;

    private boolean removed;

    @OneToMany(mappedBy = "song")
    private List<PlaylistEntryEntity> playlistEntries;

}
