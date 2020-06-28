package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BuscadorDeImagensUtil {
	public static void buscarImagensNaWeb(List<URL> listURLS, String dirSave, String tipoDeImgsBuscadas) throws Exception {
		System.out.println("------------------------------------------");
		System.out.println("BUSCANDO IMAGENS DE: "+tipoDeImgsBuscadas);
		for(URL url : listURLS) {
			if(url.toString().contains(".com") || url.toString().contains(".org") || url.toString().contains(".edu") || url.toString().contains(".jp/")) {
				//Atribui diretorio temporario para a imagem
				String nomeDoArquivo = url.toString().substring(url.toString().lastIndexOf("/")+1);
				String path = dirSave + nomeDoArquivo;
					
				try {	
					//Gera um file para a imagem, a coloca-a dentro de uma lista de Files e salva no diretorio path
					InputStream in = new URL(url.toString()).openStream();
					Files.copy(in, Paths.get(path));
					System.out.println("SUCESSO: "+url.toString());
					
				}catch (Exception ex) {
					System.out.println("DEU EXCEPTION: "+ex +" URL: "+url.toString());
					continue;	
				}
				
			}
		}
	}
	
	public static List<URL> montarArrayDeUrls_ComRepositorioDeLinks(String enderecoRepDeLinks) throws Exception {
		List<URL> listUrlImgs = new ArrayList<URL>();
		URL urlRepDeLinks = new URL(enderecoRepDeLinks);
		BufferedReader leitorDaURL_posImg = new BufferedReader(new InputStreamReader(urlRepDeLinks.openStream()));
	
		//Listando URLs
		String textURL_Aux = "";
		URL url_aux = null;
		//Laço para pegar as urls de cada imagem do banco http://image-net.org/api/text/imagenet.synset.geturls?wnid=n12663023
		while (leitorDaURL_posImg.readLine() != null) {
			textURL_Aux = leitorDaURL_posImg.readLine();
			
			if(textURL_Aux != null && textURL_Aux != "") {
				textURL_Aux = textURL_Aux.contains("https") ? textURL_Aux : textURL_Aux.replace("http", "https");
				url_aux = new URL(textURL_Aux);
				//System.out.println(url_aux.toString());
				listUrlImgs.add(url_aux);
	
			}
			
		}
		leitorDaURL_posImg.close();
		
		return listUrlImgs;
	}
	
	public static List<URL> montarArrayDeUrls_DeSitesComuns(String linkDoSite, String nomeDoHtmlTemp) throws Exception{
		List<URL> listaUrls = new ArrayList<URL>();
		
		String path = System.getProperty("java.io.tmpdir")+nomeDoHtmlTemp+".html";
		
		URL url = new URL(linkDoSite);
		
		InputStream in = new URL(url.toString()).openStream();
		Files.copy(in, Paths.get(path));
		File file = new File(path); 
		file.deleteOnExit();
		
		Document doc = Jsoup.parse(file, "UTF-8", url.toString());
		org.jsoup.select.Elements linksEncontrados = doc.select("a[href]"); // a with href

		for(Element link: linksEncontrados){
			if(link.toString().contains("https") && link.toString().contains("src=") && link.toString().contains(".jpg")) {
				String urlTratada = StringUtils.substringAfter(link.toString(), " src=");
				String urlTratadaAux = StringUtils.substringBefore(urlTratada, "alt=");
				urlTratadaAux = urlTratadaAux.replaceAll("^[\"']+|[\"']+$", "");
				urlTratadaAux = urlTratadaAux.substring(0, urlTratadaAux.lastIndexOf("."));
				String urlTratadaAux2 = urlTratadaAux+".jpg";
				
				URL urlAux = new URL(urlTratadaAux2);
				listaUrls.add(urlAux);
				//System.out.println("URL MONTADA COM SUCESSO: "+urlAux.toString());
				
			}
		}

		return listaUrls;
	}
}
