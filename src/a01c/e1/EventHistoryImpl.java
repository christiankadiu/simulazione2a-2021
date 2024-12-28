package a01c.e1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class EventHistoryImpl<E> implements EventHistory<E>{

    TreeMap<Double, E> storia;
    Map.Entry<Double, E> currentEntry;

    EventHistoryImpl(Map<Double, E> mappa){
        storia = new TreeMap<Double, E>(mappa); 
        this.currentEntry = storia.firstEntry();
    }

    @Override
    public double getTimeOfEvent() {
        return this.currentEntry.getKey();
    }

    @Override
    public E getEventContent() {
        return this.currentEntry.getValue();
    }

    @Override
    public boolean moveToNextEvent() {
        if (storia.higherEntry(currentEntry.getKey()) != null){
            this.currentEntry = storia.higherEntry(this.currentEntry.getKey());
            System.out.println("la entry corrente e: "+this.currentEntry);
            return true;
        }
        return false;
    }
    
}
