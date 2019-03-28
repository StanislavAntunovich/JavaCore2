package ru.geekbrains.lesson1.obstacles;

import ru.geekbrains.lesson1.participants.Participant;
import ru.geekbrains.lesson1.participants.Team;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle... obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        for (Participant participant : team.getParticipants()) {
            for (Obstacle obstacle : obstacles) {
                obstacle.doIt(participant);

                if (!participant.isOnDistance()) {
                    break;
                }
            }
        }
    }
}
