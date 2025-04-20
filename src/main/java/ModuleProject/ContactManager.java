package ModuleProject;

import ModuleProject.Exceptions.ContactNotFoundException;
import ModuleProject.Exceptions.RecurringContactException;

import java.util.*;

public class ContactManager {

    Set<Contact> uniquenessСheck = new HashSet<>();
    List<Contact> allContacts = new LinkedList<>();
    Map<String, HashSet<Contact>> groupByGroup = new HashMap<>();

    public void addContact(Scanner sc) throws RecurringContactException {
        System.out.print("Введите имя: ");
        String name = sc.nextLine();
        System.out.print("Введите телефон: ");
        String phone = sc.nextLine();
        System.out.print("Введите email: ");
        String email = sc.nextLine();
        System.out.print("Введите группу: ");
        String group = sc.nextLine();

        Contact contact = new Contact(name, phone, email, group);

        if (uniquenessСheck.contains(contact)){
            throw new RecurringContactException("\nКонтакт с таким телефоном и почтой уже существует");
        } else {
            uniquenessСheck.add(contact);
            allContacts.add(contact);
            groupByGroup.computeIfAbsent(group, k -> new HashSet<>()).add(contact);
            System.out.print("\nКонтакт добавлен.\n");
        }
    }

    public void deleteContact(String phone, String email) throws ContactNotFoundException {
        Contact contactForDeleteByEquals = new Contact(phone, email);
        Iterator<Contact> contactIterator = allContacts.iterator();
        boolean somethingDelete = false;

        while (contactIterator.hasNext()) {
            Contact contact = contactIterator.next();

            if (contact.equals(contactForDeleteByEquals)) {
                uniquenessСheck.remove(contact);
                groupByGroup.get(contact.getGroup()).remove(contact);
                contactIterator.remove();

                somethingDelete = true;
                System.out.println("Контакт удалён");
            }
        }
        if (! somethingDelete){
            throw new ContactNotFoundException("Контакт не найден");
        }
    }

    public void printAllContacts() {
        for (Contact contact : allContacts){
            System.out.println(contact.toString());
        }
    }

    public void searchContactByName(String name) {
        for (Contact contact : allContacts){
            if (contact.getName().equals(name)){
                System.out.println(contact.toString());
            }
        }
    }


    public void searchContactByGroup(String group) {
        HashSet<Contact> contactsInGroup = groupByGroup.get(group);
        if (contactsInGroup != null) {
            for (Contact contact : contactsInGroup) {
                System.out.println(contact.toString());
            }
        } else {
            System.out.println("Группа не найдена");
        }
    }

}