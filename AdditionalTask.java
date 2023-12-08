import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionalTask extends JFrame implements ServerListener{
    private static final int WINDOW_HIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton startServer = new JButton("Server start");
    JButton stopServer = new JButton("Server stop");
    boolean isServerWorking;
    Server server = new Server(this);
    AdditionalTask(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HIGHT);
        setTitle("Server");
        setResizable(false);

        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.start();
            }

        });

        stopServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stop();
            }
        });
        setLayout(new GridLayout(1, 2));
        add(startServer);
        add(stopServer);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AdditionalTask();
    }

    @Override
    public void messageReceive(String message) {
        System.out.println(message);
    }
}
