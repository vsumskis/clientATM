package client;

import java.io.IOException;
import java.util.Scanner;

import static sun.security.util.Password.readPassword;

class Dialog {
    private Scanner scanner;

    Dialog() {
        scanner = new Scanner(System.in);
    }

    void print(String message) {
        System.out.println(message);
    }

    String input(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}