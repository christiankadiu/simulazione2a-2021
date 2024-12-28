package a01c.e2;
import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics{

        int size;
        List<List<Boolean>> celle;
        Pair<Integer, Integer> currentPos;

        LogicsImpl(int size){
            this.size = size;
            celle = new ArrayList<>();
            for (int i = 0; i < this.size; i++){
                List<Boolean> row = new ArrayList<>();
                celle.add(row);
                for (int k = 0; k < this.size; k++){
                    row.add(false);
                }
            }
            print();
        }

        void print(){
            for (int i = 0; i < this.size; i++){
                for (int k = 0; k < this.size; k++){
                    System.out.print(celle.get(i).get(k)+"\t");
                }
                System.out.println("\t");
            }
            System.out.println("\t");
        }

        void printList(List<Pair<Integer, Integer>> lista){
            for (Pair<Integer,Integer> pair : lista) {
                System.out.print("("+pair.getX()+", "+pair.getY()+")"+"\t");
            }
            System.out.println();
        }


        private Boolean isAnyActive(){
            for (List<Boolean> list : this.celle) {
                for (Boolean boolean1 : list) {
                    if (boolean1){
                        return true;
                    }
                }
            }
            return false;
        }

        private List<Pair<Integer, Integer>> compute(Pair<Integer, Integer> pos){
            List<Pair<Integer, Integer>> posizioni = new ArrayList<>();
            if (pos.getY() > currentPos.getY()){
                for (int i = currentPos.getY(); i <= pos.getY(); i++){
                    this.celle.get(pos.getX()).set(i, true);
                    posizioni.add(new Pair<Integer,Integer>(pos.getX(), i));
                }
            }else if (pos.getY() < currentPos.getY()){
                for (int i = pos.getY(); i <= currentPos.getY(); i++){
                    this.celle.get(pos.getX()).set(i, true);
                    posizioni.add(new Pair<Integer,Integer>(pos.getX(), i));
                }
            }else if(pos.getX() > currentPos.getX()){
                for (int i = currentPos.getX(); i <= pos.getX(); i++){
                    this.celle.get(pos.getY()).set(i, true);
                    posizioni.add(new Pair<Integer,Integer>(i, pos.getY()));
                }
            }else if(pos.getX() <= currentPos.getX()){
                for (int i = pos.getX(); i < currentPos.getX(); i++){
                    this.celle.get(pos.getY()).set(i, true);
                    posizioni.add(new Pair<Integer,Integer>(i, pos.getY()));
                }
            }
            return posizioni;
        }



        @Override
        public List<Pair<Integer, Integer>> get(Pair<Integer,Integer> pos) {
            List<Pair<Integer, Integer>> lista = new ArrayList<>();
            lista.add(pos);
            if (isAnyActive()){
                List<Pair<Integer,Integer>> l = new ArrayList<>(compute(pos));
                printList(l);
                this.currentPos = pos;
                System.out.println("la posizione corrente e: "+currentPos.getX() + currentPos.getY());
                print();
                return l;
            }else{
                this.celle.get(pos.getX()).set(pos.getY(), true);
                currentPos = pos;
                System.out.println("la posizione corrente e: "+currentPos.getX() + currentPos.getY());
                return lista; 
            }
        }

        
}
