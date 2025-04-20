package ModuleProject;

import ModuleProject.Exceptions.ContactNotFoundException;
import ModuleProject.Exceptions.RecurringContactException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        ContactManager contactManager = new ContactManager();

        Scanner sc = new Scanner(System.in);

        while (true){
            boolean esc = false;
            int input = -1;

            System.out.print(
                            "\n«1»: Добавить контакт\n" +
                            "«2»: Удалить контакт\n" +
                            "«3»: Посмотреть все контакты\n" +
                            "«4»: Найти контакт\n" +
                            "«5»: Посмотреть контакты по группе\n" +
                            "«0»: Выход\n" +
                            "\n> ");

            try {
                input = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Ошибка: " + e.getClass() +
                        " Введите валидное (0-5) целое число\nПовторите ввод");
            }finally {
                sc.nextLine();
            }

            switch (input){
                case 1:
                    try {
                        contactManager.addContact(sc);
                    } catch (RecurringContactException e){
                        System.out.println("Ошибка: " + e);
                    }
                    break;
                case 2:
                    System.out.print("Введите телефон контакта, который хотите удалить: ");
                    String phone = sc.nextLine();
                    System.out.print("Введите email контакта, который хотите удалить: ");
                    String email = sc.nextLine();

                    try {
                        contactManager.deleteContact(phone, email);
                    } catch (ContactNotFoundException e){
                        System.out.println("Ошибка: " + e);
                    }
                    break;
                case 3:
                    contactManager.printAllContacts();
                    break;
                case 4:
                    System.out.print("Введите имя для поиска: ");
                    String name = sc.nextLine();
                    System.out.println("--- Контакты с именем \'" + name + "\': ---");

                    contactManager.searchContactByName(name);
                    break;
                case 5:
                    System.out.print("Введите группу для поиска: ");
                    String group = sc.nextLine();
                    System.out.println("--- Контакты в группе \'" + group + "\': ---");

                    contactManager.searchContactByGroup(group);
                    break;
                case 0:
                    esc = true;
                    break;
            }
            if (esc){
                break;
            }

        }

    }

}