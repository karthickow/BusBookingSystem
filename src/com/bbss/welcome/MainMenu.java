package com.bbss.welcome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.bbss.author.Author;
import com.bbss.booking.Booking;
import com.bbss.bus.route.Route;
import com.bbss.constants.Constants;
import com.bbss.employee.Employee;
import com.bbss.main.Main;
import com.bbss.passenger.PASS;
import com.bbss.payment.Payment;
import com.bbss.report.Booking_report;
import com.bbss.report.Bus_Details;
import com.bbss.report.Scheduling_report;
import com.bbss.report.employee_report;
import com.bbss.schedule.Schedule;
import com.bbss.user.NewUser;
import com.bbss.util.MyCalc;
import com.bbss.util.editor;
import com.bbss.util.frmSplash;

public class MainMenu extends JFrame implements WindowListener { 
	private static final long serialVersionUID = -6366475090524223554L;

	JPanel Panel1;
	private JLabel welcome;
	public JMenu MnuFile,MnuRec,MnuReport;
	String StrBusinesTitle; 
	private	static JMenuItem ItmExit, Booking,Scheduling,Payment,NewUser,Passenger,Buses,Emps,Routes,Busrpt,Emprpt,shedrpt,bookrpt;
	public JButton  NewJButton;
    Connection getConnection;
	Main  buses;
	Employee Emp;
	Author aut;
	Schedule Sched;
	Route Rut;
	PASS pass;
	ResultSet rsLogin;
	
	JDesktopPane Desk1 = new JDesktopPane();	
	//JLabel BusinessTitleLabel = new JLabel();
	JLabel header;
	private Date currDate = new Date();
	Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
	frmSplash FormSplash = new frmSplash();
	Thread ThFormSplash = new Thread(FormSplash);
	
    public MainMenu(){
   	
    	super(Constants.SUPER_TITLE);
   			
		header=new JLabel(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/Logo.png"));
	    welcome = new JLabel ("Welcome today's date is " + currDate + " ",JLabel.CENTER);
	    welcome.setFont(new Font("Dialog", Font.PLAIN, 12));
		welcome.setForeground(Color.blue);
		/*BusinessTitleLabel.setText(StrBusinesTitle);
		BusinessTitleLabel.setHorizontalAlignment(JLabel.LEFT);
		BusinessTitleLabel.setForeground(new Color(166,0,0));*/
	     
	    addWindowListener(this);
	
		Desk1.setBackground(Color.gray);
		Desk1.setBorder(BorderFactory.createEmptyBorder());
		Desk1.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	        
	    Panel1 = new JPanel(new BorderLayout());
	    Panel1.setBackground(Color.gray);
		Panel1.setBorder(BorderFactory.createLoweredBevelBorder());
	    Panel1.add(new JScrollPane(Desk1),BorderLayout.CENTER);
	    
	    // Used to add logo for the application
		getContentPane().add(CreateJToolBar(),BorderLayout.PAGE_START);
		getContentPane().add(Panel1,BorderLayout.CENTER);
	
	    getContentPane().add(welcome,BorderLayout.PAGE_END,JLabel.CENTER);
	    // Used to add menubar, menus and submenus for the application 
		setJMenuBar(CreateJMenuBar());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/appicon.png").getImage());
		setLocation(0,0);
		setSize(screen);
		setVisible(true);
		//show(); 
    }
    
    // Used to add menubar, menus and submenus for the application
    protected JMenuBar CreateJMenuBar(){
    	
    	JMenuBar NewJMenuBar = new JMenuBar();

    	MnuFile = new JMenu("Operations");
		MnuFile.setForeground((Color.blue));
		MnuFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		MnuFile.setMnemonic(KeyEvent.VK_O);
		MnuFile.setBackground(new Color(255,255,255));
		NewJMenuBar.add(MnuFile);
		MnuFile.setEnabled(false);
		
		NewUser = new JMenuItem("AddNew User");
		NewUser.setForeground(Color.blue);
		NewUser.setFont(new Font("Dialog", Font.PLAIN, 12));
		NewUser.setMnemonic(KeyEvent.VK_L);
		NewUser.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/new.png"));
		NewUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		NewUser.setActionCommand("new");
		NewUser.addActionListener(JMenuActionListener);
		NewUser.setBackground(new Color(255,255,255));
		
		JMenuItem ItmLockApp = new JMenuItem("Lock Application");
		ItmLockApp.setForeground(Color.blue);
		ItmLockApp.setFont(new Font("Dialog", Font.PLAIN, 12));
		ItmLockApp.setMnemonic(KeyEvent.VK_N);
		ItmLockApp.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/lockapplication.png"));
		ItmLockApp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK));
		ItmLockApp.setActionCommand("lockapp");
		ItmLockApp.addActionListener(JMenuActionListener);
		ItmLockApp.setBackground(new Color(255,255,255));
		
