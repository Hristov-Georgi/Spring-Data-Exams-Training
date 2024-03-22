package laptopShopExercise.model;

public enum WarrantyType {
    BASIC("BASIC"), PREMIUM("PREMIUM"), LIFETIME("LIFETIME");

    private String value;

    WarrantyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
