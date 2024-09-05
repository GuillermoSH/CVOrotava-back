package com.cvorotava.backend.enums;

public enum EquipmentType {
    WARMING_SHIRT("Camisa de calentamiento", "Warming shirt"),
    TRAINING_SHIRT("Camisa de entrenamiento", "Training shirt"),
    COMPETITION_SHIRT("Camisa de competición", "Competition shirt"),
    COMPETITION_SHORTS("Pantalones cortos de competición", "Competition shorts"),
    COMPETITION_TIGHTS("Mallas de competición", "Competition tights"),
    COMPETITION_JACKET("Chaqueta de competición", "Competition jacket"),
    COMPETITION_LONG_PANTS("Pantalones largos de competición", "Competition long pants"),
    BACKPACK("Mochila", "Backpack"),
    COACH_SHIRT("Camisa de entrenador", "Coach shirt"),
    COACH_POLO("Polo de entrenador", "Coach polo"),
    COACH_JACKET("Chaqueta de entrenador", "Coach jacket"),
    COACH_PANTS("Pantalón de entrenador", "Coach pants");

    private final String espString;
    private final String engString;

    EquipmentType(String espString, String engString) {
        this.espString = espString;
        this.engString = engString;
    }

    public String getEspString() {
        return espString;
    }

    public String getEngString() {
        return engString;
    }
}