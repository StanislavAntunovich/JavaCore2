package ru.geekbrains.lesson1;

import ru.geekbrains.lesson1.enums.Color;
import ru.geekbrains.lesson1.obstacles.Course;
import ru.geekbrains.lesson1.obstacles.Cross;
import ru.geekbrains.lesson1.obstacles.Wall;
import ru.geekbrains.lesson1.obstacles.Water;
import ru.geekbrains.lesson1.participants.*;

public class MainClass {
    public static void main(String[] args) {
        Course course = new Course(
                new Wall(1),
                new Cross(3000),
                new Water(300)
        );

        Team team = new Team(
                "Робокошки",
                new Cat("Барсик", Color.RED, 2, 3000, 2),
                new Dog("Тузик", Color.BROWN, 3, 7000, 1, 500),
                new Human("Валера", 32, 3200, 1, 200),
                new Robot("C3P0", "1.2.1", 22000, 2, 2000)
        );

        team.getTeamInfo();

        course.doIt(team);
        team.getResults();
    }
}
