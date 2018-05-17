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
* Classe permettant d'effectuer une réservation de salle
*
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/
public class PannReservationSalle extends JFrame implements ActionListener{
	

	private static final long serialVersionUID = 1L;
	private JLabel labelResa = new JLabel("Numéro de réservation: ");
    private JLabel labelEntreprise = new JLabel("Identifiant de l'entreprise: ");
    private JLabel labelSalle = new JLabel("Numéro Salle: ");
    private JLabel labelPersonnel = new JLabel("Identifiant Personnel: ");
    private JLabel labelDate = new JLabel("jour de la réservation: ");

 
 
    
    private JTextField textResa = new JTextField(20);
    private JTextField textEntreprise = new JTextField(20);
    private JTextField textSalle = new JTextField(20);
    private JTextField textPersonnel = new JTextField(20);
    private JTextField textDate = new JTextField(20);
    
	
	private JButton button1 = new JButton("Réserver salle");
	private JButton button2 = new JButton("Menu");
	private JPanel panSalle = new JPanel(new GridBagLayout());//nouvelle fenetre
	
	PreparedStatement statement = null;
    ResultSet resultat = null;
    
    public PannReservationSalle() {
    	//initialisation
    		this.init();
    }
    
    public void init(){
    	//affichage
    	GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        constraints.gridx = 0;
        constraints.gridy = 0;     
        panSalle.add(this.labelResa, constraints);
 
        constraints.gridx = 1;
        panSalle.add(this.textResa, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        panSalle.add(this.labelEntreprise, constraints);
        
        constraints.gridx = 1;
        panSalle.add(this.textEntreprise, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;     
        panSalle.add(this.labelSalle, constraints);
 
        constraints.gridx = 1;
        panSalle.add(this.textSalle, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 3;     
        panSalle.add(this.labelPersonnel, constraints);
         
        constraints.gridx = 1;
        panSalle.add(this.textPersonnel, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 4;     
        panSalle.add(this.labelDate, constraints);
 
        constraints.gridx = 1;
        panSalle.add(this.textDate, constraints);
    	
        this.button1.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panSalle.add(this.button1, constraints);
    	
        this.button2.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panSalle.add(this.button2, constraints);
        
        add(this.panSalle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.button1) {
			try {
				//creation reservation salle
				String idC = this.textEntreprise.getText();
		        String idPersonnel = this.textPersonnel.getText();
		        String numS = this.textSalle.getText();
		        String numResa = this.textResa.getText();
		        String date1 = this.textDate.getText();
		        
	
		        int idEntreprise = Integer.parseInt(idC);
		        int idPerso = Integer.parseInt(idPersonnel);
		        int numReservation = Integer.parseInt(numResa);
		        int numSalle = Integer.parseInt(numS);

				PreparedStatement preparedState = Connexion.getInstance().prepareStatement("SELECT num_resa FROM resa_salle WHERE num_resa ='"+numReservation+"'");
				resultat = preparedState.executeQuery();
				
				
				
				if(resultat.next()) {
					JOptionPane.showMessageDialog(null,"Numéro de réservation déjà utilisé ","Error",1);
					
				}
				else {
					PreparedStatement preparedState1 = Connexion.getInstance().prepareStatement("INSERT INTO resa_salle(num_resa,num_salle,id_entreprise,id_personnel,date) VALUES (?,?,?,?,?)");
					preparedState1.setInt(1, numReservation); 
					preparedState1.setInt(2, numSalle); 
					preparedState1.setInt(3, idEntreprise); 
					preparedState1.setInt(4, idPerso); 
					preparedState1.setString(5, date1); 
	
					preparedState1.executeUpdate();
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
                new PannReservationSalle().setVisible(true);
            }
        });
	
	}
    

}
