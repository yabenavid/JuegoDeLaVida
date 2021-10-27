import java.util.ArrayList;

public class Plano {

	private ArrayList<Celda> celdas;
	
	public Plano() {
		super();
		this.celdas=new ArrayList<Celda>();
	}
	
	public void generarPlano(ArrayList<Celula> celulas) {
		int x=0, y=0;
		for(int i=0; i<celulas.size();i++) {
			Celda c = new Celda();
			Coordenada tmp = new Coordenada();
			if(i<8) {
				tmp = new Coordenada(x,y);
				c = new Celda(tmp, celulas.get(i));
				x++;
			}else if(i>=8 && i<16) {
				if(i==8) {
					x=0;
				}
				y=1;
				tmp = new Coordenada(x,y);
				c = new Celda(tmp, celulas.get(i));
				x++;
			}else if(i>=16 && i<24) {
				if(i==16) {
					x=0;
				}
				y=2;
				tmp = new Coordenada(x,y);
				c = new Celda(tmp, celulas.get(i));
				x++;
			}else if(i>=24 && i<32) {
				if(i==24) {
					x=0;
				}
				y=3;
				tmp = new Coordenada(x,y);
				c = new Celda(tmp, celulas.get(i));
				x++;
			}else if(i>=32 && i<40) {
				if(i==32) {
					x=0;
				}
				y=4;
				tmp = new Coordenada(x,y);
				c = new Celda(tmp, celulas.get(i));
				x++;
			}else if(i>=40 && i<48) {
				if(i==40) {
					x=0;
				}
				y=5;
				tmp = new Coordenada(x,y);
				c = new Celda(tmp, celulas.get(i));
				x++;
			}else if(i>=48 && i<56) {
				if(i==48) {
					x=0;
				}
				y=6;
				tmp = new Coordenada(x,y);
				c = new Celda(tmp, celulas.get(i));
				x++;
			}else if(i>=56 && i<64) {
				if(i==56) {
					x=0;
				}
				y=7;
				tmp = new Coordenada(x,y);
				c = new Celda(tmp, celulas.get(i));
				x++;
			}
			
			this.celdas.add(c);
			
			
		}
	}

	public ArrayList<Celda> getCeldas() {
		return celdas;
	}
	
	
}
