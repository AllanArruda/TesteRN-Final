package rodarte.testern.enums;

import java.util.function.Predicate;

public enum EnumSexo {

	F, M;
	
	public static String valoresDisponiveis() throws Exception {
		
		return "F ou M";
		
	}
	
	public static Predicate<String> restricoesSexo() throws Exception {
		
		return sexo -> sexo.equals("M") || sexo.equals("F"); 
		
	}
	
}
