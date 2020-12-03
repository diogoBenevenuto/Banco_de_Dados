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

public class Vendedores extends JFrame {

	private JPanel contentPane;
	private static JTextField txtNome;
	private static JTextField txtCodigo;
	private static JTextField txtProduto;
	private static JTable tblVendedores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vendedores frame = new Vendedores();
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
		txtProduto.setText("");
		
		
	}
	private static void preencherTabela() {
		

		try {
			String jdbcurl = "jdbc:sqlserver://LAPTOP-FLUI4M5B\\SQLEXPRESS:64455;user=sa;password=sa;databasename=Eletrônicos";
		    Connection con = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			con = DriverManager.getConnection(jdbcurl);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from dbo.Vendedores");
			
			
			DefaultTableModel dtm = (DefaultTableModel) tblVendedores.getModel();
			
			dtm.setNumRows(0);
			
			while(rs.next()) {
				dtm.addRow(new Object[]{
						rs.getInt("Cod_vendedor"),
						rs.getString("Vendedor"),
						rs.getString("itens_vendido")
				});
				
				
				
			}
			
			tblVendedores.setModel(dtm);
			
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
	public Vendedores() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				preencherTabela();
			}
		});
		setTitle("Vendedores ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(36, 26, 52, 26);
		contentPane.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(98, 26, 185, 26);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setBounds(295, 69, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(351, 63, 110, 26);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Produto:");
		lblNewLabel_2.setBounds(36, 69, 52, 14);
		contentPane.add(lblNewLabel_2);
		
		txtProduto = new JTextField();
		txtProduto.setBounds(98, 63, 185, 26);
		contentPane.add(txtProduto);
		txtProduto.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					String jdbcurl = "jdbc:sqlserver://LAPTOP-FLUI4M5B\\SQLEXPRESS:64455;user=sa;password=sa;databasename=Eletrônicos";
				    Connection con = null;
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection(jdbcurl);
					
					
					Statement stmt = con.createStatement();
					
					
					
					if(txtNome.getText().equals("") || txtCodigo.getText().equals("") || txtProduto.getText().equals("") ) {
						JOptionPane.showMessageDialog(contentPane, "Preencha todos os Campos Por favor ");
					}	
					else {
						
						stmt.executeUpdate("Insert into dbo.Vendedores(Vendedor, Cod_vendedor, itens_vendido) Values('"+txtNome.getText()+"','"+txtCodigo.getText()+"','"+txtProduto.getText()+"')");
						JOptionPane.showMessageDialog(contentPane, "Vendedor inserido com sucesso !");
						
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
		
		tblVendedores = new JTable();
		scrollPane.setViewportView(tblVendedores);
		tblVendedores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo ", "Vendedor", "Produto"
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
						
						stmt.executeUpdate("Update dbo.Vendedores set Vendedor = '" + txtNome.getText()+ "' where Cod_vendedor =" + txtCodigo.getText());
						JOptionPane.showMessageDialog(contentPane, "Vendedor editado com sucesso !");
						
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
						
						stmt.executeUpdate("Delete from dbo.Vendedores where Cod_vendedor = " + txtCodigo.getText());
						JOptionPane.showMessageDialog(contentPane, "Vendedor excluido com sucesso !");
						
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

