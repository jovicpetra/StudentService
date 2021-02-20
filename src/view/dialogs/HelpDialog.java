package view.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import view.MainFrame;


public class HelpDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7532094756704393899L;
		
	public HelpDialog() {
			
		JDialog help = new JDialog();
		help.setTitle("Help");
		help.setIconImage(new ImageIcon("images1"+File.separator+"help.png").getImage());
		//help.setPreferredSize(new Dimension(900,800));
		JTextArea text = new JTextArea(50,100);
		text.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
		text.setLineWrap(true);
		text.setEditable(false);
		text.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		JScrollPane scroll = new JScrollPane (text);
			
		String appDescription = "\r\n"
							  + "				 Upotreba Aplikacije:\r\n\n" 
							  + " 1. Glavni prozor sadrži MenuBar, ToolBar i StatusBar.\r\n"
							  + " 2. MenuBar ima stavke File, Edit i Help.\r\n" 
							  + "  File\r\n"
							  + "	  *New - Dodavanje novog entiteta u sistem.\r\n"
						      + "	  *Close - Zatvaranje aplikacije.\r\n"
							  + "  Edit\r\n"
							  + "	  *Edit - Izmjena postojećeg entiteta.\r\n"
							  + "	  *Delete - Brisanje postojećeg entiteta.\r\n"
							  + "  Help\r\n"
							  + "	  *Help - Opis o načinu korišćenja aplikacije.\r\n"
							  + "	  *About - Prikaz verzije aplikacije, kao i kratak opis iste.\r\n"
							  + " 3. ToolBar sadrži dugmiće za dodavanje, izmjenu i brisanje entiteta. Kao i\r\n"
							  + " TextField za pretragu.\r\n"
							  + " 4. StatusBar sadrži ispisane naziv aplikacije, datum i\r\n"
							  + " i trenutno vrijeme.\r\n"
							  + " 5. Na glavnom prozoru nalazi se prikaz studenata, profesora i predmeta. Svaki\r\n"
							  + " od ovih entiteta ima svoj tab.\r\n"
							  + " 6. Ukoliko se korisnik nalazi u odgovarajućem tabu pritiskom na dugme u toolbar-u,\r\n"
							  + " pozivanjem opcije iz menija, ili upotrebom akceleratora i mnemonika poziva se dijalog\r\n"
							  + " dodavanje odgovarajućeg entiteta. Za svaki od datih entiteta kreirana je njima\r\n"
							  + " odgovarajuća model klasa.\r\n\n\n"
							  + " MNEMONICI: \r\n"
							  + "	*F - otvara se File meni\r\n"
							  + "	*E - otvara se Edit meni\r\n"
							  + "	*H - otvara se Help meni\r\n"
							  + "	*N - dodavanje entiteta\r\n"
							  + "	*C - zatvaranje aplikacije\r\n"
							  + "	*I - izmjena entiteta\r\n"
							  + "	*D - brisanje entiteta\r\n"
							  + "	*P - otvaranje Help dijaloga\r\n"
							  + "	*A - otvaranje About dijaloga\r\n"
							  + " AKCELERATORI: \r\n"
							  + "	*CTRL+N - dodavanje entiteta\r\n"
							  + "	*CTRL+C - zatvaranje aplikacije\r\n"
							  + "	*CTRL+E - izmjena entiteta\r\n"
							  + "	*CTRL+D - brisanje entiteta\r\n"
							  + "	*CTRL+H - Help\r\n"
							  + "	*CTRL+A - About\r\n"
							  + " Ukoliko se korisnik nalazi u odgovarajućem tabu pritiskom na dugme u toolbar-u,\r\n"
							  + "	pozivanjem opcije iz menija, ili upotrebom akceleratora i mnemonika poziva se\r\n"
							  + "	dijalog izmjenu odgovarajućeg entiteta.\r\n\n\n"
							  + " U okviru dijaloga za izmjenu studenta nalaze se tri taba. U prvom vidimo trenutne\r\n"
							  +"	informacije o datom studentu koje možemo izmijeniti. Nakon klika na dugme\r\n"
							  + "	potvrdi u tabeli se izmjenjuju informacije.\r\n"
							  +" Drugi tab sadrži prikaz položenih ispita datog studenta u tabeli.Iznad tabele nalazi\r\n"
							  +"	 se dugme 'Poništi ocenu' koje nam omogućava da uklonim položen ispit koji\r\n"
							  +"	 je selektovan u tabeli\r\n"
							  +"	ukoliko je ispit selektovan. Ukoliko nijedan ispit nije selektovan dobijamo\r\n"
							  + "	upozerenje. Prilikom klika na dugme vidimo dijalog gdje je neophodno da\r\n"
							  + "	potvrdimo poništavanje ispita.\r\n"
							  +" Sledeći tab sadrži prikaz nepoloženih ispita. Iznad se nalaze dugmići 'Dodaj',\r\n"
							  + "	'Ukloni' i 'Polaganje'. Klikom na dugme za dodavanje otvara se lista predmeta\r\n"
							  + "	koje student može da sluša. Dugme za brisanje omogućava uklanjanje predmeta\r\n"
							  + "	iz liste ukoliko je selektovan, a polaganje nam omogućava da unesemo ocjenu\r\n"
							  + "	studentu. Unosom ocjene dati predmet se premješta iz liste nepoloženih u\r\n"
							  + "	listu položenih ispita, odnosno iz taba nepoloženih u tab položenih. Prilikom\r\n"
							  + "	uklanjanja predmeta vidjećemo dijalog gdje je potrebno da odobrimo uklanjanje.\r\n"
							  +" Pretraga je implementirana pomoću RowFilter-a. Ukoliko je ukucan odgovarajući\r\n"
							  + "	kriterijum u tabeli će se prikazati samo entiteti koji ispunjavaju dati\r\n"
							  + "	kriterijum. Ukoliko ne postoji takav entitet tabela će biti prazna.\r\n"
							  +" Brisanje entiteta je omogućeno pomoću dugmeta iz toolbar-a, stavke iz menija\r\n"
							  + "	ili odgovarajućeg akceleratora ili mnemonika. Da bi se brisanje izvršilo\r\n"
							  + "	entitet mora biti selektovan. Pozivanjem brisanja odgovarajućom metodom otvara\r\n"
							  + "	se dijalog gdje je potrebno potvrditi brisanje. Brisanjem se dati entitet\r\n"
							  + "	uklanja iz tabele koja je prikazana. Moramo biti pozicionirani u\r\n"
							  +"	odgovarajućem tabu kako bismo uklonili entitet koji želimo.\r\n"
							  +" Sortiranje studenta je uređeno tako da je moguće sortirati ih po imenu, prezimenu,\r\n"
							  + "	godini, statusu, prosjeku i indeksu. Sortiranje po indeksu je uređeno\r\n"
							  + "	pravljenjem posebnog komparatora. Indeksi se grupišu prvo na osnovu smjera,\r\n"
							  + "	zatim po godini studija i okviru iste godine se sortiraju po broju.\r\n"
							  +"	Ostali entiteti su sortirani pomoću TableRowSorter-a. Omogućeno je sortiranje\r\n"
							  + "	u opadajućem i rastućem redoslijedu.\r\n"
							  +" U okviru diajloga za izmejnu profesora imamo dva taba. Prvi prikazuje trenutne\r\n"
							  + "	informacije trenutnog profesora koje je moguće izmijeniti. U sledećem tabu\r\n"
							  + "	nalaze se predmeti koje profesor predaje. Iznad tabele prikaza nalaze dugmići\r\n"
							  + "	'Dodaj predmet' i 'Ukloni predmet'. Klikom na dugme za dodavanje\r\n"
							  +"	otvara se lista koja nam nudi predmete koje profesor može da predaje.\r\n"
							  + "	Izborom predmeta i klikom na 'Potvrdi' predmet se dodaje u tabelu. Ukoliko\r\n"
							  + "	želimo da uklonimo predmet neophodno ga je selektovati i izabrati dugme\r\n"
							  +"	za uklanjanje. Otvoriće se dijalog gdje je potrebno odobriti uklanjanje.\r\n"
							  +" U dijalogu za izmjenu predmeta nalazi se polje u kojem je moguće dodati\r\n"
							  + "	profesora na dati predmet (isto se nalazi i u dialogu za dodavanje).\r\n"
							  + "	Klikom na dugme '+' otvara se lista profesora i izborom jednog od\r\n"
							  + "	njih popunjava se tekstualnu polje. Kada je izabran profesor moguće\r\n"
							  + "	je kliknuti i na dugme '-' čime se profesor uklanja sa predmeta.\r\n"
							  +"	Uklanjanje se završava tek klikom na dugme 'Potvrdi' u okviru\r\n"
							  + "	dijaloga za izmjenu.\r\n"
							  +" Serijalizacija je izvršena tako što se svi podaci upisuju u jednu\r\n "
							  + "	datoteku kako bi se sačuvala\r\n"
							  +"	referencijalna zavisnost. Deserijalizacija čita date podatke\r\n "
							  + "	svakim otvaranjem aplikacije i popunjava liste studenata, profesora ili predmeta.\r\n\n";
		
		String appDescription2 = "\r\n"
				  + "				 How to use the app:\r\n\n" 
				  + " 1. Main frame contains MenuBar, ToolBar and StatusBar.\r\n"
				  + " 2. MenuBar contains File, Edit and Help.\r\n" 
				  + "  File\r\n"
				  + "	  *New - Adds new entity in the system.\r\n"
			      + "	  *Close - Closes the app.\r\n"
				  + "  Edit\r\n"
				  + "	  *Edit - Edits existing entity in the system.\r\n"
				  + "	  *Delete - Deletes an existing entity in the system.\r\n"
				  + "  Help\r\n"
				  + "	  *Help - Describes how the app functions.\r\n"
				  + "	  *About - Displays app version, and short description.\r\n"
				  + " 3. ToolBar contains buttons for adding a new entity, editing and deleting an existing entity. Also one\r\n"
				  + " TextField for serching.\r\n"
				  + " 4. StatusBar contains the app name, date and\r\n"
				  + " the current time.\r\n"
				  + " 5. The main frame displays students, professors and subjects. Every\r\n"
				  + " entity has its own tab. \r\n"
				  + " 6. If the user is in the appropriate tab by pressing the button in the toolbar,\r\n"
				  + " or by calling an option from the menu, or by using accelerators and mnemonics, a dialog is called\r\n"
				  + " for adding the appropriate entity. For each of the given entities there is\r\n"
				  + " appropriate class model.\r\n\n\n"
				  + " MNEMONICS: \r\n"
				  + "	*F - opens File menu\r\n"
				  + "	*E - opens Edit menu\r\n"
				  + "	*H - opens Help menu\r\n"
				  + "	*N - adds new entity\r\n"
				  + "	*C - closes the app\r\n"
				  + "	*I - edits existing entity\r\n"
				  + "	*D - deletes existing entity\r\n"
				  + "	*P - opens Help dilog\r\n"
				  + "	*A - opens About dialog\r\n"
				  + " ACCELERATORS: \r\n"
				  + "	*CTRL+N - adds new entity\r\n"
				  + "	*CTRL+C - closes the app\r\n"
				  + "	*CTRL+E - edits existing entity\r\n"
				  + "	*CTRL+D - deletes existing entity\r\n"
				  + "	*CTRL+H - opens Help dilog\r\n"
				  + "	*CTRL+A - opens About dialog\r\n"
				  + " If the user is in the appropriate tab by pressing the button in the toolbar,\r\n"
				  + "	 or by calling an option from the menu, or by using accelerators and mnemonics,\r\n"
				  + "	 a dialog is called for editing the appropriate entity\r\n\n\n"
				  + " There are three tabs within the Student Edit Dialogue. In the first we see the current \r\n"
				  +"	informations about a given student that we can change. After clicking the button\r\n"
				  + "	confirm in the table the information is changed.\r\n"
				  +" The second tab contains an overview of the passed exams of a given student. Above the table\r\n"
				  +"	  there is a button 'Remove grade' which allows us to remove the passed exam\r\n"
				  +"	  that is selected in the table\r\n"
				  +"	 if the exam is selected. If no exam is selected, we get\r\n"
				  + "	a warning. When we click on the button, we see a dialog where it is necessary to\r\n"
				  + "	to confirm the cancellation of the exam.\r\n"
				  +" The next tab contains an overview of failed exams. Above are the buttons: 'Add',\r\n"
				  + "	'Remove' i 'Add grade'. Clicking the add button opens a list of subjects\r\n"
				  + "	that the student can listen to. The delete button allows you to remove subject\r\n"
				  + "	from the list if it is selected, and 'Add grade' allows us to enter a grade\r\n"
				  + "	to student. By entering a grade a given subject is moved from the list of failed to\r\n"
				  + "	list of passed exams, that is from the tab didnt pass in the tab passed. When\r\n"
				  + "	removing a subject we will see a dialog where we need to approve the removal.\r\n"
				  +" The search was implemented using RowFilter. If the appropriate \r\n"
				  + "	criterion is entered, only entities that meet the given criterion will be \r\n"
				  + "	displayed in the table. If such an entity does not exist, the table will be empty.\r\n"
				  +" Deleting entities is enabled using a button from the toolbar, a menu item\r\n"
				  + "	 or the appropriate accelerator or mnemonic. In order for the deletion\r\n"
				  + "	 to be performed, the entity must be selected. Invoking the deletion by the appropriate method\r\n"
				  + "	 opens a dialog where it is necessary to confirm the deletion. Deleting a given entity removes\r\n"
				  + "	 it from the table that is displayed. We need to be positioned \r\n"
				  +"	 in the appropriate tab to remove the entity we want.\r\n"
				  +" Student sorting is arranged so that it is possible to sort them by name, surname,\r\n"
				  + "	year, status, average and index. Index sort is edited\r\n"
				  + "	by creating a special comparator. Indexes are grouped first by major,\r\n"
				  + "	then by year of study and within the same year are sorted by number.\r\n"
				  +"	Other entities are sorted using TableRowSorter. Sorting is enabled\r\n"
				  + "	in descending and ascending order.\r\n"
				  +" Within the dialogue for editing the professor, we have two tabs. The first shows the current\r\n"
				  + "	information of the current professor that can be changed. In the following tab \r\n"
				  + "	there are subjects taught by the professor. There are buttons above the table \r\n"
				  + "	'Add subject' i 'Remove subject'. Click on the add button\r\n"
				  +"	 opens a list offering us subjects that the professor can teach.\r\n"
				  + "	Selecting an item and clicking 'Confirm' adds the item to the table. If\r\n"
				  + "	we want to remove the object, it is necessary to select it and select the \r\n"
				  +"	remove button.  A dialog will open where you need to approve the removal.\r\n"
				  +" There is a field in the change subject dialog where you can add\r\n"
				  + "	professor on a given subject (same as in the add dialog).\r\n"
				  + "	Clicking the '+' button opens the professor list and selects one of the\r\n"
				  + "	a text field is filled in. When a professor is selected, it is possible \r\n"
				  + "	click on the '-' button to remove the professor from the subject.\r\n"
				  +"	Removal is only completed by clicking the 'Confirm' button.\r\n"
				  +" Serialization is performed by writing all data in one\r\n "
				  + "	file to save\r\n"
				  +"	referential dependency. Deserialization reads the given data\r\n "
				  + "	each time you open the application, it fills in the lists of students, professors or subjects.\r\n\n";
		
		if( Locale.getDefault().equals( new Locale("sr", "RS")))
			text.setText(appDescription);
		else
			text.setText(appDescription2);
		help.add(scroll);
		help.pack();
		help.setLocationRelativeTo(MainFrame.getInstance());
		help.setVisible(true);
			
	}
}


