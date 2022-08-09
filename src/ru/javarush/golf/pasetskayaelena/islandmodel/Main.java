package ru.javarush.golf.pasetskayaelena.islandmodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.generators.IslandConfigGenerator;
import ru.javarush.golf.pasetskayaelena.islandmodel.processors.AnimalLifeCycleProcessor;
import ru.javarush.golf.pasetskayaelena.islandmodel.processors.PlantGenerationProcessor;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Island;
import ru.javarush.golf.pasetskayaelena.islandmodel.generators.IslandGenerator;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.IslandStatisticsDisplayer;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.ListUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String configFilePath = "island-config.json";

    public static void testRound() {
        int requiredFoodInKgForFullSatiety = 36;
        int weight = 1;

        int requiredPlantsCount = requiredFoodInKgForFullSatiety/weight;
        System.out.println(requiredPlantsCount);
    }

    public static void main(String[] args) throws IOException {

        testRound();

        IslandConfig islandConfig = loadConfig();

        System.out.println("*** Сотворение мира");

        Island island = new IslandGenerator().generate(islandConfig);

        System.out.println("Бог создал остров 🌍");
        System.out.println("добавил растения ☘");
        System.out.println("добавил зверей 👣");
        System.out.println("Бог покинул программу ***");

        new IslandStatisticsDisplayer().display(island);

        runThreads(island, islandConfig);

        processConsoleInput(island);
    }

    private static IslandConfig loadConfig() throws IOException {
        System.out.println("Загрузка конфигурации ...");

        IslandConfig islandConfig;
        // если файл конфигурации не существует, то создать его с значениями по умолчанию
        if (Files.notExists(Path.of(configFilePath))) {
            islandConfig = new IslandConfigGenerator().generate();

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(islandConfig);
            System.out.println(json);

            try (FileWriter writer = new FileWriter(configFilePath)) {
                writer.write(json);
            }
        } else {
            // иначе прочитать и десериализовать файл конфигурации
            String jsonString = Files.readString(Path.of(configFilePath));

            islandConfig = new ObjectMapper().readValue(jsonString, IslandConfig.class);
        }
        return islandConfig;
    }

    private static void processConsoleInput(Island island) {
        boolean noExit = true;
        while (noExit) {
            Scanner console = new Scanner(System.in);
            console.useDelimiter("");
            char c = console.next().charAt(0);

            switch (c) {
                case 'q':
                    noExit = false;
                    break;
                case ' ':
                    new IslandStatisticsDisplayer().display(island);
                    break;
            }
        }
    }

    private static void runThreads(Island island, IslandConfig islandConfig) {
        final int locationsCountPerThread = 2;
        final ArrayList<Location> allLocations = island.getLocations();

        List<Location>[] splitLocations = ListUtils.split(allLocations, locationsCountPerThread);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(splitLocations.length*2);

        for (List<Location> locationsPerThread : splitLocations) {
            executorService.scheduleAtFixedRate(new PlantGenerationProcessor(locationsPerThread, islandConfig), 0, 5, TimeUnit.SECONDS);
            executorService.scheduleAtFixedRate(new AnimalLifeCycleProcessor(locationsPerThread, islandConfig), 0, islandConfig.simulationCycleDuration, TimeUnit.SECONDS);
        }
    }
}