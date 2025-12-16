package com.javarush.mikhail.application.console_menu;

import com.javarush.mikhail.application.caesar_cipher.CaesarFileProcessor;

import java.util.Scanner;

/**
 * Пункты главного меню и их поведение.
 * Каждый пункт умеет выполнять своё действие (шифрование, дешифрование, выход).
 */
public enum MenuOption {

    ENCRYPT(Constant.ENCRYPT) {
        @Override
        public void execute(Scanner scanner, CaesarFileProcessor processor) {
            System.out.println("\nШИФРОВАНИЕ");

            String src = readPath(scanner, "Исходный файл: ");
            String dst = readPath(scanner, "Файл результата: ");
            int key = readKey(scanner);

            processor.encryptFile(src, dst, key);
            System.out.println("Готово");
        }
    },

    DECRYPT(Constant.DECRYPT) {
        @Override
        public void execute(Scanner scanner, CaesarFileProcessor processor) {
            System.out.println("\nДЕШИФРОВАНИЕ");

            String src = readPath(scanner, "Зашифрованный файл: ");
            String dst = readPath(scanner, "Файл результата: ");
            int key = readKey(scanner);

            processor.decryptFile(src, dst, key);
            System.out.println("Готово");
        }
    },

    INFO(Constant.INFO){
        @Override
        public void execute(Scanner scanner, CaesarFileProcessor processor){
            System.out.println(Constant.INFORMATION);
        }
    },

    EXIT(Constant.EXIT) {
        @Override
        public void execute(Scanner scanner, CaesarFileProcessor processor) {
        }
    };

    private final String title;

    MenuOption(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Выполняет действие пункта меню.
     */
    public abstract void execute(Scanner scanner, CaesarFileProcessor processor);

    /**
     * Чтение пути файла с консоли (внутреннее).
     */
    protected static String readPath(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String path = scanner.nextLine().trim();
            if (!path.isEmpty()) {
                return path;
            }
            System.out.println("Путь не может быть пустым");
        }
    }

    /**
     * Чтение ключа с консоли (внутреннее).
     * */
    protected static int readKey(Scanner scanner) {
        while (true) {
            System.out.print("Ключ: ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число");
            }
        }
    }

    /**
     * Преобразует число в пункт меню.
     */
    public static MenuOption fromNumber(int number) {
        if (number < 1 || number > values().length) {
            throw new IllegalArgumentException("Нет такого пункта меню");
        }
        return values()[number - 1];
    }
}