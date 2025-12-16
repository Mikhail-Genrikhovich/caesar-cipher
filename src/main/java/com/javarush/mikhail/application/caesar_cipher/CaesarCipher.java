package com.javarush.mikhail.application.caesar_cipher;

/**
 * Шифр Цезаря для работы с символами на основе заданного алфавита.
 */
public class CaesarCipher {
    private final CaesarAlphabet alphabet;

    public CaesarCipher(CaesarAlphabet alphabet) {
        this.alphabet = alphabet;
    }

    /**
     * Шифрует символ с использованием ключа.
     * Если символ не найден в алфавите, возвращается без изменений.
     *
     * @param ch символ
     * @param key ключ шифрования
     * @return зашифрованный символ
     */
    public char encryptChar(char ch, int key) {
        int index;
        try {
            index = alphabet.getCharIndex(ch);
        } catch (Exception e) {
            return ch;
        }
        int newIndex = (index + key) % alphabet.size();
        if (newIndex < 0) newIndex += alphabet.size();
        return alphabet.getCharByIndex(newIndex);
    }

    /**
     * Дешифрует символ с использованием ключа, меняя его значение на отрицательное.
     * Если символ не найден, возвращает без изменений.
     *
     * @param ch символ
     * @param key ключ дешифрования
     * @return расшифрованный символ
     */
    public char decryptChar(char ch, int key) {
        return encryptChar(ch, -key);
    }

}