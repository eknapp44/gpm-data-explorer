package com.knapptown.gpmdataexplorer.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Playlist implements Serializable {

    private static final long serialVersionUID = -4038496640526641680L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private @Size(min = 1, max = 50) String title;
    private @Size(min = 1, max = 25) String owner;
    private @Size(max = 100) String descriptions;
    private boolean shared;
    private boolean deleted;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "playlist_song",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> songs = new HashSet<>();
    
}
