package Main;
import plainText.*;
import textFiles.*;
import mediaFiles.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;  
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
import java.security.*;

import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.*;

public class App extends JFrame{
    
    
    public static final String alpha ="abcdefghijklmnopqrstuvwxyz";
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public void initialize(){
        //main font of the window
        final Font mainFont = new Font("Sogeo rpint",Font.BOLD,18);
        JTextField tfplainText,tfplainKey;
        JLabel lboutput;
        //form panel
        JLabel lbplainText = new JLabel("Encrypt plain Text");
        lbplainText.setFont(mainFont);
        tfplainText = new JTextField();
        tfplainText.setFont(mainFont);
        
        JLabel lbplainKey = new JLabel("Enter key for plain text encryption: ");
        lbplainKey.setFont(mainFont);
        lbplainText.setFont(mainFont);
        tfplainKey = new JTextField();
        tfplainKey.setFont(mainFont);


        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4,1,5,5));
        formPanel.add(lbplainText);
        formPanel.add(tfplainText);
        formPanel.add(lbplainKey);
        formPanel.add(tfplainKey);
        //buttons
        // 1.Text Files
        JButton btnTextFiles = new JButton("Text Files");
        btnTextFiles.setFont(mainFont);
        btnTextFiles.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JFileChooser j = new JFileChooser();
                j.setCurrentDirectory(new File("D:\\College\\SY\\Sem-1\\OOP\\Course Project\\Encryption-Decryption\\src\\File Encrytion"));
                int response = j.showOpenDialog(null);
                if(response == JFileChooser.APPROVE_OPTION){
                     File file = new File(j.getSelectedFile().getAbsolutePath());
                     String textFilePath = j.getSelectedFile().getAbsolutePath();
                    System.out.println(file);
                    
                }

                
            }

        });

        //2. Encrypt PlainText
        lboutput  = new JLabel();
        lboutput.setFont(mainFont);
        JButton btnPlainText = new JButton("Encrypt plain Text");
        btnPlainText.setFont(mainFont);
        btnPlainText.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // Encrypt
                 
                    try (Scanner sc = new Scanner(System.in)) {
                        String message = new String();
                        
                        message = tfplainText.getText();
                        
                        int key = Integer.parseInt(tfplainKey.getText());
                        
                        message = message.toLowerCase();
                        String cipherText = "";
                        for (int ii = 0; ii < message.length(); ii++) {
                            int charPosition = alpha.indexOf(message.charAt(ii));
                            int keyVal = (key + charPosition) % 26;
                            char replaceVal = alpha.charAt(keyVal);
                            cipherText += replaceVal;
                        }

                        //DECRYPTING THE PLAIN TEXT
                        System.out.println("\nEncrpyted msg:" + cipherText);
                        cipherText = cipherText.toLowerCase();
                        message = "";
                        for (int ii = 0; ii < cipherText.length(); ii++) {
                            int charPosition = ALPHABET.indexOf(cipherText.charAt(ii));
                            int keyVal = (charPosition - key) % 26;
                            if (keyVal < 0) {
                                keyVal = ALPHABET.length() + keyVal;
                            }
                            char replaceVal = ALPHABET.charAt(keyVal);
                            message += replaceVal;
                        }
                        System.out.println("Decrypted Message: "+message);
                        System.out.println("\n\n");
                        lboutput.setText("Encrypted message: " + cipherText);
                        lboutput.setText("<html>Encrypted Message: "+cipherText+"<br>"+"Decrypted Text: "+ message + "</html>" );
                        
                    }
            
                }
            

        }); 

        //3. Media Files
        JButton btnMediaFiles = new JButton("Media Files");
        btnMediaFiles.setFont(mainFont);
        btnMediaFiles.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });//end of media files
        


        //buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,5,5));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(btnPlainText);

        
        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));
        mainPanel.add(buttonsPanel,BorderLayout.SOUTH);
        mainPanel.add(formPanel,BorderLayout.NORTH);
        mainPanel.add(lboutput,BorderLayout.CENTER);
        add(mainPanel);

        //set the title of the window
        setTitle("Welcome");
        setSize(500,600);
        setMinimumSize(new Dimension(300,400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        

    }


    public static void main(String[] args) {
        App a = new App();
        a.initialize();
    
}

}

