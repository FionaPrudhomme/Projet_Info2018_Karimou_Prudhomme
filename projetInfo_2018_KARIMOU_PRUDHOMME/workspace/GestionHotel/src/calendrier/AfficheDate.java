package calendrier;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import connexionBDD.Connexion;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Classe affichant la date choisi et les chambres occupées
*
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/
public class AfficheDate extends JFrame  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int yy;  // variable annee
	private int mm;  // variable mois
	private int dd;  // variable jour
	private JLabel labelDate = new JLabel("Date : ");		
	private JLabel labelChambre = new JLabel("Chambre occupées: ");
	private JLabel labelSalle = new JLabel("Salles occupées: ");
	private List<String> li2;// liste des chambres occupées
	private List<String> li ;//liste des salles occupées
	private JButton button1 = new JButton("Retour au calendrier");
	private JPanel panAfficheDate = new JPanel(new GridBagLayout());//création de la fenetre
	PreparedStatement statement = null;
    ResultSet resultat = null;
 
	public AfficheDate(int yy ,int mm,int  dd){
		this.li = new ArrayList<String>();//initialisation liste
		this.li2 = new ArrayList<String>();//initialisation liste
		this.yy = yy;//initialisation variable		
		this.mm = mm;//initialisation variable
		this.dd = dd;//initialisation variable
		this.init();//initialisation methode
	}
	
	
	
	public void init() {
		JLabel date = new JLabel(Integer.toString(dd) +"/" +Integer.toString(mm)+ "/" +Integer.toString(yy));//affichage de la date
		String date1 = Integer.toString(dd) +"/" +Integer.toString(mm)+ "/" +Integer.toString(yy);//récupération de la date
		
		// Affichage 
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
		
        constraints.gridx = 0;
        constraints.gridy = 0;     
        panAfficheDate.add(this.labelDate, constraints);

        constraints.gridx = 1;
        panAfficheDate.add(date, constraints);
        
        
        try {
        		SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");//définition du format de la date
        		Date date2 = formatter.parse(date1);//conversion en date
        		
        		PreparedStatement preparedState = Connexion.getInstance().prepareStatement("SELECT * from resa_chambre ");//connexion bdd et requete sql
            	ResultSet resultat = preparedState.executeQuery();
                while(resultat.next()){
                	Date date_debut = formatter.parse(resultat.getString("date_debut"));
                	Date date_fin = formatter.parse(resultat.getString("date_fin")); 
                	if(date2.before(date_fin) && date2.after(date_debut) || date2.equals(date_debut) || date2.equals(date_fin)){
                		
                		li.add(Integer.toString(resultat.getInt("num_chambre")));//ajout du numéro de la chambre si elle est occupée
                	}
                	
                	
                }
                
                PreparedStatement preparedState1 = Connexion.getInstance().prepareStatement("SELECT * from resa_salle ");//connexion bdd et requete sql
            	ResultSet res = preparedState1.executeQuery();
                while(res.next()){
                	Date d = formatter.parse(res.getString("date"));
            
                	if(d.equals(date2)){
                		
                		li2.add(Integer.toString(res.getInt("num_salle")));//ajout du numéro de la salle si elle est occupée
                	}
                	
                	
                }
        	
        }
        catch (Exception e3) {
			e3.printStackTrace();
		}
		
		
        // Affichage 
        constraints.gridx = 0;
        constraints.gridy = 2;     
        panAfficheDate.add(this.labelChambre, constraints);

        String inter = String.join(" ,", li);
        JLabel chambre = new JLabel(inter);
        constraints.gridx = 1;
        panAfficheDate.add(chambre, constraints);
        
        constraints.gridx = 0;  
        constraints.gridy = 3;     
        panAfficheDate.add(this.labelSalle, constraints);
        
        String inte = String.join(" ,", li2);
        JLabel salle = new JLabel(inte);
        constraints.gridx = 1;
        panAfficheDate.add(salle, constraints);
        
        this.button1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				dispose();//action du bouton
			}
			
        });	
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panAfficheDate.add(this.button1, constraints);
        
        add(this.panAfficheDate);
        pack();
        setLocationRelativeTo(null);
	}

	
	
	



}
