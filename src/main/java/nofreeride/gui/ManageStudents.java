package nofreeride.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import nofreeride.model.Student;
import nofreeride.repo.StudentRepo;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class ManageStudents extends JFrame {
	
	private StudentRepo students ;
	private StudentTableModel model ;
	
	private Student selectedStudent ;

	private JPanel contentPane;
	private JTable tblStudents;
	private JButton btnEditStudent;
	private JButton btnAddStudent;

	/**
	 * Create the frame.
	 */
	public ManageStudents(StudentRepo students) {
		
		this.students = students ;
		this.model = new StudentTableModel() ;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Students");
		
		btnAddStudent = new JButton("Add Student");
		btnAddStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addStudent();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnEditStudent = new JButton("Edit Student");
		btnEditStudent.setEnabled(false);
		btnEditStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editStudent(selectedStudent);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnEditStudent)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddStudent, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddStudent)
						.addComponent(btnEditStudent)))
		);
		
		tblStudents = new JTable(model);
		tblStudents.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {

	        	int row = tblStudents.getSelectedRow();
	        	
	        	if (row >= 0)
	        		selectedStudent = model.getStudentAtRow(row) ;
	        	else
	        		selectedStudent = null ;
	        	
	        	btnEditStudent.setEnabled(selectedStudent != null);
	        }
	    });
		scrollPane.setViewportView(tblStudents);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void addStudent() {
		EditOrCreateStudent dlg = new EditOrCreateStudent(null) ;
	    Student createdStudent = dlg.showDialog();

	    if (createdStudent != null) {

	        //save the student to the repository
	        students.save(createdStudent) ;

	        //update the table model, which will cause this new student to be shown in the table
	        model.refreshData();
	    }
	}
	
	private void editStudent(Student student) {
		EditOrCreateStudent dlg = new EditOrCreateStudent(student) ;
	    Student editedStudent = dlg.showDialog();

	    if (editedStudent != null) {

	        //save the student to the repository
	        students.save(editedStudent) ;

	        //update the table model, which will cause this new student to be shown in the table
	        model.refreshData();
	    }
	}
	
	private class StudentTableModel extends AbstractTableModel {
		
	    private String[] columnNames = {"Student ID", "First Name", "Last Name"} ;
	    
	    private List<Student> rows ;
	    
	    public StudentTableModel() {
	    	refreshData();
	    }

	    public void refreshData() {
	    	this.rows = new ArrayList<Student>();
	    	
	    	for (Student student: students.findAll()) {
	    		this.rows.add(student) ;
	    	}
	    }
	    
	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    public int getRowCount() {
	        return rows.size();
	    }

	    public String getColumnName(int col) {
	        return columnNames[col];
	    }

	    public Object getValueAt(int row, int col) {
	    	
	    	Student student = rows.get(row) ;
	    	
	    	switch (col) {
	    		case 0: return student.getId();
	    		case 1: return student.getFirstName() ;
	    		case 2: return student.getLastName() ;
	    	}
	    	
	    	return null ;
	    	
	    }

	    public Class getColumnClass(int col) {
	    	switch (col) {
	    		case 0: return Integer.class;
	    		case 1: return String.class ;
	    		case 2: return String.class ;
	    	}
	    	
	    	return null ;
    	} 
	    
	    public Student getStudentAtRow(int row) {
	    	return rows.get(row);
	    }
	}
}
