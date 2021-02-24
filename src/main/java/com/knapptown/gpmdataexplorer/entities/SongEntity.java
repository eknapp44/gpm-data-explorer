package com.knapptown.gpmdataexplorer.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @EmbeddedId
    private SongId id;
    private int durationMs;
    private @Max(value = 5) Integer rating;
    private int playCount;
    private boolean removed;
    private int playlistIndex;

    @ManyToMany(mappedBy = "songs")
    @JsonIgnoreProperties("songs")
    private List<PlaylistEntity> playlists;

}
