/*
 * Proyecto: Agenda POO - CRUD con RandomAccessFile
 * Desarrollado por: Miguel Rojas
 * Año: 2025
 */

package com.mycompany.poo_t6;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

public class interfaz extends javax.swing.JFrame {

    public interfaz() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtNumber = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");
        jLabel2.setText("Number");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRead.setText("Read");
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addGap(26, 26, 26)
                        .addComponent(btnRead)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(txtName)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(btnUpdate)))
                        .addGap(0, 104, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnRead)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String newName = String.valueOf(txtName.getText());
            long newNumber = Long.parseLong(txtNumber.getText());

            String nameNumberString;
            String name;
            long number;

            File file = new File("agenda_miguel_rojas.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                nameNumberString = newName + "!" + String.valueOf(newNumber);
                raf.writeBytes(nameNumberString);
                raf.writeBytes(System.lineSeparator());
                System.out.println("Friend added.");
            } else {
                System.out.println("Name or number already exists.");
            }
            raf.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nameNumberString;
            String name;
            long number;

            File file = new File("agenda_miguel_rojas.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                System.out.println("Friend Name: " + name + "\n" + "Contact Number: " + number + "\n");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String newName = String.valueOf(txtName.getText());
            long newNumber = Long.parseLong(txtNumber.getText());

            File file = new File("agenda_miguel_rojas.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;
            String nameNumberString;
            String name;

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }

            if (found) {
                File tmpFile = new File("temp.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {
                    nameNumberString = raf.readLine();
                    name = nameNumberString.substring(0, nameNumberString.indexOf('!'));

                    if (name.equals(newName)) {
                        nameNumberString = name + "!" + String.valueOf(newNumber);
                    }
                    tmpraf.writeBytes(nameNumberString);
                    tmpraf.writeBytes(System.lineSeparator());
                }

                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpraf.length());
                tmpraf.close();
                raf.close();
                tmpFile.delete();

                System.out.println("Friend updated.");
            } else {
                raf.close();
                System.out.println("Input name does not exist.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String newName = String.valueOf(txtName.getText());

            File file = new File("agenda_miguel_rojas.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;
            String nameNumberString;
            String name;

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];

                if (name.equals(newName)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                File tmpFile = new File("temp.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {
                    nameNumberString = raf.readLine();
                    name = nameNumberString.substring(0, nameNumberString.indexOf('!'));

                    if (name.equals(newName)) {
                        continue;
                    }
                    tmpraf.writeBytes(nameNumberString);
                    tmpraf.writeBytes(System.lineSeparator());
                }

                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpraf.length());
                tmpraf.close();
                raf.close();
                tmpFile.delete();

                System.out.println("Friend deleted.");
            } else {
                raf.close();
                System.out.println("Input name does not exist.");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumber;
}

