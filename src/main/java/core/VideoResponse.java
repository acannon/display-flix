package core;

import java.util.List;

public class VideoResponse {
    int id;
    List<PossibleVideo> results;
}

class PossibleVideo {
    String key;
    String name;
}
