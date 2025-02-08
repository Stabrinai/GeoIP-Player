package org.github.stabrinai.geoIPPlayer.record;

import com.maxmind.db.MaxMindDbConstructor;
import com.maxmind.db.MaxMindDbParameter;


public record LookupResult(Continent continent, Country country, Location location, City city) {
    @MaxMindDbConstructor
    public LookupResult(@MaxMindDbParameter(name = "continent") Continent continent, @MaxMindDbParameter(name = "country") Country country, @MaxMindDbParameter(name = "location") Location location, @MaxMindDbParameter(name = "city") City city) {
        this.continent = continent;
        this.country = country;
        this.location = location;
        this.city = city;
    }
}

