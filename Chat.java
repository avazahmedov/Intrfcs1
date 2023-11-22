import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Chat extends JFrame {
    /*Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля, IP-адреса сервера, порта подключения
к серверу, область ввода сообщений, JTextArea область просмотра сообщений чата и JButton подключения к серверу и отправки
сообщения в чат. Желательно сразу сгруппировать компоненты, относящиеся
к серверу сгруппировать на JPanel сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу
     */

    private static final int WINDOW_HIGHT = 400;
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton connect = new JButton("Connect");
    JTextField login = new JTextField();
    JTextField password = new JTextField();
    JTextField ipAddress = new JTextField();
    JTextField message = new JTextField();
    JPanel panelServer = new JPanel(new GridLayout(6, 1));
    JPanel panelClient = new JPanel(new GridLayout(4, 1));
    JLabel loginLabel = new JLabel("Login");
    JLabel passwordLabel = new JLabel("Password");
    JLabel addressLabel = new JLabel("IP Address");
    JLabel messageLabel = new JLabel("Message");
    JTextArea area = new JTextArea();
    String msg;
    FileWriter logFile;

    Chat(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HIGHT);
        setTitle("Chat");
        setResizable(false);
        panelServer.add(loginLabel);
        panelServer.add(login);
        panelServer.add(passwordLabel);
        panelServer.add(password);
        panelServer.add(addressLabel);
        panelServer.add(ipAddress);
        panelClient.add(messageLabel);
        panelClient.add(message);
        panelClient.add(area);
        panelClient.add(connect);

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    logFile = new FileWriter("messages.txt", true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                msg = login.getText() + ": " + message.getText() + "\n";
                area.append(msg);
                System.out.println("Message sent" + msg);
                try {
                    logFile.append(msg);
                    logFile.flush();
                    logFile.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        message.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        logFile = new FileWriter("messages.txt", true);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    msg = login.getText() + ": " + message.getText() + "\n";
                    area.append(msg);
                    System.out.println("Message sent!" + msg);
                    try {
                        logFile.write(msg);
                        logFile.flush();
                        logFile.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        setLayout(new GridLayout(2, 1));
        add(panelServer);
        add(panelClient);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Chat();
    }
}
