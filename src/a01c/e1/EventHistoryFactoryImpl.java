package a01c.e1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class EventHistoryFactoryImpl implements EventHistoryFactory{

    @Override
    public <E> EventHistory<E> fromMap(Map<Double, E> map) {
        EventHistoryImpl<E> history = new EventHistoryImpl<>(map);
        return history;
    }

    @Override
    public <E> EventHistory<E> fromIterators(Iterator<Double> times, Iterator<E> content) {
        Map<Double, E> mappa = new TreeMap<>();
        while (times.hasNext() && content.hasNext()){
            mappa.put(times.next(), content.next());
        }
        EventHistoryImpl<E> history = new EventHistoryImpl<>(mappa);
        return history;
    }

    @Override
    public <E> EventHistory<E> fromListAndDelta(List<E> content, double initial, double delta) {
        int i = 0;
        Map<Double, E> mappa = new TreeMap<>();
        for (E e : content) {
            mappa.put(initial + (delta * i), e);
            i++;
        }
        EventHistoryImpl<E> history = new EventHistoryImpl<>(mappa);
        return history;
    }

    @Override
    public <E> EventHistory<E> fromRandomTimesAndSupplier(Supplier<E> content, int size) {
        Map<Double, E> mappa = new TreeMap<>();
        for (int i = 0; i < size; i++){
            mappa.put(Math.random(), content.get());
        }
        EventHistoryImpl<E> history = new EventHistoryImpl<>(mappa);
        return history;
    }

    @Override
    public EventHistory<String> fromFile(String file) throws IOException {
        Map<Double, String> mappa = new TreeMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.read() != -1){
                String[] params = br.readLine().split(":");
                double time = Double.parseDouble(params[0]);
                String event = params[1];
                mappa.put(time, event);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        EventHistoryImpl<String> history = new EventHistoryImpl<>(mappa);
        return history;
    }
    
}