		ItmExit = new JMenuItem("Exit");
		ItmExit.setForeground(Color.blue);
		ItmExit.setFont(new Font("Dialog", Font.PLAIN, 12));
		ItmExit.setMnemonic(KeyEvent.VK_E);
		ItmExit.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/exit.png"));
		ItmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		ItmExit.setActionCommand("exit");
		ItmExit.addActionListener(JMenuActionListener);
		ItmExit.setBackground(new Color(255,255,255));

	    MnuFile.add(NewUser);
		//MnuFile.add(ItmLockApp);
		MnuFile.addSeparator();
		MnuFile.add(ItmExit);
				
		MnuRec = new JMenu("Files");
		MnuRec.setFont(new Font("Dialog", Font.PLAIN, 12));
		MnuRec.setForeground ((Color.blue));
		MnuRec.setMnemonic(KeyEvent.VK_F);
		MnuRec.setBackground(new Color(255,255,255));
		NewJMenuBar.add(MnuRec);
		MnuRec.setEnabled(false);
		
	    Buses = new JMenuItem("Buses");
		Buses.setForeground(Color.blue);
		Buses.setEnabled(true);
		Buses.setFont(new Font("Dialog", Font.PLAIN, 12));
		Buses.setMnemonic(KeyEvent.VK_B);
	    Buses.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/bus.png"));
		Buses.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		Buses.setActionCommand("Buses");
		Buses.addActionListener(JMenuActionListener);
		Buses.setBackground(new Color(255,255,255));
	
		MnuRec.add(Buses); 
				
		Emps = new JMenuItem("Employees");
		Emps.setForeground(Color.blue);
		Emps.setEnabled(true);
		Emps.setFont(new Font("Dialog", Font.PLAIN, 12));
		Emps.setMnemonic(KeyEvent.VK_E);
		Emps.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/employees.png"));
		Emps.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		Emps.setActionCommand("Emp");
		Emps.addActionListener(JMenuActionListener);
		Emps.setBackground(new Color(255,255,255));
	
		MnuRec.add(Emps);
		
	    //NewJMenuBar.setBackground(new Color(255,255,255));
			    
	    Routes = new JMenuItem("Routes");
	    Routes.setEnabled(true);
	    Routes.setForeground(Color.blue);
	    Routes.setFont(new Font("Dialog", Font.PLAIN, 12));
		Routes.setMnemonic(KeyEvent.VK_R);		
		Routes.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/route.png"));		
		Routes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		Routes.setActionCommand("rute");
		Routes.addActionListener(JMenuActionListener);
		Routes.setBackground(new Color(255,255,255));		
	
		MnuRec.add(Routes);
		
		Passenger = new JMenuItem("Passengers");
		Passenger.setForeground(Color.blue);
		Passenger.setEnabled(false);
		Passenger.setFont(new Font("Dialog", Font.PLAIN, 12));
		Passenger.setMnemonic(KeyEvent.VK_P);
	    Passenger.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/pass.png"));
		Passenger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		Passenger.setActionCommand("pase");
		Passenger.addActionListener(JMenuActionListener);
		Passenger.setBackground(new Color(255,255,255));
	
