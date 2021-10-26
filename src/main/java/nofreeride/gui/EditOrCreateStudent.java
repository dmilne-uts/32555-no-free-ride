package nofreeride.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nofreeride.model.Student;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditOrCreateStudent extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private Student student ;
	
	private JTextField txtStudentId;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditOrCreateStudent dialog = new EditOrCreateStudent(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditOrCreateStudent(Student student) {
		
		this.student = student ;
		
		this.setModal(true);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblInstructions = new JLabel("Please provide details of the student");
		JLabel lblStudentId = new JLabel("Student ID");
		txtStudentId = new JFormattedTextField(new DecimalFormat("##########"));
		txtStudentId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				okButton.setEnabled(inputComplete());
			}
		});
		txtStudentId.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		
		txtFirstName = new JTextField();
		txtFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				okButton.setEnabled(inputComplete());
			}
		});
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		
		txtLastName = new JTextField();
		txtLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				okButton.setEnabled(inputComplete());
			}
		});
		txtLastName.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInstructions)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblStudentId)
							.addGap(18)
							.addComponent(txtStudentId, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInstructions)
					.addGap(30)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStudentId)
						.addComponent(txtStudentId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(lblFirstName)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(lblLastName))
						.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						createStudent();
						close();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						close();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (student != null) {
			txtStudentId.setText(student.getId().toString());
			txtFirstName.setText(student.getFirstName());
			txtLastName.setText(student.getLastName());
		}
	}
	
	private boolean inputComplete() {
		
		if (txtStudentId.getText().isEmpty() || !txtStudentId.isValid())
			return false ;
		
		if (txtFirstName.getText().isEmpty())
			return false ;
		
		if (txtLastName.getText().isEmpty())
			return false ;
		
		return true ;
	}
	
	private void createStudent() {
	    this.student = new Student(
	        Integer.valueOf(txtStudentId.getText()),
	        txtFirstName.getText(),
	        txtLastName.getText()
	    );
	}
	
	private void close() {
	    this.setVisible(false);
	    this.dispose();
	}
	
	public Student showDialog() {
	    setVisible(true);
	    return student;
	}
}
