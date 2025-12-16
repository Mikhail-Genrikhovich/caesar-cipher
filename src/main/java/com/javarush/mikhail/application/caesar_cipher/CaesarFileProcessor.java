package com.javarush.mikhail.application.caesar_cipher;

import com.javarush.mikhail.application.io.PathValidator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Обрабатывает файлы с использованием CaesarCipher.
 * Поддерживает шифрование и дешифрование больших файлов с буферизацией.
 */
public class CaesarFileProcessor {
    private final CaesarCipher cipher;
    private static final int BUFFER_SIZE = 8192;

    public CaesarFileProcessor(CaesarCipher cipher) {
        this.cipher = cipher;
    }

    /**
     * Зашифровывает файл.
     *
     * @param inputPath путь к исходному файлу
     * @param outputPath путь для сохранения зашифрованного файла
     * @param key ключ шифрования
     */
    public void encryptFile(String inputPath, String outputPath, int key) {
        processFile(inputPath, outputPath, key, true);
    }


    /**
     * Дешифрует файл.
     *
     * @param inputPath путь к зашифрованному файлу
     * @param outputPath путь для сохранения расшифрованного файла
     * @param key ключ шифрования
     */
    public void decryptFile(String inputPath, String outputPath, int key) {
        processFile(inputPath, outputPath, key, false);
    }


    /**
     * Внутренний метод обработки файла. Шифрует или дешифрует в зависимости от флага.
     *
     * @param inputPath путь к исходному файлу
     * @param outputPath путь для сохранения результата (зашифрованного или расшифрованного файла)
     * @param key ключ шифрования/дешифрования
     * @param encrypt true - шифрование, false - дешифрование
     */
    private void processFile(String inputPath, String outputPath, int key, boolean encrypt) {
        Path src = PathValidator.prepareInputPath(inputPath);
        Path dst = PathValidator.resolveOutputPath(inputPath, outputPath);

        try (BufferedReader reader = Files.newBufferedReader(src, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(dst, StandardCharsets.UTF_8)) {

            char[] buffer = new char[BUFFER_SIZE];
            int read;
            while ((read = reader.read(buffer)) != -1) {
                for (int i = 0; i < read; i++) {
                    buffer[i] = encrypt ? cipher.encryptChar(buffer[i], key)
                            : cipher.decryptChar(buffer[i], key);
                }
                writer.write(buffer, 0, read);
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка обработки файла", e);
        }
    }
}