package a06.e1;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;


public class AcceptorFactoryAdvancedImpl implements AcceptorFactory{

    AcceptorFactoryAdvancedImpl(){
    }

    @Override
    public Acceptor<String, Integer> countEmptyStringsOnAnySequence() {
        Acceptor<String, Integer> acceptor = new AcceptorImpl<>(){

            Optional<Integer> res = Optional.of(0);
            int count = 1;
            @Override
            public boolean accept(String e){
                if (e.isEmpty()){
                    res = Optional.ofNullable(count);
                    count++;
                }
                return true;
            }
            @Override
            public Optional<Integer> end(){
                return res;
            }
        };
        return acceptor;
    }

    @Override
    public Acceptor<Integer, String> showAsStringOnlyOnIncreasingSequences() {
        Acceptor<Integer, String> acceptor = new AcceptorImpl<>(){

            Optional<String> res = Optional.ofNullable(null);
            int currentValue = 0;
            String s = "";
            boolean can = true;
            String sep;
            @Override
            public boolean accept(Integer e){
                if (can){
                    currentValue = e;
                    can = false;
                }
                if (e >= currentValue){
                    switch(s){
                        case "":  sep = ""; break;
                        default: sep = ":";
                    }
                    s = s+sep+Integer.toString(e);
                    res = Optional.of(s);
                    currentValue = e;
                    return true;
                }else{
                    res = Optional.ofNullable(null);
                    return false;
                }
            }
            @Override
            public Optional<String> end(){
                return res;
            }
        };
        return acceptor;
    }

    @Override
    public Acceptor<Integer, Integer> sumElementsOnlyInTriples() {
        Acceptor<Integer, Integer> acceptor = new AcceptorImpl<>(){

            Optional<Integer> res = Optional.ofNullable(null);
            int count = 0;
            int somma = 0;
            @Override
            public boolean accept(Integer e){
                if (count >= 3){
                    count++;
                    return false;
                }
                count++;
                somma = somma + e;
                res = Optional.of(somma);
                return true;
            }

            @Override
            public Optional<Integer> end(){
                if (count != 3){
                    return Optional.ofNullable(null);
                }else{
                    return res;
                }
            }
        };
        return acceptor;
    }

    @Override
    public <E, O1, O2> Acceptor<E, Pair<O1, O2>> acceptBoth(Acceptor<E, O1> a1, Acceptor<E, O2> a2) {
        Acceptor<E, Pair<O1, O2>> acceptor = new AcceptorImpl<>(){

            Optional<Pair<O1, O2>> ris;
            @Override
            public boolean accept(E e){
                if (a1.accept(e) && a2.accept(e)){
                    if (a1.end().isPresent() && a2.end().isPresent()){
                        ris = Optional.ofNullable(new Pair<O1,O2>(a1.end().get(), a2.end().get()));
                        return true;
                    }else{
                        ris = Optional.ofNullable(null);
                        return true;
                    }
                }else{
                    ris = Optional.ofNullable(null);
                    return false;
                }
            }
            @Override
            public Optional<Pair<O1, O2>> end(){
                return ris;
            }
        };
        return acceptor;
    }

    @Override
    public <E, O, S> Acceptor<E, O> generalised(S initial, BiFunction<E, S, Optional<S>> stateFun,
            Function<S, Optional<O>> outputFun) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generalised'");
    }
    
}
