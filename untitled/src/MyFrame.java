import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyFrame extends JFrame // наследуемся от класса JFrame для того чтобы не изобретать велосипед
        implements ActionListener { //  имплементирует интерфейс ActionListener (для того чтобы уметь реагировать на событие,
    //    вызываемое  нажатием на кнопки)
public  int count=0; // заводим переменную для подсчета нажатий кнопки no
    JButton jButton; // создаем 2 переменные типа КНОПКА (перевод слова button)
    JButton jButton1;

    public MyFrame() { // вот этот метод выполнится первым после того как мы выделим память под экземпляр нашего класса
        setSize(500, 300); // вызываем метод который задает X Y размеров окна
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // вызываем метод отвечающий за обработку нажатий на крестик окна и указываем как действовать при нажатии на него "DO_NOTHING_ON_CLOSE"

        JLabel label = new JLabel("Do you like java ?"); // создаем надпись с текстом "Do you like java" по умолчанию
        label.setBounds(50,50,250,50); // надпись будет 50 по y относительно главного окна 50 по x,250 в ширину и 50 в высоту

        jButton = new JButton("yes"); //  создаем кнопку с надписью  'yes' по умолчанию
        jButton.setBounds(100, 100, 100, 50); //Параметры : 1-координата по х,2-координата по y,3-ширина,4-высота

         ActionImpl action = new ActionImpl ();// Создаем чувака который отвечает за обработку событий т.к. он имплементирует ActionListener

        jButton1 = new JButton("no"); // аналогично кнопке 'yes'
        jButton1.setBounds(300, 100, 100, 50);


        jButton.addActionListener(action);  // тут мы указываем того,кто будет отвечать за обработку событий
        jButton1.addActionListener(this); // this-ссылка на самого себя,т.е. обработкой событий может заниматься сам класс JFrame т.к. он имплементит ActionListener (cм второй комментарий)

        setLocation(500, 300); // сохраняем координаты главного окна (мы являемся главным окном т.к. Наследуемся от JFrame)
        setLayout(null); // отключаем автоматическое расположение компонентов в главном окне

        add(label); // добавляем в главное окно ссылки на наши кнопки и надпись
        add(jButton1);
        add(jButton);

        setVisible(true); // делаем наше окно видимым на экране

    }

    class ActionImpl implements ActionListener { // класс внутри класса,да в жабе так можно
        // реализуем метод actionPerformed интерфейса ActionListener
    @Override
        public void actionPerformed(ActionEvent e) {
        // чуть выше мы указали,что на кнопку "да" будет реагировать объект именно этого класса
      //   ActionImpl action = new ActionImpl (); jButton.addActionListener(action);
        System.exit(0); // вырубаем программу если нажали да
    }
}
        @Override
        public void actionPerformed(ActionEvent e) { // аналогично (выше)

        setLocation((int)(Math.random()*500),(int)(Math.random()*500));//сохраняем новые координаты окна
            count++; // увеличиваем кол-во нажатий на кнопку
            if(count>3){ // если юзер нажал на кнопку больше трех раз,то средствами java
                // приказываем операционке лечь спать
                try {
                    Runtime.getRuntime().exec("shutdown-s");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
