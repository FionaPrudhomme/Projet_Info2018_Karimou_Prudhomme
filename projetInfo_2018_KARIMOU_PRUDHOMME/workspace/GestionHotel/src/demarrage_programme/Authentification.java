package demarrage_programme;



import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
//import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import connexionBDD.Connexion;
import panneau_principal.PanneauPrincipal;

/**
* Classe permettant de s'authentifier avant d'accéder à l'application
*
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/
public class Authentification extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelUsername = new JLabel("Identifiant: ");  	// la fenetre affiche deux lignes contenant identifiant 
    private JLabel labelPassword = new JLabel("Mot de passe: "); 	// et mot de passe
    private JTextField textUsername = new JTextField(20);   		// Taille du texte entrée par le personnel
    private JPasswordField fieldPassword = new JPasswordField(20);
    private JButton buttonLogin = new JButton("Entrer");  		// Creation d'un bouton entrer
    PreparedStatement statement = null;
    ResultSet resultat = null;
    JPanel newPanel = new JPanel(new GridBagLayout());  	// nouveau panneau
     
    public Authentification() {
                 
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
         
        // Affichage 
        
        constraints.gridx = 0;
        constraints.gridy = 0;     
        newPanel.add(labelUsername, constraints);
 
        constraints.gridx = 1;
        newPanel.add(textUsername, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        newPanel.add(labelPassword, constraints);
         
        constraints.gridx = 1;
        newPanel.add(fieldPassword, constraints);
        
        buttonLogin.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonLogin, constraints);
         
        
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Authentification"));
         
        
        add(newPanel);
         
        pack();
        setLocationRelativeTo(null);
    }
     
    


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		// On demande au personnel d'entrer son identifiants et son mot de passe
		String id = textUsername.getText();
        char[] mdp1 = fieldPassword.getPassword();
        
        String mdp = String.valueOf(mdp1);
        
        int id2 = Integer.parseInt(id);
		Connection conn = Connexion.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//vérification de l'identifiant et du mot de passe dans la bdd
		try {
			PreparedStatement preparedState = conn.prepareStatement("SELECT mdp FROM personnel WHERE id = '"+id2+"'");
			resultat = preparedState.executeQuery();
			
			if(resultat.next())
			{
				String motDePasse = resultat.getString(1);
				if(motDePasse.equals(mdp)) // si le mot de passe est juste 
				{ 
					JOptionPane.showMessageDialog(null,"Connexion réussie ! ","Success",JOptionPane.PLAIN_MESSAGE);
					dispose();
					PanneauPrincipal fenetre = new PanneauPrincipal();
					fenetre.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Mot de passe incorrect ! ","Error",1);
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"Login incorrect ! ","Error",1);
			}
 
			
			
 
			conn.close();//connexion fermé

		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Authentification().setVisible(true);//visiblité de la fenetre
            }
        });
        
        
    }



}
