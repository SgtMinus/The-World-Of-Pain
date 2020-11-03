package gleb.frames;

import gleb.Connection;
import gleb.Message;
import gleb.locales.Locale_es_DO;
import gleb.locales.Locale_ro_RO;
import gleb.locales.Locale_ru_RU;
import gleb.locales.Locale_uk_UA;

import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {
	private final JComboBox<String> languageComboBox;
	private final JTextField login;
	private final JPasswordField password;
	private final JButton loginButton;
	private final JButton registerButton;
	private final JLabel loginText;
	private final JLabel passwordText;
	public static Locale_es_DO locale_es_do = new Locale_es_DO();
	public static Locale_uk_UA locale_uk_ua = new Locale_uk_UA();
	public static Locale_ro_RO locale_ro_ro = new Locale_ro_RO();
	public static Locale_ru_RU locale_ru_ru = new Locale_ru_RU();
	public static String[] lang = {"Русский", "Română", "Український", "Español (Dominicano Republic)"};

	public AuthFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Vehicles");
		languageComboBox = new JComboBox<>(lang);

		loginText = new JLabel();
		passwordText = new JLabel();

		login = new JTextField(15);

		password = new JPasswordField(15);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel langPanel = new JPanel();
		langPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		langPanel.add(languageComboBox);

		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(3, 2, 5, 5));

		JPanel jLogin = new JPanel();
		jLogin.setLayout(new FlowLayout(FlowLayout.CENTER));
		jLogin.add(loginText);
		loginPanel.add(jLogin);
		loginPanel.add(login);

		JPanel jPassword = new JPanel();
		jPassword.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPassword.add(passwordText);
		loginPanel.add(jPassword);
		loginPanel.add(password);

		loginButton = new JButton();
		registerButton = new JButton();
		loginPanel.add(loginButton);
		loginPanel.add(registerButton);

		panel.add(langPanel);
		panel.add(loginPanel);

		add(panel);
		setSize(400, 200);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

		loginText.setText(locale_ru_ru.getString("Login"));
		passwordText.setText(locale_ru_ru.getString("Password"));
		loginButton.setText(locale_ru_ru.getString("Log in"));
		registerButton.setText(locale_ru_ru.getString("Sign up"));

		//login
		loginButton.addActionListener(actionEvent -> {
			try {
				String log_in = Connection.sendReadMessage(new Message(login.getText(), password.getText(), " ", " "));
				if (log_in.equals("LOGIN")) {
					dispose();
					if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(0))) {
						new MainFrame(login.getText(), password.getText(), locale_ru_ru);
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(1))) {
						new MainFrame(login.getText(), password.getText(), locale_ro_ro);
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(2))) {
						new MainFrame(login.getText(), password.getText(), locale_uk_ua);
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(3))) {
						new MainFrame(login.getText(), password.getText(), locale_es_do);
					}
				} else {
					if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(0))) {
						new InfoFrame(locale_ru_ru.getString(log_in), locale_ru_ru.getString("Error"));
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(1))) {
						new InfoFrame(locale_ru_ru.getString(log_in), locale_ru_ru.getString("Error"));
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(2))) {
						new InfoFrame(locale_ru_ru.getString(log_in), locale_ru_ru.getString("Error"));
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(3))) {
						new InfoFrame(locale_ru_ru.getString(log_in), locale_ru_ru.getString("Error"));
					}
				}
			} catch (Exception e) {
				dispose();
				InfoFrame error = new InfoFrame(locale_ru_ru.getString("Connection error"), locale_ru_ru.getString("Error"));
				error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

		//register
		registerButton.addActionListener(actionEvent -> {
			try {
				String register = Connection.sendReadMessage(new Message(login.getText(), password.getText(), "REGISTER", " "));
				if (register.equals("REGISTER")) {
					dispose();
					if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(0))) {
						new MainFrame(login.getText(), password.getText(), locale_ru_ru);
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(1))) {
						new MainFrame(login.getText(), password.getText(), locale_ro_ro);
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(2))) {
						new MainFrame(login.getText(), password.getText(), locale_uk_ua);
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(3))) {
						new MainFrame(login.getText(), password.getText(), locale_es_do);
					}
				} else {
					if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(0))) {
						new InfoFrame(locale_ru_ru.getString(register), locale_ru_ru.getString("Error"));
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(1))) {
						new InfoFrame(locale_ru_ru.getString(register), locale_ru_ru.getString("Error"));
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(2))) {
						new InfoFrame(locale_ru_ru.getString(register), locale_ru_ru.getString("Error"));
					} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(3))) {
						new InfoFrame(locale_ru_ru.getString(register), locale_ru_ru.getString("Error"));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				dispose();
				InfoFrame error = new InfoFrame(locale_ru_ru.getString("Connection error"), locale_ru_ru.getString("Error"));
				error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

		//change language
		languageComboBox.addActionListener(actionEvent -> {
			if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(0))) {
				loginText.setText(locale_ru_ru.getString("Login"));
				passwordText.setText(locale_ru_ru.getString("Password"));
				loginButton.setText(locale_ru_ru.getString("Log in"));
				registerButton.setText(locale_ru_ru.getString("Sign up"));
			} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(1))) {
				loginText.setText(locale_ro_ro.getString("Login"));
				passwordText.setText(locale_ro_ro.getString("Password"));
				loginButton.setText(locale_ro_ro.getString("Log in"));
				registerButton.setText(locale_ro_ro.getString("Sign up"));
			} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(2))) {
				loginText.setText(locale_uk_ua.getString("Login"));
				passwordText.setText(locale_uk_ua.getString("Password"));
				loginButton.setText(locale_uk_ua.getString("Log in"));
				registerButton.setText(locale_uk_ua.getString("Sign up"));
			} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(3))) {
				loginText.setText(locale_es_do.getString("Login"));
				passwordText.setText(locale_es_do.getString("Password"));
				loginButton.setText(locale_es_do.getString("Log in"));
				registerButton.setText(locale_es_do.getString("Sign up"));
			}
		});
	}
}
