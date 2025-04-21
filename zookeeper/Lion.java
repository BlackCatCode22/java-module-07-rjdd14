package zookeeper;

public class Lion extends Animal {

    public Lion() {
        super();
        Animal.numOfLions++;
    }

    public Lion(String sex, int age, int weight, String animalName,
                String animalID, String animalBirthDate, String animalColor,
                String animalOrigin, String animalArrivalDate) {
        super(sex, age, weight, animalName, animalID, animalBirthDate, animalColor,
              animalOrigin, animalArrivalDate);
        Animal.numOfLions++;
    }
}
