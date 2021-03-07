package rodarte.testern.viewmodel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import rodarte.testern.funcoes.FuncoesAlunos;
import rodarte.testern.funcoes.FuncoesExportacaoResultados;
import rodarte.testern.funcoes.FuncoesImportacaoAlunos;

public class VmPageInicio {

	private Media arquivoTemporario;
	
	private boolean visibilidadeBotaoLerEProcessar = true;
	
	@Command
	public void uploadArquivo(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws Exception {
		
		UploadEvent event = (UploadEvent) ctx.getTriggerEvent();

		arquivoTemporario = event.getMedia();
		
		BindUtils.postNotifyChange(null, null, VmPageInicio.this, "nomeArquivo");
		
	}
	
	@Command
	public void lerArquivoEProcessar() throws Exception {
		
		if(arquivoTemporario == null) {
			
			Clients.showNotification("Selecione um arquivo!", Clients.NOTIFICATION_TYPE_ERROR, null, null, 3500, true);
			
		} else {
		
			try {
				
				InputStream arquivo = arquivoTemporario.getStreamData();
				
				List<String> lsErros = new FuncoesImportacaoAlunos().iniciarImportacao(arquivo, getNomeArquivo());
				
				if(lsErros.isEmpty()) {
				
					visibilidadeBotaoLerEProcessar = false;
					
					BindUtils.postNotifyChange(null, null, VmPageInicio.this, "visibilidadeBotaoLerEProcessar");
					
					Clients.showNotification("Processamento realizado com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null, 3500, true);
				
				} else {
					
					Messagebox.show("Sua planilha contém erros! Por favor, verifique.", "Erro Importação", Messagebox.OK, Messagebox.ERROR);
					
				}
				
			} catch (Exception ex) {
				
				Messagebox.show(ex.getMessage(), "Erro ao Ler e Processar Arquivo", Messagebox.OK, Messagebox.ERROR);
				
			} 
		
		}
		
	}
	
	@Command
	public void exportarResultados() {
		
		try {
			
			File arquivo = new File("Resultados.xlsx").getCanonicalFile();
			OutputStream out = new FileOutputStream(arquivo.getAbsolutePath());
			
			new FuncoesExportacaoResultados(new FuncoesAlunos().buscarAlunos()).gerarArquivo(out);
			
			Filedownload.save(arquivo, ".xlsx");
			
			Clients.showNotification("Arquivo exportado com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null, 3500, true);
			
		} catch (Exception ex) {

			Messagebox.show(ex.getMessage(), "Erro ao Exportar Resultados", Messagebox.OK, Messagebox.ERROR);
			
		}
		
	}
	
	@Command
	public void resetarImportacao() {
		
		try {
			
			new FuncoesAlunos().resetarImportacao();
			
			arquivoTemporario = null;
			
			visibilidadeBotaoLerEProcessar = true;
			
			BindUtils.postNotifyChange(null, null, VmPageInicio.this, "visibilidadeBotaoLerEProcessar");
			BindUtils.postNotifyChange(null, null, VmPageInicio.this, "nomeArquivo");
			
			Clients.showNotification("Atenção quanto aos dados que foram persistidos no banco!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3500, true);
			
		} catch (Exception ex) {

			Messagebox.show(ex.getMessage(), "Erro Resetar Importacao", Messagebox.OK, Messagebox.ERROR);
			
		}
		
	}
	
	public String getNomeArquivo() {
		
		return arquivoTemporario == null ? "" : arquivoTemporario.getName();
		
	}
	
	public boolean getVisibilidadeBotaoLerEProcessar() {
		
		return this.visibilidadeBotaoLerEProcessar;
		
	}
	
}
