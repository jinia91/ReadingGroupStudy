
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Calculator extends JFrame implements ActionListener {

	private JPanel panel;
	private JTextField display;
	private JButton[] buttons;
	private String[] labels = { "+", "-", "x", "/", "=", "C" };
	private double result;
	private double number;
	private String operator;
	private boolean firstFlag = true;

	public Calculator() {

		display = new JTextField(30);
		display.setText("");

		Font bigFont = display.getFont().deriveFont(Font.PLAIN, 25f);
		display.setFont(bigFont);

		panel = new JPanel();

		panel.setLayout(new GridLayout(0, 2, 10, 10));

		buttons = new JButton[6];
		
		int index = 0;

		for (int i = 0; i < 6; i++) {

			buttons[index] = new JButton(labels[index]);

			panel.add(buttons[index]);

			buttons[index].addActionListener(this);

			index++;
		}

		add(display, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		pack();

	}

	public void actionPerformed(ActionEvent button) {

		if (button.getActionCommand().equals("C")) {

			display.setText("");
			result = 0;
			firstFlag = true;
			return;
		}

		if (button.getActionCommand().equals("=") && firstFlag) {

			if (operator.equals("+"))
				result += number;

			else if (operator.equals("-"))
				result -= number;

			else if (operator.equals("x"))

				result *= number;

			else if (operator.equals("/"))

				result /= number;

			display.setText("" + result);

			return;
		}

		number = Double.parseDouble(display.getText());

		if (firstFlag) {

			result = number;

			operator = button.getActionCommand();

			display.setText("");

			firstFlag = false;

		}

		else {

			if (operator.equals("+"))
				result += number;

			else if (operator.equals("-"))
				result -= number;

			else if (operator.equals("x"))

				result *= number;

			else if (operator.equals("/"))

				result /= number;

			display.setText("" + result);

			firstFlag = true;
		
		}
	}


}