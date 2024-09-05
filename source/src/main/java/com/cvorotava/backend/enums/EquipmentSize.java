package com.cvorotava.backend.enums;

public enum EquipmentSize {
    SIZE_8_10("8/10"),
    SIZE_10_12("10/12"),
    SIZE_12_14("12/14"),
    SIZE_14_16("14/16"),
    SIZE_S("S"),
    SIZE_M("M"),
    SIZE_L("L"),
    SIZE_XL("XL"),
    SIZE_XXL("XXL"),
    SIZE_3XL("3XL");

    private final String sizeStr;

    EquipmentSize(String sizeStr) {
        this.sizeStr = sizeStr;
    }

    public String getSizeStr() {
        return sizeStr;
    }
}
