package util;

import domain.Pixel;
import java.util.Comparator;

public class Comparators {
    public static final Comparator<Pixel> BY_NAME =
            (a, b) -> {
                int cmp = a.getName().compareTo(b.getName());
                if (cmp != 0) {
                    return cmp;
                }
                return Integer.compare(a.getId(), b.getId());
            };
    public static final Comparator<Pixel> BY_POWER =
            (a, b) -> {
                int cmp = Integer.compare(a.getPoder(), b.getPoder());
                if (cmp != 0) return cmp;
                return Integer.compare(a.getId(), b.getId());
            };
}