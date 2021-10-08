package com.examples.flow;

import org.kie.dmn.feel.lang.FEELProperty;

public class Customer {
    private double cartAmount;
    private double initialRating;

    // ---------------------------------------------------------------------------------------------------------
    // GETTERS / SETTERS

    @FEELProperty("cart amount")
    public double getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(double cartAmount) {
        this.cartAmount = cartAmount;
    }

    @FEELProperty("initial rating")
    public double getInitialRating() {
        return initialRating;
    }

    public void setInitialRating(double initialRating) {
        this.initialRating = initialRating;
    }
}
