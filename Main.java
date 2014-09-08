package interfaces;

import javax.swing.SwingUtilities;

public class main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	InitialInterface initialInterface = new InitialInterface();
                	initialInterface.setVisible(true);
                	
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
	});

	}

}
