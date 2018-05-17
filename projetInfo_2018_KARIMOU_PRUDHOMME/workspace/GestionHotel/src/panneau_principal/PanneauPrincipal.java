package panneau_principal;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import facture.FacturerChambre;
import facture.FacturerSalle;
import fiches.AfficheClient;
import fiches.AfficheEntreprise;
import fiches.AjoutPointFidelite;
import fiches.CreerFicheClient;
import fiches.CreerFicheEntreprise;
import reservations.PannReservationChambre;
import reservations.PannReservationSalle;
import calendrier.Cal;
/**
* Classe permettant d'accéder aux differentes actions de l'application
*
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/



public class PanneauPrincipal extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// creation des bouttons 
	private JButton button1 = new JButton("Réserver chambre");
	private JButton button2 = new JButton("Réserver salle");
	private JButton button3 = new JButton("Planning");
	private JButton button4 = new JButton("Créer une fiche client");
	private JButton button5 = new JButton("Créer une fiche entrerpise");
	private JButton button6 = new JButton("Ajouter points de fidélité");
	private JButton button7 = new JButton("Facturer (Chambre)");
	private JButton button8 = new JButton("Facturer (Salle)");
	private JButton button9 = new JButton("Liste Clients");
	private JButton button10 = new JButton("Liste Entreprises");
    private JPanel pan = new JPanel();
	
	public PanneauPrincipal(){
		//initialisation
		this.init();
	}
	
	public void init(){
		
		
		this.setTitle("Gestion Hotel");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);   
		
		
		pan.setBackground(Color.PINK); 
		this.setContentPane(pan);               
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    //Trois lignes sur deux colonnes
	    this.setLayout(new GridLayout(5, 2));
	    //On ajoute le boutton au content pane de la JFrame
	    this.getContentPane().add(button1);
	    this.getContentPane().add(button2);
	    this.getContentPane().add(button3);
	    this.getContentPane().add(button4);
	    this.getContentPane().add(button5);
	    this.getContentPane().add(button6);
	    this.getContentPane().add(button7);
	    this.getContentPane().add(button8);
	    this.getContentPane().add(button9);
	    this.getContentPane().add(button10);
	    this.setVisible(true);
	    button1.addActionListener(this);
	    button2.addActionListener(this);
	    button3.addActionListener(this);
	    button4.addActionListener(this);
	    button5.addActionListener(this);
	    button6.addActionListener(this);
	    button7.addActionListener(this);
	    button8.addActionListener(this);
	    button9.addActionListener(this);
	    button10.addActionListener(this);
	 
	}
	
	
	// Definir les fonctionnalités de chaque boutton du panneau
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			dispose();
			PannReservationChambre pann = new PannReservationChambre();
			pann.setVisible(true);
			
		}
		
		if(e.getSource() == button2) {
			
			dispose();
			PannReservationSalle pann = new PannReservationSalle();
			pann.setVisible(true);
		}
		
		if(e.getSource() == button3) {
			dispose();
			Cal pann = new Cal();
			pann.frame.setVisible(true);
		}
		
		if(e.getSource() == button4) {
			dispose();
			CreerFicheClient pann = new CreerFicheClient();
			pann.setVisible(true);
		}
		if(e.getSource() == button5) {
			dispose();
			CreerFicheEntreprise pann = new CreerFicheEntreprise();
			pann.setVisible(true);
		}
		if(e.getSource() == button6) {
			dispose();
			AjoutPointFidelite pann = new AjoutPointFidelite();
			pann.setVisible(true);
		}
		if(e.getSource() == button7) {
			dispose();
			FacturerChambre pann = new FacturerChambre();
			pann.setVisible(true);
		}
		if(e.getSource() == button8) {
			dispose();
			FacturerSalle pann = new FacturerSalle();
			pann.setVisible(true);
		}
		if(e.getSource() == button9) {
			dispose();
			AfficheClient pann = new AfficheClient();
			pann.setVisible(true);
		}
		if(e.getSource() == button10) {
			dispose();
			AfficheEntreprise pann = new AfficheEntreprise();
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
                new PanneauPrincipal().setVisible(true);
            }
        });
	
	}


}
