package br.com.robertomassoni.xyinc.enumerator;


public enum EntityType {
    PRODUCT("Product"),
    CATEGORY("Category");
    
    String value;

    EntityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
