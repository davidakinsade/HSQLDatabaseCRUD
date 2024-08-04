package org.example;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PlayerGUI extends JFrame {
    private final JTable playersTable;
    private final JTextField firstnameField;
    private final JTextField lastnameField;
    private final JTextField clubField;

    public PlayerGUI() {
        super("Player Manager");
        JPanel mainPanel = new JPanel(new BorderLayout());

        this.playersTable = new JTable(new DefaultTableModel(new Object[]{"First Name", "Last Name", "Club"}, 0));
        JScrollPane playersScrollPane = new JScrollPane(this.playersTable);
        mainPanel.add(playersScrollPane, "Center");
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        JLabel firstnameLabel = new JLabel("First Name:");


        this.firstnameField = new JTextField();
        inputPanel.add(firstnameLabel);
        inputPanel.add(this.firstnameField);
        JLabel lastnameLabel = new JLabel("Last Name:");

        this.lastnameField = new JTextField();
        inputPanel.add(lastnameLabel);
        inputPanel.add(this.lastnameField);
        JLabel clubLabel = new JLabel("Club:");

        this.clubField = new JTextField();
        inputPanel.add(clubLabel);
        inputPanel.add(this.clubField);
        mainPanel.add(inputPanel, "South");
        JPanel buttonPanel = new JPanel(new GridLayout(0, 4));
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                PlayerGUI.this.createPlayer();
            }
        });
        buttonPanel.add(createButton);
        JButton readButton = new JButton("Read");
        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerGUI.this.readPlayers();
            }
        });
        buttonPanel.add(readButton);
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerGUI.this.updatePlayer();
            }
        });
        buttonPanel.add(updateButton);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerGUI.this.deletePlayer();
            }
        });
        buttonPanel.add(deleteButton);
        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerGUI.this.exportPlayers();
            }
        });
        buttonPanel.add(exportButton);
        mainPanel.add(buttonPanel, "North");
        this.setContentPane(mainPanel);
        this.setSize(600, 400);
        this.setLocationRelativeTo((Component)null);
        this.readPlayers();
    }

    private void createPlayer() {
        String firstname = this.firstnameField.getText();
        String lastname = this.lastnameField.getText();
        String club = this.clubField.getText();
        PlayerManager.createPlayer(firstname, lastname, club);
        this.readPlayers();
    }

    private void readPlayers() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name", "Club"}, 0);
        Iterator var2 = PlayerManager.readPlayers().iterator();

        while(var2.hasNext()) {
            Player player = (Player)var2.next();
            model.addRow(new Object[]{player.getId(), player.getFirstname(), player.getLastname(), player.getClub()});
        }

        this.playersTable.setModel(model);
    }

    private void updatePlayer() {
        int selectedRow = this.playersTable.getSelectedRow();
        if (selectedRow >= 0) {
            String firstname = this.firstnameField.getText();
            String lastname = this.lastnameField.getText();
            String club = this.clubField.getText();
            PlayerManager.updatePlayer(firstname, lastname, club);
            this.readPlayers();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row  to update.", "Update Player", 2);
        }

    }

    private void deletePlayer() {
        int selectedRow = this.playersTable.getSelectedRow();
        if (selectedRow >= 0) {
            this.readPlayers();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete.", "Delete Player", 2);
        }

    }

    private void exportPlayers() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == 0) {
            File file = fileChooser.getSelectedFile();
            PlayerManager.exportPlayers(file.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Players exported.", "Export Players", 1);
        }

    }

    public static void main(String[] args) {
        PlayerGUI gui = new PlayerGUI();
        gui.setDefaultCloseOperation(3);
        gui.setVisible(true);
    }
}
