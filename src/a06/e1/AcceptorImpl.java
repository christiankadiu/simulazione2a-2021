package a06.e1;
import java.util.Optional;


public class AcceptorImpl<E,R> implements Acceptor<E,R>{

    Optional<R> res;
    
    public boolean accept(E e){
        if (this.end() != null){
            return true;
        }
        return false;
    }

    public Optional<R> end(){
       return res;
    }

}
