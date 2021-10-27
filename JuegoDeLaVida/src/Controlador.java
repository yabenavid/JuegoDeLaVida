import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Controlador {
	
	private ArrayList<Celula> celulas;
	private Plano plano;
		
	public Controlador() {
		super();
		this.celulas = new ArrayList<>();
		this.plano = new Plano();
	}

	public ArrayList<Celda> inicializar() {
		ArrayList<Integer> numerosPatron = new ArrayList<>();
		
		GestionFile f = new GestionFile();
		
			
	    try {
			numerosPatron = f.readNums();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			numerosPatron = f.getLista();
		}
	    
	    for(int i=0; i<numerosPatron.size(); i++) {
	    	System.out.println(numerosPatron.get(i));
	    }
	    generarCelulas(numerosPatron);
	    
	    plano.generarPlano(celulas);
	    return plano.getCeldas();
	}
	
	public void generarCelulas(ArrayList<Integer> patron){
		
		Double aleat;
		for(int i=0; i<patron.size();i++) {
			Celula tmp = new Celula();
			if(patron.get(i) == 0) {
				tmp.setEstadoDeVida(false);
			}else {
				tmp.setEstadoDeVida(true);
			}
			aleat = tmp.evaluarCelula();
			if(aleat >= 0.8) {
				tmp.setInmunidad(true);
			}else if(aleat <= 0.2){
				tmp.setSalud(false);
				tmp.setTiempoDeVida(5);
			}
			
			celulas.add(tmp);
		}
	}
	
	public ArrayList<Celda> evolucionar() {
		ArrayList<Celula> celulas = new ArrayList<>();
		ArrayList<Integer> posEnListaVecinas = new ArrayList<>();
		ArrayList<Integer> posVacsIteracionActual = new ArrayList<>();
		int cantVecinasVivas=0, posx, posy, posProxInfected;
		Random rn = new Random();
		
		posVacsIteracionActual = vacunar();
		
		for(int i=0; i<plano.getCeldas().size(); i++) {
			/*
			 * si la celula a evolucionar ha sido vacunada en la actual iteración.
			 */
			if (posVacsIteracionActual.contains(i)) {
				continue;
			}
			
			Celula tmp = plano.getCeldas().get(i).getCelula();
			Coordenada c = plano.getCeldas().get(i).getCoordenada();
			posx=c.getX();
			posy=c.getY();
			
			/*
			 * si tmp está viva..
			 */
			if(tmp.isEstadoDeVida()) {
				
				//si tmp está infectada
				if(tmp.isSalud()==false) { 
					plano.getCeldas().get(i).getCelula().setTiempoDeVida(tmp.getTiempoDeVida()-1);
					
					for(int j=0;j<plano.getCeldas().size();j++) {
						/*
						 * si la celula está viva..
						 */
						if(plano.getCeldas().get(j).getCelula().isEstadoDeVida()) {
							/*
							 * Si la célula es vecina de tmp..
							 */
							if(plano.getCeldas().get(j).getCoordenada().getX() == (posx-1)) {
								if(plano.getCeldas().get(j).getCoordenada().getY() == (posy-1) || plano.getCeldas().get(j).getCoordenada().getY()
										== posy	|| plano.getCeldas().get(j).getCoordenada().getY() == (posy+1)) {
									/*
									 * si la célula vecina NO está infectada y además NO es inmune..
									 */
									if(plano.getCeldas().get(j).getCelula().isSalud() && plano.getCeldas().get(j).getCelula().isInmunidad()==false) {
										posEnListaVecinas.add(j);
									}
								}
								
							}else if(plano.getCeldas().get(j).getCoordenada().getX() == posx) {
								if(plano.getCeldas().get(j).getCoordenada().getY() == (posy-1) || 
										plano.getCeldas().get(j).getCoordenada().getY() == (posy+1)) {
									if(plano.getCeldas().get(j).getCelula().isSalud() && plano.getCeldas().get(j).getCelula().isInmunidad()==false) {
										posEnListaVecinas.add(j);
									}
								}
							}else if(plano.getCeldas().get(j).getCoordenada().getX() == (posx+1)) {
								if(plano.getCeldas().get(j).getCoordenada().getY() == (posy-1) || plano.getCeldas().get(j).getCoordenada().getY() 
										== posy || plano.getCeldas().get(j).getCoordenada().getY() == (posy+1)) {
									if(plano.getCeldas().get(j).getCelula().isSalud() && plano.getCeldas().get(j).getCelula().isInmunidad()==false) {
										posEnListaVecinas.add(j);
									}
								}
							}
						}
					}
					
					/*
					 * si se encontraron células vecinas que pueden ser infectadas..
					 */
					if(posEnListaVecinas.isEmpty()==false) {
						posProxInfected= posEnListaVecinas.get(rn.nextInt(posEnListaVecinas.size()));
						plano.getCeldas().get(posProxInfected).getCelula().setSalud(false);
					}
					
					if(plano.getCeldas().get(i).getCelula().getTiempoDeVida()==0) {
						plano.getCeldas().get(i).getCelula().setEstadoDeVida(false);
					}
				}
				/*
				 * si tmp NO está infectada..
				 */
				else {
					for(int j=0; j<plano.getCeldas().size(); j++) {
						
						if(plano.getCeldas().get(j).getCoordenada().getX() == (posx-1)) {
							if(plano.getCeldas().get(j).getCoordenada().getY() == (posy-1) || plano.getCeldas().get(j).getCoordenada().getY() 
									== (posy) || plano.getCeldas().get(j).getCoordenada().getY() == (posy+1)) {
								/*
								 * si la vecina está viva..
								 */
								if(plano.getCeldas().get(j).getCelula().isEstadoDeVida()) {
									cantVecinasVivas++;
								}
								
							}
						}else if(plano.getCeldas().get(j).getCoordenada().getX() == (posx)) {
							if(plano.getCeldas().get(j).getCoordenada().getY() == (posy-1) 
									||  plano.getCeldas().get(j).getCoordenada().getY() == (posy+1)) {
								/*
								 * si la vecina está viva..
								 */
								if(plano.getCeldas().get(j).getCelula().isEstadoDeVida()) {
									cantVecinasVivas++;
								}
								
							}
						}else if(plano.getCeldas().get(j).getCoordenada().getX() == (posx+1)) {
							if(plano.getCeldas().get(j).getCoordenada().getY() == (posy-1) || plano.getCeldas().get(j).getCoordenada().getY() 
									== (posy)  ||  plano.getCeldas().get(j).getCoordenada().getY() == (posy+1)) {
								/*
								 * si la vecina está viva..
								 */
								if(plano.getCeldas().get(j).getCelula().isEstadoDeVida()) {
									cantVecinasVivas++;
								}
								
							}
						}
						
					}
					
					//FALLECIMIENTO
					if(cantVecinasVivas<2 || cantVecinasVivas>3) {
						plano.getCeldas().get(i).getCelula().setEstadoDeVida(false);
					}
				}
				
			}
			/*
			 * si tmp está muerta..
			 */
			else {
				for(int j=0; j<plano.getCeldas().size(); j++) {
					
					if(plano.getCeldas().get(j).getCoordenada().getX() == (posx-1)) {
						if(plano.getCeldas().get(j).getCoordenada().getY() == (posy-1) || plano.getCeldas().get(j).getCoordenada().getY() 
								== (posy) || plano.getCeldas().get(j).getCoordenada().getY() == (posy+1)) {
							/*
							 * si la vecina está viva..
							 */
							if(plano.getCeldas().get(j).getCelula().isEstadoDeVida()) {
								cantVecinasVivas++;
							}
							
						}
					}else if(plano.getCeldas().get(j).getCoordenada().getX() == (posx)) {
						if(plano.getCeldas().get(j).getCoordenada().getY() == (posy-1) 
								||  plano.getCeldas().get(j).getCoordenada().getY() == (posy+1)) {
							/*
							 * si la vecina está viva..
							 */
							if(plano.getCeldas().get(j).getCelula().isEstadoDeVida()) {
								cantVecinasVivas++;
							}
							
						}
					}else if(plano.getCeldas().get(j).getCoordenada().getX() == (posx+1)) {
						if(plano.getCeldas().get(j).getCoordenada().getY() == (posy-1) || plano.getCeldas().get(j).getCoordenada().getY() 
								== (posy)  ||  plano.getCeldas().get(j).getCoordenada().getY() == (posy+1)) {
							/*
							 * si la vecina está viva..
							 */
							if(plano.getCeldas().get(j).getCelula().isEstadoDeVida()) {
								cantVecinasVivas++;
							}
							
						}
					}
					
				}
				
				//NACIMIENTO
				if(cantVecinasVivas == 3) {
					plano.getCeldas().get(i).getCelula().setEstadoDeVida(true);
				}
			
			}
			
			
		}
		
		for(int i=0; i<plano.getCeldas().size();i++) {
			Celula cl = plano.getCeldas().get(i).getCelula();
			celulas.add(cl);
		}
		
		return plano.getCeldas();
	}
	
	public ArrayList<Integer> vacunar() {
		double valorDeRef = 0.3;
		double evaluacionCelula;
		ArrayList<Integer> posiciones = new ArrayList<>();
		for(int i=0; i<plano.getCeldas().size();i++) {
			if(plano.getCeldas().get(i).getCelula().isEstadoDeVida()) {
				if(plano.getCeldas().get(i).getCelula().isSalud()==false) {
					evaluacionCelula = plano.getCeldas().get(i).getCelula().evaluarCelula();
					if(evaluacionCelula < valorDeRef) {
						plano.getCeldas().get(i).getCelula().setSalud(true);
						posiciones.add(i);
					}
				}
			}
		}
		return posiciones;
	}

}
