 import javax.swing.*;
 import javax.swing.table.DefaultTableModel;
 import java.awt.*;

 public class CustomerFrame extends JFrame {

     private final HotelService hotelService;
     private final DashboardFrame dashboardFrame;

     private JTextField idField;
     private JTextField firstNameField;
     private JTextField lastNameField;
     private JTextField addressField;
     private JTextField nationalIdField;
     private JTextField phoneField;

     private JTable customerTable;
     private DefaultTableModel tableModel;

     public CustomerFrame(
             HotelService hotelService,
             DashboardFrame dashboardFrame
     ) {

         this.hotelService = hotelService;
         this.dashboardFrame = dashboardFrame;

         setTitle("Customer Management");
         setSize(1000, 650);
         setLocationRelativeTo(null);

         setDefaultCloseOperation(
                 JFrame.DISPOSE_ON_CLOSE
         );

         buildUI();
         loadCustomers();
     }

     private void buildUI() {

         JPanel mainPanel =
                 new JPanel(
                         new BorderLayout(15, 15)
                 );

         mainPanel.setBorder(
                 BorderFactory.createEmptyBorder(
                         20, 20, 20, 20
                 )
         );

         mainPanel.setBackground(
                 new Color(245, 247, 250)
         );

         JLabel title =
                 new JLabel("Customer Management");

         title.setFont(
                 new Font(
                         "Arial",
                         Font.BOLD,
                         26
                 )
         );

         title.setForeground(
                 new Color(20, 32, 55)
         );

         mainPanel.add(
                 title,
                 BorderLayout.NORTH
         );

         JPanel formPanel =
                 new JPanel(
                         new GridLayout(
                                 3, 4, 10, 10
                         )
                 );

         formPanel.setBackground(Color.WHITE);

         formPanel.setBorder(
                 BorderFactory.createCompoundBorder(
                         BorderFactory.createLineBorder(
                                 new Color(220, 225, 230)
                         ),
                         BorderFactory.createEmptyBorder(
                                 15, 15, 15, 15
                         )
                 )
         );

         idField = new JTextField();
         firstNameField = new JTextField();
         lastNameField = new JTextField();
         addressField = new JTextField();
         nationalIdField = new JTextField();
         phoneField = new JTextField();

         formPanel.add(createLabel("Customer ID"));
         formPanel.add(idField);

         formPanel.add(createLabel("First Name"));
         formPanel.add(firstNameField);

         formPanel.add(createLabel("Last Name"));
         formPanel.add(lastNameField);

         formPanel.add(createLabel("Address"));
         formPanel.add(addressField);

         formPanel.add(createLabel("National ID"));
         formPanel.add(nationalIdField);

         formPanel.add(createLabel("Phone"));
         formPanel.add(phoneField);

         JPanel centerPanel =
                 new JPanel(
                         new BorderLayout(10, 10)
                 );

         centerPanel.setBackground(
                 new Color(245, 247, 250)
         );

         centerPanel.add(
                 formPanel,
                 BorderLayout.NORTH
         );

         tableModel =
                 new DefaultTableModel(
                         new String[]{
                                 "ID",
                                 "First Name",
                                 "Last Name",
                                 "Address",
                                 "National ID",
                                 "Phone"
                         },
                         0
                 );

         customerTable =
                 new JTable(tableModel);

         customerTable.setRowHeight(28);

         customerTable.setFont(
                 new Font(
                         "Arial",
                         Font.PLAIN,
                         14
                 )
         );

         customerTable.getTableHeader()
                 .setFont(
                         new Font(
                                 "Arial",
                                 Font.BOLD,
                                 14
                         )
                 );

         centerPanel.add(
                 new JScrollPane(customerTable),
                 BorderLayout.CENTER
         );

         mainPanel.add(
                 centerPanel,
                 BorderLayout.CENTER
         );

         JButton addButton =
                 createButton("Add Customer");

         JButton searchButton =
                 createButton("Search");

         JButton deleteButton =
                 createButton("Delete");

         JButton clearButton =
                 createButton("Clear");

         JButton refreshButton =
                 createButton("Refresh");

         JPanel buttonPanel =
                 new JPanel(
                         new FlowLayout(
                                 FlowLayout.CENTER,
                                 10,
                                 10
                         )
                 );

         buttonPanel.setBackground(
                 new Color(245, 247, 250)
         );

         buttonPanel.add(addButton);
         buttonPanel.add(searchButton);
         buttonPanel.add(deleteButton);
         buttonPanel.add(clearButton);
         buttonPanel.add(refreshButton);

         mainPanel.add(
                 buttonPanel,
                 BorderLayout.SOUTH
         );

         addButton.addActionListener(
                 e -> addCustomer()
         );

         searchButton.addActionListener(
                 e -> searchCustomer()
         );

         deleteButton.addActionListener(
                 e -> deleteCustomer()
         );

         clearButton.addActionListener(
                 e -> clearFields()
         );

         refreshButton.addActionListener(
                 e -> loadCustomers()
         );

         add(mainPanel);
     }

     private JLabel createLabel(String text) {

         JLabel label =
                 new JLabel(text);

         label.setFont(
                 new Font(
                         "Arial",
                         Font.BOLD,
                         13
                 )
         );

         label.setForeground(
                 new Color(45, 55, 72)
         );

         return label;
     }

     private JButton createButton(String text) {

         JButton button =
                 new JButton(text);

         button.setFont(
                 new Font(
                         "Arial",
                         Font.BOLD,
                         13
                 )
         );

         button.setFocusPainted(false);

         button.setBackground(
                 new Color(52, 152, 219)
         );

         button.setForeground(Color.WHITE);

         return button;
     }

     private void addCustomer() {

         try {

             int id =
                     Integer.parseInt(
                             idField.getText().trim()
                     );

             Customer customer =
                     new Customer(
                             id,
                             firstNameField.getText(),
                             lastNameField.getText(),
                             addressField.getText(),
                             nationalIdField.getText(),
                             phoneField.getText()
                     );

             hotelService.addCustomer(customer);

             loadCustomers();
             clearFields();

             dashboardFrame.refreshDashboard();

             JOptionPane.showMessageDialog(
                     this,
                     "Customer added successfully.",
                     "Success",
                     JOptionPane.INFORMATION_MESSAGE
             );

         } catch (NumberFormatException e) {

             showError(
                     "Customer ID must be a number."
             );

         } catch (IllegalArgumentException e) {

             showError(e.getMessage());
         }
     }

     private void searchCustomer() {

         try {

             int id =
                     Integer.parseInt(
                             idField.getText().trim()
                     );

             Customer customer =
                     hotelService.searchCustomerByID(id);

             if (customer == null) {

                 showWarning(
                         "Customer not found."
                 );

                 return;
             }

             firstNameField.setText(
                     customer.getFirstName()
             );

             lastNameField.setText(
                     customer.getLastName()
             );

             addressField.setText(
                     customer.getAddress()
             );

             nationalIdField.setText(
                     customer.getNationalId()
             );

             phoneField.setText(
                     customer.getPhone()
             );

         } catch (NumberFormatException e) {

             showError(
                     "Enter a valid Customer ID."
             );
         }
     }

     private void deleteCustomer() {

         try {

             int id =
                     Integer.parseInt(
                             idField.getText().trim()
                     );

             Customer customer =
                     hotelService.searchCustomerByID(id);

             if (customer == null) {

                 showWarning(
                         "Customer not found."
                 );

                 return;
             }

             int result =
                     JOptionPane.showConfirmDialog(
                             this,
                             "Are you sure you want to delete this customer?",
                             "Confirm Delete",
                             JOptionPane.YES_NO_OPTION
                     );

             if (result ==
                     JOptionPane.YES_OPTION) {

                 hotelService.removeCustomer(id);

                 loadCustomers();
                 clearFields();

                 dashboardFrame.refreshDashboard();

                 JOptionPane.showMessageDialog(
                         this,
                         "Customer deleted successfully.",
                         "Success",
                         JOptionPane.INFORMATION_MESSAGE
                 );
             }

         } catch (NumberFormatException e) {

             showError(
                     "Enter a valid Customer ID."
             );

         } catch (IllegalArgumentException e) {

             showError(e.getMessage());
         }
     }

     private void loadCustomers() {

         tableModel.setRowCount(0);

         for (Customer customer :
                 hotelService.getCustomers()) {

             tableModel.addRow(
                     new Object[]{
                             customer.getCustomerId(),
                             customer.getFirstName(),
                             customer.getLastName(),
                             customer.getAddress(),
                             customer.getNationalId(),
                             customer.getPhone()
                     }
             );
         }
     }

     private void clearFields() {

         idField.setText("");
         firstNameField.setText("");
         lastNameField.setText("");
         addressField.setText("");
         nationalIdField.setText("");
         phoneField.setText("");
     }

     private void showError(String message) {

         JOptionPane.showMessageDialog(
                 this,
                 message,
                 "Error",
                 JOptionPane.ERROR_MESSAGE
         );
     }

     private void showWarning(String message) {

         JOptionPane.showMessageDialog(
                 this,
                 message,
                 "Warning",
                 JOptionPane.WARNING_MESSAGE
         );
     }
 }