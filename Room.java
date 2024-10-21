public class Room {
    private String description;
    private Room northExit;
    private Room eastExit;
    private Room southExit;
    private Room westExit;

    public Room(String description) {
        this.description = description;
    }

    public void setExits(Room north, Room east, Room south, Room west) {
        if (north != null) {
            this.northExit = north;
        }
        if (east != null) {
            this.eastExit = east;
        }
        if (south != null) {
            this.southExit = south;
        }
        if (west != null) {
            this.westExit = west;
        }
    }

    public String getDescription() {
        return description;
    }

    public Room getNorthExit() {
        return northExit;
    }

    public Room getEastExit() {
        return eastExit;
    }

    public Room getSouthExit() {
        return southExit;
    }

    public Room getWestExit() {
        return westExit;
    }
}
