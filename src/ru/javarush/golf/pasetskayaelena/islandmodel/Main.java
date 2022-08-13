package ru.javarush.golf.pasetskayaelena.islandmodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion.DirectionType;
import ru.javarush.golf.pasetskayaelena.islandmodel.generators.IslandConfigGenerator;
import ru.javarush.golf.pasetskayaelena.islandmodel.processors.AnimalLifeCycleProcessor;
import ru.javarush.golf.pasetskayaelena.islandmodel.processors.PlantGenerationProcessor;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Island;
import ru.javarush.golf.pasetskayaelena.islandmodel.generators.IslandGenerator;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.IslandStatisticsDisplayer;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.ListUtils;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.LogExceptionScheduledExecutorService;

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
    private static final String CONFIG_FILE_PATH = "island-config.json";

    public static void testRound() {
        DirectionType[] directionTypes = { DirectionType.Right, DirectionType.Left};
        DirectionType directionType = Animal.chooseMoveDirection(directionTypes);
        System.out.println(directionType);
    }

    public static void main(String[] args) throws IOException {

        testRound();

        IslandConfig islandConfig = loadConfig();

        System.out.println("*** World creation");

        Island island = new IslandGenerator(islandConfig).generate();

        System.out.println("God made an island 🌍");
        System.out.println("added plants ☘");
        System.out.println("added animals 👣");
        System.out.println("God left the program ***");

        new IslandStatisticsDisplayer().display(island);

        runThreads(island, islandConfig);

        processConsoleInput(island);
    }

    private static IslandConfig loadConfig() throws IOException {
        System.out.println("Download configuration ...");

        IslandConfig islandConfig;
        if (Files.notExists(Path.of(CONFIG_FILE_PATH))) {
            islandConfig = new IslandConfigGenerator().generate();

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(islandConfig);
            System.out.println(json);

            try (FileWriter writer = new FileWriter(CONFIG_FILE_PATH)) {
                writer.write(json);
            }
        } else {
            String jsonString = Files.readString(Path.of(CONFIG_FILE_PATH));

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

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(splitLocations.length * 2);
        LogExceptionScheduledExecutorService executorService = new LogExceptionScheduledExecutorService(scheduledExecutorService);

        for (List<Location> locationsPerThread : splitLocations) {
            executorService.scheduleAtFixedRate(new PlantGenerationProcessor(locationsPerThread, islandConfig), "Error in plants processor", 0, islandConfig.simulationCycleDuration, TimeUnit.SECONDS);
            executorService.scheduleAtFixedRate(new AnimalLifeCycleProcessor(locationsPerThread, islandConfig, island), "Error in animal processor", 0, islandConfig.simulationCycleDuration, TimeUnit.SECONDS);
        }
    }
}