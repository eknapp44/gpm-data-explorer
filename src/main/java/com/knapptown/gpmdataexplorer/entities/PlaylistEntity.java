package com.knapptown.gpmdataexplorer.entities;

import java.io.Serializable;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonIgnoreProperties("playlists")
    private List<SongEntity> songs;

    /**
     * Add a Song Entity instance to the Playlist's list of songs.
     * @param songEntity A Song Entity instance.
     */
    public void addSong(SongEntity songEntity) {
        this.songs.add(songEntity);
    }

    /**
     * Add a list of Song Entity instances to the Playlist's list of songs.
     * @param songEntities A List of Song Entity instances.
     */
    public void addSongs(List<SongEntity> songEntities) {
        this.songs.addAll(songEntities);
    }

}
