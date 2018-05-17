package reservations;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import connexionBDD.Connexion;
import panneau_principal.PanneauPrincipal;
/**
* Classe permettant d'effectuer une réservation d'une chambre
*
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/
public class PannReservationChambre extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// lignes indiquant les details de chaque reservation
	private JLabel labelResa = new JLabel("Numéro de réservation: ");
    private JLabel labelClient = new JLabel("Identifiant du client: ");
    private JLabel labelChambre = new JLabel("Numéro Chambre: ");
    private JLabel labelPersonnel = new JLabel("Identifiant Personnel: ");
    private JLabel labelDate1 = new JLabel("Début de la Réservation: ");
    private JLabel labelDate2 = new JLabel("Fin de la réservation: ");
    private JLabel labelOptions = new JLabel("Options: ");
    private JLabel labelNuit = new JLabel("Nombre de nuit: ");
    
    private JTextField textResa = new JTextField(20);
    private JTextField textClient = new JTextField(20);
    private JTextField textChambre = new JTextField(20);
    private JTextField textPersonnel = new JTextField(20);
    private JTextField textDate1 = new JTextField(20);
    private JTextField textDate2 = new JTextField(20);
    private JTextField textOptions = new JTextField(20);
    private JTextField textNuit = new JTextField(20);
	
	private JButton button1 = new JButton("Réserver chambre");
	private JButton button2 = new JButton("Menu");
	private JPanel pan = new JPanel(new GridBagLayout());//nouvelle fenetre
	
	
    ResultSet resultat = null;
	
    public PannReservationChambre() {
    	//initialisation
    		this.init();
    	}
    
    public void init(){
    	//Affichage
    	GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        constraints.gridx = 0;
        constraints.gridy = 0;     
        pan.add(labelResa, constraints);
 
        constraints.gridx = 1;
        pan.add(textResa, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        pan.add(labelClient, constraints);
        
        constraints.gridx = 1;
        pan.add(textClient, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;     
        pan.add(labelChambre, constraints);
 
        constraints.gridx = 1;
        pan.add(textChambre, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 3;     
        pan.add(labelPersonnel, constraints);
         
        constraints.gridx = 1;
        pan.add(textPersonnel, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 4;     
        pan.add(labelDate1, constraints);
 
        constraints.gridx = 1;
        pan.add(textDate1, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 5;     
        pan.add(labelDate2, constraints);
         
        constraints.gridx = 1;
        pan.add(textDate2, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 6;     
        pan.add(labelOptions, constraints);
 
        constraints.gridx = 1;
        pan.add(textOptions, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 7;     
        pan.add(labelNuit, constraints);
         
        constraints.gridx = 1;
        pan.add(textNuit, constraints);
    	
        button1.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        pan.add(button1, constraints);
    	
        button2.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        pan.add(button2, constraints);
        
        add(pan);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
        
       
		
		
	if(e.getSource() == button1) {
		try {
			//reservation d'une chambre
			String idC = textClient.getText();
		    String idPersonnel = textPersonnel.getText();
		    String numCh = textChambre.getText();
		    String numResa = textResa.getText();
		    String date1 = textDate1.getText();
		    String date2 = textDate2.getText();
		    String options = textOptions.getText();
		    String nbNuit = textNuit.getText();
		        
		    int idClient = Integer.parseInt(idC);
		    int idPerso = Integer.parseInt(idPersonnel);
		    int numReservation = Integer.parseInt(numResa);
		    int numChambre = Integer.parseInt(numCh);
		    int nbN = Integer.parseInt(nbNuit);
			PreparedStatement preparedState = Connexion.getInstance().prepareStatement("SELECT num_resa FROM resa_chambre WHERE num_resa ='"+numReservation+"'");
			resultat = preparedState.executeQuery();
				
				
				
			if(resultat.next()) {
				JOptionPane.showMessageDialog(null,"Numéro de réservation déjà utilisé ","Error",1);
					
			}
			else {
				PreparedStatement preparedState2 = Connexion.getInstance().prepareStatement("INSERT INTO resa_chambre(num_resa,num_chambre,id_client,id_personnel,date_debut,date_fin,options,nb_nuits) VALUES (?,?,?,?,?,?,?,?)");
				preparedState2.setInt(1, numReservation); 
				preparedState2.setInt(2, numChambre); 
				preparedState2.setInt(3, idClient); 
				preparedState2.setInt(4, idPerso); 
				preparedState2.setString(5, date1); 
				preparedState2.setString(6, date2);
				preparedState2.setString(7, options); 
				preparedState2.setInt(8, nbN); 
	
				preparedState2.executeUpdate();
				JOptionPane.showMessageDialog(null,"Ajout réussi ","Success",JOptionPane.PLAIN_MESSAGE);
				dispose();
				PanneauPrincipal fenetre = new PanneauPrincipal();
				fenetre.setVisible(true);
			}
	
			
		}
			
		catch (Exception e3) {
			e3.printStackTrace();
		}
			
	}
		
	if(e.getSource() == button2) {
		dispose();
		PanneauPrincipal pann = new PanneauPrincipal();
		pann.setVisible(true);
			
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
                new PannReservationChambre().setVisible(true);
            }
        });
	
	}

}
