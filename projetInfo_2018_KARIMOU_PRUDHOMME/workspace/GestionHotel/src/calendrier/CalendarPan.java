package calendrier;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

/**
* Classe créant le design du panneau calendrier
*
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/
public class CalendarPan extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int jour, mois, annee, dom;
	Calendar calendar;

	// constructeur
	public CalendarPan(int jour, int mois, int annee) {
		repaint();
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;

	}

	// variable de classe
	private static final int TAILLE = 60;

	static int ymoni = 500;

	public static int postitle = 450;

	public static final String[] jours = { "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim" };

	//dernier jours de chaque mois
	 
	public final static int DernierJour[] = { 31, 28, 31,
			30, /* jan feb mar apr */
			31, 30, 31, 31, /* may jun jul aug */
			30, 31, 30, 31 /* sep oct nov dec */
	};

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 500, 500);
		// recuperer le premier jour du mois (lun mar mer ...)
		int first = new GregorianCalendar(annee, mois, 0).get(Calendar.DAY_OF_WEEK) - 1;
		// ajuster le panneau
		int ajustx = 30;

		int jrcount = 1;
		g.setColor(Color.pink);
		g.fillRect(0, 0, ymoni, 40);
		// Année bissextile
		if (annee % 4 == 0)
			DernierJour[1] = 29;
		else
			DernierJour[1] = 28;
		for (int i = 0; i < jours.length; i++) {
			g.setColor(Color.white);
			g.drawString(jours[i], i * TAILLE + ajustx, 25);

		}
		int monte = 50;
		// placer les contenus dans les panneau
		g.setColor(Color.blue);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < jours.length; j++) {

				if ((j >= first || i > 0) && jrcount <= DernierJour[mois])
					if (jrcount == jour) {// si c'est le jour du mois
						if (jour < 10)// ajuste carree bleu
							g.fillRect(j * TAILLE + ajustx - 2, i * TAILLE + monte, 20, 30);
						else
							g.fillRect(j * TAILLE + ajustx - 2, i * TAILLE + monte, 30, 30);
						g.setColor(Color.white);
						g.drawString("" + jrcount++, j * TAILLE + ajustx + 5, i * TAILLE + monte + 20);
					} else {// si c'est le jour normal
						g.setColor(Color.pink);
						g.drawString("" + jrcount++, j * TAILLE + ajustx + 5, i * TAILLE + monte + 20);

					}
			}
		}

	}

}
