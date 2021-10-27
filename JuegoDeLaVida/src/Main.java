import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int iter=0;
		ArrayList<Celda> celdas = new ArrayList<Celda>();
		Controlador ctr = new Controlador();
		celdas = ctr.inicializar();
		for(int i=0; i<celdas.size(); i++) {
			Celula c = celdas.get(i).getCelula();
			if(c.isEstadoDeVida()) {
				System.out.print("1 ");
			}else {
				System.out.print("0 ");
			}
			if(i==7 || i==15 || i==23 || i==31 || i==39 || i==47 || i==55) {
				System.out.println("\n");
			}
		}
		

	}

}
