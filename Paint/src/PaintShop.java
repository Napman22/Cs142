import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * This class is complete. DON'T CHANGE IT. It builds the GUI for the user.
 * <p>
 * There is no need for you to understand the code. But of course, if you have
 * any question, don't hesitate to ask me.
 */

public class PaintShop extends JFrame {

	private static final long serialVersionUID = 1L;
	// Set the look and feel according to the user's machine
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Possible values for the combo boxes
	private String[] footValues = new String[101];

	private String[] inchValues = new String[12];
	// initialize the arrays
	{
		for (int i = 0; i < footValues.length; i++)
			footValues[i] = "" + i;
		for (int i = 0; i < inchValues.length; i++)
			inchValues[i] = "" + i;
	}

	// Elements of the GUI
	private JPanel paintShopPanel = new JPanel();

	private JComboBox<String> cboHeightFeet = new JComboBox<String>(footValues);

	private JLabel lblHeight = new JLabel();

	private JLabel lblHeightFeet = new JLabel();

	private JComboBox<String> cboHeightInches = new JComboBox<String>(inchValues);

	private JLabel lblHeightInches = new JLabel();

	private JComboBox<String> cboLengthInches = new JComboBox<String>(inchValues);

	private JComboBox<String> cboLengthFeet = new JComboBox<String>(footValues);

	private JLabel lblLengthInches = new JLabel();

	private JLabel lblLengthFeet = new JLabel();

	private JLabel lblLength = new JLabel();

	private JComboBox<String> cboWidthInches = new JComboBox<String>(inchValues);

	private JComboBox<String> cboWidthFeet = new JComboBox<String>(footValues);

	private JLabel lblWidthInches = new JLabel();

	private JLabel lblWidthFeet = new JLabel();

	private JLabel lblWidth = new JLabel();

	private JButton btnCalculate = new JButton();

	private JLabel lblTitle = new JLabel();

	private JScrollPane scpResult = new JScrollPane();

	private JTextArea txtResult = new JTextArea();

	/**
	 * Constructs the GUI for the paint shop
	 */
	public PaintShop() {
		// Show the frame to compute its size
		setVisible(true);
		Insets insets = this.getInsets();

		// Size of the window
		setSize(new Dimension(450 + insets.left + insets.right, 500 + insets.top + insets.bottom));
		setTitle("Paint shop");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		guiInit();
		setVisible(true);
	}

	/**
	 * Places the different graphics components in the window
	 */
	private void guiInit() {
		paintShopPanel.setLayout(null);
		lblHeight.setText("Height");
		lblHeight.setBounds(new Rectangle(94, 48, 68, 17));
		lblHeightFeet.setText("feet");
		lblHeightFeet.setBounds(new Rectangle(213, 48, 33, 17));
		lblHeightInches.setText("inches");
		lblHeightInches.setBounds(new Rectangle(314, 48, 42, 17));
		lblLengthInches.setText("inches");
		lblLengthInches.setBounds(new Rectangle(314, 79, 42, 17));
		lblLengthFeet.setText("feet");
		lblLengthFeet.setBounds(new Rectangle(213, 79, 33, 17));
		lblLength.setToolTipText("");
		lblLength.setText("Length");
		lblLength.setBounds(new Rectangle(94, 79, 68, 17));
		lblWidthInches.setText("inches");
		lblWidthInches.setBounds(new Rectangle(314, 110, 42, 17));
		lblWidthFeet.setText("feet");
		lblWidthFeet.setBounds(new Rectangle(213, 110, 33, 17));
		lblWidth.setText("Width");
		lblWidth.setBounds(new Rectangle(94, 110, 68, 17));
		btnCalculate.setBounds(new Rectangle(180, 149, 89, 30));
		btnCalculate.setText("Calculate");
		btnCalculate.addActionListener(e -> btnCalculateActionPerformed());
		lblTitle.setFont(new java.awt.Font("Serif", 3, 30));
		lblTitle.setForeground(Color.blue);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setText("Paint Shop");
		lblTitle.setBounds(new Rectangle(0, 0, 450, 40));
		txtResult.setEditable(false);
		scpResult.setBounds(new Rectangle(10, 192, 430, 298));
		cboWidthInches.setBounds(new Rectangle(246, 109, 61, 19));
		cboWidthFeet.setBounds(new Rectangle(146, 109, 61, 19));
		cboLengthInches.setBounds(new Rectangle(246, 77, 61, 19));
		cboLengthFeet.setBounds(new Rectangle(146, 76, 61, 19));
		cboHeightInches.setBounds(new Rectangle(246, 43, 61, 19));
		cboHeightFeet.setBounds(new Rectangle(146, 43, 61, 19));
		add(paintShopPanel, BorderLayout.CENTER);
		paintShopPanel.add(lblHeight, null);
		paintShopPanel.add(cboHeightFeet, null);
		paintShopPanel.add(lblHeightFeet, null);
		paintShopPanel.add(cboHeightInches, null);
		paintShopPanel.add(lblHeightInches, null);
		paintShopPanel.add(cboLengthInches, null);
		paintShopPanel.add(lblLengthInches, null);
		paintShopPanel.add(cboLengthFeet, null);
		paintShopPanel.add(lblLengthFeet, null);
		paintShopPanel.add(lblLength, null);
		paintShopPanel.add(lblWidth, null);
		paintShopPanel.add(cboWidthFeet, null);
		paintShopPanel.add(lblWidthFeet, null);
		paintShopPanel.add(cboWidthInches, null);
		paintShopPanel.add(lblWidthInches, null);
		paintShopPanel.add(btnCalculate, null);
		paintShopPanel.add(lblTitle, null);
		paintShopPanel.add(scpResult, null);
		scpResult.getViewport().add(txtResult, null);
	}

	/**
	 * Calls the PaintShopCalculator to perform the paint computation
	 */
	private void btnCalculateActionPerformed() {
		// Dimensions of the room
		int heightFeet = Integer.parseInt((String) cboHeightFeet.getSelectedItem());
		int heightInches = Integer.parseInt((String) cboHeightInches.getSelectedItem());
		int widthFeet = Integer.parseInt((String) cboWidthFeet.getSelectedItem());
		int widthInches = Integer.parseInt((String) cboWidthInches.getSelectedItem());
		int lengthFeet = Integer.parseInt((String) cboLengthFeet.getSelectedItem());
		int lengthInches = Integer.parseInt((String) cboLengthInches.getSelectedItem());

		// Compute and display the amount of paint needed
		PaintShopCalculator pc = new PaintShopCalculator(heightFeet, heightInches, lengthFeet, lengthInches, widthFeet,
				widthInches);
		txtResult.setText(pc.toString());
	}

	public static void main(String[] args) {
		new PaintShop();
	}
}