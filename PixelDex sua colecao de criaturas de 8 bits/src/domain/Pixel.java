package domain;

import java.util.Objects;

public class Pixel {
    private final int id;
    private final String name;
    private final Raridade rarity;
    private final int poder;

    public Pixel(int id, String name, Raridade rarity, int poder) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.poder = poder;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Raridade getRarity() {
        return rarity;
    }
    public int getPoder() {
        return poder;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return id == pixel.id || name.equalsIgnoreCase(pixel.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name.toLowerCase());
    }
    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Raridade: %s | Poder: %d",
                id, name, rarity, poder);
    }
}