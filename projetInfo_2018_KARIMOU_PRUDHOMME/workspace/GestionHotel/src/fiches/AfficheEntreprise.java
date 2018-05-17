package fiches;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import connexionBDD.Connexion;
import panneau_principal.PanneauPrincipal;
/**
* Classe affichant la liste des entreprises enregistrés dans la base de donnée
* 
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/
public class AfficheEntreprise extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int id_client;

	private String[] li1=new String[10];//colonne table
	private String[] li2=new String[10];//colonne table
	private String[] li3=new String[10];//colonne table
	private String[] li4=new String[10];//colonne table
	
	
	private JButton button1 = new JButton("Menu");
	
	private String[][] list1=new String[20][20];//colonnes finales tableau
	
	public AfficheEntreprise(){

        try {
        	//recuperation donnees
			for(int i=1; i<10 ; i++){
        		PreparedStatement preparedState = Connexion.getInstance().prepareStatement("SELECT id from entreprise where id = '"+i+"'");
            	ResultSet resultat = preparedState.executeQuery();
                while(resultat.next()){
                	System.out.println(li1);
                	li1[i-1] = String.valueOf(resultat.getInt(1));
                	
                	
                }
                PreparedStatement preparedState1 = Connexion.getInstance().prepareStatement("SELECT nom from entreprise where id = '"+i+"'");
            	ResultSet resultat1 = preparedState1.executeQuery();
                while(resultat1.next()){
                	
                	li2[i-1] = resultat1.getString(1);
                }  
                
                PreparedStatement preparedState2 = Connexion.getInstance().prepareStatement("SELECT email from entreprise where id = '"+i+"'");
            	ResultSet resultat2 = preparedState2.executeQuery();
                while(resultat2.next()){
                	li3[i-1] = resultat2.getString(1);
                	
                }
                
                PreparedStatement preparedState3 = Connexion.getInstance().prepareStatement("SELECT num_tel from entreprise where id = '"+i+"'");
            	ResultSet resultat3 = preparedState3.executeQuery();
                while(resultat3.next()){
                	li4[i-1] = resultat3.getString(1);
                	
                }
                
             
  	
        	}
     
        }
        catch (Exception e3) {
				e3.printStackTrace();
		}
        
        
       
        for(int i =0; i<li1.length; i++){
        	
        	 list1[i][0] = li1[i];
        	 list1[i][1] = li2[i];
        	 list1[i][2] = li3[i];
        	 list1[i][3] = li4[i];
        	
        	
        }
        String[] entetes = {"Id", "Nom", "email", "numéro de téléphone"};
        
        JTable tableau = new JTable(list1,entetes);
        
        setTitle("JTable basique dans un JPanel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
 
        pack();
        
        this.getContentPane().add(button1, BorderLayout.SOUTH);
        button1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {//action si l'on appuis sur le bouton 
				dispose();
				PanneauPrincipal pann = new PanneauPrincipal();
				pann.setVisible(true);
				
			}
			
		});
	}
	
	public static void main(String[] args) {
        new AfficheEntreprise().setVisible(true);
    }

}
