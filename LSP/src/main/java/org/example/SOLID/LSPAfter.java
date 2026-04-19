package org.example.SOLID;

class Vehicle1 {
    public void start() {
        System.out.println("Starting a Vehicle");
    }
    public void stop() {
        System.out.println("Stopping a Vehicle");
    }
}

class Bike1 extends Vehicle1 {
    @Override
    public void start() {
        System.out.println("Starting a Bike"); // consistent with Vehicle contract
    }
    @Override
    public void stop() {
        System.out.println("Stopping a Bike");
    }
}

class Car1 extends Vehicle1 {
    @Override
    public void start() {
        System.out.println("Starting a Car");
    }
    @Override
    public void stop() {
        System.out.println("Stopping a Car");
    }
}

public class LSPAfter {
    static void testDrive(Vehicle1 vehicle) {
        vehicle.start();
        vehicle.stop();
    }
    public static void main(String[] args) {
        testDrive(new Vehicle1()); // works
        testDrive(new Bike1());    // works
        testDrive(new Car1());     // works
    }
}
