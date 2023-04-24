
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class checklistGUI extends JFrame {
	JList<String> myList;
	static DefaultListModel<String> defaultList;
	JButton addTask, remove;
	static JLabel statusLabel;
	String status = "List status is displayed here";
	static JTextField task;
	JButton plus;
	JPanel taskManipulation; // panel for task manipulation
	ListModel model;
	JDialog statusTell;

	public checklistGUI(String name) {
		super(name);
		this.setVisible(true); // opening and showing of window
		this.setComponents();
		this.setLayout(null);
		int abc=10;
		System.out.println("Champ 123"+123);
		this.setBounds(0, 0, 300, 400); // setting size of window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closing of window
	}

	checklistGUI() {
	}

	void setComponents() {
		// Creating a default list
		defaultList = new DefaultListModel<String>();
		defaultList.addElement("Item 1");
		defaultList.addElement("Item 2");
		defaultList.addElement("Item 3");
		myList = new JList<String>(defaultList);
		myList.setSelectionBackground(Color.LIGHT_GRAY);
		myList.setBounds(8, 35, 200, 200);

		// Creating task adding panel
		task = new JTextField("Quick add");
		plus = new JButton("+");
		plus.setBackground(new Color(90, 170, 250)); // blue background
		plus.setForeground(Color.WHITE); // white text
		plus.setBounds(168, 5, 45, 20);
		task.setBounds(8, 5, 150, 20);

		// Task handling panel
		taskManipulation = new JPanel(null);
		taskManipulation.setOpaque(true);
		taskManipulation.setBounds(8, 250, 200, 80);

		// Adding buttons
		addTask = new JButton("Add");
		addTask.setBounds(0, 0, 90, 30);
		addTask.setBackground(new Color(90, 170, 250)); // blue background
		addTask.setForeground(Color.WHITE); // white text
		addTask.addMouseListener(new addButton());
		taskManipulation.add(addTask);

		remove = new JButton("Remove");
		remove.setBounds(100, 0, 90, 30);
		remove.setBackground(new Color(250, 90, 90)); // red background
		remove.setForeground(Color.WHITE); // white text
		remove.addMouseListener(new removeButton());
		taskManipulation.add(remove);

		// Adding label
		statusLabel = new JLabel(status);
		statusLabel.setBounds(0, 30, 200, 30);
		statusLabel.setForeground(Color.GRAY);
		taskManipulation.add(statusLabel);
		add(taskManipulation);

		add(task);
		add(plus);
		plus.addMouseListener(new plusButton());
		add(myList); // this was the headache error
	}

//Actions

	// plus button actions
	class plusButton implements MouseListener {
		public void mouseClicked(MouseEvent pBe) {
			// adding to the list
			defaultList.addElement(task.getText());
			// adding to database
			checklistDB database = new checklistDB(task.getText());
			try {
				database.addingATask(new checklistDB().addConnection());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			statusLabel.setText("A task added");
			if (!task.getText().equals("Quick add")) {
				task.setText("");
			}
		}

		/*
		 * Invoked when
		 * 
		 * a mouse button has been pressed on a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mousePressed(MouseEvent e) {

		}

		/*
		 * Invoked when
		 * 
		 * a mouse button has been released on a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		/*
		 * Invoked when the mouse enters a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		/*
		 * Invoked when
		 * 
		 * the mouse exits a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mouseExited(MouseEvent e) {
			statusLabel.setText(status);
		}
	}

	// remove button actions
	class removeButton implements MouseListener {

		/*
		 * Invoked when the mouse button has
		 * 
		 * been clicked (pressed and released) on a component.
		 *
		 * @param e the event to be processed
		 */

		@Override
		public void mouseClicked(MouseEvent e) {
			model = myList.getModel();
			if (model.getSize() != 0) {
				Object item = model.getElementAt(model.getSize() - 1);

				if (!myList.isSelectionEmpty()) {
					item = myList.getSelectedValue();
				}
				defaultList.removeElement(item);
				statusLabel.setText("Task removed");
			}
		}

		/*
		 * Invoked when
		 * 
		 * a mouse button has been pressed on a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mousePressed(MouseEvent e) {

		}

		/*
		 * Invoked when
		 * 
		 * a mouse button has been released on a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		/*
		 * Invoked when
		 * 
		 * the mouse enters a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		/*
		 * Invoked when
		 * 
		 * the mouse exits a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mouseExited(MouseEvent e) {
			statusLabel.setText(status);
		}

	}

	class addButton implements MouseListener {

		/*
		 * Invoked when the mouse button has
		 * 
		 * been clicked (pressed and released) on a component.
		 *
		 * @param e the event to be processed
		 */

		@Override
		public void mouseClicked(MouseEvent e) {
			new addTaskDialog("Add a task");
			statusLabel.setText("Task adding dialog box opened");
		}

		/*
		 * Invoked when
		 * 
		 * a mouse button has been pressed on a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mousePressed(MouseEvent e) {

		}

		/*
		 * Invoked when
		 * 
		 * a mouse button has been released on a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		/*
		 * Invoked when
		 * 
		 * the mouse enters a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		/*
		 * Invoked when
		 * 
		 * the mouse exits a component.**
		 * 
		 * @param e the event to be processed
		 */

		@Override
		public void mouseExited(MouseEvent e) {
			statusLabel.setText(status);
		}
	}

	void addingTaskFromDialogBox() {
		addTaskDialog dialogTask = new addTaskDialog();
		String taskD = dialogTask.taskDialog.getText();
		if (taskD != "") {
			defaultList.addElement(taskD);
		}

		checklistDB database = new checklistDB(task.getText());
		try {
			database.addingATask(new checklistDB().addConnection());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		statusLabel.setText("A task added");
		if (!task.getText().equals("Quick add")) {
			task.setText("");

		}
	}

	public static void main(String[] args) {

		checklistGUI checklistguicode = new checklistGUI("Checklist");
		checklistguicode.addingTaskFromDialogBox();

	}
}