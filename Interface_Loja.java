import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Interface_Loja extends JFrame {

	private JPanel contentPane;
	private static JTextField txtNome;
	private static JTextField txtCodigo;
	private static JTextField txtEmail;
	private static JTable tblCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface_Loja frame = new Interface_Loja();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private static void limparTela() {
		
		txtNome.setText("");
		txtCodigo.setText("");
		txtEmail.setText("");
		
		
	}
	private static void preencherTabela() {
		

		try {
			String jdbcurl = "jdbc:sqlserver://LAPTOP-FLUI4M5B\\SQLEXPRESS:64455;user=sa;password=sa;databasename=Eletrônicos";
		    Connection con = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			con = DriverManager.getConnection(jdbcurl);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from dbo.Clientes");
			
			
			DefaultTableModel dtm = (DefaultTableModel) tblCliente.getModel();
			
			dtm.setNumRows(0);
			
			while(rs.next()) {
				dtm.addRow(new Object[]{
						rs.getInt("cod_cliente"),
						rs.getString("nome_cliente"),
						rs.getString("email_cliente")
				});
				
				
				
			}
			
			tblCliente.setModel(dtm);
			
			stmt.close();
			con.close();
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		
	}
	
	
	/**
	 * Create the frame.
	 */
	public Interface_Loja() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				preencherTabela();
			}
		});
		setTitle("Cadastro Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(36, 26, 46, 26);
		contentPane.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(89, 26, 185, 26);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setBounds(295, 69, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(351, 63, 110, 26);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setBounds(36, 69, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(89, 63, 185, 26);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					String jdbcurl = "jdbc:sqlserver://LAPTOP-FLUI4M5B\\SQLEXPRESS:64455;user=sa;password=sa;databasename=Eletrônicos";
				    Connection con = null;
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection(jdbcurl);
					
					
					Statement stmt = con.createStatement();
					
					
					
					if(txtNome.getText().equals("") || txtCodigo.getText().equals("") || txtEmail.getText().equals("") ) {
						JOptionPane.showMessageDialog(contentPane, "Preencha todos os Campos Por favor ");
					}	
					else {
						
						stmt.executeUpdate("Insert into dbo.Clientes(nome_cliente, cod_cliente, email_cliente) Values('"+txtNome.getText()+"','"+txtCodigo.getText()+"','"+txtEmail.getText()+"')");
						JOptionPane.showMessageDialog(contentPane, "Cliente inserido com sucesso !");
						
						limparTela();
						preencherTabela();
					}
					
					stmt.close();
					con.close();
					
					
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
				
				
			}

		
		});
		btnCadastrar.setBounds(89, 114, 96, 26);
		contentPane.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limparTela();
				
				
			}
		});
		btnLimpar.setBounds(295, 170, 89, 26);
		contentPane.add(btnLimpar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 207, 468, 243);
		contentPane.add(scrollPane);
		
		tblCliente = new JTable();
		scrollPane.setViewportView(tblCliente);
		tblCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nome", "Email"
			}
		));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String jdbcurl = "jdbc:sqlserver://LAPTOP-FLUI4M5B\\SQLEXPRESS:64455;user=sa;password=sa;databasename=Eletrônicos";
				    Connection con = null;
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection(jdbcurl);
					
					
					Statement stmt = con.createStatement();
					
					
					
					if(txtNome.getText().equals("") || txtCodigo.getText().equals("") ) {
						JOptionPane.showMessageDialog(contentPane, "Preencha os Campos Por favor ");
					}	
					else {
						
						stmt.executeUpdate("Update dbo.Clientes set nome_cliente = '" + txtNome.getText()+ "' where cod_cliente =" + txtCodigo.getText());
						JOptionPane.showMessageDialog(contentPane, "Cliente editado com sucesso !");
						
						limparTela();
						preencherTabela();
					}
					
					stmt.close();
					con.close();
					
					
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
				
				
				
				
			}
		});
		btnEditar.setBounds(295, 114, 89, 26);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String jdbcurl = "jdbc:sqlserver://LAPTOP-FLUI4M5B\\SQLEXPRESS:64455;user=sa;password=sa;databasename=Eletrônicos";
				    Connection con = null;
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection(jdbcurl);
					
					
					Statement stmt = con.createStatement();
					
					
					
					if(txtCodigo.getText().equals("") ) {
						JOptionPane.showMessageDialog(contentPane, "Preencha o Campo Por favor ");
					}	
					else {
						
						stmt.executeUpdate("Delete from dbo.Clientes where cod_cliente = " + txtCodigo.getText());
						JOptionPane.showMessageDialog(contentPane, "Cliente excluido com sucesso !");
						
						limparTela();
						preencherTabela();
					}
					
					stmt.close();
					con.close();
					
					
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
				
				
				
			}
		});
		btnExcluir.setBounds(89, 170, 96, 26);
		contentPane.add(btnExcluir);
	}
}
