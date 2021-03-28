package com.knapptown.gpmdataexplorer.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

/**
 * A Playlist entity. This is a representation of a playlist object that contains
 * a list of songs.
 */
@Entity(name = "playlist")
@Getter @Setter @Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistEntity implements Serializable {

    private static final long serialVersionUID = -4038496640526641680L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Playlist title cannot be empty.")
    @Size(max = 50)
    private String title;

    @NotBlank(message = "User cannot be empty/")
    @Size(max = 25)
    private String owner;

    @Size(max = 100)
    private String description;

    private boolean shared ;

    private boolean deleted;

    @OneToMany(mappedBy = "playlist")
    private List<PlaylistEntryEntity> playlistEntries;

}
