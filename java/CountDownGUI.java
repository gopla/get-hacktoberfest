/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modul6;

/**
 *
 * @author TOSHIBA
 */
//nomor 3
import javax.swing.*;
import java.awt.*;

class CountDownGUI extends JFrame {
    JLabel label;

    CountDownGUI(String title) {
        super(title);
        label=new JLabel("Start count!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new Panel(),BorderLayout.WEST);
        getContentPane().add(label);
        setSize(300,300);
        setVisible(true);
    }
    void startCount(){
        try{
            for (int i = 10; i > 0; i--) {
                Thread.sleep(1000);
                label.setText(i+ "");
            }
            Thread.sleep(1000);
            label.setText("Count down complete.");
            Thread.sleep(1000);
        }catch(InterruptedException ie){
        }
        label.setText(Thread.currentThread().toString());
    }
    public static void main(String[] args) {
        CountDownGUI cdg = new CountDownGUI("Count down GUI");
        cdg.startCount();
    }
    
}
