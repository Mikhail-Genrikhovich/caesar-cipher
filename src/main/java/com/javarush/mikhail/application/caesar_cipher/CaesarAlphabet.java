package com.javarush.mikhail.application.caesar_cipher;

import com.javarush.mikhail.application.exception.AlphabetException;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, представляющий алфавит для шифра Цезаря.
 * Содержит русские и английские буквы (в обоих регистрах),
 * цифры и некоторые символы.
 * Так же содержит основные методы для работы с ним.
 */
public final class CaesarAlphabet {
    private static final String ru = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String en = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String symbols = ".,!?&^`~<>\\|/'\";: ";
    private static final String numbers = "0123456789";
    private static final char[] alphabetArr = (ru + ru.toLowerCase() +
            en + en.toLowerCase() + symbols + numbers).toCharArray();


    private static final Map<Character, Integer> alphabetMap = new HashMap<>();

    static {
        for (int i = 0; i < alphabetArr.length; i++) {
            alphabetMap.put(alphabetArr[i], i);
        }
    }

    public Character getCharByIndex(int index) {
        if (index < 0 || index >= alphabetMap.size()) {
            throw new AlphabetException(String.format("некорректный индекс %d! Допустимое значение от: 0 до %d.",
                    index, alphabetArr.length - 1));
        }
        return alphabetArr[index];
    }

    public int getCharIndex(Character character) {
        if (!alphabetMap.containsKey(character)) {
            throw new AlphabetException(String.format("некорректный символ: '%c'", character));
        }
        return alphabetMap.get(character);
    }

    public int size() {
        return alphabetArr.length;
    }

}
