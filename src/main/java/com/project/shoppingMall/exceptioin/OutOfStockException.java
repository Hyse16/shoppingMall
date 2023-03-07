package com.project.shoppingMall.exceptioin;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String message) {
        super(message);

    }
}