		MnuRec.add(Passenger); 
		
	    JMenu MnuTrans = new JMenu("Processes ");
		MnuTrans.setFont(new Font("Dialog", Font.PLAIN, 12));
		MnuTrans.setForeground((Color.blue));
		MnuTrans.setMnemonic(KeyEvent.VK_P);
		MnuTrans.setBackground(new Color(255,255,255));
		
		NewJMenuBar.add(MnuTrans);
			
		Scheduling = new JMenuItem("Scheduling");
		Scheduling.setEnabled(false);
		Scheduling.setForeground(Color.blue);
		Scheduling.setFont(new Font("Dialog", Font.PLAIN, 12));
		Scheduling.setMnemonic(KeyEvent.VK_S);
	    Scheduling.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/calendar.png"));
		Scheduling.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		Scheduling.setActionCommand("ched");
		Scheduling.addActionListener(JMenuActionListener);
		Scheduling.setBackground(new Color(255,255,255));
	
		MnuTrans.add(Scheduling);
		
	    Booking=new JMenuItem("Booking");
		Booking.setEnabled(false);
		Booking.setForeground(Color.blue);
		Booking.setFont(new Font("Dialog",Font.PLAIN,12));
		Booking.setMnemonic(KeyEvent.VK_B);
		Booking.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/book.png"));
		Booking.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,ActionEvent.CTRL_MASK));
	    Booking.setActionCommand("buk");
		Booking.addActionListener(JMenuActionListener);
		Booking.setBackground(new Color(255,255,255));
	
		MnuTrans.add(Booking );
		
		Payment = new JMenuItem("Payments");
		Payment.setForeground(Color.blue);
		Payment.setEnabled(false);
		Payment.setFont(new Font("Dialog", Font.PLAIN, 12));
		Payment.setMnemonic(KeyEvent.VK_P);
	    Payment.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/pay.png"));
		Payment.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK));
		Payment.setActionCommand("Pay");
		Payment.addActionListener(JMenuActionListener);
		Payment.setBackground(new Color(255,255,255));
	
		MnuTrans.add(Payment);

		MnuReport = new JMenu("Reports ");
		MnuReport.setFont(new Font("Dialog", Font.PLAIN, 12));
		MnuReport.setForeground(Color.blue);
		MnuReport.setMnemonic(KeyEvent.VK_R);
		MnuReport.setBackground(new Color(255,255,255));
		
		//NewJMenuBar.add(MnuReport);	
		
		Busrpt = new JMenuItem("Bus Report");
		Busrpt.setForeground(Color.blue);
		Busrpt.setFont(new Font("Dialog", Font.PLAIN, 12));
		Busrpt.setMnemonic(KeyEvent.VK_P);
	    Busrpt.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/busr.png"));
		Busrpt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
		Busrpt.setActionCommand("Busrpt");
		Busrpt.addActionListener(JMenuActionListener);
		Busrpt.setBackground(new Color(255,255,255));
	   
		MnuReport.add(Busrpt);
	    
	    Emprpt = new JMenuItem("Employee Report");
		Emprpt.setForeground(Color.blue);
		//Busrpt.setEnabled(false);
		Emprpt.setFont(new Font("Dialog", Font.PLAIN, 12));
		Emprpt.setMnemonic(KeyEvent.VK_P);
	    Emprpt.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/empr.png"));
		Emprpt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		Emprpt.setActionCommand("emprpt");
		Emprpt.addActionListener(JMenuActionListener);
		Emprpt.setBackground(new Color(255,255,255));
	    
		MnuReport.add(Emprpt);
	    
	    shedrpt = new JMenuItem("Scheduling Report");
		shedrpt.setForeground(Color.blue);
		shedrpt.setFont(new Font("Dialog", Font.PLAIN, 12));
		shedrpt.setMnemonic(KeyEvent.VK_S);
	    shedrpt.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/schr.png"));
		shedrpt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
		shedrpt.setActionCommand("schedrpt");
		shedrpt.addActionListener(JMenuActionListener);
		shedrpt.setBackground(new Color(255,255,255));
	    
		MnuReport.add(shedrpt);
	    
	    bookrpt = new JMenuItem("Booking Report");
		bookrpt.setForeground(Color.blue);
		bookrpt.setFont(new Font("Dialog", Font.PLAIN, 12));
		bookrpt.setMnemonic(KeyEvent.VK_B);
	    bookrpt.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/bookr.png"));
		bookrpt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
		bookrpt.setActionCommand("bukdrpt");
		bookrpt.addActionListener(JMenuActionListener);
		bookrpt.setBackground(new Color(255,255,255));
	    
		MnuReport.add(bookrpt);
		
		JMenu MnuTools = new JMenu("Tools ");
		MnuTools.setFont(new Font("Dialog", Font.PLAIN, 12));
		MnuTools.setForeground(Color.blue);
		MnuTools.setMnemonic(KeyEvent.VK_T);
		MnuTools.setBackground(new Color(255,255,255));
		
		//NewJMenuBar.add(MnuTools);
			
		JMenuItem Calculator = new JMenuItem("Calculator");
		Calculator.setForeground(Color.blue);
		Calculator.setFont(new Font("Dialog", Font.PLAIN, 12));
		Calculator.setMnemonic(KeyEvent.VK_C);
	    Calculator.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/calc.png"));
		Calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		Calculator.setActionCommand("calculator");
		Calculator.addActionListener(JMenuActionListener);
		Calculator.setBackground(new Color(255,255,255));
	
		MnuTools.add(Calculator);
	    
	    JMenuItem Note = new JMenuItem("EDITOR");
	    Note.setForeground(Color.blue);
		Note.setFont(new Font("Dialog", Font.PLAIN, 12));
		Note.setMnemonic(KeyEvent.VK_N);
	    Note.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/notepad.png"));
		Note.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		Note.setActionCommand("note");
		Note.addActionListener(JMenuActionListener);
		Note.setBackground(new Color(255,255,255));
	
		MnuTools.add(Note);
	    
	    JMenu Options=new JMenu ("Change Background");
	    Options.setFont(new Font("Dialog",Font.PLAIN,12));
	    Options.setForeground(Color.blue);
	    Options.setMnemonic(KeyEvent.VK_C);
	    Options.setBackground(new Color(255,255,255));
		
	    NewJMenuBar.add(Options);
		
		JMenuItem Change = new JMenuItem("Options");
		Change.setForeground(Color.blue);
		Change.setFont(new Font("Dialog", Font.PLAIN, 12));
		Change.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/background.png"));
		Change.setMnemonic(KeyEvent.VK_O);
		Change.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		Change.setActionCommand("change");
		Change.addActionListener(JMenuActionListener);
	
		Options.add(Change );
	    
	    JMenu MnuHelp = new JMenu("Help ");
		MnuHelp.setFont(new Font("Dialog", Font.PLAIN, 12));
		MnuHelp.setForeground(Color.blue);
		MnuHelp.setMnemonic(KeyEvent.VK_H);
		MnuHelp.setBackground(new Color(255,255,255));
		NewJMenuBar.add(MnuHelp);
			
		JMenuItem Authour = new JMenuItem("Author");
		Authour.setForeground(Color.blue);
		Authour.setFont(new Font("Dialog", Font.PLAIN, 12));
		Authour.setMnemonic(KeyEvent.VK_A);
	    Authour.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/Author.png"));
		Authour.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		Authour.setActionCommand("me");
		Authour.addActionListener(JMenuActionListener);
	
		MnuHelp.add(Authour);
		
		JMenuItem man = new JMenuItem("User Manual");
		man.setForeground(Color.blue);
		man.setFont(new Font("Dialog", Font.PLAIN, 12));
		man.setMnemonic(KeyEvent.VK_U);
	    man.setIcon(new ImageIcon(Constants.USER_DIR+"/src/com/bbss/images/manual.png"));
		man.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
		man.setActionCommand("user");
		man.addActionListener(JMenuActionListener);
	
		MnuHelp.add(man);
		
	    //NewJMenuBar.setBackground(new Color(255,255,255));
		
	    return NewJMenuBar;
    }
	
    // Used to add logo for the application
    protected JToolBar CreateJToolBar (){
    	JToolBar NewJToolBar = new JToolBar("Toolbar");
	
		NewJToolBar.setMargin(new Insets(0,0,0,0));	
		NewJToolBar.add(header);
		NewJToolBar.addSeparator();
		NewJToolBar.addSeparator();
		NewJToolBar.addSeparator();
		NewJToolBar.addSeparator();
		NewJToolBar.addSeparator();
		NewJToolBar.addSeparator();
		NewJToolBar.addSeparator();
		
		return NewJToolBar;
	}		
	
	protected void buses() throws SQLException{
		boolean AlreadyLoaded = isLoaded("Bus Records");
		
		if(AlreadyLoaded==false){
			buses = new Main();
			Desk1.add(buses);
			buses.setVisible(true);
			//buses.show();
		}
	}
	
	protected void emp() throws SQLException{
		boolean AlreadyLoaded = isLoaded("Employee Details");
		
		if(AlreadyLoaded==false){
			Emp = new Employee();
			Desk1.add(Emp);
			Emp.setVisible(true);
			//Emp.show();
		}
	}
	
	protected void Aut() throws SQLException{
		boolean AlreadyLoaded = isLoaded("Employee Details");
		
		if(AlreadyLoaded==false){
			aut = new Author();
			Desk1.add(aut);
			aut.setVisible(true);
			//aut.show();
		}
	}
	
	protected void rut() throws SQLException{
		boolean AlreadyLoaded = isLoaded("fgfg");

		if(AlreadyLoaded==false){
			Rut = new Route();
			Desk1.add(Rut);
			Rut.setVisible(true);
			//Rut.show();
		}
	}
	
	protected void pase() throws SQLException{
		boolean AlreadyLoaded = isLoaded("rute");
	
		if(AlreadyLoaded==false){
			pass = new PASS();
			Desk1.add(pass);
			pass.setVisible(true);
			//pass.show();
		}	
	}
	
	protected void Sched() throws SQLException{	
		boolean AlreadyLoaded = isLoaded("Bus Records");
	
		if(AlreadyLoaded==false){		
			Sched = new Schedule();
			Desk1.add(buses);
			Sched.setVisible(true);
			//Sched.show();
		}
	}
	
	ActionListener JMenuActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			String srcObject = e.getActionCommand();
			
			if(srcObject=="Buses"){
				try{
					buses();
				}catch(Exception sqle){
					JOptionPane.showMessageDialog(null, sqle.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
				}
			} 
			
			else if(srcObject=="Emp"){
				try{
					emp();
				}catch(Exception sqle){
					JOptionPane.showMessageDialog(null, sqle.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(srcObject=="rute"){
				try{
					rut();
				}catch(Exception sqle){
					JOptionPane.showMessageDialog(null, sqle.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(srcObject=="calculator"){
				try{
					//runComponents("Calc.exe");
					new MyCalc().setVisible(true);
				}catch(Exception sqle){
					JOptionPane.showMessageDialog(null, sqle.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(srcObject=="pase"){
				try{
					pase();
				}catch(Exception sqle){
					JOptionPane.showMessageDialog(null, sqle.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(srcObject=="note"){ 
				try{
					//runComponents("Notepad.exe");
					new editor().setVisible(true);
				}catch(Exception sqle){
					JOptionPane.showMessageDialog(null, sqle.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(srcObject=="me"){
                new Author().setVisible(true); 
            }
            else if(srcObject=="ched"){                  
            	new Schedule().setVisible(true);
			}
			
            else if(srcObject=="exit"){
				UnloadWindow();
			}
			
            else if(srcObject=="user"){
				runURL(Constants.USER_DIR+"/src/com/bbss/manual/Main.html");
			}
			
            else if(srcObject=="buk"){
            	new Booking().setVisible(true);
			}
 			
            else if (srcObject=="new"){
				new NewUser().setVisible(true);
			}
		
			else if (srcObject=="Pay"){
				new Payment().setVisible(true);
			}
			
			else if (srcObject=="Busrpt"){
				new Bus_Details().setVisible(true);
			}
			
			else if (srcObject=="emprpt"){
				new employee_report().setVisible(true);
			}
			
			else if (srcObject=="schedrpt"){
				new Scheduling_report().setVisible(true);
			}
			
			else if (srcObject=="bukdrpt"){
				new Booking_report().setVisible(true);
			}		
			
			else if (srcObject == "change") {
				Color cl = new Color (153, 153, 204);
				cl= JColorChooser.showDialog (null, "Choose Background Color", cl);
				
				if (cl == null) { }
				else {
					Desk1.setBackground (cl);
					Desk1.repaint ();
				}
			}
		}
	};	

	public void windowOpened(WindowEvent e){
	}
	
	public void windowClosing(WindowEvent e){
		UnloadWindow();
	}
	
	public void windowClosed(WindowEvent e){
	}
	
	public void windowIconified(WindowEvent e){
	}
	
	public void windowDeiconified(WindowEvent e){
	}
	
	public void windowActivated(WindowEvent e){
	}
	
	public void windowDeactivated(WindowEvent e){
	}
	
	protected void UnloadWindow(){
		
		String ObjButtons[] = {"Yes","No"};
		int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
		Constants.EXIT, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons,ObjButtons[1]);
		
		if(PromptResult==0){
			System.out.println(
				"\n\n" +
				"------------------------------------------------------------\n\n" +
				"THANK YOU FOR USING THIS SYSTEM!!! \n\n" +
				"------------------------------------------------------------\n\n" +
				Constants.ABOUT +
				"------------------------------------------------------------\n" +
				"\n\n"
				);
			System.exit(0);
		}
	}
	
	protected boolean isLoaded(String FormTitle) {

		JInternalFrame Form[] = Desk1.getAllFrames();
		for (int i = 0; i < Form.length; i++) {
			if (Form[i].getTitle().equalsIgnoreCase (FormTitle)) {
				Form[i].show ();
				try{
					Form[i].setIcon(true);
					Form[i].setSelected(true);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
				}
				return true;
			}
		}
		return false;
	}

  /*public static void main(String[] args){
   		JFrame.setDefaultLookAndFeelDecorated(true);
		new MainMenu().setVisible(true);
	}*/

  	protected void runComponents(String sComponents){
		Runtime rt = Runtime.getRuntime();
		try{
			rt.exec(sComponents);
		}
		catch(IOException evt){
			JOptionPane.showMessageDialog(null, evt.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void runURL(String sURL){		
		try{
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + sURL);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), Constants.ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}		
	
	public void enabeManagerPermission(){
		MnuFile.setEnabled(true);
		MnuRec.setEnabled(true);
		Booking.setEnabled(true);
		Scheduling.setEnabled(true);
		Payment.setEnabled(true);
		Routes.setEnabled(true);
		Emps.setEnabled(true);
		Buses.setEnabled(true);
		Passenger.setEnabled(true);
	}
	
	public void enabeSupervisorPermission(){
		MnuFile.setEnabled(true);
		NewUser.setEnabled(false);
		ItmExit.setEnabled(true);
		MnuRec.setEnabled(true);
		Scheduling.setEnabled(true);
		Payment.setEnabled(true);
		Routes.setEnabled(true);
		Emps.setEnabled(true);
		Buses.setEnabled(true);
	}
	
	public void enableBookingClerkPermission(){
		MnuFile.setEnabled(true);
		NewUser.setEnabled(false);
		Booking.setEnabled(true);
		Payment.setEnabled(true);
		MnuRec.setEnabled(true);
		Passenger.setEnabled(true);
		ItmExit.setEnabled(true);
	}		
}