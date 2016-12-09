import java.io.*;

import java.awt.*;

import java.awt.event.*;




import javax.swing.*;




import java.util.List;

import java.util.ArrayList;




public class Recite_student2 extends JFrame {

JLabel lblWord = new JLabel("word");

JLabel lblMeaning = new JLabel("meaning");

Label label1 = new Label("I love learning English!"); // add a label

JButton btn = new JButton("Next Word");

JButton btn2 = new JButton("Last Word");

JButton btn3 = new JButton("Random");




List<String> words = new ArrayList<>();

List<String> meanings = new ArrayList<>();

int current = 0;

int last = current;

int random = 0;




public void init() {

setSize(1000, 100);

setLayout(new FlowLayout());

getContentPane().add(btn);

getContentPane().add(btn2);

getContentPane().add(btn3);

getContentPane().add(lblWord);

getContentPane().add(lblMeaning);

btn.addActionListener(new BtnActionAdapter());

btn2.addActionListener(new BtnActionAdapter2());

btn3.addActionListener(new BtnActionAdapter3());

setDefaultCloseOperation(EXIT_ON_CLOSE);

setVisible(true);

}




class BtnActionAdapter implements ActionListener { // each time the user

// press the button

// 'Next Word', the next

// word will appear in

// the window




@Override

public void actionPerformed(ActionEvent e) {

// TODO Auto-generated method stub

lblWord.setText(words.get(current));

lblMeaning.setText(meanings.get(current));

current++;

System.out.println(current + '\n');

}

}




class BtnActionAdapter2 implements ActionListener { // each time the user

// press the button

// 'Last Word', the last

// word will appear in

// the window

public void actionPerformed(ActionEvent e) {

if (current == 0) {



last = 0;

System.out.println(last);

lblWord.setText(words.get(last));

lblMeaning.setText(meanings.get(last));

System.out.println(current);



} else {

last = current - 1;

System.out.println(last);

lblWord.setText(words.get(last));

lblMeaning.setText(meanings.get(last));

current--;

System.out.println(current);

}




}

}




class BtnActionAdapter3 implements ActionListener { // this is a button to

// disorder the word in

// the next appearance

public void actionPerformed(ActionEvent e) {

random = (int) (Math.random() * words.size());

System.out.println("random :" + random);

lblWord.setText(words.get(random));

lblMeaning.setText(meanings.get(random));

current = random;

System.out.println(current + '\n');

}

}




public void start() {

new Thread(() -> {

try {

readAll();

} catch (IOException ex) {

}

}).start();

}




public void readAll() throws IOException {

String fileName = "College_Grade4.txt";

String charset = "GB2312";

BufferedReader reader = new BufferedReader(new InputStreamReader(

new FileInputStream(fileName), charset));

String line;

while ((line = reader.readLine()) != null) {

line = line.trim();

if (line.length() == 0)

continue;

int idx = line.indexOf("\t");

words.add(line.substring(0, idx));

meanings.add(line.substring(idx + 1));

}

reader.close();

}




public static void main(String[] args) {

Recite f = new Recite();




f.setBackground(Color.GREEN);

f.init();

f.start();

}

}