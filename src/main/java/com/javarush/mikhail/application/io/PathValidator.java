package com.javarush.mikhail.application.io;



import com.javarush.mikhail.application.exception.FileOperationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс утилита для валидации и подготовки путей файлов.
 * Поддерживает проверку существующих файлов и формирование путей для выходных файлов.
 */
public final class PathValidator {

    /**
     * Проверяет существование и читаемость входного файла.
     *
     * @param filePath путь к файлу
     * @return Path к файлу
     * @throws FileOperationException если файл не существует, не читаемый или не является файлом
     */
    public static Path prepareInputPath(String filePath) {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new FileOperationException("файл не существует: " + filePath);
        }
        if (!Files.isReadable(path)) {
            throw new FileOperationException("нет прав на чтение: " + filePath);
        }
        if (!Files.isRegularFile(path)) {
            throw new FileOperationException("не является файлом: " + filePath);
        }
        return path;
    }


    /**
     * Вычисляет корректный путь для выходного файла.
     * Если outputPath относительный — создаётся рядом с inputPath.
     * Создаёт недостающие директории.
     *
     * @param inputPath путь исходного файла
     * @param outputPath путь файла результата
     * @return Path к файлу результата
     * @throws FileOperationException если не удалось создать директорию
     */
    public static Path resolveOutputPath(String inputPath, String outputPath) {
        Path output = Path.of(outputPath);

        if (!output.isAbsolute()) {
            Path inputParent = Path.of(inputPath).getParent();
            if (inputParent != null) {
                output = inputParent.resolve(output);
            }
        }

        Path parent = output.getParent();
        if (parent != null && !Files.exists(parent)) {
            try {
                Files.createDirectories(parent);
            } catch (IOException e) {
                throw new FileOperationException(
                        "Не удалось создать директорию: " + parent, e
                );
            }
        }

        return output.normalize();
    }
}