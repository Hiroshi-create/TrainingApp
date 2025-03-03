package editor;

import instance.Trade;

import java.util.Comparator;

public class Sort implements Comparator<Trade> {
    public int compare(Trade a, Trade b) {
        return b.getDateTime().compareTo(a.getDateTime());
    }
}
