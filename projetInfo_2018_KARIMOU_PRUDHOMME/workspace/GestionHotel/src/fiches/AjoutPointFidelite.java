package fiches;

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
* Classe ajoutant le nombres de points de fidélités voulu au client voulu
* 
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/
public class AjoutPointFidelite extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelId = new JLabel("Identifiant : ");
    private JLabel labelPDF = new JLabel("Points de fidélités: ");
   

 
 
    
    private JTextField textId = new JTextField(20);
    private JTextField textPDF = new JTextField(20);//points de fidelites

    
	
	private JButton button1 = new JButton("Ajouter points de fidélités");
	private JButton button2 = new JButton("Menu");
	private JPanel panFiche = new JPanel(new GridBagLayout());//nouvelle fenetre
	
	PreparedStatement statement = null;
    ResultSet resultat = null;
    
    public AjoutPointFidelite() {
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
        panFiche.add(this.labelId, constraints);
 
        constraints.gridx = 1;
        panFiche.add(this.textId, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;     
        panFiche.add(this.labelPDF, constraints);
        
        constraints.gridx = 1;
        panFiche.add(this.textPDF, constraints);
    	
        this.button1.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panFiche.add(this.button1, constraints);
    	
        this.button2.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panFiche.add(this.button2, constraints);
        
        add(this.panFiche);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    	
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.button1) {
			try {
				//ajout des points de fidelites
				String idC = this.textId.getText();
		        String pdf1 = this.textPDF.getText();
		  
		        
		        
		        int idClient = Integer.parseInt(idC);
		        int pdf = Integer.parseInt(pdf1);
		      
				PreparedStatement preparedState = Connexion.getInstance().prepareStatement("SELECT id FROM client WHERE id ='"+idClient+"'");
				resultat = preparedState.executeQuery();
				
				
				
				if(resultat.next()) {
					PreparedStatement preparedState2 = Connexion.getInstance().prepareStatement("update client set  point_de_fidelite='"+pdf+"'where id ='"+idClient+"'"); 
					preparedState2.executeUpdate();
					JOptionPane.showMessageDialog(null,"Ajout réussi ","Success",JOptionPane.PLAIN_MESSAGE);
					dispose();
					PanneauPrincipal fenetre = new PanneauPrincipal();
					fenetre.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Client non enregistré ","Error",1);
				
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
                new AjoutPointFidelite().setVisible(true);
            }
        });
	
	}


}
