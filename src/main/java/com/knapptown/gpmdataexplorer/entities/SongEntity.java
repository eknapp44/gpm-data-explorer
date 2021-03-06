package com.knapptown.gpmdataexplorer.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
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
@IdClass(SongEntityId.class)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongEntity implements Serializable {

    @Id
    private @Size(min = 1, max = 150) String title;
    @Id
    private @Size(max = 150) String album;
    @Id
    private @Size(max = 200) String artist;
    private @PositiveOrZero int durationMs;
    private @Max(value = 5) Integer rating;
    private @PositiveOrZero int playCount;
    private boolean removed;

    @OneToMany(mappedBy = "song")
    private List<PlaylistEntryEntity> playlistEntries;

}
