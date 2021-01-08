package com.knapptown.gpmdataexplorer.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter @NoArgsConstructor
public class Song implements Serializable {

    private static final long serialVersionUID = -4082590851179640267L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private @Size(min = 1, max = 50) String title;
    private @Size(min = 1, max = 50) String album;
    private @Size(min = 1, max = 50) String artist;
    private int durationMs;
    private @Max(value = 5) Integer rating;
    private int playCount;
    private boolean removed;
    private int playlistIndex;

    @ManyToMany(mappedBy = "songs")
    private Set<Playlist> playlists = new HashSet<>();

}
