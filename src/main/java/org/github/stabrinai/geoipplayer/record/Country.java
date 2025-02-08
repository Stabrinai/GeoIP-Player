package org.github.stabrinai.geoipplayer.record;

import com.maxmind.db.MaxMindDbConstructor;
import com.maxmind.db.MaxMindDbParameter;

import java.util.Map;

public record Country(String iso_code, long geoname_id, Map<String, String> names) {
    @MaxMindDbConstructor
    public Country(@MaxMindDbParameter(name = "iso_code") String iso_code, @MaxMindDbParameter(name = "geoname_id") long geoname_id, @MaxMindDbParameter(name = "names") Map<String, String> names) {
        this.iso_code = iso_code;
        this.geoname_id = geoname_id;
        this.names = names;
    }
}
