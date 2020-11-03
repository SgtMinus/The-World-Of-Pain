package gleb.frames;


import gleb.Connection;
import gleb.Message;
import gleb.app.Check;
import gleb.frames.AddFrame;
import gleb.frames.InfoFrame;
import gleb.frames.PaintFrame;
import gleb.frames.UpdateFrame;
import gleb.frames.exceptions.ScriptRecursionException;


import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Timer;

import static gleb.frames.AuthFrame.*;

public class MainFrame extends JFrame {
	static String[] fields = {"id", "name", "x", "y", "creationDate", "EnginePower", "numberOfWheels", "VehicleType", "FuelType", "UserCreator"};
	static JButton helpButton = new JButton();
	static JButton infoButton = new JButton();
	static JButton showButton = new JButton();
	static JButton addButton = new JButton();
	static JButton updateButton = new JButton();
	static JButton removeButton = new JButton();
	static JButton clearButton = new JButton();
	static JButton addIfMaxButton = new JButton();
	static JButton scriptButton = new JButton();
	static JButton removeGreaterButton = new JButton();
	static JButton removeLowerButton = new JButton();
	static JButton groupWheelsButton = new JButton();
	static JButton filterButton = new JButton();
	static JButton printAscendingButton = new JButton();
	static JButton exitButton = new JButton();
	static JButton visualButton = new JButton();
	static String[] lang = {"Русский", "Română", "Український", "Español (Dominicano Republic)"};
	static JComboBox<String> languageComboBox = new JComboBox<>(lang);
	static ListResourceBundle language;
	static JLabel jLogin = new JLabel();
	TextField filePath = new TextField();
	static String login;
	static String pass;

