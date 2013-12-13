/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mad.views;

/**
 *
 * @author Francois
 */
public class MenuPrincipal extends javax.swing.JFrame {
	private static final long serialVersionUID = 3842278862777833254L;
	/**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenuPrincipal = new javax.swing.JPanel();
        lblTitre = new javax.swing.JLabel();
        btnPartieSolo = new javax.swing.JButton();
        btnPartieMultijoueur = new javax.swing.JButton();
        btnOptions = new javax.swing.JButton();
        btnQuitter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("M.A.D. le jeu");
        setBackground(new java.awt.Color(153, 153, 255));

        pnlMenuPrincipal.setBackground(new java.awt.Color(153, 153, 255));

        lblTitre.setFont(new java.awt.Font("DejaVu Sans", 1, 48)); // NOI18N
        lblTitre.setText("M.A.D.");

        btnPartieSolo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnPartieSolo.setText("Partie solo");
        btnPartieSolo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPartieSoloMouseClicked(evt);
            }
        });

        btnPartieMultijoueur.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnPartieMultijoueur.setText("Partie multijoueur");
        btnPartieMultijoueur.setEnabled(false);

        btnOptions.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnOptions.setText("Options");
        btnOptions.setEnabled(false);

        btnQuitter.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnQuitter.setText("Quitter");
        btnQuitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuitterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuPrincipalLayout = new javax.swing.GroupLayout(pnlMenuPrincipal);
        pnlMenuPrincipal.setLayout(pnlMenuPrincipalLayout);
        pnlMenuPrincipalLayout.setHorizontalGroup(
            pnlMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuPrincipalLayout.createSequentialGroup()
                .addGroup(pnlMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMenuPrincipalLayout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(lblTitre))
                    .addGroup(pnlMenuPrincipalLayout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addGroup(pnlMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPartieMultijoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPartieSolo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        pnlMenuPrincipalLayout.setVerticalGroup(
            pnlMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuPrincipalLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblTitre)
                .addGap(29, 29, 29)
                .addComponent(btnPartieSolo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnPartieMultijoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPartieSoloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPartieSoloMouseClicked
        this.setVisible(false);
        new MenuPartieSolo().start();
        this.dispose();
    }//GEN-LAST:event_btnPartieSoloMouseClicked

    private void btnQuitterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitterMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnQuitterMouseClicked

    
    public void start() {
        final MenuPrincipal me = this;
        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                me.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOptions;
    private javax.swing.JButton btnPartieMultijoueur;
    private javax.swing.JButton btnPartieSolo;
    private javax.swing.JButton btnQuitter;
    private javax.swing.JLabel lblTitre;
    private javax.swing.JPanel pnlMenuPrincipal;
    // End of variables declaration//GEN-END:variables
}
