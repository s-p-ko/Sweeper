package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;
    }

    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    public void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED:
                setClosedBox(coord);
                break;
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    private void setClosedBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    int getCountClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToClosedBombBox(Coord coord) {
        if (Box.CLOSED == flagMap.get(coord)) {
            flagMap.set(coord, Box.OPENED);
        }
    }

    void setNoBombToFlagedSafeBox(Coord coord) {
        if (Box.FLAGED == flagMap.get(coord)) {
            flagMap.set(coord, Box.NOBOMB);
        }
    }


    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.FLAGED == flagMap.get(around)) {
                count++;
            }
        }
        return count;
    }
}
