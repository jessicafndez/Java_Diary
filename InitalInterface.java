package interfaces;

import java.awt.BorderLayout;

public class InitialInterface extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Container container;
	
	private JPanel initial;
	
	private JButton backBtn = new JButton("MENU");
	private JButton searchBtn;
	private JButton newBtn;
	
	private Color backgroundColor = new Color (255, 229, 204);
	private Color backgroundColor2 = new Color (255, 204, 204);
	private JTextField textField;
	
	private BufferedImage diaryLogo;

	/**
	 * Create the frame.
	 */
	public InitialInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Diary");
		//setBounds(0, 0, 500, 700);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		iniComponents();
		pack();
		
	}
	
	public void iniComponents() {
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		container.add(initial(), BorderLayout.CENTER);
	}
	
	public JPanel initial() {
		initial = new JPanel();
		initial.setLayout(null);
		initial.setPreferredSize(new Dimension(500, 700));		
		initial.setBackground(backgroundColor);
		
		searchBtn = new JButton("SEARCH");
		newBtn = new JButton("NEW");	
		searchBtn.setBounds(70, 570, 90, 20);
		newBtn.setBounds(350, 570, 90, 20);
		
		searchBtn.addActionListener(this);
		newBtn.addActionListener(this);
		
		JRadioButton jRadio1 = new JRadioButton("NAME");
		jRadio1.setBounds(70, 463, 90, 20);
		jRadio1.setBackground(backgroundColor);
		JRadioButton jRadio2 = new JRadioButton("SURNAME");
		jRadio2.setBounds(70, 486, 90, 20);
		jRadio2.setBackground(backgroundColor);
		
		initial.add(jRadio1);
		initial.add(jRadio2);
		
		initial.add(searchBtn);
		initial.add(newBtn);
		
		textField = new JTextField();
		textField.setBounds(70, 524, 189, 20);
		initial.add(textField);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textField.setColumns(10);
		
		try {
			diaryLogo = ImageIO.read(new File("res/mydiary.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel labelImage = new JLabel(new ImageIcon(diaryLogo));
		labelImage.setSize(400, 300);
		labelImage.setLocation(50, 10);
		
		initial.add(labelImage);
		
		return initial;
	}
	
	public JPanel btnMenu() {
		JPanel menuBackPanel = new JPanel();
		backBtn.setBounds(400, 670, 90, 20);
		backBtn.addActionListener(this);
		
		menuBackPanel.setBackground(backgroundColor2);
		menuBackPanel.add(backBtn);
		
		return menuBackPanel;
	}
	
	public void search() {	
		container.remove(initial);
		SearchInterface searchInterface = new SearchInterface();
		//container.add(searchPanel(), BorderLayout.CENTER);
		container.add(searchInterface, BorderLayout.CENTER);
		container.add(btnMenu(), BorderLayout.SOUTH);
		container.revalidate();
		container.repaint();		
	}
	
	public void newFriend() {
		container.remove(initial);
		NewFriendInterface newFriend = new NewFriendInterface();
		//container.add(addPanel(), BorderLayout.CENTER);
		container.add(newFriend, BorderLayout.CENTER);
		container.add(btnMenu(), BorderLayout.SOUTH);
		container.revalidate();
		container.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		JButton source = (JButton) arg0.getSource();
		
		if(source == searchBtn) {
			search();
		}
		if(source == newBtn) {
			newFriend();
		}	
		if (source == backBtn) {
			container.removeAll();
			iniComponents();
			container.revalidate();
			container.repaint();
		}
		
	}
}
