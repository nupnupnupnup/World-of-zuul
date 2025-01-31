public class Game extends Parser {
    private Parser parser;
    private Room currentRoom;

    public Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room outside, theatre, pub, lab, office;

        // Create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // Initialize room exits
        outside.setExits(null, theatre, lab, pub);
        theatre.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, null, office, null);
        office.setExits(null, lab, null, null);

        currentRoom = outside;  // Start game outside
    }

    public void play() {
        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Goodbye!");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        System.out.print("north ");
        System.out.print("east ");
        System.out.print("south ");
        System.out.print("west ");
        System.out.println();
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You wander around at the university.");
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = null;

        if (direction.equals("north")) {
            nextRoom = currentRoom.getNorthExit();
        } else if (direction.equals("east")) {
            nextRoom = currentRoom.getEastExit();
        } else if (direction.equals("south")) {
            nextRoom = currentRoom.getSouthExit();
        } else if (direction.equals("west")) {
            nextRoom = currentRoom.getWestExit();
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            if (currentRoom.getNorthExit() != null) {
                System.out.print("north ");
            }
            if (currentRoom.getEastExit() != null) {
                System.out.print("east ");
            }
            if (currentRoom.getSouthExit() != null) {
                System.out.print("south ");
            }
            if (currentRoom.getWestExit() != null) {
                System.out.print("west ");
            }
            System.out.println();
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}
