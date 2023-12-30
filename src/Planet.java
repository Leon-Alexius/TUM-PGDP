public enum Planet {
    EARTH,
    JUPITER,
    SATURN,
    URANUS,
    NEPTUNE;

    public boolean isGasPlanet() {
        return switch (this) {
            case JUPITER, SATURN, URANUS, NEPTUNE -> true;
            default -> false;
        };
    }
}
