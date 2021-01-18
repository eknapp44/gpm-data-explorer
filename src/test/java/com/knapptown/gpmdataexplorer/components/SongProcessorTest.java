package com.knapptown.gpmdataexplorer.components;

import com.knapptown.gpmdataexplorer.models.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SongProcessorTest {

    @Mock
    private DataCsvReader dataCsvReader;

    @Mock
    private Song song;

    @Mock
    private Path path;

    private SongProcessor songProcessor;

    private static final Path TRACK_PATH = Paths.get(new File(".", "test-data\\Tracks").getAbsolutePath());

    @BeforeEach
    public void init() throws IOException {
        MockitoAnnotations.openMocks(this);
        Mockito.when(dataCsvReader.readSongFromCsv(Mockito.any())).thenReturn(song);
        songProcessor = new SongProcessor(dataCsvReader);
    }

    @Test
    public void testSongProcessor() {
        songProcessor.processTracksDirectory(TRACK_PATH);
    }
}
