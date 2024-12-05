import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class CalculatorGUI {
	private static HistoryManager historyManager = new HistoryManager();

	static JFrame frame = new JFrame("Formulator");
	static JFrame historyFrame = new JFrame("Calculation History");

	static JPanel historyPanel = new JPanel();

	static JLabel mainText = new JLabel();

	static JButton plus = new JButton("+"), minus = new JButton("-"), times = new JButton("x"),
			divide = new JButton("÷"), clear = new JButton("C"), zero = new JButton("0"), one = new JButton("1"),
			two = new JButton("2"), three = new JButton("3"), four = new JButton("4"), five = new JButton("5"),
			six = new JButton("6"), seven = new JButton("7"), eight = new JButton("8"), nine = new JButton("9"),
			point = new JButton("."), equals = new JButton("="), backspace = new JButton("⌫");

	static JButton historyPage = new JButton("View History");

	static enum Keys {
		PLUS, MINUS, TIMES, DIVIDE, CLEAR, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, POINT, EQUALS,
		BACKSPACE
	}

	public static void main(String[] args) {
		historyManager.loadHistoryFromFile();

		SetLabels();
		SetButtons();
		SetListeners();

		ImageIcon img = new ImageIcon("./JavaCalcGUI/calcIcon.png");
		frame.setIconImage(img.getImage());

		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.addKeyListener(new MKeyListener());

		frame.getContentPane().setBackground(Color.BLACK);
		frame.setSize(325, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public static void refreshHistoryPanel() {
		historyPanel.removeAll();

		List<CalculationHistory> history = historyManager.getHistory();
		Collections.reverse(history);

		for (CalculationHistory entry : history) {
			JLabel historyLabel = new JLabel(
					String.format("<html><font color='white'>%s = %s <br><small>%s</small></font></html>",
							entry.getQuery(), entry.getResult(), entry.getDateTime()));
			historyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			historyLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
			historyPanel.add(historyLabel);
		}

		historyPanel.revalidate();
		historyPanel.repaint();
	}

	public static void OpenHistory() {
		historyFrame = new JFrame("Calculation History");
		historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		historyFrame.setSize(325, 500);
		historyFrame.getContentPane().setBackground(Color.BLACK);

		historyPanel = new JPanel();
		historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
		historyPanel.setBackground(Color.BLACK);

		ImageIcon img = new ImageIcon("C:\\Users\\johnb\\OneDrive\\Desktop\\QCU\\CC101\\calc.png");
		historyFrame.setIconImage(img.getImage());

		refreshHistoryPanel();

		JScrollPane scrollPane = new JScrollPane(historyPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		verticalScrollBar.setBackground(Color.DARK_GRAY);
		verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.GRAY;
			}
		});

		historyFrame.add(scrollPane);
		historyFrame.setLocationRelativeTo(null);
		historyFrame.setVisible(true);
	}

	public static void SetButtons() {
		{
			historyPage.setBounds(20, 380, 130, 40);
			historyPage.setBackground(new Color(4, 4, 138));
			historyPage.setFont(new Font("Arial", Font.BOLD, 15));
			historyPage.setForeground(Color.WHITE);
			historyPage.setBorderPainted(false);
			historyPage.setFocusPainted(false);
			historyPage.setFocusable(false);
			frame.add(historyPage);
		}
		{
			plus.setBounds(20, 65, 60, 50);
			plus.setBackground(new Color(255, 159, 10));
			plus.setFont(new Font("Arial", Font.BOLD, 20));
			plus.setForeground(Color.WHITE);
			plus.setBorderPainted(false);
			plus.setFocusPainted(false);
			plus.setFocusable(false);
			frame.add(plus);
		}
		{
			minus.setBounds(20, 125, 60, 50);
			minus.setBackground(new Color(255, 159, 10));
			minus.setFont(new Font("Arial", Font.BOLD, 20));
			minus.setForeground(Color.WHITE);
			minus.setBorderPainted(false);
			minus.setFocusPainted(false);
			minus.setFocusable(false);
			frame.add(minus);
		}
		{
			times.setBounds(20, 185, 60, 50);
			times.setBackground(new Color(255, 159, 10));
			times.setFont(new Font("Arial", Font.BOLD, 20));
			times.setForeground(Color.WHITE);
			times.setBorderPainted(false);
			times.setFocusPainted(false);
			frame.add(times);
		}
		{
			divide.setBounds(20, 245, 60, 50);
			divide.setBackground(new Color(255, 159, 10));
			divide.setFont(new Font("Arial", Font.BOLD, 20));
			divide.setForeground(Color.WHITE);
			divide.setBorderPainted(false);
			divide.setFocusPainted(false);
			frame.add(divide);
		}
		{
			clear.setBounds(20, 305, 60, 50);
			clear.setBackground(new Color(255, 159, 10));
			clear.setFont(new Font("Arial", Font.BOLD, 20));
			clear.setForeground(Color.WHITE);
			clear.setBorderPainted(false);
			clear.setFocusPainted(false);
			clear.setFocusable(false);
			frame.add(clear);
		}
		{
			backspace.setBounds(90, 305, 60, 50);
			backspace.setBackground(new Color(42, 42, 44));
			backspace.setForeground(Color.WHITE);
			backspace.setBorderPainted(false);
			backspace.setFocusPainted(false);
			backspace.setFocusable(false);
			frame.add(backspace);
		}
		{
			seven.setBounds(90, 65, 60, 50);
			seven.setBackground(new Color(42, 42, 44));
			seven.setFont(new Font("Arial", Font.BOLD, 20));
			seven.setForeground(Color.WHITE);
			seven.setBorderPainted(false);
			seven.setFocusPainted(false);
			seven.setFocusable(false);
			frame.add(seven);
		}
		{
			four.setBounds(90, 125, 60, 50);
			four.setBackground(new Color(42, 42, 44));
			four.setFont(new Font("Arial", Font.BOLD, 20));
			four.setForeground(Color.WHITE);
			four.setBorderPainted(false);
			four.setFocusPainted(false);
			four.setFocusable(false);
			frame.add(four);
		}
		{
			one.setBounds(90, 185, 60, 50);
			one.setBackground(new Color(42, 42, 44));
			one.setFont(new Font("Arial", Font.BOLD, 20));
			one.setForeground(Color.WHITE);
			one.setBorderPainted(false);
			one.setFocusPainted(false);
			one.setFocusable(false);
			frame.add(one);
		}
		{
			zero.setBounds(90, 245, 60, 50);
			zero.setBackground(new Color(42, 42, 44));
			zero.setFont(new Font("Arial", Font.BOLD, 20));
			zero.setForeground(Color.WHITE);
			zero.setBorderPainted(false);
			zero.setFocusPainted(false);
			frame.add(zero);
		}
		{
			eight.setBounds(160, 65, 60, 50);
			eight.setBackground(new Color(42, 42, 44));
			eight.setFont(new Font("Arial", Font.BOLD, 20));
			eight.setForeground(Color.WHITE);
			eight.setBorderPainted(false);
			eight.setFocusPainted(false);
			frame.add(eight);
		}
		{
			five.setBounds(160, 125, 60, 50);
			five.setBackground(new Color(42, 42, 44));
			five.setFont(new Font("Arial", Font.BOLD, 20));
			five.setForeground(Color.WHITE);
			five.setBorderPainted(false);
			five.setFocusPainted(false);
			frame.add(five);
		}
		{
			two.setBounds(160, 185, 60, 50);
			two.setBackground(new Color(42, 42, 44));
			two.setFont(new Font("Arial", Font.BOLD, 20));
			two.setForeground(Color.WHITE);
			two.setBorderPainted(false);
			two.setFocusPainted(false);
			frame.add(two);
		}
		{
			point.setBounds(160, 245, 60, 50);
			point.setBackground(new Color(42, 42, 44));
			point.setFont(new Font("Arial", Font.BOLD, 20));
			point.setForeground(Color.WHITE);
			point.setBorderPainted(false);
			point.setFocusPainted(false);
			frame.add(point);
		}
		{
			nine.setBounds(230, 65, 60, 50);
			nine.setBackground(new Color(42, 42, 44));
			nine.setFont(new Font("Arial", Font.BOLD, 20));
			nine.setForeground(Color.WHITE);
			nine.setBorderPainted(false);
			nine.setFocusPainted(false);
			frame.add(nine);
		}
		{
			six.setBounds(230, 125, 60, 50);
			six.setBackground(new Color(42, 42, 44));
			six.setFont(new Font("Arial", Font.BOLD, 20));
			six.setForeground(Color.WHITE);
			six.setBorderPainted(false);
			six.setFocusPainted(false);
			frame.add(six);
		}
		{
			three.setBounds(230, 185, 60, 50);
			three.setBackground(new Color(42, 42, 44));
			three.setFont(new Font("Arial", Font.BOLD, 20));
			three.setForeground(Color.WHITE);
			three.setBorderPainted(false);
			three.setFocusPainted(false);
			frame.add(three);
		}
		{
			equals.setBounds(230, 245, 60, 50);
			equals.setBackground(new Color(42, 42, 44));
			equals.setFont(new Font("Arial", Font.BOLD, 20));
			equals.setForeground(Color.WHITE);
			equals.setBorderPainted(false);
			equals.setFocusPainted(false);
			frame.add(equals);
		}
	}

	public static void SetListeners() {
		{
			plus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.PLUS);
				}
			});
			minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.MINUS);
				}
			});
			times.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.TIMES);
				}
			});
			divide.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.DIVIDE);
				}
			});
			clear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.CLEAR);
				}
			});
			equals.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.EQUALS);
				}
			});
			point.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.POINT);
				}
			});
			one.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.ONE);
				}
			});
			two.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String text = mainText.getText();

					if ((text.length() == 1 && text.charAt(0) == '0'))
						mainText.setText("2");
					else
						mainText.setText(text + "2");
				}
			});
			three.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.THREE);
				}
			});
			four.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.FOUR);
				}
			});
			five.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.FIVE);
				}
			});
			six.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.SIX);
				}
			});
			seven.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.SEVEN);
				}
			});
			eight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.EIGHT);
				}
			});
			nine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.NINE);
				}
			});
			zero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.ZERO);
				}
			});
			backspace.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyPressed(Keys.BACKSPACE);
				}
			});
			historyPage.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Call method to open new frame
					OpenHistory();
				}
			});
		}
	}

	public static void KeyPressed(Keys key) {
		switch (key) {
			case PLUS: {
				String text = mainText.getText();
				String character = String.valueOf(text.charAt(text.length() - 1));

				if (text.length() == 0 || (text.length() == 1 && text.charAt(0) == '0'))
					;
				else if (text.length() > 0 && "+-x÷".contains(character))
					mainText.setText(text.substring(0, text.length() - 1) + "+");
				else
					mainText.setText(text + "+");
				break;
			}
			case MINUS: {
				String text = mainText.getText();
				String character = String.valueOf(text.charAt(text.length() - 1));

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("-");
				else if (text.length() > 0 && "+-x÷".contains(character))
					mainText.setText(text.substring(0, text.length() - 1) + "-");
				else
					mainText.setText(text + "-");
				break;
			}
			case TIMES: {
				String text = mainText.getText();
				String character = String.valueOf(text.charAt(text.length() - 1));

				if (text.length() == 0 || (text.length() == 1 && text.charAt(0) == '0'))
					;
				else if (text.length() > 0 && "+-x÷".contains(character))
					mainText.setText(text.substring(0, text.length() - 1) + "x");
				else
					mainText.setText(text + "x");
				break;
			}
			case DIVIDE: {
				String text = mainText.getText();
				String character = String.valueOf(text.charAt(text.length() - 1));

				if (text.length() == 0 || (text.length() == 1 && text.charAt(0) == '0'))
					;
				else if (text.length() > 0 && "+-x÷".contains(character))
					mainText.setText(text.substring(0, text.length() - 1) + "÷");
				else
					mainText.setText(text + "÷");
				break;
			}
			case CLEAR: {
				mainText.setText("0");
				break;
			}
			case EQUALS: {
				String text = mainText.getText();
				String result = evaluate(text);
				mainText.setText(result);

				historyManager.addHistory(text, result);

				if (historyFrame.isVisible())
					refreshHistoryPanel();
				break;
			}
			case POINT: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText(".");
				else
					mainText.setText(text + ".");
				break;
			}
			case ONE: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("1");
				else
					mainText.setText(text + "1");
				break;
			}
			case TWO: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("2");
				else
					mainText.setText(text + "2");
				break;
			}
			case THREE: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("3");
				else
					mainText.setText(text + "3");
				break;
			}
			case FOUR: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("4");
				else
					mainText.setText(text + "4");
				break;
			}
			case FIVE: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("5");
				else
					mainText.setText(text + "5");
				break;
			}
			case SIX: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("6");
				else
					mainText.setText(text + "6");
				break;
			}
			case SEVEN: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("7");
				else
					mainText.setText(text + "7");
				break;
			}
			case EIGHT: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("8");
				else
					mainText.setText(text + "8");
				break;
			}
			case NINE: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("9");
				else
					mainText.setText(text + "9");
				break;
			}
			case ZERO: {
				String text = mainText.getText();

				if ((text.length() == 1 && text.charAt(0) == '0'))
					mainText.setText("0");
				else
					mainText.setText(text + "0");
				break;
			}
			case BACKSPACE: {
				String text = mainText.getText();
				String text2 = "";

				if (text.length() > 0)
					text2 = text.substring(0, text.length() - 1);

				if (text2 == "")
					text2 = "0";

				mainText.setText(text2);
				break;
			}
			default:
				break;
		}
	}

	public static class MKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent event) {
			switch (String.valueOf(event.getKeyChar()).toLowerCase()) {
				case "=": {
					KeyPressed(Keys.EQUALS);
					break;
				}
				case "+": {
					KeyPressed(Keys.PLUS);
					break;
				}
				case "x":
				case "*": { // Handle both 'x' and '*' for multiplication
					KeyPressed(Keys.TIMES);
					break;
				}
				case "-": { // Handle the minus key
					KeyPressed(Keys.MINUS);
					break;
				}
				case "/": { // Handle the division key
					KeyPressed(Keys.DIVIDE);
					break;
				}
				case "0": {
					KeyPressed(Keys.ZERO);
					break;
				}
				case "1": {
					KeyPressed(Keys.ONE);
					break;
				}
				case "2": {
					KeyPressed(Keys.TWO);
					break;
				}
				case "3": {
					KeyPressed(Keys.THREE);
					break;
				}
				case "4": {
					KeyPressed(Keys.FOUR);
					break;
				}
				case "5": {
					KeyPressed(Keys.FIVE);
					break;
				}
				case "6": {
					KeyPressed(Keys.SIX);
					break;
				}
				case "7": {
					KeyPressed(Keys.SEVEN);
					break;
				}
				case "8": {
					KeyPressed(Keys.EIGHT);
					break;
				}
				case "9": {
					KeyPressed(Keys.NINE);
					break;
				}
				case ".": { // Handle the decimal point
					KeyPressed(Keys.POINT);
					break;
				}
				case "c": { // Handle clear (optional)
					KeyPressed(Keys.CLEAR);
					break;
				}
			}

			switch (event.getKeyCode()) {
				case KeyEvent.VK_ENTER: {
					KeyPressed(Keys.EQUALS);
					break;
				}
				case KeyEvent.VK_BACK_SPACE: {
					KeyPressed(Keys.BACKSPACE);
					break;
				}
			}
		}
	}

	public static void SetLabels() {
		mainText.setText("0");
		mainText.setBounds(20, -5, 500, 80);
		mainText.setFont(new Font("Arial", Font.BOLD, 25));
		mainText.setForeground(Color.WHITE);
		frame.add(mainText);
	}

	public static String evaluate(String expression) {
		expression = expression.replaceAll(",", "");

		Stack<Double> values = new Stack<>();
		Stack<Character> operators = new Stack<>();

		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (Character.isDigit(ch) || ch == '.' || (ch == '-'
					&& (i == 0 || !Character.isDigit(expression.charAt(i - 1)) && expression.charAt(i - 1) != ')'))) {
				StringBuilder num = new StringBuilder();
				if (ch == '-') {
					num.append('-');
					i++;
				}
				while (i < expression.length()
						&& (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
					num.append(expression.charAt(i++));
				}
				i--;
				values.push(Double.parseDouble(num.toString()));
			} else if (ch == '+' || ch == '-' || ch == 'x' || ch == '÷') {
				while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
					values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
				}
				operators.push(ch);
			}
		}

		while (!operators.isEmpty()) {
			values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
		}

		double result = values.pop();
		String finalResult;

		DecimalFormat formatter = new DecimalFormat("#,###");

		if (result == (long) result) {
			finalResult = formatter.format((long) result);
		} else {
			String formatted = String.format("%.2f", result).replaceAll("\\.?0*$", "");
			finalResult = formatter.format(Double.parseDouble(formatted));
		}

		return finalResult;
	}

	public static boolean hasPrecedence(char currentOp, char stackOp) {
		if ((stackOp == 'x' || stackOp == '÷') && (currentOp == '+' || currentOp == '-'))
			return true;
		return false;
	}

	public static double applyOperation(char operator, double b, double a) {
		switch (operator) {
			case '+':
				return a + b;
			case '-':
				return a - b;
			case 'x':
				return a * b;
			case '÷':
				if (b == 0)
					return a;
				return a / b;
		}
		return 0;
	}
}
