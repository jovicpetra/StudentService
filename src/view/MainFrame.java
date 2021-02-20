package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import controller.SerializeClass;

import javax.swing.ImageIcon;

public class MainFrame extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8456560429229699542L;

	private ResourceBundle resourceBundle;
	private ToolBar tb;
	private MyMenuBar menu;
	private MyStatusBar status;
	private TabbedPane tabbedPane;
	
	private static MainFrame instance = null;
	
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	public MainFrame() {		
		
		SerializeClass.getInstance().deserialize();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(3 * screenWidth / 4, 3 * screenHeight / 4);
		setTitle("Studentska služba");
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("images"+File.separator+"icon.png").getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowListener() {

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
				SerializeClass.getInstance().serialize();
			
				JFrame frame = (JFrame) e.getComponent();
				int code = JOptionPane.showConfirmDialog(frame, resourceBundle.getString("zatvaranjeAplikacije"),
						resourceBundle.getString("zatvaranjeAplikacijeNaslov"), JOptionPane.YES_NO_OPTION);
				if (code != JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				} else {
					frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		tb = new ToolBar();
		this.add(tb, BorderLayout.NORTH);
		
		//MENU_BAR
		menu = new MyMenuBar();
		this.setJMenuBar(menu);
		
		//SATUS_BAR
		status = new MyStatusBar();
		this.add(status, BorderLayout.SOUTH);
		//Tabovi
		tabbedPane = TabbedPane.getInstance();
		this.add(tabbedPane, BorderLayout.CENTER);
		
		//LOKALIZACIJA
		Locale.setDefault(new Locale("sr", "RS"));
		setResourceBundle(ResourceBundle.getBundle("gui.MessageResources.MessageResources", Locale.getDefault()));

		UIManager.put("OptionPane.yesButtonText", resourceBundle.getObject("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getObject("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getObject("okOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getObject("cancelOption"));

		setVisible(true);
		
	}
	
	public void changeLanguage() {

		resourceBundle = ResourceBundle.getBundle("gui.MessageResources.MessageResources", Locale.getDefault());
		setTitle(resourceBundle.getString("naslovAplikacije"));
		menu.initComponents();
		status.initComponents();
		tabbedPane.initComponents();
		tb.initComponents();
		initComponents();

		UIManager.put("OptionPane.yesButtonText", resourceBundle.getObject("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getObject("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getObject("okOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getObject("cancelOption"));
	}

	public void initComponents() {
		setTitle(resourceBundle.getString("naslovAplikacije"));
	}
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

}
