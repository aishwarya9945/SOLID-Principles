package org.example.SOLID;

class Vehicle {
    public void start() {
        System.out.println("Starting a Vehicle");
    }
    public void stop() {
        System.out.println("Stopping a Vehicle");
    }
}

class Bike extends Vehicle {
    @Override
    public void start() {
        // Violates LSP: throws unexpected exception
        throw new RuntimeException("Boom you are gone..");
    }
}

class Car extends Vehicle {
    @Override
    public void start() {
        // Violates LSP: throws unexpected exception
        throw new RuntimeException("Boom you are gone..");
    }
}

public class LSPBefore {
    static void testDrive(Vehicle vehicle) {
        vehicle.start();
        vehicle.stop();
    }
    public static void main(String[] args) {
        testDrive(new Vehicle()); // works
        testDrive(new Bike());    // works
        testDrive(new Car());     // works
    }
}

