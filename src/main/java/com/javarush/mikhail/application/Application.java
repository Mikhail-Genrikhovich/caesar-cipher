package com.javarush.mikhail.application;

import com.javarush.mikhail.application.caesar_cipher.CaesarAlphabet;
import com.javarush.mikhail.application.caesar_cipher.CaesarCipher;
import com.javarush.mikhail.application.caesar_cipher.CaesarFileProcessor;
import com.javarush.mikhail.application.console_menu.ConsoleMenu;

/**
 * Главный класс - точка входа в программу.
 */
public class Application {
    public static void main(String[] args) {
        CaesarFileProcessor caesarFileProcessor = new CaesarFileProcessor(new CaesarCipher(new CaesarAlphabet()));
        ConsoleMenu consoleMenu = new ConsoleMenu(caesarFileProcessor);

        consoleMenu.start();
    }
}