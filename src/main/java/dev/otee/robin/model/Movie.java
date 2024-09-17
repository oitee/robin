package dev.otee.robin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;

@Table("movies")
public class Movie {
    @Id
    int id;
    String name;
    String slug;
    String description;
    Double avgRating;
    String tags;
    int releasedYear;
    String lang;
    OffsetDateTime createdAt;

    public Movie(String name, String slug, String description, String tags, int releasedYear, String lang){
        this.name = name;
        this. slug = slug;
        this.description = description;
        this.avgRating = 0.0D;
        this.tags = tags;
        this.releasedYear = releasedYear;
        this.lang = lang;
        this.createdAt = OffsetDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public String getTags() {
        return tags;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public String getLang() {
        return lang;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
