package bran.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Run {

	public static GraphDrawer run() {
        JTextField methodsParam = new JTextField(4);
        JTextField maxSizParam = new JTextField(4);
        JTextField trialAmountParam = new JTextField(4);
        JTextField testCasesParam = new JTextField(4);

        JPanel parameters = new JPanel();
        parameters.add(new JLabel("Method Names (Sep. by comma): "));
        parameters.add(methodsParam);
        parameters.add(Box.createHorizontalStrut(4));
        parameters.add(new JLabel("Max Collection Size: "));
        parameters.add(maxSizParam);
        parameters.add(Box.createHorizontalStrut(4));
        parameters.add(new JLabel("Trial Amount: "));
        parameters.add(trialAmountParam);
        parameters.add(Box.createHorizontalStrut(4));
        parameters.add(new JLabel("Test Cases: "));
        parameters.add(testCasesParam);

        GraphDrawer graphDrawer = null;

        if (JOptionPane.showConfirmDialog(null, parameters, 
                "Enter Parameters", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

			String[] methodNames = methodsParam.getText().compareTo("") == 0 ? new String[] { "add" }
					: methodsParam.getText().split(",");
			for (String s : methodNames)
				s = s.trim();

        	String maxSizeString = maxSizParam.getText();
        	String trialAmountString = trialAmountParam.getText();
        	String testCasesString = testCasesParam.getText();

        	int testCases = testCasesString.compareTo("") == 0 ? 200 : Integer.parseInt(testCasesString);

        	graphDrawer = new GraphDrawer(methodNames, maxSizeString.compareTo("") == 0 ? 10000 : Integer.parseInt(maxSizeString),
        			trialAmountString.compareTo("") == 0 ? 3 : Integer.parseInt(trialAmountString), testCases);

            JFrame window = new JFrame("Collection Simulations");
            window.setContentPane(graphDrawer);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//            window.setPreferredSize(new Dimension(500, 500));
//            window.pack();
//            window.setLocationRelativeTo(null);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            window.setVisible(true);
            Insets insets = window.getInsets();
            window.setSize(testCases + GraphDrawer.leftMargin + GraphDrawer.rightMargin + insets.left + insets.right,
            		(int) (GraphDrawer.MAX_TIME_VALUE_DEFAULT / 100 + GraphDrawer.topMargin + GraphDrawer.bottomMargin + insets.top + insets.bottom));
            window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
            window.setBackground(Color.WHITE);
            graphDrawer.init();
//            gD.start();
        }
        else {
        	parameters.setVisible(false);
        }
		return graphDrawer;
    }
 
}
