package rodarte.testern.funcoes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class FuncoesImportacoes {

	public List<String> lsErros = new ArrayList<String>();
	public int numLinhasVazias;
	
	public static final int LIMITE_LINHAS_VAZIAS = 5;
	
	/**
	 * Verifica se todas as colunas de uma linha estão vazias.
	 * @param celulas
	 * @return
	 */
	public static boolean isLinhaVazia(Cell... celulas) {
		
		boolean linhaVazia = true;
		
		for(Cell celula : celulas) {
			
			if(celula != null && celula.getCellType() != CellType.BLANK) {
				
				linhaVazia = false;
				break;
				
			}
			
		}
		
		return linhaVazia;
		
	}
	
	/**
	 * Verifica se todas as colunas de uma linha estão vazias.
	 * @param celulas
	 * @return
	 */
	public static boolean isLinhaVazia(List<Cell> celulas) {
		
		boolean linhaVazia = true;
		
		for(Cell celula : celulas) {
			
			if(celula != null && celula.getCellType() != CellType.BLANK) {
				
				linhaVazia = false;
				break;
				
			}
			
		}
		
		return linhaVazia;
		
	}
	
	/**
	 * Ler em uma celula uma string ou um número, e retornar o valor no formato de uma String.
	 * @param cell - Celula que terá seu valor analisado.
	 * @param lsErros - Lista de erros para registro caso ocorra algum erro.
	 * @param campo - Nome do campo que está sendo verificado.
	 * @param arquivo - Nome do arquivo que gerou o erro.
	 * @param restricoes - Restrições que devem ser verificadas na leitura do campo.
	 * @param valoresPermitidos - Valores predefinidos que são aceitos para o campo lido.
	 * @param linha - Index da linha lida, que será somado a 1 em caso de erro, para exibição.
	 * @return
	 * @throws Exception
	 */
	public static String lerStringOuNumero(Cell cell, List<String> lsErros, String campo, String arquivo, Predicate<String> restricoes, String valoresPermitidos, int linha) throws Exception {
		
		if(cell == null || cell.getCellType() == CellType.BLANK) {
			
			lsErros.add(campo + " em branco! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
			return "";
			
		} else if(cell.getCellType() == CellType.STRING) {
			
			if(restricoes == null || restricoes.test(cell.getStringCellValue())) {
			
				return cell.getStringCellValue();
			
			} else {
				
				lsErros.add(campo + " Inválido! Valores permitidos: " + valoresPermitidos + ". Arquivo: " + arquivo + ". Linha: " + (linha + 1));
				return "";
				
			}
			
		} else if(cell.getCellType() == CellType.NUMERIC) {
			
			if(restricoes == null || restricoes.test(String.valueOf((int) cell.getNumericCellValue()))){
				
				return String.valueOf((int) cell.getNumericCellValue());
			
			} else {
				
				lsErros.add(campo + " Inválido! Valores permitidos: " + valoresPermitidos + ". Arquivo: " + arquivo + ". Linha: " + (linha + 1));
				return "";
				
			}
			
		} else {
			
			lsErros.add(campo + " em formato incorreto! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
			return "";
			
		}
		
	}
	
	/**
	 * Ler em uma celula uma string e retornar o valor.
	 * @param cell - Celula que terá seu valor analisado.
	 * @param lsErros - Lista de erros para registro caso ocorra algum erro.
	 * @param campo - Nome do campo que está sendo verificado.
	 * @param arquivo - Nome do arquivo que gerou o erro.
	 * @param restricoes - Restrições que devem ser verificadas na leitura do campo.
	 * @param valoresPermitidos - Valores predefinidos que são aceitos para o campo lido.
	 * @param linha - Index da linha lida, que será somado a 1 em caso de erro, para exibição.
	 * @return
	 * @throws Exception
	 */
	public static String lerString(Cell cell, List<String> lsErros, String campo, String arquivo, Predicate<String> restricoes, String valoresPermitidos, int linha) throws Exception {
		
		if(cell == null || cell.getCellType() == CellType.BLANK) {
			
			lsErros.add(campo + " em branco! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
			return "";
			
		} else if(cell.getCellType() == CellType.STRING) {
			
			if(restricoes == null || restricoes.test(cell.getStringCellValue())) {
				
				return cell.getStringCellValue();
				
			} else {
				
				lsErros.add(campo + " inválido! Valores permitidos: " + valoresPermitidos + ". Arquivo: " + arquivo + ". Linha: " + (linha + 1));
				return "";
				
			}
			
		} else {
			
			lsErros.add(campo + " em formato incorreto! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
			return "";
			
		}
		
	}
	
	/**
	 * Ler em uma celula uma data e retornar seu valor.
	 * @param cell - Celula que terá seu valor analisado.
	 * @param lsErros - Lista de erros para registro caso ocorra algum erro.
	 * @param campo - Nome do campo que está sendo verificado.
	 * @param arquivo - Nome do arquivo que gerou o erro.
	 * @param restricoes - Restrições que devem ser verificadas na leitura do campo.
	 * @param valoresPermitidos - Valores predefinidos que são aceitos para o campo lido.
	 * @param linha - Index da linha lida, que será somado a 1 em caso de erro, para exibição.
	 * @return
	 * @throws Exception
	 */
	public static LocalDate lerData(Cell cell, List<String> lsErros, String campo, String arquivo, Predicate<LocalDate> restricoes, String valoresPermitidos, int linha) throws Exception {
		
		if(cell == null || cell.getCellType() == CellType.BLANK) {
			
			lsErros.add(campo + " em branco! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
			return null;
			
		} else if(cell.getCellType() == CellType.NUMERIC) {
			
			try {
				
				if(restricoes == null || restricoes.test(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
				
					return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				} else {
					
					lsErros.add(campo + " inválida! Valores permitidos: " + valoresPermitidos + ". Arquivo: " + arquivo + ". Linha: " + (linha + 1));
					return null;
					
				}
				
			} catch (Exception e) {

				lsErros.add(campo + " inválida! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
				return null;
				
			}
			
		} else {
			
			if(cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().toUpperCase().equals("ND")) {
				
				return null;
				
			} else if(cell.getCellType() == CellType.STRING) {
			
				try {
					
					String valor = cell.getStringCellValue().trim();
					
					if(valor.length() > 10)
						valor = valor.substring(0, 10);
						
					LocalDate data = LocalDate.parse(valor, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					
					return data;
					
				} catch (Exception ex) {

					lsErros.add(campo + " em formato incorreto! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
					return null;
					
				}
				
			
			} else {
			
				lsErros.add(campo + " em formato incorreto! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
				return null;
			
			}
			
		}
		
	}
	
	/**
	 * Ler em uma celula um double e retornar o valor.
	 * @param cell - Celula que terá seu valor analisado.
	 * @param lsErros - Lista de erros para registro caso ocorra algum erro.
	 * @param campo - Nome do campo que está sendo verificado.
	 * @param arquivo - Nome do arquivo que gerou o erro.
	 * @param restricoes - Restrições que devem ser verificadas na leitura do campo.
	 * @param valoresPermitidos - Valores predefinidos que são aceitos para o campo lido.
	 * @param linha - Index da linha lida, que será somado a 1 em caso de erro, para exibição.
	 * @return
	 * @throws Exception
	 */
	public static double lerDouble(Cell cell, List<String> lsErros, String campo, String arquivo, Predicate<Double> restricoes, String valoresPermitidos, int linha) throws Exception {
		
		if(cell == null || cell.getCellType() == CellType.BLANK) {
			
			lsErros.add(campo + " em branco! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
			return 0;
			
		} else if(cell.getCellType() == CellType.NUMERIC) {
			
			if(restricoes == null || restricoes.test(cell.getNumericCellValue())){
				
				return cell.getNumericCellValue();
			
			} else {
				
				lsErros.add(campo + " Inválido! Valores permitidos: " + valoresPermitidos + ". Arquivo: " + arquivo + ". Linha: " + (linha + 1));
				return 0;
				
			}
			
		} else {
			
			lsErros.add(campo + " em formato incorreto! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
			return 0;
			
		}
		
	}
	
	/**
	 * Ler em uma celula um double e retornar o valor.
	 * @param cell - Celula que terá seu valor analisado.
	 * @param lsErros - Lista de erros para registro caso ocorra algum erro.
	 * @param campo - Nome do campo que está sendo verificado.
	 * @param arquivo - Nome do arquivo que gerou o erro.
	 * @param restricoes - Restrições que devem ser verificadas na leitura do campo.
	 * @param valoresPermitidos - Valores predefinidos que são aceitos para o campo lido.
	 * @param linha - Index da linha lida, que será somado a 1 em caso de erro, para exibição.
	 * @return
	 * @throws Exception
	 */
	public static int lerInteiro(Cell cell, List<String> lsErros, String campo, String arquivo, Predicate<Integer> restricoes, String valoresPermitidos, int linha) throws Exception {
		
		if(cell == null || cell.getCellType() == CellType.BLANK) {
			
			lsErros.add(campo + " em branco! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
			return 0;
			
		} else if(cell.getCellType() == CellType.NUMERIC) {
			
			if(restricoes == null || restricoes.test((int)cell.getNumericCellValue())){
				
				return (int)cell.getNumericCellValue();
			
			} else {
				
				lsErros.add(campo + " Inválido! Valores permitidos: " + valoresPermitidos + ". Arquivo: " + arquivo + ". Linha: " + (linha + 1));
				return 0;
				
			}
			
		} else {
			
			lsErros.add(campo + " em formato incorreto! Arquivo: " + arquivo + ". Linha: " + (linha + 1));
			return 0;
			
		}
		
	}
	
	public static InputStream clonarArquivo(final InputStream inputStream) throws Exception {
       
		inputStream.mark(0);
        
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
		byte[] buffer = new byte[1024];
        int readLength = 0;
        
        while ((readLength = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, readLength);
        }
        
        inputStream.reset();
        outputStream.flush();
        
        return new ByteArrayInputStream(outputStream.toByteArray());
	
    }
	
	public static void fecharWorkbook(XSSFWorkbook wb) throws Exception {
		
		if(wb != null)
			wb.close();
		
	}
	
}
