/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Exception class for objects thrown when number was to high or to low to convert.
 *
 * @author Quecheri
 * @version 1.1
 */
public class NumberOutOfBoundsExeption extends Exception {

    /**
     * Non-parameter constructor
     */
    public NumberOutOfBoundsExeption() {
    }

    /**
     * Exception class constructor
     *
     * @param message display message
     */
    public NumberOutOfBoundsExeption(String message) {
        super(message);
    }
}