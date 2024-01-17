package Fruit_Basket;

public enum Taste {
    SWEET,
    SOUR,
    BITTER,
    SALTY;

    public boolean isSweet(){
        return switch (this){
            case SOUR, BITTER, SALTY -> false;
            default -> true;
        };
    }
}