	public MainFrame(String login, String password, ListResourceBundle language) {
		MainFrame.login = login;
		setLanguage(language, this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		pass = password;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel1.add(jLogin);
		panel1.add(languageComboBox);
		panel1.add(exitButton);
		JPanel buttonsPanel1 = new JPanel();
		buttonsPanel1.setLayout(new GridLayout(6, 1, 5, 5));
		buttonsPanel1.add(helpButton);
		buttonsPanel1.add(infoButton);
		buttonsPanel1.add(showButton);
		buttonsPanel1.add(visualButton);
		buttonsPanel1.add(groupWheelsButton);
		buttonsPanel1.add(printAscendingButton);

		JPanel buttonsPanel2 = new JPanel();
		buttonsPanel2.setLayout(new GridLayout(4, 2, 5, 5));
		buttonsPanel2.add(addButton);
		buttonsPanel2.add(addIfMaxButton);
		buttonsPanel2.add(updateButton);
		buttonsPanel2.add(filePath);
		buttonsPanel2.add(removeButton);
		buttonsPanel2.add(removeLowerButton);
		buttonsPanel2.add(clearButton);
		buttonsPanel2.add(filterButton);
		panel.add(panel1);
		panel.add(buttonsPanel1);
		panel.add(Box.createVerticalStrut(5));
		panel.add(buttonsPanel2);
		add(panel);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);

		//help
		helpButton.addActionListener(actionEvent -> {
			try {
				String answer = Connection.sendReadMessage(new Message(login, password, "HELP", " "));
				new InfoFrame(answer, MainFrame.language.getString("Help"));
			} catch (IOException e) {
				e.printStackTrace();
				new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
			}
		});

		//info
		infoButton.addActionListener(actionEvent -> {
			try {
				String answer = Connection.sendReadMessage(new Message(login, password, "INFO", " "));
				new InfoFrame(answer, MainFrame.language.getString("Info"));
			} catch (IOException e) {
				e.printStackTrace();
				new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
			}
		});

		//show
		showButton.addActionListener(actionEvent -> {
			try {
				JFrame tableFrame = new JFrame();
				String vehicles = Connection.sendReadMessage(new Message(login, password, "SHOW", " "));
				String[] vehicleTable = vehicles.split("\n");
				String[][] table = new String[vehicleTable.length][];
				for (int i = 0; i < vehicleTable.length; i++) {
					table[i] = MainFrame.createTable(vehicleTable[i]);
				}
				JTable jTable = new JTable(new DefaultTableModel(table, MainFrame.fields));
				MainFrame.tableSize(jTable);
				RowSorter<TableModel> sorter = new TableRowSorter<>(new DefaultTableModel(table, MainFrame.fields));
				jTable.setRowSorter(sorter);
				JScrollPane jScrollPane = new JScrollPane(jTable);
				tableFrame.add(jScrollPane);
				tableFrame.setMinimumSize(new Dimension(1500, 200));
				tableFrame.pack();
				tableFrame.setVisible(true);
				tableFrame.setLocationRelativeTo(null);


			} catch (Exception e) {
				e.printStackTrace();
				new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
			}
		});

		//add {element}
		addButton.addActionListener(actionEvent -> {
			new AddFrame(login, password, "ADD");
		});

		//update
		updateButton.addActionListener(actionEvent -> {
			try {
				new UpdateFrame(login, password, "UPDATE");
			} catch (Exception e) {
				e.printStackTrace();
				new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
			}
		});

		//remove
		removeButton.addActionListener(actionEvent -> {
			JFrame removeWindow = new JFrame(MainFrame.language.getString("Remove by ID"));
			JPanel idPanel = new JPanel();
			JLabel id = new JLabel(MainFrame.language.getString("Enter ID"));
			JTextField idField = new JTextField(20);
			JButton removeButton = new JButton(MainFrame.language.getString("Remove"));
			idPanel.add(id);
			idPanel.add(idField);
			idPanel.add(removeButton);
			removeWindow.add(idPanel);
			removeWindow.pack();
			removeWindow.setVisible(true);
			removeWindow.setResizable(false);
			removeWindow.setLocationRelativeTo(null);

			removeButton.addActionListener(removeEvent -> {
				try {
					if (Check.checkLong(idField.getText())) {
						String answer = Connection.sendReadMessage(new Message(login, password, "REMOVE_BY_ID", idField.getText()));
						removeWindow.dispose();
						new InfoFrame(answer, MainFrame.language.getString("Remove"));
					} else {
						new InfoFrame(MainFrame.language.getString("Wrong number format"), MainFrame.language.getString("Error"));
					}
				} catch (IOException e) {
					e.printStackTrace();
					new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
				}
			});
		});

		//clear
		clearButton.addActionListener(actionEvent -> {
			try {
				String answer = Connection.sendReadMessage(new Message(login, password, "CLEAR", " "));
				new InfoFrame(answer, MainFrame.language.getString("Clear"));
			} catch (IOException e) {
				e.printStackTrace();
				new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
			}
		});

		//add_if_max
		addIfMaxButton.addActionListener(actionEvent -> executeScript());

		//remove_greater
		removeGreaterButton.addActionListener(actionEvent -> new AddFrame(login, password, "REMOVE_GREATER"));

		//remove_lower
		removeLowerButton.addActionListener(actionEvent -> new AddFrame(login, password, "REMOVE_LOWER"));

		//group_counting_by_number_of_wheels
		groupWheelsButton.addActionListener(actionEvent -> {
			try {
				String answer = Connection.sendReadMessage(new Message(login, password, "GROUP_COUNTING_BY_NUMBER_OF_WHEELS", " "));
				new InfoFrame(answer, MainFrame.language.getString("Group counting by number of wheels"));
			} catch (IOException e) {
				e.printStackTrace();
				new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
			}
		});

		//filter_greater_than_type
		filterButton.addActionListener(actionEvent -> {
			JFrame filter = new JFrame(MainFrame.language.getString("Filter"));
			JPanel filterPanel = new JPanel();
			String[] types = {MainFrame.language.getString("Coordinates"), MainFrame.language.getString("Power"), MainFrame.language.getString("Wheels"), MainFrame.language.getString("Name"),MainFrame.language.getString("transport"),MainFrame.language.getString("fuel")};

			JComboBox<String> type = new JComboBox<>(types);
			JTextField number = new JTextField(15);
			JButton filterButton = new JButton(MainFrame.language.getString("Do Filter"));
			filterPanel.add(new JLabel(MainFrame.language.getString("Choose type:")));
			filterPanel.add(type);
			filterPanel.add(new JLabel(MainFrame.language.getString("Enter number:")));
			filterPanel.add(number);
			filterPanel.add(filterButton);
			filter.add(filterPanel);
			filter.pack();
			filter.setVisible(true);
			filter.setResizable(false);
			filter.setLocationRelativeTo(null);

			filterButton.addActionListener(filterEvent -> {
				filter.dispose();
				try {
			//		if (Check.checkInt(number.getText())) {
					if (true){
						String answer = Connection.sendReadMessage(new Message(login, password, "FILTER_GREATER_THAN_TYPE", type.getSelectedItem() + " " + number.getText()));
						new InfoFrame(answer, MainFrame.language.getString("Filter greater than type"));
					} else {
						new InfoFrame(MainFrame.language.getString("Wrong number format"), MainFrame.language.getString("Error"));
					}
				} catch (IOException e) {
					e.printStackTrace();
					new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
				}
			});
		});

		//print_field_ascending_number_of_wheels
		printAscendingButton.addActionListener(actionEvent -> {
			try {
				String answer = Connection.sendReadMessage(new Message(login, password, "PRINT_FIELD_ASCENDING_NUMBER_OF_WHEELS", " "));
				new InfoFrame(answer, MainFrame.language.getString("Print field ascending number of wheels"));
			} catch (IOException e) {
				e.printStackTrace();
				new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
			}
		});

		//exit
		exitButton.addActionListener(actionEvent -> System.exit(0));

		//visual
		visualButton.addActionListener(actionEvent -> {
			PaintFrame paintFrame = new PaintFrame(login, password);
			paintFrame.setVisible(true);
		});

		languageComboBox.addActionListener(actionEvent -> {
			if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(0))) {
				MainFrame.language = locale_ru_ru;
				setLanguage(locale_ru_ru, this);
			} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(1))) {
				MainFrame.language = locale_ro_ro;
				setLanguage(locale_ro_ro, this);
			} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(2))) {
				MainFrame.language = locale_uk_ua;
				setLanguage(locale_uk_ua, this);
			} else if (languageComboBox.getSelectedItem().equals(languageComboBox.getItemAt(3))) {
				MainFrame.language = locale_es_do;
				setLanguage(locale_es_do, this);
			}
		});
	}

	public static String[] createTable(String vehicle) {
		try {
			String[] fields = vehicle.split(" ");
			fields = new String[]{
					fields[0],
					fields[1],
					fields[2],
					fields[3],
					fields[4],
					fields[5],
					fields[6],
					fields[7],
					fields[8],
					fields[9]
			};
			return fields;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static void setLanguage(ListResourceBundle language, MainFrame mainFrame) {
		if (language.equals(locale_ru_ru)) {
			languageComboBox.setSelectedIndex(0);
		} else if (language.equals(locale_ro_ro)) {
			languageComboBox.setSelectedIndex(1);
		} else if (language.equals(locale_uk_ua)) {
			languageComboBox.setSelectedIndex(2);
		} else if (language.equals(locale_es_do)) {
			languageComboBox.setSelectedIndex(3);
		}
		MainFrame.language = language;
		jLogin.setText(language.getString("Login:") + " " + MainFrame.login);
		mainFrame.setTitle(language.getString("Table"));
		fields = new String[]{language.getString("ID"), language.getString("Name"), "x", "y", language.getString("Creation date"), language.getString("Engine power"), language.getString("Number of wheels"), language.getString("Vehicle type"), language.getString("Fuel type"), language.getString("User creator")};
		helpButton.setText(language.getString("Help"));
		infoButton.setText(language.getString("Info"));
		showButton.setText(language.getString("Show"));
		addButton.setText(language.getString("Add"));
		updateButton.setText(language.getString("Update"));
		removeButton.setText(language.getString("Remove"));
		clearButton.setText(language.getString("Clear"));
		addIfMaxButton.setText(language.getString("execute script"));
		removeGreaterButton.setText(language.getString("Remove grater"));
		removeLowerButton.setText(language.getString("Remove lower"));
		groupWheelsButton.setText(language.getString("Group counting by number of wheels"));
		filterButton.setText(language.getString("Filter greater than type"));
		printAscendingButton.setText(language.getString("Print field ascending number of wheels"));
		exitButton.setText(language.getString("Exit"));
		visualButton.setText(language.getString("visual"));
	}

	public static void tableSize(JTable jTable) {
		jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(10);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(10);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(10);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(250);
		jTable.getColumnModel().getColumn(5).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(6).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(7).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(8).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(9).setPreferredWidth(100);
	}

	public void executeScript(){
		String scriptPath = filePath.getText();

		if (scriptPath.equals("")){
			filePath.setText("Введите путь до файла!");
			return;
		}
		List<String> commandsFromScript = new ArrayList<>();
		Set<String> pathChecker = new HashSet<>();
		try {
			File file = new File(scriptPath);
			Scanner scanner = new Scanner(file);
			scriptLoader(file, commandsFromScript, pathChecker);
			System.out.println(commandsFromScript);
			String answer = "";
			for (String command : commandsFromScript){
				String [] separateCommand = command.split(" ");
				if(separateCommand.length == 2){
					answer += Connection.sendReadMessage(new Message(login, pass, separateCommand[0].toUpperCase(), separateCommand[1])) + "\n\n";
				} if( separateCommand.length == 1){
					answer += Connection.sendReadMessage(new Message(login, pass, separateCommand[0], " ")) + "\n\n";
				} if (separateCommand.length == 8){
					String s = "";
					for (int i=1;i<=6;i++){
						s+=separateCommand[i]+" ";
					}
					s+=separateCommand[7];
					answer += Connection.sendReadMessage(new Message(login, pass, separateCommand[0], s)) + "\n\n";
				}
			}
			JFrame jFrame = new JFrame("Result:");
			TextArea scriptResult = new TextArea();
			scriptResult.append(answer);
			jFrame.add(scriptResult);
			jFrame.setSize(500,500);
			jFrame.setDefaultCloseOperation(1);
			jFrame.setVisible(true);
		} catch (ScriptRecursionException foundException) {
			filePath.setText("Скрипт содержит рекурсию!");
		} catch (FileNotFoundException foundException) {
			filePath.setText("Данного файла не существует");
		} catch (IOException exception) {
			filePath.setText("Что-то пошло не так... попробуйте снова");
		}
	}

	private void scriptLoader(File file, List<String> scriptData, Set<String> filePaths) throws FileNotFoundException, ScriptRecursionException {
		Scanner sc = new Scanner(file);
		while (sc.hasNext()){
			String buffe2 = sc.nextLine();
			List<String> inCommand = Arrays.asList(buffe2.split(" "));
			if(inCommand.get(0).equals("execute_script")){
				if(filePaths.contains(inCommand.get(1))){
					throw new ScriptRecursionException();
				}
				filePaths.add(inCommand.get(1));
				scriptLoader(new File(inCommand.get(1)), scriptData, filePaths);
				continue;
			}
			if(inCommand.size() == 1) {
				scriptData.add(inCommand.get(0));
				continue;

			}
			if(inCommand.size() == 2 ){
				scriptData.add(inCommand.get(0)+" "+inCommand.get(1));
				continue;
			}
			if(inCommand.size() == 8){
				String s = "";
				for (int i=0;i<=7;i++){
					s+=inCommand.get(i)+" ";
				}
				scriptData.add(s);
				continue;
			}
		}

	}
}
