package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.FrontController;
import model.CalDto;

class Calculator extends JFrame implements ActionListener {

	private JPanel panel;
	private JTextField display;
	private JButton[] buttons;
	private String[] labels = { "+", "-", "x", "/", "=", "C" };

	private CalDto calDto;
	private static FrontController frontController;

	
	// �����ڷ� ȭ�� ������ + ����Ʈ ��Ʈ�ѷ� ��ü ����
	public Calculator() {
		
		// ������ ������ ������ �� �� �ִ� �ڵ�, �׷��� ���������� ����ϸ� �˾Ƽ� �ذ�����
		frontController = new FrontController();
		//
		calDto = new CalDto();
		
		// UI ������
		display = new JTextField(20);
		display.setText("");

		Font bigFont = display.getFont().deriveFont(Font.PLAIN, 25f);
		display.setFont(bigFont);

		panel = new JPanel();

		panel.setLayout(new GridLayout(0, 2, 5, 5));

		buttons = new JButton[6];

		for (int i = 0; i < 6; i++) {

			buttons[i] = new JButton(labels[i]);
			buttons[i].setFont(new Font("��������",Font.BOLD,40));
			panel.add(buttons[i]);
			
			buttons[i].addActionListener(this);

		}
		
		add(display, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		
		setTitle("CALCULATOR");
		setVisible(true);
		setPreferredSize(new Dimension(400,400));
		setResizable(false);
		pack();
	}
	
	
	// O/I�����޼ҵ�
	public void actionPerformed(ActionEvent button) {

		// input ���� dto�� ����
		String inpOper = button.getActionCommand();
		
		if(display.getText().equals("")) return;
		
		Double num = Double.parseDouble(display.getText());
		
		calDto.setOper(inpOper);
		calDto.setNumber(num);
				
		// DTO�� ������ �ٽ� ��ȯ����
		calDto = frontController.calControl(calDto);
		
		// output ���� ���
		display.setText(calDto.getResult());
	}

}