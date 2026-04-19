package org.example.SOLID;

interface VegRestaurant {
    void vegMeals();
}

interface NonVegRestaurant {
    void nonVegMeals();
}

class abcvegrestaurant implements VegRestaurant {
    @Override
    public void vegMeals() {
        System.out.println("Serving vegetarian meals");
    }
}

class XYZNonVegRestaurant implements NonVegRestaurant {
    @Override
    public void nonVegMeals() {
        System.out.println("Serving non-vegetarian meals");
    }
}

public class ISPAfter {
    public static void main(String[] args) {
        VegRestaurant vegOnly = new abcvegrestaurant();
        vegOnly.vegMeals();

        NonVegRestaurant nonVegOnly = new XYZNonVegRestaurant();
        nonVegOnly.nonVegMeals();
    }
}
