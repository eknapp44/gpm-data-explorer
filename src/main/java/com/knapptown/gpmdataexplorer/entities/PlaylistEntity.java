package com.knapptown.gpmdataexplorer.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

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
@IdClass(PlaylistEntityId.class)
@Getter @Setter @Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistEntity implements Serializable {

    @Id
    private @Size(min = 1, max = 50) String title;
    @Id
    private @Size(min = 1, max = 25) String owner;
    private @Size(max = 100) String description;
    private boolean shared;
    private boolean deleted;

    @OneToMany(mappedBy = "playlist")
    private List<PlaylistEntryEntity> playlistEntries;

}
