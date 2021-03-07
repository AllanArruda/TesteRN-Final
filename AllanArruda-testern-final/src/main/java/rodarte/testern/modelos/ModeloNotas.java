package rodarte.testern.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModeloNotas {

	private long id;
	private int periodo;
	private double nota;
	
	public ModeloNotas(int periodo, double nota) {
		
		this.periodo = periodo;
		this.nota = nota;
		
	}
	
}
