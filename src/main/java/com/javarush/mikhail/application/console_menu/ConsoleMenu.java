package com.javarush.mikhail.application.console_menu;

import com.javarush.mikhail.application.caesar_cipher.CaesarFileProcessor;

import java.util.Scanner;

/**
 * Главное консольное меню приложения.
 * Обеспечивает выбор пункта и выполнение соответствующего действия.
 */
public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final CaesarFileProcessor processor;

    public ConsoleMenu(CaesarFileProcessor processor) {
        this.processor = processor;
    }

    /**
     * Запуск меню.
     * */
    public void start() {
        System.out.println("КРИПТОАНАЛИЗАТОР ЦЕЗАРЯ\n");

        while (true) {
            printMenu();
            MenuOption option = readChoice();

            if (option == MenuOption.EXIT) {
                System.out.println("Завершение работы");
                return;
            }

            try {
                option.execute(scanner, processor);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            System.out.print("\nДля продолжения нажмите \"Enter\"");
            scanner.nextLine();
        }
    }

    /**
     * Вывод пунктов меню на экран.
     * */
    private void printMenu() {
        for (MenuOption option : MenuOption.values()) {
            System.out.printf("%d. %s%n",
                    option.ordinal() + 1,
                    option.getTitle());
        }
        System.out.print("Выбор: ");
    }

    /**
     * Чтение выбора пользователя.
     * */
    private MenuOption readChoice() {
        while (true) {
            try {
                return MenuOption.fromNumber(
                        Integer.parseInt(scanner.nextLine().trim())
                );
            } catch (Exception e) {
                System.out.print("Неверный выбор: ");
            }
        }
    }
}