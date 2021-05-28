package br.alura.test.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.alura.loja.Servidor;
import br.alura.loja.modelo.Carrinho;
import br.alura.loja.modelo.Produto;
import br.alura.loja.modelo.Projeto;
import br.alura.loja.resources.ProjetoResource;

public class ProjetoTest {

	HttpServer server;

	@Before
	public void subirServidor() {
		server = Servidor.startaServidor();
	}

	@After
	public void derrubarServidor() {
		server.stop();
	}

	@Test
	public void testaProjetos() {

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://localhost:8080");

		Projeto conteudo = target.path("/projetos/1").request().get(Projeto.class);
		
		Assert.assertEquals("Minha loja", conteudo.getNome());

	}
	
	@Test
	public void testaAdicionaCarrinho() {
		
		
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://localhost:8080");
		
		Projeto projeto = new Projeto();
		projeto.setId(2l);
		projeto.setNome("Partiu Irlanda");
		projeto.setAnoDeInicio(2019);
		
		Entity<Projeto> entity = Entity.entity(projeto, MediaType.APPLICATION_XML);

        Response response = target.path("/projetos").request().post(entity);
        
        Assert.assertEquals(201, response.getStatus());
	}

}
