package a01c.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map< Pair<Integer, Integer>, JButton> cells = new HashMap<>();
    Logics logic;
    
    public GUI(int size) {
        logic = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
            Pair<Integer, Integer> pos = new Pair<Integer,Integer>(null, null);
            for (Map.Entry<Pair<Integer, Integer>, JButton> entry : this.cells.entrySet()) {
                if (entry.getValue().equals(button)){
                    pos = entry.getKey();
                }
            }
            for (Pair<Integer, Integer> p : logic.get(pos)) {
                this.cells.get(p).setText("*");
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(new Pair<Integer,Integer>(i, j), jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}
