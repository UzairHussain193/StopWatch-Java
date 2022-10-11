import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.lang.*;

class Welcome extends JFrame {

    public Welcome() {
        setTitle("Welcome to Stopwatch");
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
        pb.setMaximum(20);
        jp.add(pb, BorderLayout.SOUTH);

        add(jp);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(jp);
        // setLocation(200, 300);
        setVisible(true);
        setBounds(300, 200, 500, 400);
        // setSize(500, 400);

        for (int i = 0; i <= 100; i++) {
            pb.setValue(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (i == 20) {
                setVisible(false);
                info in = new info();
                // call the class here which you want to show
            }
        }
    }
}

class info extends JFrame implements ActionListener {
    Container c;
    JLabel title, name, roll, subj, teacher;
    JButton start;

    public info() {

        setTitle("Details:");
        setBackground(Color.BLUE);
        setBounds(300, 200, 500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setForeground(Color.BLUE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setResizable(false);

        c = getContentPane();
        getContentPane().setBackground(Color.DARK_GRAY);
        c.setLayout(null);
        title = new JLabel("📌 Project Details. ");
        title.setFont(new Font("Courier", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(80, 20, 350, 50);
        c.add(title);

        name = new JLabel("Developed by : UZAIR HUSSAIN");
        name.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        name.setForeground(Color.WHITE);
        name.setSize(400, 30);
        name.setLocation(50, 100);
        c.add(name);

        roll = new JLabel("Roll No: 21SW 085 ");
        roll.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        roll.setForeground(Color.WHITE);
        roll.setSize(400, 30);
        roll.setLocation(50, 150);
        c.add(roll);

        subj = new JLabel("Subject: OOP ");
        subj.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        subj.setForeground(Color.WHITE);
        subj.setSize(400, 30);
        subj.setLocation(50, 200);
        c.add(subj);

        teacher = new JLabel("Teacher: SIR MOHSIN. ");
        teacher.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        teacher.setForeground(Color.WHITE);
        teacher.setSize(400, 30);
        teacher.setLocation(50, 250);
        c.add(teacher);

        start = new JButton("START");
        start.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        start.setBackground(Color.YELLOW);
        start.setForeground(Color.BLACK);
        start.setSize(200, 50);
        start.setLocation(100, 300);
        start.addActionListener(this);
        c.add(start);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == start) {
            this.dispose();
            NewJFrame n = new NewJFrame();
            n.main();
        }
    }
}

class NewJFrame extends JFrame implements Runnable {

    Thread t;
    int hcnt = 0, mcnt = 0, scnt = 0, mscnt = 0;
    String str = "", nstr = "", mstr = "", dstr = "";
    int cnt = 0, cnt2 = 0;

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        t = new Thread(this);
        reset();
    }

    public void reset() {
        hcnt = 0;
        mcnt = 0;
        scnt = 0;
        mscnt = 0;
        nstr = "00:00:00";
        mstr = "000";
        dstr = "";
        cnt2 = 0;
        display();
    }

    public void display() {
        lbdisplay1.setText(nstr);
        lbdisplay2.setText(mstr);
    }

    public void setTimeCounter() {
        nstr = "";
        if (hcnt < 10) {
            nstr = "0" + hcnt;
        } else {
            nstr = "" + hcnt;
        }
        if (mcnt < 10) {
            nstr += ":0" + mcnt;
        } else {
            nstr += ":" + mcnt;
        }
        if (scnt < 10) {
            nstr += ":0" + scnt;
        } else {
            nstr += ":" + scnt;
        }
    }

    public void setMTimeCounter() {
        mstr = "";
        if (mscnt < 10) {
            mstr = "00" + mscnt;
        } else if (mscnt >= 10 && mscnt < 100) {
            mstr = "0" + mscnt;
        } else {
            mstr = "" + mscnt;
        }
    }

    public void run() {
        try {
            while (true) {
                mscnt++;
                if (mscnt > 499) {
                    mscnt = 0;
                    scnt++;
                }
                if (scnt > 56) {
                    scnt = 0;
                    mscnt++;
                }
                if (mcnt > 59) {
                    mcnt = 0;
                    hcnt++;
                }
                if (hcnt > 99) {
                    mcnt = 0;
                    hcnt++;
                }

                setTimeCounter();
                setMTimeCounter();
                display();
                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printToList() {
        cnt2++;
        dstr += "\n" + cnt2 + " : " + lbdisplay1.getText() + " " + lbdisplay2.getText() + "\n";
        textlist.setText(dstr);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        ImageIcon img = new ImageIcon("stp.png");
        jPanel1 = new JPanel();
        lbdisplay1 = new JLabel();
        lbdisplay2 = new JLabel();
        btnstart = new JButton();
        btnreset = new JButton();
        jScrollPane1 = new JScrollPane();
        textlist = new JTextArea();

        setLocation(300, 200);
        setIconImage(img.getImage());
        setTitle("Stopwatch");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new Color(102, 255, 102));

        lbdisplay1.setFont(new Font("Segoe UI", 1, 70)); // NOI18N
        lbdisplay1.setText("00:00:00");

        lbdisplay2.setFont(new Font("Segoe UI", 1, 48)); // NOI18N
        lbdisplay2.setText("000");

        btnstart.setBackground(Color.YELLOW);
        btnstart.setFont(new Font("Segoe UI", 1, 36)); // NOI18N
        btnstart.setText("Start");
        btnstart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnstartActionPerformed(evt);
            }
        });

        btnreset.setBackground(new Color(0, 0, 255));
        btnreset.setFont(new Font("Segoe UI", 1, 36)); // NOI18N
        btnreset.setText("Reset");
        btnreset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        textlist.setBackground(new Color(153, 153, 0));
        textlist.setColumns(20);
        textlist.setLineWrap(true);
        textlist.setRows(5);
        jScrollPane1.setViewportView(textlist);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(btnstart, GroupLayout.PREFERRED_SIZE, 173,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnreset, GroupLayout.PREFERRED_SIZE, 174,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lbdisplay1, GroupLayout.PREFERRED_SIZE,
                                                        295,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbdisplay2)))
                                .addContainerGap(15, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbdisplay1, GroupLayout.PREFERRED_SIZE, 70,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbdisplay2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnstart, GroupLayout.PREFERRED_SIZE, 52,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnreset, GroupLayout.PREFERRED_SIZE, 52,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 217,
                                        Short.MAX_VALUE)
                                .addContainerGap()));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);

    }// </editor-fold>

    private void btnstartActionPerformed(ActionEvent evt) {

        if (btnstart.getText().equals("Start")) {
            btnstart.setText("Stop");
            btnstart.setBackground(Color.RED);
            cnt++;
            if (cnt == 1) {
                t.start();
            } else {
                t.resume();
            }
        } else {
            btnstart.setText("Start");
            btnstart.setBackground(Color.YELLOW);
            t.suspend();
            printToList();
        }
    }

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {

        reset();
        t.suspend();
        btnstart.setText("Start");
        textlist.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public void main() {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JButton btnreset;
    private JButton btnstart;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JLabel lbdisplay1;
    private JLabel lbdisplay2;
    private JTextArea textlist;
    // End of variables declaration
}

public class Stopwatch {
    public static void main(String[] args) {
        Welcome w = new Welcome();
        // Intro in = new Intro();
    }
}