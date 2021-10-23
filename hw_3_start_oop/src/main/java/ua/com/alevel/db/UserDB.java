package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.UUID;

public class UserDB {

    private static User[] users = new User[10];

    public static void create(User user) {
        user.setId(generateId());
        int userAmount = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                userAmount++;
            }
        }
        if (userAmount == users.length) {
            User[] tempUsers = new User[users.length + 10];
            for (int i = 0; i < users.length; i++) {
                tempUsers[i] = users[i];
            }
            users = tempUsers;
        } else {
            users[userAmount] = user;
        }
        System.out.println("User was successfully added!");


    }

    public static void update(User user) {
        int exist = 0;
        for (User userCurrent : users) {
            if (user.getId().equals(userCurrent.getId())) {
                userCurrent.setName(user.getName());
                userCurrent.setAge(user.getAge());
                userCurrent.setEmail(user.getEmail());
                System.out.println("User was successfully updated!");
                exist = 1;
                break;
            }
        }
        if (exist == 0) {
            System.out.println("There is no such User in DB!");
        }
    }

    public static void delete(String id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId().equals(id)) {
                for (int j = i; j < users.length; j++) {
                    if (j != users.length - 1) {
                        users[j] = users[j + 1];
                    } else {
                        users[j] = null;
                    }
                }
                System.out.println("User was successfully deleted!");
                break;
            }
        }
        System.out.println("There is no such user!");
    }

    public static User findById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        System.out.println("User was not found!");
        return null;
    }

    public static User[] findAll() {
        return users;
    }

    private static String generateId() {
        String id = UUID.randomUUID().toString();
        for (User user : users) {
            if (user != null && user.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}
