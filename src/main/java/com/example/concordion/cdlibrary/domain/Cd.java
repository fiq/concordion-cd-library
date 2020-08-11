package com.example.concordion.cdlibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Cd {
    private String artist;
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cd cd = (Cd) o;
        return artist.equals(cd.artist) &&
                title.equals(cd.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, title);
    }
}
