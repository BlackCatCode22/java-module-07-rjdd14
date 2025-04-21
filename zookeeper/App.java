package zookeeper;

import java.io.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to my Zoo Program!");

        String filePath = "zookeeper/animalNames.txt";
        AnimalNameListsWrapper animalLists = Utilities.createAnimalNameLists(filePath);
        ArrayList<String> hyenaNames = animalLists.getHyenaNameList();

        ArrayList<String> zooReportLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("zookeeper/arrivingAnimals.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println("\nRaw Line: " + line);

                String[] parts = line.split(", ");
                String[] ageSexSpecies = parts[0].split(" ");
                int age = Integer.parseInt(ageSexSpecies[0]);
                String sex = ageSexSpecies[3];
                String species = ageSexSpecies[4].toLowerCase();

                String birthSeason = parts[1].replace("born in ", "").trim();
                String color = parts[2].replace(" color", "").trim();
                int weight = Integer.parseInt(parts[3].replace(" pounds", "").trim());
                String origin = parts[4];
                if (parts.length > 5) {
                    origin += ", " + parts[5];
                }
                origin = origin.replaceFirst("from ", "").trim();

                String id = Utilities.calcAnimalID(species);
                String birthDate = Utilities.calcAnimalBirthDate(age, birthSeason);
                String arrivalDate = Utilities.arrivalDate();

                String recordLine = "";

                if (species.equals("hyena") && !hyenaNames.isEmpty()) {
                    String name = hyenaNames.remove(0);
                    Hyena hyena = new Hyena(sex, age, weight, name, id, birthDate, color, origin, arrivalDate);
                    recordLine = formatRecord(hyena);
                    System.out.println("✅ Created Hyena:\n" + recordLine);

                } else if (species.equals("lion") && !animalLists.getLionNameList().isEmpty()) {
                    String name = animalLists.getLionNameList().remove(0);
                    Lion lion = new Lion(sex, age, weight, name, id, birthDate, color, origin, arrivalDate);
                    recordLine = formatRecord(lion);
                    System.out.println("✅ Created Lion:\n" + recordLine);

                } else if (species.equals("tiger") && !animalLists.getTigerNameList().isEmpty()) {
                    String name = animalLists.getTigerNameList().remove(0);
                    Tiger tiger = new Tiger(sex, age, weight, name, id, birthDate, color, origin, arrivalDate);
                    recordLine = formatRecord(tiger);
                    System.out.println("✅ Created Tiger:\n" + recordLine);

                } else if (species.equals("bear") && !animalLists.getBearNameList().isEmpty()) {
                    String name = animalLists.getBearNameList().remove(0);
                    Bear bear = new Bear(sex, age, weight, name, id, birthDate, color, origin, arrivalDate);
                    recordLine = formatRecord(bear);
                    System.out.println("✅ Created Bear:\n" + recordLine);
                }

                if (!recordLine.isEmpty()) {
                    zooReportLines.add(recordLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading arrivingAnimals.txt: " + e.getMessage());
        }
    
        try {
    File zooFile = new File("zookeeper/zooPopulation.txt");
    FileWriter fw = new FileWriter(zooFile, false); // overwrite mode
    BufferedWriter bw = new BufferedWriter(fw);
    PrintWriter writer = new PrintWriter(bw);

    System.out.println("✅ Writing zoo report to: " + zooFile.getAbsolutePath());

  
    for (String line : zooReportLines) {
        writer.println(line);
    }
   
    writer.println();
    writer.println("===== Animal Count Summary =====");
    writer.println("Hyenas: " + Animal.numOfHyenas);
    writer.println("Lions: " + Animal.numOfLions);
    writer.println("Tigers: " + Animal.numOfTigers);
    writer.println("Bears: " + Animal.numOfBears);
    writer.println("✅ Summary block written successfully");

    writer.flush();
    writer.close();

    System.out.println("📄 zooPopulation.txt written with report + summary!");
} catch (IOException e) {
    System.out.println("🚨 Error writing zooPopulation.txt: " + e.getMessage());
}


        System.out.println("\n===== Animal Count Summary =====");
        System.out.println("Hyenas: " + Animal.numOfHyenas);
        System.out.println("Lions: " + Animal.numOfLions);
        System.out.println("Tigers: " + Animal.numOfTigers);
        System.out.println("Bears: " + Animal.numOfBears);
    }

    private static String formatRecord(Animal animal) {
        return animal.getAnimalID() + "; " + animal.getAnimalName() + "; birth date: " +
               animal.getAnimalBirthDate() + "; " + animal.getAnimalColor() + "; " +
               animal.getSex() + "; " + animal.getWeight() + " pounds; from " +
               animal.getAnimalOrigin() + "; arrived " + animal.getAnimalArrivalDate();
    }
}
