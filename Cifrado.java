/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframeslevy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Jorge
 */
public class Cifrado extends JFrame{
    
    private final JTextArea area1;
    private final JButton BottonEncriptar;
    private final JButton BottonDesencriptar;
    private final JTextArea area2;
    
    public static void main(String[] args) {
        Cifrado Ventana = new Cifrado();
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ventana.setVisible(true);

        Toolkit miVentana = Toolkit.getDefaultToolkit();
        Dimension tamañoPantalla = miVentana.getScreenSize();

        int alturaPantalla = tamañoPantalla.height;
        int anchoPantalla = tamañoPantalla.width;

        Ventana.setSize(anchoPantalla / 2, alturaPantalla / 2);
        Ventana.setLocation(anchoPantalla / 4, alturaPantalla / 4);

        Ventana.setResizable(false);

        Image MiIcono = miVentana.getImage("increible.jpg");
        Ventana.setIconImage(MiIcono);

    }
    public Cifrado(){
        super("Clipher Demo");
        setLayout(new FlowLayout());

        area1 = new JTextArea(10, 15);
        add(area1);

        BottonEncriptar = new JButton("Encriptar");
        add(BottonEncriptar);

        BottonDesencriptar = new JButton("Desencriptar");
        add(BottonDesencriptar);

        area2 = new JTextArea(10, 15);
        area2.setEditable(false);
        add(area2);
        BottonEncriptar.addActionListener(new HandlerEncriptar());
        BottonDesencriptar.addActionListener(new HandlerDesencriptar());
    }
    
    private class HandlerEncriptar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            String command = event.getActionCommand();
            String input = area1.getText();
            if (command.equals("Encriptar")) {
                String output = encrypt(input);
                area2.setText(output);
            } 
        }

        private String encrypt(String input) {
          StringBuilder result = new StringBuilder();
          for(int i = 0; i<input.length(); i++){
              char c = input.charAt(i);
              
              if(Character.isLetter(c)){
                  c += 3;
                  
                  if((Character.isLowerCase(input.charAt(i))&& c > 'z')||
                          (Character.isUpperCase(input.charAt(i))&& c > 'Z')){
                      c-=26;
                  }
              }
              result.append(c);
          }
          result.reverse();
          
          int halfLength = result.length() / 2;
          for(int i = halfLength; i < result.length(); i++){
              char c = result.charAt(i);
              c -=1;
              result.setCharAt(i, c);
          }
          return result.toString();
        }
    }

    private class HandlerDesencriptar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            String input = area1.getText();
            if (command.equals("Desencriptar")) {
                String output = decrypt(input);
                area2.setText(output);
            }
        }
        private String decrypt(String input) {
            StringBuilder result = new StringBuilder(input);
            
            int halfLength = input.length()/2;
            for(int i = halfLength; i < input.length(); i++){
                char c = result.charAt(i);
                c += 1;
                result.setCharAt(i, c);
            }
            return null;
        }
    }
}
