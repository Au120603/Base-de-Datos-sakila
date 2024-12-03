package com.sakila.ui;

import com.sakila.controllers.FilmController;
import com.sakila.data.Film;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private FilmController filmController;

    public ConsoleUI() {
        this.filmController = new FilmController();
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Film Management");
            System.out.println("1. Add Film");
            System.out.println("2. Get Film");
            System.out.println("3. Update Film");
            System.out.println("4. Delete Film");
            System.out.println("5. List Films");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addFilm(scanner);
                    break;
                case 2:
                    getFilm(scanner);
                    break;
                case 3:
                    updateFilm(scanner);
                    break;
                case 4:
                    deleteFilm(scanner);
                    break;
                case 5:
                    listFilm();
                    break;
                case 6:
                    return;
            }
        }
    }

    public void addFilm(Scanner scanner) {
        System.out.print("Enter film title: ");
        scanner.nextLine(); // Limpiar el buffer del scanner
        String title = scanner.nextLine();
        System.out.print("Enter film description: ");
        String description = scanner.nextLine();
    
        Film film = new Film(0, title, description);
        filmController.addFilm(film);
    }
    

    public void getFilm(Scanner scanner) {
        System.out.print("Enter film ID: ");
        int id = scanner.nextInt();

        Film film = filmController.getFilm(id);
        if (film != null) {
            System.out.println(film.getTitle() + ": " + film.getDescription());
        } else {
            System.out.println("Film not found.");
        }
    }

    public void updateFilm(Scanner scanner) {
        System.out.print("Enter film ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
    
        Film film = filmController.getFilm(id);
        if (film != null) {
            System.out.print("Enter new title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new description: ");
            String description = scanner.nextLine();
    
            film.setTitle(title);
            film.setDescription(description);
    
            filmController.updateFilm(film);
        } else {
            System.out.println("Film not found.");
        }
    }
    

    public void deleteFilm(Scanner scanner) {
        System.out.print("Enter film ID: ");
        int id = scanner.nextInt();
        filmController.deleteFilm(id);
    }

    public void listFilm() {
        List<Film> films = filmController.getAllFilms();
        for (Film film : films) {
            System.out.println(film.getTitle() + ": " + film.getDescription());
        }
    }

    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.displayMenu();
    }
}
