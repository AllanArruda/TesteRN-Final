package rodarte.testern.funcoes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;

public abstract class FuncoesExportacoes {

	private static final int TAMANHO_BUFFER = 4096; //4KB
	
	public int linhaAtual = -1, colunaAtual = -1;
	public XSSFRow linha;
	public XSSFCell celula;
	
	public static XSSFCellStyle ESTILO_CABECALHO_COMUM, ESTILO_CABECALHO_VERMELHO, ESTILO_CABECALHO_AZUL, ESTILO_CABECALHO_VERDE, ESTILO_CONTEUDO_COMUM, 
								ESTILO_CONTEUDO_DOUBLE, ESTILO_CONTEUDO_DATA, ESTILO_CONTEUDO_PERCENTUAL, ESTILO_CABECALHO_BRANCO, ESTILO_CONTEUDO_DOUBLE_AZUL,
								ESTILO_SIMPLES_PERCENTUAL, ESTILO_CONTEUDO_DOUBLE_6_CASAS, ESTILO_CONTEUDO_DOUBLE_AZUL_6_CASAS, ESTILO_CONTEUDO_COMUM_CENTRALIZADO;
	
	public void definirEstilos(XSSFWorkbook wb) {
		
		XSSFFont fonteCabecalhoComum = wb.createFont();
		fonteCabecalhoComum.setBold(true);
		fonteCabecalhoComum.setFontHeightInPoints((short)10);
		fonteCabecalhoComum.setFontName("Arial Narrow");
		
		XSSFFont fonteCabecalhoBranco = wb.createFont();
		fonteCabecalhoBranco.setBold(true);
		fonteCabecalhoBranco.setFontHeightInPoints((short)10);
		fonteCabecalhoBranco.setColor(IndexedColors.WHITE.getIndex());
		fonteCabecalhoBranco.setFontName("Arial Narrow");
		
		XSSFFont fonteConteudoComum = wb.createFont();
		fonteConteudoComum.setBold(false);
		fonteConteudoComum.setFontHeightInPoints((short)10);
		fonteConteudoComum.setFontName("Arial Narrow");
		
		XSSFFont fonteConteudoAzul = wb.createFont();
		fonteConteudoAzul.setBold(false);
		fonteConteudoAzul.setFontHeightInPoints((short)10);
		fonteConteudoAzul.setFontName("Arial Narrow");
		
		XSSFFont fonteVersao = wb.createFont();
		fonteVersao.setBold(true);
		fonteVersao.setItalic(true);
		fonteVersao.setFontHeightInPoints((short)8);
		fonteVersao.setFontName("Arial Narrow");
		
		ESTILO_CABECALHO_COMUM = wb.createCellStyle();
		ESTILO_CABECALHO_COMUM.setFont(fonteCabecalhoComum);
		ESTILO_CABECALHO_COMUM.setBorderTop(BorderStyle.THIN);
		ESTILO_CABECALHO_COMUM.setBorderLeft(BorderStyle.THIN);
		ESTILO_CABECALHO_COMUM.setBorderRight(BorderStyle.THIN);
		ESTILO_CABECALHO_COMUM.setBorderBottom(BorderStyle.THIN);
		ESTILO_CABECALHO_COMUM.setAlignment(HorizontalAlignment.CENTER);
		ESTILO_CABECALHO_COMUM.setVerticalAlignment(VerticalAlignment.CENTER);
		ESTILO_CABECALHO_COMUM.setWrapText(true);
		
		ESTILO_CABECALHO_BRANCO = wb.createCellStyle();
		ESTILO_CABECALHO_BRANCO.cloneStyleFrom(ESTILO_CABECALHO_COMUM);
		ESTILO_CABECALHO_BRANCO.setFont(fonteCabecalhoBranco);
		ESTILO_CABECALHO_BRANCO.setFillForegroundColor(new XSSFColor(new java.awt.Color(32, 55, 100), new DefaultIndexedColorMap()));
		ESTILO_CABECALHO_BRANCO.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		ESTILO_CABECALHO_VERMELHO = wb.createCellStyle();
		ESTILO_CABECALHO_VERMELHO.cloneStyleFrom(ESTILO_CABECALHO_COMUM);
		ESTILO_CABECALHO_VERMELHO.setFillForegroundColor(new XSSFColor(new java.awt.Color(248, 203, 173), new DefaultIndexedColorMap()));
		ESTILO_CABECALHO_VERMELHO.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		ESTILO_CABECALHO_AZUL = wb.createCellStyle();
		ESTILO_CABECALHO_AZUL.cloneStyleFrom(ESTILO_CABECALHO_COMUM);
		ESTILO_CABECALHO_AZUL.setFillForegroundColor(new XSSFColor(new java.awt.Color(221, 235, 247), new DefaultIndexedColorMap()));
		ESTILO_CABECALHO_AZUL.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		ESTILO_CABECALHO_VERDE = wb.createCellStyle();
		ESTILO_CABECALHO_VERDE.cloneStyleFrom(ESTILO_CABECALHO_COMUM);
		ESTILO_CABECALHO_VERDE.setFillForegroundColor(new XSSFColor(new java.awt.Color(226, 239, 218), new DefaultIndexedColorMap()));
		ESTILO_CABECALHO_VERDE.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		ESTILO_CONTEUDO_COMUM = wb.createCellStyle();
		ESTILO_CONTEUDO_COMUM.setFont(fonteConteudoComum);
		ESTILO_CONTEUDO_COMUM.setBorderLeft(BorderStyle.THIN);
		ESTILO_CONTEUDO_COMUM.setBorderBottom(BorderStyle.THIN);
		ESTILO_CONTEUDO_COMUM.setBorderRight(BorderStyle.THIN);
		
		ESTILO_CONTEUDO_COMUM_CENTRALIZADO = wb.createCellStyle();
		ESTILO_CONTEUDO_COMUM_CENTRALIZADO.cloneStyleFrom(ESTILO_CONTEUDO_COMUM);
		ESTILO_CONTEUDO_COMUM_CENTRALIZADO.setAlignment(HorizontalAlignment.CENTER);
		
		ESTILO_CONTEUDO_DOUBLE = wb.createCellStyle();
		ESTILO_CONTEUDO_DOUBLE.cloneStyleFrom(ESTILO_CONTEUDO_COMUM);
		ESTILO_CONTEUDO_DOUBLE.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));
		ESTILO_CONTEUDO_DOUBLE.setAlignment(HorizontalAlignment.CENTER);
		
		ESTILO_CONTEUDO_DOUBLE_6_CASAS = wb.createCellStyle();
		ESTILO_CONTEUDO_DOUBLE_6_CASAS.cloneStyleFrom(ESTILO_CONTEUDO_DOUBLE);
		ESTILO_CONTEUDO_DOUBLE_6_CASAS.setDataFormat(wb.createDataFormat().getFormat("#,##0.000000"));
		
		ESTILO_CONTEUDO_DATA = wb.createCellStyle();
		ESTILO_CONTEUDO_DATA.cloneStyleFrom(ESTILO_CONTEUDO_COMUM);
		ESTILO_CONTEUDO_DATA.setDataFormat(wb.createDataFormat().getFormat("dd/MM/yyyy"));
		ESTILO_CONTEUDO_DATA.setAlignment(HorizontalAlignment.CENTER);
		
		ESTILO_CONTEUDO_PERCENTUAL = wb.createCellStyle();
		ESTILO_CONTEUDO_PERCENTUAL.cloneStyleFrom(ESTILO_CONTEUDO_COMUM);
		ESTILO_CONTEUDO_PERCENTUAL.setDataFormat(wb.createDataFormat().getFormat("#,##0.00%"));
		ESTILO_CONTEUDO_PERCENTUAL.setAlignment(HorizontalAlignment.CENTER);
		
		ESTILO_CONTEUDO_DOUBLE_AZUL = wb.createCellStyle();
		ESTILO_CONTEUDO_DOUBLE_AZUL.setFont(fonteConteudoAzul);
		ESTILO_CONTEUDO_DOUBLE_AZUL.setFillForegroundColor(new XSSFColor(new java.awt.Color(180, 198, 231), new DefaultIndexedColorMap()));
		ESTILO_CONTEUDO_DOUBLE_AZUL.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		ESTILO_CONTEUDO_DOUBLE_AZUL.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));
		
		ESTILO_CONTEUDO_DOUBLE_AZUL_6_CASAS = wb.createCellStyle();
		ESTILO_CONTEUDO_DOUBLE_AZUL_6_CASAS.cloneStyleFrom(ESTILO_CONTEUDO_DOUBLE_AZUL);
		ESTILO_CONTEUDO_DOUBLE_AZUL_6_CASAS.setDataFormat(wb.createDataFormat().getFormat("#,##0.000000"));
	
		ESTILO_SIMPLES_PERCENTUAL = wb.createCellStyle();
		ESTILO_SIMPLES_PERCENTUAL.setFont(fonteCabecalhoComum);
		ESTILO_SIMPLES_PERCENTUAL.setDataFormat(wb.createDataFormat().getFormat("#,##0.00%"));
		
		
	}
	
	/**
	 * Método responsável por pegar uma linha, caso ela exista, e caso não exista ele cria a linha.
	 * @param aba
	 * @param linhaAtual
	 * @return
	 */
	public XSSFRow pegaLinha(XSSFSheet aba, int linhaAtual) {
		
		return aba.getRow(linhaAtual) == null ? aba.createRow(linhaAtual) : aba.getRow(linhaAtual);
		
	}
	
	/**
	 * Verifica se uma matrícula é apenas numerica.
	 * @param matricula - Matrícula que será analisada.
	 * @return - Retorna TRUE caso a matrícula tenha apenas números ou FALSE caso tenha algum outro caracter.
	 */
	public boolean verificaMatriculaNumerica(String matricula) {
		
		try {
			
			Integer.parseInt(matricula);
			
			return true;
			
		} catch (Exception ex) {

			return false;
			
		}
		
	}
	
	/**
	 * Método responsável por zipar uma lista de arquivos.
	 * @param arquivos - Lista com o nome dos arquivos que serão zipados.
	 * @param out - Arquivo final no formato zip.
	 * @throws Exception
	 */
	public static void ziparArquivos(List<String> arquivos, OutputStream out) throws Exception {
		
		int cont;
		byte[] dados = new byte[TAMANHO_BUFFER];
		
		BufferedInputStream origem = null;
		FileInputStream streamDeEntrada = null;
		ZipOutputStream saida = null;
		ZipEntry entry = null;
		
		try{
			
			saida = new ZipOutputStream(new BufferedOutputStream(out));
			
			for(int z = 0; z < arquivos.size(); z++){
				
				File file = new File(arquivos.get(z)).getCanonicalFile();
				
				streamDeEntrada = new FileInputStream(file);
				origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
				entry = new ZipEntry(file.getName());
				saida.putNextEntry(entry);
				
				while((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1){
					saida.write(dados, 0, cont);
				}
				
				origem.close();
				saida.closeEntry();
			}
			
			saida.close();
		
		
		} catch(Exception ex) {
			
			throw new Exception("Não foi possível compactar os arquivos! Erro: " +ex);
			
		}
		
	}
	
	/**Bloqueia a aba indicada contra alterações com uma senha aleatória.
	 * @param aba - Aba que receberá a proteção.
	 */
	public static void protegerGuia(XSSFSheet guia) throws Exception {
		
		String SENHAPROTEGERGUIA = "$"+Math.random() + "%&";
		
		guia.enableLocking();
	    CTSheetProtection guiaProtegida = guia.getCTWorksheet().getSheetProtection(); 
	    
	    guiaProtegida.setSelectLockedCells(false); 
	    guiaProtegida.setSelectUnlockedCells(false); 
	    guiaProtegida.setFormatCells(true); 
	    guiaProtegida.setFormatColumns(false); 
	    guiaProtegida.setFormatRows(false); 
	    guiaProtegida.setInsertColumns(true); 
	    guiaProtegida.setInsertRows(true); 
	    guiaProtegida.setInsertHyperlinks(true); 
	    guiaProtegida.setDeleteColumns(true); 
	    guiaProtegida.setDeleteRows(true); 
	    guiaProtegida.setSort(false); 
	    guiaProtegida.setAutoFilter(false); 
	    guiaProtegida.setPivotTables(true); 
	    guiaProtegida.setObjects(false); 
	    guiaProtegida.setScenarios(false);
	    
	    guia.protectSheet(SENHAPROTEGERGUIA);
	    
	}
	
	public static void desprotegerColuna(XSSFWorkbook wb, XSSFSheet aba, int coluna) {
		
		XSSFCellStyle estilo = wb.createCellStyle();
		estilo.setLocked(false);
		
		aba.setDefaultColumnStyle(coluna, estilo);
		
	}
	
	public static void desprotegerColunas(XSSFWorkbook wb, XSSFSheet aba, int colunaInicial, int colunaFinal) {
		
		XSSFCellStyle estilo = wb.createCellStyle();
		estilo.setLocked(false);
		
		for(int z = colunaInicial; z <= colunaFinal; z++) {
		
			aba.setDefaultColumnStyle(z, estilo);
			
		}
		
	}
	
	/**
	 * Bloqueia o arquivo xlsx cliado.
	 * @param wb - Arquivo que será protegido
	 * @throws Exception
	 */
	public static void protegerArquivo(XSSFWorkbook wb) throws Exception {
		
		String SENHAPROTEGERPLANILHA = "**"+Math.random() + "%&#";

		//BLOQUEIA A PLANILHA, IMPEDINDO A EXCLUS�O DE ABAS
	    wb.lockStructure();
	    wb.setWorkbookPassword(SENHAPROTEGERPLANILHA, null);
		
	}
	
}
