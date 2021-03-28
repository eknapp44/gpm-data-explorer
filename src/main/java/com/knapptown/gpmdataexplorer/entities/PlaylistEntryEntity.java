package com.knapptown.gpmdataexplorer.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity(name = "playlist_entry")
@IdClass(PlaylistEntryEntityId.class)
@Getter @Setter @Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistEntryEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private SongEntity song;

    @Id
    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private PlaylistEntity playlist;

    @Id
    @PositiveOrZero
    private Integer playlistIndex;

}
