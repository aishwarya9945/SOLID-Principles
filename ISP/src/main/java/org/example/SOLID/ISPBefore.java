interface Restaurant {
    void vegMeals();
    void nonVegMeals();
}

class ABCVegRestaurant implements Restaurant {
    @Override
    public void vegMeals() {
        System.out.println("Serving vegetarian meals");
    }

    @Override
    public void nonVegMeals() {
        System.out.println("Sorry, I don't serve non-veg meals");
    }
}

public class ISPBefore {
    public static void main(String[] args) {
        Restaurant vegOnly = new ABCVegRestaurant();
        vegOnly.vegMeals();
        vegOnly.nonVegMeals(); // forced to implement, but meaningless
    }
}
