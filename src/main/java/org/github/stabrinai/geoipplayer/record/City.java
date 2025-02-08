package org.github.stabrinai.geoipplayer.record;

import com.maxmind.db.MaxMindDbConstructor;
import com.maxmind.db.MaxMindDbParameter;

import java.util.Map;

public record City(long geoname_id, Map<String, String> names) {
    @MaxMindDbConstructor
    public City(@MaxMindDbParameter(name = "geoname_id") long geoname_id, @MaxMindDbParameter(name = "names") Map<String, String> names) {
        this.geoname_id = geoname_id;
        this.names = names;
    }
}
