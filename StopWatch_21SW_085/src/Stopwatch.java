import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.json.simple.*;
import java.io.*;
import java.text.DecimalFormat;

class Welcome extends JFrame {
    public Welcome() {
        ImageIcon ic = new ImageIcon("welcome.jpg");
        JPanel jp = new JPanel();

        jp.setLayout(new BorderLayout());
        JLabel lb = new JLabel(ic);

        jp.add(lb);
        // lb.setIcon(ic);
        jp.add(lb, BorderLayout.CENTER);

        JProgressBar pb = new JProgressBar();
        pb.setStringPainted(true);
        pb.setMaximum(0);
        pb.setMaximum(100);
        jp.add(pb, BorderLayout.SOUTH);

        add(jp);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(jp);
        setLocation(200, 300);
        setVisible(true);

        setSize(500, 400);

        for (int i = 0; i <= 100; i++) {
            pb.setValue(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (i == 100) {
                setVisible(false);
                Intro intro = new Intro();
                // call the class here which you want to show
            }
        }
    }
}

class Intro extends JFrame implements ActionListener {
    Container c;
    JButton stopwatch, info;

    public Intro() {
        ImageIcon ic = new ImageIcon("intro.jpg");
        JLabel lb = new JLabel(ic);
        // JPanel jp = new JPanel();
        setTitle("Home Screen");
        setBounds(300, 90, 500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setResizable(false);
        c = getContentPane();
        c.setLayout(new BorderLayout());
        c.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel title = new JLabel("👋 Home Screen 🙂");
        title.setFont(new Font("Courier", Font.BOLD, 30));
        title.setBounds(100, 30, 300, 30);
        title.setBackground(Color.BLACK);
        title.setForeground(Color.GREEN);
        c.add(title);

        c.add(lb);
        // c.add(lb, BorderLayout.CENTER);

        stopwatch = new JButton("STOPWATCH");
        stopwatch.setSize(150, 50);
        stopwatch.setLocation(100, 300);
        stopwatch.setFont(new Font("Courier", Font.BOLD, 20));
        stopwatch.setBackground(Color.BLACK);
        stopwatch.setForeground(Color.WHITE);
        stopwatch.setVisible(true);
        stopwatch.addActionListener(this);
        c.add(stopwatch);

        info = new JButton("INFO");
        info.setSize(150, 50);
        info.setFont(new Font("Courier", Font.BOLD, 20));
        info.setLocation(270, 300);
        info.setBackground(Color.BLACK);
        info.setForeground(Color.WHITE);
        info.setVisible(true);
        c.add(info);

        // add(jp);
        c.setLayout(null);
        // c.add(lb, null);

        stopwatch.addActionListener(this);
        info.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == stopwatch) { // when stopwatch button is selected
            stpwatch stp = new stpwatch();
        }
        if (e.getSource() == info) {
            try {
                this.dispose();
                JOptionPane.showMessageDialog(null, "Details About Project.");
            } catch (Exception a) {
                a.printStackTrace();
            } // when info button is selected

        }

    }
}

class stpwatch extends JFrame implements ActionListener {
    Timer t;
    JOptionPane dialog;
    JLabel display;
    DecimalFormat df;
    JPanel watchPanel, controlPanel;
    JButton ssBtn, reBtn, prBtn, about;

    public stpwatch() {
        ButtonPerformed btnPerform = new ButtonPerformed();
        ssBtn = new JButton("Start");
        reBtn = new JButton("Reset");
        prBtn = new JButton("Pause");
        about = new JButton("About");
        prBtn.setEnabled(false);
        watchPanel = new JPanel();
        controlPanel = new JPanel();
        df = new DecimalFormat("00");
        display = new JLabel();
        display.setText("Elapse : " + df.format(hour) + " : " + df.format(minute)
                + " : " + df.format(second) + " : " + df.format(csecond));
        dialog = new JOptionPane();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        t = new Timer(10, this);
        watchPanel.setLayout(new FlowLayout());
        controlPanel.setLayout(new FlowLayout());
        getContentPane().setLayout(new BorderLayout());
        watchPanel.add(display);
        controlPanel.add(ssBtn);// Start/Stop
        controlPanel.add(prBtn);// Pause/Resume
        controlPanel.add(reBtn);// Reset
        controlPanel.add(about);
        getContentPane().add(watchPanel, "North");
        getContentPane().add(controlPanel, "Center");
        prBtn.addActionListener(btnPerform);
        ssBtn.addActionListener(btnPerform);
        reBtn.addActionListener(btnPerform);
        about.addActionListener(btnPerform);
        setSize(330, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void startTimer() {
        t.start();
    }

    void stopTimer() {
        t.stop();
        csecond = 0;
        second = 0;
        minute = 0;
        hour = 0;
    }

    void pauseTimer() {
        t.stop();
    }

    void resumeTimer() {
        t.start();
    }

    void resetTimer() {
        csecond = 0;
        second = 0;
        minute = 0;
        hour = 0;
        display.setText("Elapse : " + df.format(hour) + " : " + df.format(minute)
                + " : " + df.format(second) + " : " + df.format(csecond));
    }

    private int csecond = 0;
    private int second = 0;
    private int minute = 0;
    private int hour = 0;
    private boolean isPause = true;
    private boolean isStart = true;

    public void actionPerformed(ActionEvent e) {
        csecond++;
        if (csecond == 100) {
            second++;
            csecond = 0;
        }
        if (second == 60) {
            minute++;
            second = 0;
        }
        if (minute == 60) {
            hour++;
            minute = 0;
        }

        display.setText("Elapse : " + df.format(hour) + " : " + df.format(minute)
                + " : " + df.format(second) + " : " + df.format(csecond));
    }

    private class ButtonPerformed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ssBtn) {
                if (isStart) {
                    startTimer();
                    isStart = false;
                    ssBtn.setText("Stop");
                    prBtn.setEnabled(true);

                } else {
                    isStart = true;
                    ssBtn.setText("Start");
                    stopTimer();
                    isPause = true;
                    prBtn.setText("Pause");
                    prBtn.setEnabled(false);
                }
            }
            if (e.getSource() == prBtn) {
                if (isPause) {
                    prBtn.setText("Resume");
                    pauseTimer();
                    isPause = false;
                } else {
                    prBtn.setText("Pause");
                    startTimer();
                    isPause = true;
                }
            }
            if (e.getSource() == reBtn) {
                resetTimer();
            }
            if (e.getSource() == about) {
                dialog.showMessageDialog(null, "StopWatch Demo.\nCreated by " +
                        "Uzair Hussain\nRoll NO : 21SW085 \nSec: 3");
            }
        }
    }
}

public class Stopwatch extends JFrame {

    public static void main(String[] args) {
        Welcome w = new Welcome();
        // Intro in = new Intro();
    }
}
