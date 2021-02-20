package view.dialogs;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.MainFrame;

public class AboutDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2566945927276159128L;

	public AboutDialog(){
		
		JDialog about = new JDialog();
		about.setTitle("About");
		//about.setSize(700, 600);
		about.setResizable(false);
		about.setIconImage(new ImageIcon("images1"+File.separator+"about.png").getImage());
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel student1 = new JPanel();
		student1.setLayout(new BorderLayout());
		student1.add(new JLabel(new ImageIcon("./images1/andjela.png")), BorderLayout.WEST);
		JPanel student1Text = new JPanel();
		String addText1 = "<html><b><br><br><br> Anđela Đurić, 21<b>"
						+ " <p>Rođena 3.12.1999. u Bijeljini, BiH. </p>"
						+ " <p>Završila gimnaziju 'Filip Višnjić' u Bijeljini 2018. godine.</p>"
						+ " <p>Student treće godine Fakulteta tehničkih nauka u Novom Sadu, </p>"
						+ " <p>smjer računarstvo i automatika.</p> "
						+ " <p><br><br> mail: andjelajduric@outlook.com</p>";
		
		String addText1E = "<html><b><br><br><br> Andjela Djuric, 21<b>"
				+ " <p>Born on 3rd December 1999. in Bijeljina, BiH. </p>"
				+ " <p>Graduated Grammar school 'Filip Višnjic' in Bijeljina in 2018.</p>"
				+ " <p>Third year student of Faculty of Technical Sciences in Novi Sad, </p>"
				+ " <p>module Computing and Control Engineering.</p> "
				+ " <p><br><br> mail: andjelajduric@outlook.com</p>";
		
		if( Locale.getDefault().equals( new Locale("sr", "RS")))
			student1Text.add(new JLabel(addText1));
		else
			student1Text.add(new JLabel(addText1E));
		
		student1.add(student1Text, BorderLayout.EAST);
		
		JPanel student2 = new JPanel();
		student2.setLayout(new BorderLayout());
		student2.add(new JLabel(new ImageIcon("./images1/petra.png")), BorderLayout.EAST);
		JPanel student2Text = new JPanel();
		String addText2 = "<html><b><br><br><br> Petra Jović, 21<b>"
						+ " <p>Rođena 22.1.1999. u Bijeljini, BiH</p>"
						+ " <p>Završila gimnaziju 'Filip Visnjić' u Bijeljini 2018. godine.</p>"
						+ " <p>Student treće godine Fakulteta tehničkih nauka u Novom Sadu, </p>"
						+ " <p>smjer računarstvo i automatika.</p> "
						+ " <p><br><br> mail: jovic.petra99@gmail.com</p>"
						+ " <p><br><br><br><br><br><br> © Version 1.0.0";
		
		String addText2E = "<html><b><br><br><br> Petra Jovic, 21<b>"
				+ " <p>Born on 22.1.1999. in Bijeljina, BiH.</p>"
				+ " <p>Graduated Grammar school 'Filip Višnjic' in Bijeljina in 2018.</p>"
				+ " <p>Third year student of Faculty of Technical Sciences in Novi Sad, </p>"
				+ " <p>module Computing and Control Engineering.</p> "
				+ " <p><br><br> mail: jovic.petra99@gmail.com</p>"
				+ " <p><br><br><br><br><br><br> © Version 1.0.0";
		if( Locale.getDefault().equals( new Locale("sr", "RS")))
			student2Text.add(new JLabel(addText2));
		else
			student2Text.add(new JLabel(addText2E));
		
		student2.add(student2Text, BorderLayout.WEST);
		
		panel.add(student1, BorderLayout.NORTH);
		panel.add(student2, BorderLayout.SOUTH);
		about.add(panel);
		about.pack();
		about.setLocationRelativeTo(MainFrame.getInstance());
		about.setVisible(true);
	
	}
}