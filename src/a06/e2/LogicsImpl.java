package a06.e2;

import java.util.HashMap;
import java.util.Map;

public class LogicsImpl implements Logics{

    Map<Pair<Integer, Integer>, Integer> mappa;
    int size;

    LogicsImpl(int size){
        this.size = size;
        mappa = new HashMap<>();
        for (int i = 0; i < this.size; i++){
            for (int k = 0; k < this.size; k++){
                mappa.put(new Pair<>(i, k), 0);
            }
        }
    }

    @Override
    public int getContent(Pair<Integer, Integer> pos) {
        mappa.replace(pos, 1);
        return 1;
    }
}
