package com.example.proyecto_final;
/*
    Nombre del programa: Proyecto_Final.
    Autor: Daniel Vázquez Joaquín.
    Materia: DAPPS.
    Tarea: Proyecto Final.
    Fecha: 29/03/22.
    Descripción: Es la utilería que encripta la contraseña
    por el método md5.
    Contenido:
    class Utils
    public static String md5
*/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Utils {

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
        }

    }
