import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Monkey {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // Card constants
    static final String[] SUITS = {"♣", "♦", "♥", "♠"}; 
    static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    static final String[] SUIT_NAMES = {"Clubs", "Diamonds", "Hearts", "Spades"};
    static String[][] players = new String[4][13]; // Max 13 cards per player
    static int[] playerCardCount = {0, 0, 0, 0}; // Track card count per player
    static String[] deck = new String[52];
    static void Title() {
        String[] titleLines = {
            "████████████████████████████████████████████████████████████",
            "",
            "███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    ",
            "████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    ",
            "██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     ",
            "██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      ",
            "██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       ",
            "╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       ",
            "                                                           ",
            "███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    ",
            "████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    ",
            "██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     ",
            "██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      ",
            "██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       ",
            "╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       ",
            "                                                           ",
            "",
            "███████████████████████████████████████████████████████████"
        };
        String[] Monkey = {
                        "                                                     .--.\r\n" + //
                        "                                                   .-    \\\r\n" + //
                        "                                                  /_      \\\r\n" + //
                        "                                                 (o        )\r\n" + //
                        "                                               _/          |\r\n" + //
                        "                                              (c       .-. |\r\n" + //
                        "             ___                  ;;          /      .'   \\\r\n" + //
                        "          .''   ``.               ;;         O)     |      \\\r\n" + //
                        "        _/ .-. .-. \\_     () ()  / _          `.__  \\       \\\r\n" + //
                        "       (o|( O   O )|o)   ()(O)() |/ )           /    \\       \\\r\n" + //
                        "        .'         `.      ()\\  _|_            /      \\       \\\r\n" + //
                        "       /    (c c)    \\        \\(_  \\          /        \\       \\\r\n" + //
                        "       |             |        (__)  `.______ ( ._/      \\       )\r\n" + //
                        "       \\     (o)     /        (___)`._      .'           )     /\r\n" + //
                        "        `.         .'         (__)  ______ /            /     /\r\n" + //
                        "          `-.___.-'            /|\\         |           /     /\r\n" + //
                        "          ___)(___            /  \\         \\          /     /\r\n" + //
                        "       .-'        `-.                       `.      .'     /\r\n" + //
                        "      / .-.      .-. \\                        `-  /.'     /\r\n" + //
                        "     / /  ( .  . )  \\ \\                         / \\)| | | |\r\n" + //
                        "    / /    \\    /    \\ \\                       /     \\_\\_\\_)\r\n" + //
                        "    \\ \\     )  (     / /                     (    /\r\n" + //
                        "     \\ \\   ( __ )   / /                        \\   \\ \\  \\\r\n" + //
                        "    /   )  //  \\\\  (   \\                        \\   \\ \\  \\\r\n" + //
                        "(\\ / / /\\) \\\\  // (/\\ \\ \\ /)                     )   \\ \\  \\\r\n" + //
                        " -'-'-'  .'  )(  `.  `-`-`-                     .'   |.'   |\r\n" + //
                        "       .'_ .'  `. _`.                      _.--'     (     (\r\n" + //
                        "MJP  oOO(_)      (_)OOo                   (__.--._____)_____)\n"+
                        "████████████████████████████████████████████████████████████",
                        "",
                        "  ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    ",
                        "  ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    ",
                        "  ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     ",
                        "  ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      ",
                        "  ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       ",
                        "  ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       ",
                        "                                                           ",
                        "  ███╗   ███╗ ██████╗ ███╗   ██╗██╗  ██╗███████╗██╗   ██╗    ",
                        "  ████╗ ████║██╔═══██╗████╗  ██║██║ ██╔╝██╔════╝╚██╗ ██╔╝    ",
                        "  ██╔████╔██║██║   ██║██╔██╗ ██║█████╔╝ █████╗   ╚████╔╝     ",
                        "  ██║╚██╔╝██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝    ╚██╔╝      ",
                        "  ██║ ╚═╝ ██║╚██████╔╝██║ ╚████║██║  ██╗███████╗   ██║       ",
                        "  ╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝   ╚═╝       ",
                        "                                                           ",
                        "",
                        "███████████████████████████████████████████████████████████"
                    };

        for (int i = 0; i < Monkey.length; i++) {
            clearScreen();
            for (int j = 0; j <= i; j++) {
                System.out.println(Monkey[j]);
            }
            try {
                Thread.sleep(100); // Adjust the delay for animation speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   // Function to check for duplicate cards in the deck using an array
    public static void checkForDuplicates(String[] deck) {
        boolean hasDuplicate = false;

        for (int i = 0; i < deck.length; i++) {
            for (int j = i + 1; j < deck.length; j++) {
                if (deck[i].equals(deck[j])) {
                    System.out.println("Duplicate found: " + deck[i]);
                    hasDuplicate = true;
                }
            }
        }

        if (!hasDuplicate) {
            System.out.println("No duplicates found in the deck.");
        }
        System.out.println("Total number of cards: " + deck.length);
    }
       
        
    static String getCardASCII(String card) {
        String rank = card.substring(0, card.length() - 1); // Extract rank (e.g., "A" from "A♠")
        String suit = card.substring(card.length() - 1); // Extract suit (e.g., "♠" from "A♠")
        
        // Padding for ranks to ensure proper alignment
        String paddedRank = rank.length() == 1 ? rank + " " : rank;
        
        return String.format(
            "┌───────┐\n" +
            "│ %s    │\n" +
            "│       │\n" +
            "│   %s   │\n" +
            "│       │\n" +
            "│    %s │\n" +
            "└───────┘", 
            paddedRank, suit, paddedRank);
    }
    // ETO NADAGDAG 
    static String[] backCard() {
        String cardASCII = 
            "┌───────┐\n" +
            "│░░░░░░░│\n" +
            "│░░░░░░░│\n" +
            "│░░░░░░░│\n" +
            "│░░░░░░░│\n" +
            "│░░░░░░░│\n" +
            "└───────┘";
        
        return cardASCII.split("\n");
    }
    static void printCard(String[] hand, boolean reveal) {
        String[][] cardLines = new String[hand.length][];

        for (int i = 0; i < hand.length; i++) {
            cardLines[i] = reveal ? getCardASCII(hand[i]).split("\n") : backCard();
        }

        // Print each row of all cards side by side
        for (int line = 0; line < 7; line++) {
            for (int i = 0; i < hand.length; i++) {
                System.out.print(cardLines[i][line] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
 static String getSuitName(String suit) {
        switch (suit) {
            case "♣": return "Clubs";
            case "♦": return "Diamonds";
            case "♥": return "Hearts";
            case "♠": return "Spades";
            default: return "";
        }
    }
    // Function to check and remove duplicates from a player's hand
    static String[] checkAndRemoveDuplicates(String[] hand) {
        boolean[] isPaired = new boolean[hand.length];
        int newSize = hand.length;

        for (int i = 0; i < hand.length; i++) {
            for (int j = i + 1; j < hand.length; j++) {
                if (!isPaired[i] && !isPaired[j] && hand[i].substring(0, hand[i].length() - 1).equals(hand[j].substring(0, hand[j].length() - 1))) {
                    isPaired[i] = isPaired[j] = true;
                    newSize -= 2;
                    break;
                }
            }
        }

        String[] newHand = new String[newSize];
        int newIndex = 0;
        for (int i = 0; i < hand.length; i++) {
            if (!isPaired[i]) {
                newHand[newIndex++] = hand[i];
            }
        }
        return newHand;
    }
    static boolean hasDuplicates = false;
        // Function to check for duplicates and return the found duplicate cards
            static String[] findDuplicates(String[] hand) {
                String[] seenCards = new String[hand.length];
                int seenIndex = 0;
                String[] duplicates = new String[hand.length];
                int duplicateIndex = 0;
    
                for (String card : hand) {
                    boolean isDuplicate = false;
                    for (int i = 0; i < seenIndex; i++) {
                        if (seenCards[i].substring(0, seenCards[i].length() - 1)
                            .equals(card.substring(0, card.length() - 1))) {
                            duplicates[duplicateIndex++] = card;
                            isDuplicate = true;
                            hasDuplicates = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    seenCards[seenIndex++] = card;
                }
            }
            return Arrays.copyOf(duplicates, duplicateIndex);
        }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Title();
        System.out.println("1. Start Game");
        System.out.println("2. How to Play");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                clearScreen();
                
                break;
            case 2:
                clearScreen();
                System.out.println("How to Play");
                System.out.println("The game is played by 4 players. Each player is dealt 13 cards.");
                System.out.println("The player with the highest card wins the round.");
                System.out.println("The game continues until all 52 cards are played.");
                System.out.println("The player with the most number of rounds won is the winner.");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
       
        // Define card suits and ranks
        String[] SUITS = {"♣", "♠", "♥", "♦"};
        String[] SUIT_NAMES = {"Clubs", "Spades", "Hearts", "Diamonds"};
        String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Create and display the deck
        String[] deck = new String[52];
        int cardIndex = 0;
        
       for (String suit : SUITS) {
            System.out.println("=== " + suit + " (" + getSuitName(suit) + ") ===\n");
            
            // Create an array to hold all cards of current suit
            String[] suitCards = new String[RANKS.length];
            for (int i = 0; i < RANKS.length; i++) {
                suitCards[i] = RANKS[i] + suit;
            }

            String[][] cardLines = new String[suitCards.length][];
            for (int i = 0; i < suitCards.length; i++) {
                cardLines[i] = getCardASCII(suitCards[i]).split("\n");
            }
                
                // Print each row of all cards
                for (int line = 0; line < 7; line++) {  // 7 lines per card
                    for (int j = 0; j < cardLines.length; j++) {
                        System.out.print(cardLines[j][line] + "  ");
                    }
                    System.out.println();
                }
                System.out.println();  // Extra space between rows
            
    }
    for (String suit : SUITS) {
        for (String rank : RANKS) {
            deck[cardIndex++] = rank + suit;
        }
    }
        // Call the duplicate check function
        checkForDuplicates(deck);

        System.out.println("Enter any key to pick random card from the deck:");
        scanner.next();
        // Pick a random card from the deck
        Random rand = new Random();
        String chosenCard = deck[rand.nextInt(52)];
        System.out.println("\nRandomly Picked Card: ");

        // Display the chosen card in ASCII art
        System.out.println(getCardASCII(chosenCard));

        System.out.println("The Deck after removing the chosen card:");
        
        
        System.out.println("Enter any key to shuffle the cards:");
        scanner.next();
        // Remove the chosen card from the deck
        for (int i = 0; i < deck.length; i++) {
            if (deck[i].equals(chosenCard)) {
            deck[i] = deck[deck.length - 1];
            deck = Arrays.copyOf(deck, deck.length - 1);
            break;
            }
        }
        System.out.println("Deck after removing the chosen card:");
        System.out.println("\n");

        // Shuffle the deck after removing the chosen card
        for (int i = deck.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp; 
        }

        // Visual representation of the deck after shuffling and removing the chosen card
         System.out.println("Deck after shuffling and removing the chosen card:");
          int cardsPerRow = 13; // Number of cards per row
          for (int i = 0; i < deck.length; i += cardsPerRow) {
          String[][] cardLines = new String[Math.min(cardsPerRow, deck.length - i)][];

            // Convert each card into ASCII format and split into lines
            for (int j = 0; j < cardLines.length; j++) {
                cardLines[j] = getCardASCII(deck[i + j]).split("\n");
            }

            // Print each line of all cards side by side
            for (int line = 0; line < 7; line++) {  // Each card has 7 lines
            for (int j = 0; j < cardLines.length; j++) {
            System.out.print(cardLines[j][line] + "  "); // Print each card line side by side
            }
                System.out.println(); // Move to the next line
        }
        System.out.println(); // Add space between rows of cards
    }
        System.out.println("Enter any key to distribute the cards to the players and 4 computers:");
        scanner.next();
        // Reset card index
        cardIndex = 0;

        // Distribute the cards
        String[] humanHand = new String[11];
        String[][] botHands = new String[4][10];

        // Assign 11 cards to the human player
        for (int i = 0; i < 11; i++) {
            humanHand[i] = deck[cardIndex++];
        }

        // Assign 10 cards to each bot
        for (int b = 0; b < 4; b++) {
            for (int i = 0; i < 10; i++) {
                botHands[b][i] = deck[cardIndex++];
            }
        }

        // Display hands
        System.out.println("\nHuman Player's Hand:");
            printCard(humanHand, true); 
        
        System.out.println("\n");

        // Display bot hands
        for (int b = 0; b < 4; b++) {
            System.out.println("Bot " + (b + 1) + "'s Hand:");
            printCard(botHands[b], false);
            System.out.println("\n");
        }

        // Hidden card remains secret
        System.out.println("A card is hidden for game mechanics.");
        System.out.println(("Checking Duplicate Cards..."));

        // Check for duplicate cards
        boolean hasDuplicates = false;
        String[] seenCards = new String[52];
        int seenIndex = 0;
        String[] duplicates = new String[52];
        int duplicateIndex = 0;
        

    

        // Check and list duplicates for the Human Player
        String[] humanDuplicates = findDuplicates(humanHand);
        if (humanDuplicates.length > 0) {
            System.out.print("Human Player has found duplicate cards: ");
            System.out.println(Arrays.toString(humanDuplicates));
        } else {
            System.out.println("Human Player has no duplicate cards.");
        }

        // Check and list duplicates for each bot
        for (int b = 0; b < botHands.length; b++) {
            String[] botDuplicates = findDuplicates(botHands[b]);
            if (botDuplicates.length > 0) {
                System.out.print("Bot " + (b + 1) + " has found duplicate cards: ");
                System.out.println(Arrays.toString(botDuplicates));
            } else {
                System.out.println("Bot " + (b + 1) + " has no duplicate cards.");
            }
        }

        // Now remove duplicates using checkAndRemoveDuplicates()
        humanHand = checkAndRemoveDuplicates(humanHand);
        for (int b = 0; b < botHands.length; b++) {
            botHands[b] = checkAndRemoveDuplicates(botHands[b]);
        }
        // Process each player's hand to remove duplicates
        humanHand = checkAndRemoveDuplicates(humanHand);

        for (int b = 0; b < botHands.length; b++) {
            botHands[b] = checkAndRemoveDuplicates(botHands[b]);
        }

        // Display remaining cards for each player
        System.out.println("\nHuman Player's Hand after removing duplicates:");
        printCard(humanHand, true);

        for (int b = 0; b < botHands.length; b++) {
            System.out.println("Bot " + (b + 1) + "'s Hand after removing duplicates:");
            printCard(botHands[b], true);
            System.out.println("\n");
        }
        System.out.println("Enter any key to roll the dice to decide who picks first:");
        scanner.next();

        // Simulate dice rolling animation for human player
        int humanRoll = 0;
        try {
            humanRoll = rollDiceWithAnimation(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] botRolls = new int[4];
        boolean[] rollUsed = new boolean[7]; // Track used rolls (1-6)
        rollUsed[humanRoll] = true;

        System.out.println("Human Player rolled: " + humanRoll);
        
        for (int i = 0; i < 4; i++) {
            int roll;
            do {
            // Simulate dice rolling animation for each bot
            try {
                roll = rollDiceWithAnimation(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
                roll = random.nextInt(6) + 1; // Fallback in case of interruption
            }
            } while (rollUsed[roll]);
            botRolls[i] = roll;
            rollUsed[roll] = true;
            System.out.println("Bot " + (i + 1) + " rolled: " + roll);
        }

        // Display dice roll results
        System.out.println("Human Player rolled: " + humanRoll);
        for (int i = 0; i < 4; i++) {
            System.out.println("Bot " + (i + 1) + " rolled: " + botRolls[i]);
        }

        // Determine picking order
        int[] rolls = new int[5];
        rolls[0] = humanRoll;
        System.arraycopy(botRolls, 0, rolls, 1, 4);

        Integer[] order = new Integer[5];
        for (int i = 0; i < 5; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Integer.compare(rolls[a], rolls[b]));

        System.out.println("Picking order:");
        for (int i = 0; i < 5; i++) {
            if (order[i] == 0) {
            System.out.println((i + 1) + ": Human Player");
            } else {
            System.out.println((i + 1) + ": Bot " + order[i]);
            }
        }
        int activePlayers = 5;
        // Picking process  
        while (activePlayers > 1) { // Continue until one player remains
            for (int i = 0; i < order.length; i++) {
                int currentPlayer = order[i];
        
                // Skip if the current player has no cards
                if (currentPlayer == 0) {
                    if (humanHand.length == 0) continue;
                } else {
                    if (botHands[currentPlayer - 1].length == 0) continue;
                }
        
                // 🛑 **Fix: Stop game immediately when only one player remains**
                if (activePlayers == 1) {
                    System.out.println("Only one player remains. Ending game...");
                    break;
                }
        
                // Find the next player with cards
                int targetIndex = (i + 1) % order.length;
                int targetPlayer = order[targetIndex];
        
                // Keep looking for a player with cards if the current target has none
                int searchCount = 0;
                while ((targetPlayer == 0 && humanHand.length == 0) || 
                       (targetPlayer != 0 && botHands[targetPlayer - 1].length == 0)) {
                    
                    // Move to the next player in the order
                    targetIndex = (targetIndex + 1) % order.length;
                    targetPlayer = order[targetIndex];
        
                    searchCount++;
                    if (searchCount >= order.length) {
                        // 🛑 **Fix: If no valid targets remain, end the game**
                        System.out.println("No other players with cards found!");
                        activePlayers = 1;
                        break;
                    }
                }
        
               
        
                // 🛑 **Fix: Prevent the last player from picking a card from themselves**
                if (activePlayers == 1) {
                    System.out.println("Only one player remains. Ending game...");
                    break;
                }
                 // 🛑 **Fix: Prevent the last player from picking from themselves**
                if (currentPlayer == targetPlayer) {
                    System.out.println("No valid opponents left. Ending game...");
                    activePlayers = 1;
                    break;
                }
                String pickedCard = "";
        
                // 🔹 Continue with picking logic...
        
        
                // 🔹 Existing picking logic continues...
        
                
                // Display both players' hands before picking
                System.out.println("\nPicking Player: " + (currentPlayer == 0 ? "Human Player" : "Bot " + currentPlayer));
                printCard(currentPlayer == 0 ? humanHand : botHands[currentPlayer - 1], true);
                
                System.out.println("Opponent: " + (targetPlayer == 0 ? "Human Player" : "Bot " + targetPlayer));
                printCard(targetPlayer == 0 ? humanHand : botHands[targetPlayer - 1], true);
                
                if (currentPlayer == 0) {
                    // Human picks manually
                    if (targetPlayer == 0) {
                        // Shouldn't happen with proper checks above
                        System.out.println("Error: Human cannot pick from themselves!");
                        continue;
                    } else {
                        System.out.print("Enter index (0-" + (botHands[targetPlayer - 1].length - 1) + "): ");
                        int pickIndex = scanner.nextInt();
                        pickedCard = botHands[targetPlayer - 1][pickIndex];
                        botHands[targetPlayer - 1] = removeCard(botHands[targetPlayer - 1], pickIndex);
                    }
                } else {
                    // Bot picks using shift cipher but follows opponent order
                    int botIndex = (currentPlayer - 1) % 4;
                    
                    if (targetPlayer == 0) {
                        // If target is human player
                        int pickIndex = botIndex % humanHand.length;
                        pickedCard = humanHand[pickIndex];
                        humanHand = removeCard(humanHand, pickIndex);
                    } else {
                        // If target is another bot
                        int pickIndex = botIndex % botHands[targetPlayer - 1].length;
                        pickedCard = botHands[targetPlayer - 1][pickIndex];
                        botHands[targetPlayer - 1] = removeCard(botHands[targetPlayer - 1], pickIndex);
                    }
                    
                    System.out.println("Bot " + currentPlayer + " picked " + pickedCard + " from " + (targetPlayer == 0 ? "Human Player" : "Bot " + targetPlayer));
                }
                
                // Add picked card to the picking player's hand
                if (currentPlayer == 0) {
                    humanHand = addCard(humanHand, pickedCard);
                    printCard(humanHand, true);
                } else {
                    botHands[currentPlayer - 1] = addCard(botHands[currentPlayer - 1], pickedCard);
                    printCard(botHands[currentPlayer - 1], true);
                }
                
                // Check for duplicate pairs based on both rank and suit
                boolean duplicateFound = false;
                if (currentPlayer == 0) {
                    String[] updatedHand = removePairs(humanHand);
                    duplicateFound = updatedHand.length < humanHand.length;
                    humanHand = updatedHand;
                } else {
                    String[] updatedHand = removePairs(botHands[currentPlayer - 1]);
                    duplicateFound = updatedHand.length < botHands[currentPlayer - 1].length;
                    botHands[currentPlayer - 1] = updatedHand;
                }
                
                // Display message about duplicate
                if (duplicateFound) {
                    System.out.println("Duplicate found: " + pickedCard + ". Both cards removed!");
                    System.out.println(getCardASCII(pickedCard));
                } else {
                    System.out.println("No duplicate found. " + pickedCard + " added to hand.");
                }
                
                // Check if a player wins and adjust order
                if ((currentPlayer == 0 && humanHand.length == 0) || 
                    (currentPlayer != 0 && botHands[currentPlayer - 1].length == 0)) {
                    
                    System.out.println((currentPlayer == 0 ? "Human Player" : "Bot " + currentPlayer) + 
                                    " has finished and has no cards left.");
                    
                    order = removePlayerFromOrder(order, currentPlayer);
                    activePlayers--;
                    if (activePlayers == 1) break; // Stop if only one player remains
                }
            }
        }
                            // End the game properly when only one player remains
                if (activePlayers == 1) {
                    System.out.println("\nGame Over! The last player with the Monkey Card loses.");

                    // Find the last player (human or bot)
                    int lastPlayer = -1;
                    if (humanHand.length == 1) {
                        lastPlayer = 0;
                        System.out.println("Human Player is the last with one card.");
                    } else {
                        for (int i = 0; i < botHands.length; i++) {
                            if (botHands[i].length == 1) {
                                lastPlayer = i + 1;
                                System.out.println("Bot " + lastPlayer + " is the last with one card.");
                                break;
                            }
                        }
                    }

                    // Display the Monkey Card
                    System.out.println("\nThe Monkey Card is:");
                    System.out.println(getCardASCII(chosenCard));

                    // Announce the loser
                    if (lastPlayer == 0) {
                        System.out.println("\nYou got the Monkey Card! You lose!");
                    } else {
                        System.out.println("\nBot " + lastPlayer + " got the Monkey Card and loses!");
                    }

                    return; // **Exit the game to prevent further looping**
                }



            // Display winning order
            System.out.println("\nWinning Order:");
            for (int i = 0; i < order.length; i++) {
                System.out.println((i + 1) + ": " + (order[i] == 0 ? "Human Player" : "Bot " + order[i]));
            }
        
        
        

    }
    // Method to count the number of active players
    static int activePlayers(String[] humanHand, String[][] botHands) {
        int count = humanHand.length > 0 ? 1 : 0; // Count human if they have cards

        for (String[] botHand : botHands) {
            if (botHand.length > 0) count++; // Count bots that still have cards
        }

        return count;
    }

   
   // Method to remove a player from the picking order when they finish
    static Integer[] removePlayerFromOrder(Integer[] order, int finishedPlayer) {
        Integer[] newOrder = new Integer[order.length - 1];
        int index = 0;

        for (int player : order) {
            if (player != finishedPlayer) {
                newOrder[index++] = player; // Keep only active players
            }
        }

        return newOrder;
    }

    // Helper method to remove a card from an array
    static String[] removeCard(String[] hand, int index) {
        String[] newHand = new String[hand.length - 1];
        int newIndex = 0;
        for (int i = 0; i < hand.length; i++) {
            if (i != index) {
                newHand[newIndex++] = hand[i];
            }
        }
        return newHand;
    }

    // Helper method to add a card to an array
    static String[] addCard(String[] hand, String card) {
        String[] newHand = Arrays.copyOf(hand, hand.length + 1);
        newHand[hand.length] = card;
        return newHand;
    }

    // Helper method to remove pairs from a hand
    static String[] removePairs(String[] hand) {
        boolean[] isPaired = new boolean[hand.length];
        int newSize = hand.length;

        for (int i = 0; i < hand.length; i++) {
            for (int j = i + 1; j < hand.length; j++) {
                if (!isPaired[i] && !isPaired[j] && hand[i].substring(0, hand[i].length() - 1).equals(hand[j].substring(0, hand[j].length() - 1))) {
                    isPaired[i] = isPaired[j] = true;
                    newSize -= 2;
                    break;
                }
            }
        }

        String[] newHand = new String[newSize];
        int newIndex = 0;
        for (int i = 0; i < hand.length; i++) {
            if (!isPaired[i]) {
                newHand[newIndex++] = hand[i];
            }
        }
        return newHand;
    }

    // Helper method to check if all bots have finished
    static boolean checkBotsWin(String[][] botHands) {
        for (String[] botHand : botHands) {
            if (botHand.length > 0) {
                return false;
            }
        }
        return true;
    }
 
     // Dice rolling animation
     private static int rollDiceWithAnimation(Random random) throws InterruptedException {
        String[] diceFaces = {
            "┌───────┐\n│       │\n│   ●   │\n│       │\n└───────┘", // 1
            "┌───────┐\n│ ●     │\n│       │\n│     ● │\n└───────┘", // 2
            "┌───────┐\n│ ●     │\n│   ●   │\n│     ● │\n└───────┘", // 3
            "┌───────┐\n│ ●   ● │\n│       │\n│ ●   ● │\n└───────┘", // 4
            "┌───────┐\n│ ●   ● │\n│   ●   │\n│ ●   ● │\n└───────┘", // 5
            "┌───────┐\n│ ●   ● │\n│ ●   ● │\n│ ●   ● │\n└───────┘"  // 6
        };

        int finalRoll = random.nextInt(6) + 1;

        for (int i = 0; i < 10; i++) {  // Simulate rolling animation
            clearScreen();
            System.out.println("Rolling: ");
            System.out.println(diceFaces[random.nextInt(6)]);
            Thread.sleep(200);  // Delay to create animation effect
        }

        clearScreen();
        System.out.println("Final Roll: ");
        System.out.println(diceFaces[finalRoll - 1]);

        return finalRoll;
    }
}