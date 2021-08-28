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

	
	// 생성자로 화면 렌더링 + 프론트 컨트롤러 객체 생성
	public Calculator() {
		
		// 실제론 의존성 문제가 될 수 있는 코드, 그러나 스프링등을 사용하면 알아서 해결해줌
		frontController = new FrontController();
		//
		calDto = new CalDto();
		
		// UI 렌더링
		display = new JTextField(20);
		display.setText("");

		Font bigFont = display.getFont().deriveFont(Font.PLAIN, 25f);
		display.setFont(bigFont);

		panel = new JPanel();

		panel.setLayout(new GridLayout(0, 2, 5, 5));

		buttons = new JButton[6];

		for (int i = 0; i < 6; i++) {

			buttons[i] = new JButton(labels[i]);
			buttons[i].setFont(new Font("맑은샘물",Font.BOLD,40));
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
	
	
	// O/I구동메소드
	public void actionPerformed(ActionEvent button) {

		// input 정보 dto에 저장
		String inpOper = button.getActionCommand();
		
		if(display.getText().equals("")) return;
		
		Double num = Double.parseDouble(display.getText());
		
		calDto.setOper(inpOper);
		calDto.setNumber(num);
				
		// DTO를 던지고 다시 반환받음
		calDto = frontController.calControl(calDto);
		
		// output 정보 출력
		display.setText(calDto.getResult());
	}

}