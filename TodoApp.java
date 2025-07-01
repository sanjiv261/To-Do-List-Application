import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TodoApp extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JButton addButton;
    private JButton deleteButton;
    
    public TodoApp() {
        // Set up the main frame
        setTitle("Java To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the window
        
        // Create components
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskInput = new JTextField(20);
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");
        
        // Set up layout
        setLayout(new BorderLayout(10, 10));
        
        // Create panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(taskInput);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        
        // Add components to frame
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        
        // Add button functionality
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInput.getText().trim();
                if (!task.isEmpty()) {
                    listModel.addElement(task);
                    taskInput.setText("");
                } else {
                    JOptionPane.showMessageDialog(TodoApp.this, 
                        "Please enter a task", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Delete button functionality
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(TodoApp.this, 
                        "Please select a task to delete", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Allow pressing Enter to add tasks
        taskInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButton.doClick();
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TodoApp().setVisible(true);
            }
        });
    }
}