package org.github.stabrinai.geoIPPlayer.record;

import com.maxmind.db.MaxMindDbConstructor;
import com.maxmind.db.MaxMindDbParameter;

public record Location(int accuracy_radius, String time_zone, double latitude, double longitude) {
    @MaxMindDbConstructor
    public Location(@MaxMindDbParameter(name = "accuracy_radius") int accuracy_radius, @MaxMindDbParameter(name = "time_zone") String time_zone, @MaxMindDbParameter(name = "latitude") double latitude, @MaxMindDbParameter(name = "longitude") double longitude) {
        this.accuracy_radius = accuracy_radius;
        this.time_zone = time_zone;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
