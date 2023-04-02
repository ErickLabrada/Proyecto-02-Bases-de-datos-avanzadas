/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.Exceptions;

/**
 *
 * @author Erick
 */
public class AlreadyPaidException extends RuntimeException {

    public AlreadyPaidException() {
    }

    public AlreadyPaidException(String message) {
        super(message);
    }
}
