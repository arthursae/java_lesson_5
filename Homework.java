package lesson5;

import java.util.*;

public class Homework {

    public static void main(String[] args) {
      
        /*
        Реализовать консольное приложение – телефонный справочник.
        У одной фамилии может быть несколько номеров.
        Пользователь может вводить команды:
        1: ADD Ivanov 88005553535 – добавить в справочник новое значение. Первый аргумент – фамилия, второй – номер телефона
        2: GET Ivanov – получить список всех номеров по фамилии
        3: REMOVE Ivanov – удалить все номера по фамилии
        4: LIST – Посмотреть все записи
        5: EXIT – Завершить программу

        Если при GET или REMOVE нужной фамилии нет, вывести информацию об этом

        Пример взаимодействия (=> – это вывод на консоль):
        ADD Ivanov 8 800 555 35 35
        ADD Ivanov 8 800 100 10 10
        GET Ivanov => [8 800 555 35 35, 8 800 100 10 10]
        ADD Petrov 8 555 12 34
        LIST => Ivanov = [8 800 5553535, 8 800 100 10 10], Petrov = [8 555 12 34]
        REMOVE Ivanov
        LIST => Petrov = [8 555 12 34]
        GET NoName => Не найдена запись с фамилией "NoName"
        REMOVE NoName => Не найдена запись с фамилией "NoName"3
         */

        Scanner scanner = new Scanner(System.in);
        Map<String, ArrayList<String>> phoneBook = new HashMap<>();

        while (true) {
            String userInput = scanner.nextLine();

            if (!userInput.isEmpty()) {
                String[] splitCommand = userInput.trim().split("\\s+");
                int length = splitCommand.length;
                String mainCommand = splitCommand[0].toUpperCase();

                if (mainCommand.equals("EXIT")) {
                    System.out.println("Программа завершена");
                    break;

                } else if (mainCommand.equals("LIST")) {
                    if (phoneBook.size() > 0) {
                        phoneBook.forEach((surname, numbers) -> {
                            System.out.print(surname + ": ");
                            numbers.forEach((n) -> System.out.print(n + ", "));
                            System.out.print("\n");
                        });
                    } else {
                        System.err.println("Список пуст");
                    }

                } else if (mainCommand.equals("REMOVE") && length == 2) {
                    String surname = splitCommand[1];

                    if (phoneBook.containsKey(surname)) {
                        phoneBook.remove(surname);
                        // System.out.printf("Запись %s удалена \n", surname);

                    } else {
                        System.err.printf("Записи с фамилией %s не существует \n", surname);
                    }

                } else if (mainCommand.equals("GET") && length == 2) {
                    String surname = splitCommand[1];

                    if (phoneBook.containsKey(surname)) {
                        phoneBook.get(surname).forEach((n) -> System.out.print(n + ", "));
                        System.out.print("\n");
                    } else {
                        System.err.printf("Записи с фамилией %s не существует \n", surname);
                    }

                } else if (mainCommand.equals("ADD") && length == 3) {
                    String surname = splitCommand[1];
                    String number = splitCommand[2];

                    if (phoneBook.containsKey(surname)) {
                        ArrayList<String> numbers = phoneBook.get(surname);
                        numbers.add(number);
                        // System.out.printf("Номер %s добавлен \n", number);
                    } else {
                        ArrayList<String> numbers = new ArrayList<>();
                        numbers.add(number);
                        phoneBook.put(surname, numbers);
                        // System.out.printf("Запись для %s c номером телефона %s добавлена \n", surname, number);
                    }
                } else {
                    System.err.println("Неправильная команда");
                }
            }
        }
    }
}
