/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.Utilidades;

/**
 *
 * @author Erick
 */
public class EncriptadorSecreto {

    public String encriptar(String mensaje) {
        String stringEncriptado;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (letra >= 'A' && letra <= 'Z') {
                letra = (char) ((letra - 'A' + 7) % 26 + 'A');
            } else if (letra >= 'a' && letra <= 'z') {
                letra = (char) ((letra - 'a' + 7) % 26 + 'a');
            }
            stringBuilder.append(letra);
        }
        stringEncriptado = stringBuilder.toString();
        return stringEncriptado;
    }

    public String desencriptar(String mensaje) {
        String stringDesencriptado;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (letra >= 'A' && letra <= 'Z') {
                letra = (char) ((letra - 'A' - 7+26) % 26 + 'A');
            } else if (letra >= 'a' && letra <= 'z') {
                letra = (char) ((letra - 'a' - 7+26) % 26 + 'a');
            }
            stringBuilder.append(letra);
        }
        stringDesencriptado = stringBuilder.toString();
        return stringDesencriptado;
    }
}