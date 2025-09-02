package org.poo.main;

import org.poo.classesforgame.*;
import org.poo.checker.Checker;

import org.poo.classesforoutput.OutputsActions;
import org.poo.classesforoutput.OutputsCommands;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.checker.CheckerConstants;
import org.poo.fileio.ActionsInput;
import org.poo.fileio.CardInput;
import org.poo.fileio.Input;
import org.poo.heroes.EmpressThorina;
import org.poo.heroes.GeneralKocioraw;
import org.poo.heroes.KingMudface;
import org.poo.heroes.LordRoyce;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();


        Stats stats = new Stats();

        for (int currentGameIdx = 0; currentGameIdx < inputData.getGames().size();
             currentGameIdx++) {

            Game game = new Game();
            game.start = new Start(inputData.getGames().get(currentGameIdx).getStartGame());

            ArrayList<Actions> copy = new ArrayList<>();
            ArrayList<ActionsInput> actions =
                    inputData.getGames().get(currentGameIdx).getActions();
            for (ActionsInput actionsInput : actions) {
                copy.add(new Actions(actionsInput));
            }
            game.actions = copy; // aici am rezolvat cu actions urile jocului

            stats.setTotalGamesPlayed(stats.getTotalGamesPlayed() + 1);
            Random random1 = new Random(game.start.getShuffleSeed()); // aici tine shuffle seed ul
            Random random2 = new Random(game.start.getShuffleSeed()); // aici tine shuffle seed ul

            Hero hero1 = new Hero(inputData.getGames().get(currentGameIdx).getStartGame().
                    getPlayerOneHero());
            switch (inputData.getGames().get(currentGameIdx).
                    getStartGame().getPlayerOneHero().getName()) {
                case "Lord Royce":
                    LordRoyce aux = new LordRoyce(inputData.getGames()
                            .get(currentGameIdx).getStartGame().getPlayerOneHero());
                    game.start.setPlayerOneHero(aux);
                    break;
                case "Empress Thorina":
                    EmpressThorina aux1 = new EmpressThorina(inputData.getGames()
                            .get(currentGameIdx).getStartGame().getPlayerOneHero());
                    game.start.setPlayerOneHero(aux1);
                    break;
                case "King Mudface":
                    KingMudface aux2 = new KingMudface(inputData.getGames()
                            .get(currentGameIdx).getStartGame().getPlayerOneHero());
                    game.start.setPlayerOneHero(aux2);
                    break;
                case "General Kocioraw":
                    GeneralKocioraw aux3 = new GeneralKocioraw(inputData.getGames()
                            .get(currentGameIdx).getStartGame().getPlayerOneHero());
                    game.start.setPlayerOneHero(aux3);
                    break;
                default:
                    break;
            }

            Hero hero2 = new Hero(inputData.getGames().get(currentGameIdx).getStartGame().
                    getPlayerTwoHero());
            switch (inputData.getGames().get(currentGameIdx)
                    .getStartGame().getPlayerTwoHero().getName()) {
                case "Lord Royce":
                    LordRoyce aux = new LordRoyce(inputData.getGames()
                            .get(currentGameIdx).getStartGame().getPlayerTwoHero());
                    game.start.setPlayerTwoHero(aux);
                    break;
                case "Empress Thorina":
                    EmpressThorina aux1 = new EmpressThorina(inputData.getGames()
                            .get(currentGameIdx).getStartGame().getPlayerTwoHero());
                    game.start.setPlayerTwoHero(aux1);
                    break;
                case "King Mudface":
                    KingMudface aux2 = new KingMudface(inputData.getGames()
                            .get(currentGameIdx).getStartGame().getPlayerTwoHero());
                    game.start.setPlayerTwoHero(aux2);
                    break;
                case "General Kocioraw":
                    GeneralKocioraw aux3 = new GeneralKocioraw(inputData.getGames()
                            .get(currentGameIdx).getStartGame().getPlayerTwoHero());
                    game.start.setPlayerTwoHero(aux3);
                    break;
                default:
                    break;
            }

            //copie pt deck uri
            Decks decksPl1 = new Decks(inputData.getPlayerOneDecks());
            Decks decksPl2 = new Decks(inputData.getPlayerOneDecks());
            Decks pl1copyDecks = new Decks(inputData.getPlayerOneDecks());
            Decks pl2copyDecks = new Decks(inputData.getPlayerOneDecks());

            ArrayList<Card> deckPlayer1 = new ArrayList<Card>();
            for (CardInput carte : inputData.getPlayerOneDecks().getDecks().
                    get(game.start.getPlayerOneDeckIdx())) {
                Card cartea = new Card(carte);
                deckPlayer1.add(cartea);
            } // array ul asta contine deckul lui player 1
            Collections.shuffle(deckPlayer1, random1);

            ArrayList<Card> deckPlayer2 = new ArrayList<Card>();
            for (CardInput carte : inputData.getPlayerTwoDecks().getDecks().
                    get(game.start.getPlayerTwoDeckIdx())) {
                Card cartea = new Card(carte);
                deckPlayer2.add(cartea);
            } // array ul asta contine deckul lui player 2
            Collections.shuffle(deckPlayer2, random2);

            GameUpdate update = new GameUpdate();
            update.deckPl1 = deckPlayer1;
            update.getInHandPL1(update.deckPl1, update.pl1CardsInHand);
            update.deckPl2 = deckPlayer2;
            update.getInHandPL2(update.deckPl2, update.pl2CardsInHand);

            game.playerTurn = game.start.getStartingPlayer();
            OutputsCommands outputsCommands = new OutputsCommands();
            OutputsActions outputsActions = new OutputsActions();

            for (Actions action : game.actions) {
                action.doAction(output, outputsActions, outputsCommands, game, stats,
                        update, objectMapper);
            }
        }

        /*
         * TODO Implement your function here
         *
         * How to add output to the output array?
         * There are multiple ways to do this, here is one example:
         *
         * ObjectMapper mapper = new ObjectMapper();
         *
         * ObjectNode objectNode = mapper.createObjectNode();
         * objectNode.put("field_name", "field_value");
         *
         * ArrayNode arrayNode = mapper.createArrayNode();
         * arrayNode.add(objectNode);
         *
         * output.add(arrayNode);
         * output.add(objectNode);
         *
         */
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
