/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import model.Usuario;

/**
 *
 * @author Joel Grullon
 */
public class Session {
    private static Usuario currentUser;

    public static void login(Usuario user) {
        currentUser = user;
    }

    public static Usuario getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isAuthenticated() {
        return currentUser != null;
    }
}