package gleb.frames;

import gleb.Connection;
import gleb.Message;
import gleb.app.Check;
import gleb.enums.FuelType;
import gleb.enums.VehicleType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UpdateFrame extends JFrame {

	private final JTextField idField;
	private final JTextField nameField;
	private final JTextField xField;
	private final JTextField yField;
	private final JTextField engineField;
	private final JTextField wheelsField;
	private final JComboBox<VehicleType> typeBox;
	private final JComboBox<FuelType> fuelBox;

	public UpdateFrame(String login, String password, String command) {
		setTitle(MainFrame.language.getString("Update element"));
		JLabel id = new JLabel(MainFrame.language.getString("ID"));
		idField = new JTextField(10);
		JLabel name = new JLabel(MainFrame.language.getString("Name"));
		nameField = new JTextField(10);
		JLabel x = new JLabel("X");
		xField = new JTextField(10);
		JLabel y = new JLabel("Y");
		yField = new JTextField(10);
		JLabel engine = new JLabel(MainFrame.language.getString("Engine power"));
		engineField = new JTextField(10);
		JLabel wheels = new JLabel(MainFrame.language.getString("Number of wheels"));
		wheelsField = new JTextField(10);
		JLabel type = new JLabel(MainFrame.language.getString("Vehicle type"));
		typeBox = new JComboBox<>(VehicleType.values());
		JLabel fuel = new JLabel(MainFrame.language.getString("Fuel type"));
		fuelBox = new JComboBox<>(FuelType.values());
		JButton addButton = new JButton(MainFrame.language.getString("Update"));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9, 2, 5, 5));

		JPanel jId = new JPanel();
		jId.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jId.add(id);
		panel.add(jId);
		panel.add(idField);

		JPanel jName = new JPanel();
		jName.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jName.add(name);
		panel.add(jName);
		panel.add(nameField);

		JPanel jX = new JPanel();
		jX.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jX.add(x);
		panel.add(jX);
		panel.add(xField);

		JPanel jY = new JPanel();
		jY.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jY.add(y);
		panel.add(jY);
		panel.add(yField);

		JPanel jEngine = new JPanel();
		jEngine.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jEngine.add(engine);
		panel.add(jEngine);
		panel.add(engineField);

		JPanel jWheels = new JPanel();
		jWheels.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jWheels.add(wheels);
		panel.add(jWheels);
		panel.add(wheelsField);

		JPanel jType = new JPanel();
		jType.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jType.add(type);
		panel.add(jType);
		panel.add(typeBox);

		JPanel jFuel = new JPanel();
		jFuel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jFuel.add(fuel);
		panel.add(jFuel);
		panel.add(fuelBox);

		panel.add(addButton);
		add(panel);
		pack();
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

		addButton.addActionListener(actionEvent -> {
			try {
				if (Check.checkLong(idField.getText()) && Check.checkFloat(xField.getText()) && Check.checkFloat(yField.getText()) && Check.checkInt(engineField.getText()) && Check.checkLong(wheelsField.getText())) {
					dispose();
					String answer = Connection.sendReadMessage(new Message(login, password, command, idField.getText() + " " + nameField.getText() + " " + xField.getText() + " " + yField.getText() + " " + engineField.getText() + " " + wheelsField.getText() + " " + typeBox.getSelectedItem() + " " + fuelBox.getSelectedItem()));
					new InfoFrame(answer, MainFrame.language.getString("Update"));
				} else {
					new InfoFrame(MainFrame.language.getString("Wrong number format"), MainFrame.language.getString("Error"));
				}
			} catch (IOException e) {
				new InfoFrame(MainFrame.language.getString("Connection error"), MainFrame.language.getString("Error"));
			}
		});
	}
}
