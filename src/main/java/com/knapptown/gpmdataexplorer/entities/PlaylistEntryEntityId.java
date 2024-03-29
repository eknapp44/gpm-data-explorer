package com.knapptown.gpmdataexplorer.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Getter @Setter @Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistEntryEntityId implements Serializable {

    private Long song;

    private Long playlist;

    @PositiveOrZero
    private Integer playlistIndex;
}
