package ru.geekbrains.lesson1.obstacles;

import ru.geekbrains.lesson1.participants.Participant;

public class Cross extends Obstacle {
    private int distance;

    public Cross(int distance) {

        this.distance = distance;
    }

    @Override
    public void doIt(Participant participant) {

        participant.run(distance);
    }
}
