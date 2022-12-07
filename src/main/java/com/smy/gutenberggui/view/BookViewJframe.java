package com.smy.gutenberggui.view;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import java.io.IOException;

/**
 *
 * @author sam
 */
public class BookViewJframe extends javax.swing.JFrame {

    private String etext_no;

    private MainFrame mainFrame;

    /**
     * Creates new form BookViewJframe
     */
    public BookViewJframe(String etext_no, MainFrame mainFrame) throws IOException {
        initComponents();
        this.mainFrame = mainFrame;
        this.etext_no = etext_no;
        final HtmlPage page;
        page = mainFrame.getWebClient().getPage("https://www.gutenberg.org/ebooks/" + etext_no);

        HtmlTable htmlTable = (HtmlTable) page.getByXPath("/html/body/div[1]/div[1]/div[2]/div[4]/div/div[1]/div/table").get(0);

        HtmlAnchor a = (HtmlAnchor) htmlTable.getRow(htmlTable.getRowCount() - 2).getCell(1).getElementsByTagName("a").get(0);

        TextPage textPage = mainFrame.getWebClient().getPage("https://www.gutenberg.org" + a.getHrefAttribute());

        // kitabı okuma yerine ekle
        this.bookTextArea.setText(textPage.getContent());

        Thread t = new Thread() {
            public void run() {
                while(jScrollPane2.getVerticalScrollBar().getModel().getValue()<1000){
                    
                }
                jScrollPane2.getVerticalScrollBar().getModel().setValue(0);
            }
        };
        t.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainJPanel = new javax.swing.JPanel();
        geriButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        bookTextArea = new javax.swing.JTextArea();
        kitaplarimaEkleButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Read a Book");
        setMinimumSize(new java.awt.Dimension(930, 630));
        setResizable(false);
        setSize(new java.awt.Dimension(930, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainJPanel.setMaximumSize(new java.awt.Dimension(930, 630));
        mainJPanel.setMinimumSize(new java.awt.Dimension(930, 630));
        mainJPanel.setPreferredSize(new java.awt.Dimension(930, 630));
        mainJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        geriButton.setText("Geri");
        geriButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geriButtonActionPerformed(evt);
            }
        });
        mainJPanel.add(geriButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 580, 128, -1));

        bookTextArea.setEditable(false);
        bookTextArea.setBackground(new java.awt.Color(187, 187, 187));
        bookTextArea.setColumns(20);
        bookTextArea.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        bookTextArea.setForeground(new java.awt.Color(0, 0, 0));
        bookTextArea.setLineWrap(true);
        bookTextArea.setRows(5);
        bookTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(bookTextArea);

        mainJPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 20, 901, 494));

        kitaplarimaEkleButton.setText("Kitaplarıma Ekle");
        kitaplarimaEkleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kitaplarimaEkleButtonActionPerformed(evt);
            }
        });
        mainJPanel.add(kitaplarimaEkleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 540, -1, -1));

        getContentPane().add(mainJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void geriButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geriButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_geriButtonActionPerformed

    private void kitaplarimaEkleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kitaplarimaEkleButtonActionPerformed
        this.mainFrame.getUser().addBook(this.etext_no, this.jScrollPane2.getVerticalScrollBar().getModel().getValue());
        // get scrollbar's current position -> this.jScrollPane2.getVerticalScrollBar().getModel().getValue();

        this.setVisible(false);
        this.dispose();


    }//GEN-LAST:event_kitaplarimaEkleButtonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea bookTextArea;
    private javax.swing.JButton geriButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton kitaplarimaEkleButton;
    private javax.swing.JPanel mainJPanel;
    // End of variables declaration//GEN-END:variables
}
