package com.mycompany.muratkeskintombolagame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Random;
import java.util.Scanner;

public class MuratKeskinBingoGame {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter 1 for manually added matrixes(2 players): ");
        System.out.println("Enter 2 or more for number of players to play randomly");
        System.out.println("if you want to change matrices manually, you can do this in main class.");
        System.out.println("Also, if you want to change the drawn number range, you can do this in the playBingo method at the beginning.");
        int num = scanner.nextInt();

        if (num == 1) {
            // Manually defined cards for demonstration purposes
            int[][] manualCard1 = {
                {-1, 10, 20, 34, -1, 54, -1, 73, -1},
                {4, -1, 22, -1, 41, -1, 66, -1, 86},
                {-1, 14, -1, 39, -1, 57, 67, 76, -1}
            };

            int[][] manualCard2 = {
                {-1, 15, 24, 36, -1, 56, -1, 77, -1},
                {4, -1, 28, -1, 41, -1, 62, -1, 84},
                {-1, 18, -1, 37, -1, 52, 64, 73, -1}
            };

            startGame(2, new int[][][]{manualCard1, manualCard2});
            playBingo(new int[][][]{manualCard1, manualCard2});
        } else {
            MuratKeskinMultiLinkedList[] playerCards = startGame(num); // Generate and convert cards for players
            playBingo(playerCards); // Play the game with these cards

        }

    }

    //methods for randomly generated cards
    public static MuratKeskinMultiLinkedList[] startGame(int numberOfPlayers) {
        MuratKeskinMultiLinkedList[] playerCards = new MuratKeskinMultiLinkedList[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            int[][] card = generateBingoCard();
            playerCards[i] = convertArrayToLinkedList(card); // Convert card to MultiLinkedList
        }
        return playerCards;
    }

    public static void playBingo(MuratKeskinMultiLinkedList[] playerCards) {
        int[] numbers = generatePermutation(90);

        int[] bingoCounts = new int[playerCards.length];

        for (int number : numbers) {
            System.out.println("\nDrawing number: " + number);

            for (int i = 0; i < playerCards.length; i++) {
                if (bingoCounts[i] >= 3) {
                    continue;
                }

                markNumberInLinkedList(playerCards[i], number);

                // Print the player's card state immediately after marking the number.
                System.out.println("Player " + (i + 1) + "'s card after drawing " + number + ":");
                printLinkedListCard(playerCards[i]);
            }

            // After printing all card states, check for and announce bingos.
            for (int i = 0; i < playerCards.length; i++) {
                if (bingoCounts[i] >= 3) {
                    continue;
                }

                // Check for bingo after marking the number and printing the card state.
                int currentBingoCount = checkForBingo(playerCards[i]);
                if (currentBingoCount > bingoCounts[i]) {
                    bingoCounts[i] = currentBingoCount;
                    System.out.println("Player " + (i + 1) + " achieves " + ordinal(currentBingoCount) + " Bingo!");
                    
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine(); 
                    if (bingoCounts[i] == 3) {
                        // End the game when any player achieves 3 Bingos.
                        System.out.println("Game Over. Player " + (i + 1) + " wins with 3 Bingos!");
                        return;
                    }
                }
            }
        }

        System.out.println("Game Over. No player achieved 3 Bingos.");
    }

    private static String ordinal(int number) {
        if (number >= 11 && number <= 13) {
            return number + "th";
        }
        switch (number % 10) {
            case 1:
                return number + "st";
            case 2:
                return number + "nd";
            case 3:
                return number + "rd";
            default:
                return number + "th";
        }
    }

    public static int[][] generateBingoCard() {
        int[][] card = new int[3][9]; // 3 rows and 9 columns
        Random rand = new Random();

        for (int col = 0; col < 9; col++) {
            int[] columnNumbers = new int[10];
            for (int j = 0; j < 10; j++) {
                columnNumbers[j] = col == 0 ? 1 + j : col * 10 + j;
            }

            // Simple shuffle for column numbers
            for (int j = 0; j < columnNumbers.length; j++) {
                int randomIndexToSwap = rand.nextInt(columnNumbers.length);
                int temp = columnNumbers[randomIndexToSwap];
                columnNumbers[randomIndexToSwap] = columnNumbers[j];
                columnNumbers[j] = temp;
            }

            // Fill the card with the first 3 numbers from the shuffled column
            for (int row = 0; row < 3; row++) {
                card[row][col] = columnNumbers[row];
            }
        }

        // Randomly select 4 numbers to be replaced with -1 in each row
        for (int row = 0; row < 3; row++) {
            int[] positions = generateRandomPositions();
            for (int pos : positions) {
                card[row][pos] = -1; // Mark as blank
            }
        }

        return card;
    }

    public static int[] generateRandomPositions() {
        Random rand = new Random();
        int[] positions = new int[4];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = rand.nextInt(9); // Random column index
            // Ensure uniqueness
            for (int j = 0; j < i; j++) {
                if (positions[i] == positions[j]) {
                    i--; // Redo if duplicate
                    break;
                }
            }
        }
        return positions;
    }

    public static void printCard(int[][] card) {
        for (int[] row : card) {
            for (int number : row) {
                if (number == -1) {
                    System.out.print("XX ");
                } else {
                    System.out.printf("%2d ", number);
                }
            }
            System.out.println();
        }
    }

    public static int[] generatePermutation(int n) {
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }

        Random rand = new Random();
        for (int i = 0; i < numbers.length; i++) {
            int randomIndexToSwap = rand.nextInt(numbers.length);
            int temp = numbers[randomIndexToSwap];
            numbers[randomIndexToSwap] = numbers[i];
            numbers[i] = temp;
        }

        return numbers;
    }

    public static void generateRandomNumber() {
        // Generate and print random permutation
        int[] randomPermutation = generatePermutation(90);
        System.out.println("Random Permutation:");
        for (int num : randomPermutation) {
            System.out.print(num + " ");
        }
        System.out.println(); // New line after printing all numbers

    }

    //methods for manuel cards 
    public static void startGame(int numberOfPlayers, int[][][] manualCards) {
        for (int i = 0; i < numberOfPlayers; i++) {
            // Use manual cards for the first two players, generate new cards for others but it doesnt make any sense to do this 
            // because we can play just with two players in manuel way
            int[][] card;
            if (i < manualCards.length) {
                card = manualCards[i];
            } else {
                card = generateBingoCard();
            }
            System.out.println("Card " + (i + 1) + ":");
            printCard(card);

            // Create a MultiLinkedList and populate it with unblocked numbers from this card
            MuratKeskinMultiLinkedList list = new MuratKeskinMultiLinkedList();
            for (int row = 0; row < card.length; row++) {
                for (int col = 0; col < card[row].length; col++) {
                    if (card[row][col] != -1) { // If the number is not a blank
                        list.append(card[row][col]);
                    }
                }
            }

            // to show multi-linked list
            System.out.println("Unblocked numbers in MultiLinkedList for Card " + (i + 1) + ":");
            list.printList();
            System.out.println();
        }
    }

    public static void playBingo(int[][][] cards) {
        int[] drawnNumbers = generatePermutation(90);
        int[] playerBingos = new int[cards.length]; // Track the number of bingos for each player
        //method to convert 2D array cards to MultiLinkedList representations
        MuratKeskinMultiLinkedList[] linkedLists = new MuratKeskinMultiLinkedList[cards.length];

        // Initialize linkedLists for each player based on their 2D array card
        for (int i = 0; i < cards.length; i++) {
            linkedLists[i] = convertArrayToLinkedList(cards[i]);
        }

        for (int drawnNumber : drawnNumbers) {
            System.out.println("\nNumber drawn: " + drawnNumber);

            for (int playerIndex = 0; playerIndex < cards.length; playerIndex++) {
                //method to mark a number in the MultiLinkedList representation
                markNumberInLinkedList(linkedLists[playerIndex], drawnNumber);
                System.out.println("Player " + (playerIndex + 1) + "'s Card:");
                //method to print the card from its MultiLinkedList representation
                printLinkedListCard(linkedLists[playerIndex]);
                int newBingoCount = checkForBingo(linkedLists[playerIndex]);

                if (newBingoCount > playerBingos[playerIndex]) {
                    for (int b = playerBingos[playerIndex] + 1; b <= newBingoCount; b++) {
                        System.out.println("Player " + (playerIndex + 1) + "'s " + b + " Bingo!");
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine(); // Wait for user to press Enter to continue
                    }
                    playerBingos[playerIndex] = newBingoCount;

                    if (newBingoCount == 3) {
                        System.out.println("Player " + (playerIndex + 1) + " wins with 3 Bingos!");
                        System.out.println("Game Over.");
                        return;
                    } 
                }
            }
        }
        System.out.println("Game Over. No player achieved 3 Bingos.");
    }

    public static int checkForBingo(MuratKeskinMultiLinkedList card) {
        int bingoCount = 0; // Initialize bingo counter
        MuratKeskinMultiLinkedNode rowStart = card.head; // Start with the first node, assuming it's the start of a row

        while (rowStart != null) {
            int markedCount = 0; // Counter for marked numbers in the current row
            MuratKeskinMultiLinkedNode currentNode = rowStart;

            // Iterate through nodes in the current row
            while (currentNode != null) {
                if (currentNode.marked) { // Check if the node is marked
                    markedCount++; // Increment if the number is marked
                }
                currentNode = currentNode.next; // Move to the next node in the row
            }

            if (markedCount == 5) { // Assuming a row needs 5 marked numbers for a Bingo
                bingoCount++; // Increment bingo count if a full row is marked
            }

            rowStart = rowStart.child; // Move to the start of the next row
        }

        return bingoCount; // Return the total count of bingos found
    }

    private static MuratKeskinMultiLinkedList convertArrayToLinkedList(int[][] card) {
        MuratKeskinMultiLinkedList list = new MuratKeskinMultiLinkedList();
        MuratKeskinMultiLinkedNode lastRowStart = null;

        for (int[] row : card) {
            MuratKeskinMultiLinkedNode previousNode = null;
            MuratKeskinMultiLinkedNode rowStartNode = null;

            for (int value : row) {
                if (value == -1) {
                    continue;
                }
                MuratKeskinMultiLinkedNode newNode = new MuratKeskinMultiLinkedNode(value);
                if (list.head == null) {
                    list.head = newNode;
                    lastRowStart = newNode;
                    rowStartNode = newNode;
                } else if (previousNode == null) {
                    lastRowStart.child = newNode;
                    lastRowStart = newNode;
                    rowStartNode = newNode;
                } else {
                    previousNode.next = newNode;
                }
                previousNode = newNode;
            }

            if (rowStartNode == null && lastRowStart != null) {
                lastRowStart.child = new MuratKeskinMultiLinkedNode(-1);
                lastRowStart = lastRowStart.child;
            }
        }
        return list;
    }

    private static void printLinkedListCard(MuratKeskinMultiLinkedList linkedList) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int minVal = col * 10;
                int maxVal = col == 8 ? 90 : minVal + 9;
                String toPrint = findNumberInRange(linkedList, minVal, maxVal, row);
                System.out.print(toPrint + " ");
            }
            System.out.println();
        }
    }

    private static String findNumberInRange(MuratKeskinMultiLinkedList linkedList, int minVal, int maxVal, int targetRow) {
        int currentRow = 0;
        MuratKeskinMultiLinkedNode rowStart = linkedList.head;

        while (rowStart != null && currentRow <= targetRow) {
            MuratKeskinMultiLinkedNode currentNode = rowStart;
            while (currentNode != null) {
                if (currentNode.data >= minVal && currentNode.data <= maxVal) {
                    if (currentRow == targetRow) { // Found the number in the correct row
                        return currentNode.marked ? "*" : String.format("%2d", currentNode.data);
                    } else {
                        break; // Found the number, but in an earlier row
                    }
                }
                currentNode = currentNode.next;
            }
            rowStart = rowStart.child;
            currentRow++;
        }

        return "XX"; // Number not found in the range for the specified row
    }

    private static void markNumberInLinkedList(MuratKeskinMultiLinkedList linkedList, int drawnNumber) {
        MuratKeskinMultiLinkedNode rowStart = linkedList.head;
        while (rowStart != null) {
            MuratKeskinMultiLinkedNode currentNode = rowStart;
            while (currentNode != null) {
                if (currentNode.data == drawnNumber) {
                    currentNode.marked = true; // Mark the number as selected
                }
                currentNode = currentNode.next; // Move to the next node in the row
            }
            rowStart = rowStart.child; // Move to the start of the next row
        }
    }

}
