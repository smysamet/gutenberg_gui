package com.smy.gutenberggui.view;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author sam
 */
public class MyBooksJpanel extends javax.swing.JPanel {

    private MainFrame mainFrame;
    private List<JLabel> books;
    private DefaultTableModel model;

    class customRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
            jtable.setRowHeight(160);
            return (Component) o;
        }

    }

    /**
     * Creates new form SearchBookForm
     *
     * @param mainFrame
     */
    public MyBooksJpanel(MainFrame mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        this.model = (DefaultTableModel) jTable1.getModel();
        this.jTable1.getColumn("Cover").setCellRenderer(new customRenderer());
        this.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.jTable1.getColumn("Cover").setPreferredWidth(100);
        this.jTable1.getColumn("Title").setPreferredWidth(515);
        this.jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void updateContent() {
        ((DefaultTableModel) this.jTable1.getModel()).setRowCount(0);
        List<String> userBooks = this.mainFrame.getUser().getAllBooks();

        for (int i = 0; i < userBooks.size(); i += 2) {
            try {
                // kitabın kapağını getir
                HtmlPage page = this.mainFrame.getWebClient().getPage("https://www.gutenberg.org/ebooks/" + userBooks.get(i));

                if (page.getByXPath("/html/body/div[1]/div[1]/div[2]/div[3]/div[1]/img").isEmpty()) {
                    System.out.println("No image found!");
                } else {
                    HtmlImage image = (HtmlImage) page.getByXPath("/html/body/div[1]/div[1]/div[2]/div[3]/div[1]/img").get(0);

                    // kitap kapak
                    JLabel tempJLabel = new JLabel();

                    tempJLabel.setText(userBooks.get(i) + "&myMarker&" + userBooks.get(i + 1));
                    tempJLabel.setFont(new Font("Lucida Grande", 1, 0));

                    BufferedImage bufferedImage = ImageIO.read(new URL(image.getSrc()));

                    tempJLabel.setIcon(new ImageIcon(bufferedImage.getScaledInstance(100, 150, Image.SCALE_SMOOTH)));

                    // kitap title
                    HtmlHeading1 htmlHeading1 = (HtmlHeading1) page.getByXPath("/html/body/div[1]/div[1]/h1").get(0);
                    String title = htmlHeading1.getTextContent();

                    this.model.addRow(new Object[]{tempJLabel, title});

                }
            } catch (IOException ex) {
                Logger.getLogger(MyBooksJpanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FailingHttpStatusCodeException ex) {
                Logger.getLogger(MyBooksJpanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGeri = new javax.swing.JButton();
        btnOku = new javax.swing.JButton();
        btnSil = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(650, 630));
        setMinimumSize(new java.awt.Dimension(650, 630));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGeri.setText("Back");
        btnGeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeriActionPerformed(evt);
            }
        });
        add(btnGeri, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 570, -1, -1));

        btnOku.setText("Read");
        btnOku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkuActionPerformed(evt);
            }
        });
        add(btnOku, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 570, -1, -1));

        btnSil.setText("Delete");
        btnSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSilActionPerformed(evt);
            }
        });
        add(btnSil, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 570, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cover", "Title"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
        }

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 630, 550));
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkuActionPerformed

        if (this.jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Please select a book!",
                    "No book choosen.",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        JLabel currentBookCover = (JLabel) this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0);
        String[] info = currentBookCover.getText().split("&myMarker&");
        BookReadJframe temp = new BookReadJframe(info[0], this.mainFrame, currentBookCover);
        temp.setLocationRelativeTo(null);
        temp.setVisible(true);
        // set books scrollbar position
        temp.setPosition(Integer.parseInt(info[1]));
        this.mainFrame.setVisible(false);

    }//GEN-LAST:event_btnOkuActionPerformed

    private void btnGeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeriActionPerformed
        this.mainFrame.getCardLayout().show(this.mainFrame.getMainPanel(), "bookSearchJpanel");
        this.mainFrame.setCurrentVisibleJpanel(this.mainFrame.getBookSearchJpanel());
        this.mainFrame.getKitaplarimButton().setEnabled(true);
    }//GEN-LAST:event_btnGeriActionPerformed

    private void btnSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSilActionPerformed
        if (this.jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null,
                    "Lütfen bir kitap seçiniz!",
                    "Kitap Seçilmedi",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }
        
        String etext_no = ((JLabel)this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0))
                .getText().split("&myMarker&")[0];
        
        
        ((DefaultTableModel) this.jTable1.getModel()).removeRow(this.jTable1.getSelectedRow());
        this.mainFrame.getUser().deleteBook(etext_no);
        
        
    }//GEN-LAST:event_btnSilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGeri;
    private javax.swing.JButton btnOku;
    private javax.swing.JButton btnSil;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
