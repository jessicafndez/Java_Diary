package interfaces;

import java.awt.BorderLayout;

public class InitialInterface extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Container container;
	
	private JPanel initial;
	
	private JButton backBtn = new JButton("MENU");
	private JButton searchBtn;
	private JButton newBtn;
	private JRadioButton jRadioName;
	private JRadioButton jRadioSurname;
	private JComboBox comboBoxOptions;
	private ButtonGroup buttonGroup;
	
	private Color backgroundColor = new Color (255, 229, 204);
	private Color backgroundColor2 = new Color (255, 204, 204);
	private JTextField textField;
	
	private BufferedImage diaryLogo;
	private JButton btnS;
	
	private List<Contacts> listaContactosName = new ArrayList<Contacts>();
	private String contactSelected;
	private String contactName, contactSurname;

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
		searchBtn.setBounds(50, 619, 90, 20);
		newBtn.setBounds(360, 619, 90, 20);
		
		searchBtn.addActionListener(this);
		newBtn.addActionListener(this);
		
		initial.add(searchBtn);
		initial.add(newBtn);
		
		textField = new JTextField();
		textField.setBounds(50, 490, 189, 20);
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
		
		btnS = new JButton("S");
		btnS.setBounds(249, 489, 46, 23);
		btnS.addActionListener(this);
		initial.add(btnS);
		
		comboBoxOptions = new JComboBox();
		comboBoxOptions.setBounds(50, 544, 245, 20);
		initial.add(comboBoxOptions);
		
		jRadioName = new JRadioButton("NAME");
		jRadioName.setBounds(50, 463, 90, 20);
		jRadioName.setBackground(backgroundColor);
		jRadioName.setActionCommand("name");
		
		jRadioSurname = new JRadioButton("SURNAME");
		jRadioSurname.setBounds(153, 463, 90, 20);
		jRadioSurname.setBackground(backgroundColor);
		jRadioSurname.setActionCommand("surname");
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(jRadioName);
		buttonGroup.add(jRadioSurname);
		
		jRadioName.setSelected(true);
		
		initial.add(jRadioName);
		initial.add(jRadioSurname);
		
		
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
		String nameSearching = textField.getText();
	//	SearchInterface searchInterface = new SearchInterface(nameSearching);
		SearchInterface searchInterface = new SearchInterface(contactName, contactSurname);
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
	
	public void loadComboBox(String selection) {
		String name = textField.getText();
		
		System.out.println("We wanna search by: "+selection);
		System.out.println("NAME: "+name);
		
		final String nam;
		final String sur;
		
		comboBoxOptions.removeAllItems();
		
		if (name.equalsIgnoreCase("")) {
			System.out.println("ENTER NAME TO SEARCH!!!");
			JOptionPane.showMessageDialog(null,"ENTER NAME OR SURNAME TO SEARCH", "INVALID SEARCH", JOptionPane.INFORMATION_MESSAGE);
		}else {	
			if (selection.equalsIgnoreCase("name")) {
				DataManager dm = new DataManager();
				System.out.println("SEARCH BY NAME: "+name);
				listaContactosName = dm.getContactsName(name);
				System.out.println("Adding contacts...");
				Iterator iter = listaContactosName.iterator();
				while (iter.hasNext()) {
					Contacts c = (Contacts)iter.next();
					String n = c.getName();
					String s = c.getSurname();
					String completName = n+ ","+s;
					System.out.println("---"+completName);
					comboBoxOptions.addItem(completName);
				}
			}
			else {
				System.out.println("SEARCH BY SURNAME");
				DataManager dm = new DataManager();
				System.out.println("SEARCH BY SURNAME: "+name);
				listaContactosName = dm.getContactsSurname(name);
				System.out.println("Adding contacts...");
				Iterator iter = listaContactosName.iterator();
				while (iter.hasNext()) {
					Contacts c = (Contacts)iter.next();
					String n = c.getName();
					String s = c.getSurname();
					String completName = s+ ","+n;
					System.out.println("---"+completName);
					comboBoxOptions.addItem(completName);
				}
			}
		}
		
		if (comboBoxOptions.getItemCount() == 0) {
			JOptionPane.showMessageDialog(null,"CONTACT NAME OR SURNAME DON'T EXIST IN DATABASE", "INVALID SEARCH", JOptionPane.INFORMATION_MESSAGE);
		}
		
		comboBoxOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contactSelected = comboBoxOptions.getSelectedItem().toString();
				System.out.println("SELECTED: "+contactSelected);
				String[] separateString = contactSelected.split(",");
				contactName = separateString[0];
				contactSurname = separateString[1];
				
				System.out.println("____"+contactName+"__"+contactSurname);
				}
		});
		
		//String[] separateString = contactSelected.split(", ");
		//System.out.println("ADDING, "+separateString[0]+ "--"+separateString[1]);
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
		
		if (source == btnS) {
			System.out.println("EMPTY: "+listaContactosName.isEmpty());
			System.out.println("Size: "+listaContactosName.size());
		
			System.out.println("Loading ..."+textField.getName());	
			String selection = buttonGroup.getSelection().getActionCommand();
			System.out.println("SELECTED: "+selection);
		
			loadComboBox(selection);
		}
		
	}
}

