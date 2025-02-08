package org.github.stabrinai.geoipplayer.record;

import com.maxmind.db.MaxMindDbConstructor;
import com.maxmind.db.MaxMindDbParameter;

import java.util.Map;

public record Continent(String code, long geoname_id, Map<String, String> names) {
    @MaxMindDbConstructor
    public Continent(@MaxMindDbParameter(name = "code") String code, @MaxMindDbParameter(name = "geoname_id") long geoname_id, @MaxMindDbParameter(name = "names") Map<String, String> names) {
        this.code = code;
        this.geoname_id = geoname_id;
        this.names = names;
    }
}
