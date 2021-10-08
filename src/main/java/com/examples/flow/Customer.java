package com.examples.flow;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.kie.dmn.feel.lang.FEELProperty;

public class Customer {
    private double cartAmount;
    private double initialRating;

    @Override
    public String toString() {
        return String.format("{cartAmount:%s, initialRating:%s}", cartAmount, initialRating);
    }

    // ---------------------------------------------------------------------------------------------------------
    // GETTERS / SETTERS

    @FEELProperty("cart amount")
    @JsonProperty("cart amount")
    public double getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(double cartAmount) {
        this.cartAmount = cartAmount;
    }

    @FEELProperty("initial rating")
    @JsonProperty("initial rating")
    public double getInitialRating() {
        return initialRating;
    }

    public void setInitialRating(double initialRating) {
        this.initialRating = initialRating;
    }
}
