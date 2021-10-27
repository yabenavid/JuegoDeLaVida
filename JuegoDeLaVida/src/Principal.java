import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNewButton;
	private Controlador ctr;
	private int contIter;
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		contIter=0;
		ctr = new Controlador();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		btnNewButton = new JButton("Start");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<Celda> cds;
				
				if(contIter==0) {
					cds = ctr.inicializar();
				}else {
					cds = ctr.evolucionar();
				}
				dibujar(cds);
				contIter++;
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
        panel.setBounds(129, 39, 246, 170);
        panel.setSize(50,50);
        //contentPane.add(panel);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
	}
	
	public void dibujar(ArrayList<Celda> celulas) {
		Graphics g = panel.getGraphics();
		int scaledX = panel.getWidth();
		int scaledY = panel.getHeight();
		
		
		for(int i=0; i<celulas.size(); i++) {
			Coordenada tmp = celulas.get(i).getCoordenada();
			g.drawRect(tmp.getX(), tmp.getY(), 20, 20);
			
		}
	}

}
