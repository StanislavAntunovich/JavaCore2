package ru.geekbrains.lesson1.participants;

public class Team {
    private String name;
    private Participant[] participants;

    public Team(String name, Participant participant1, Participant participant2, Participant participant3, Participant participant4) {
        this.name = name;
        this.participants = new Participant[]{participant1, participant2, participant3, participant4};
    }

    public Participant[] getParticipants() {
        return participants;
    }

    public void getResults() {
        System.out.println(String.format("Команда: %s \nСоревнования прошли: ", name));
        for (Participant p : participants) {
            if (p.isOnDistance())
                p.getInfo();
        }
    }

    public void getTeamInfo() {
        System.out.println(String.format("Команда %s, Участники:", name));
        for (Participant p : participants)
            p.getInfo();
    }


}
