package br.alura.test.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.alura.loja.Servidor;
import br.alura.loja.modelo.Carrinho;
import br.alura.loja.modelo.Produto;

public class ClientTest {
	
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
	public void testaCarrinho() {
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target("http://localhost:8080");

		Carrinho conteudo = target.path("/carrinhos/1").request().get(Carrinho.class);
		
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", conteudo.getRua());
	}
	
	@Test
	public void testaAdicionaCarrinho() {
		
		
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://localhost:8080");
		
		Produto produto = new Produto(1l, "Gaseificadora de agua", 450.00 , 1);
		
		Carrinho carrinho = new Carrinho();
		carrinho.setRua("Av. Sen. Teotonio Vilela");
		carrinho.setCidade("Sao Paulo");
		carrinho.adiciona(produto);
		
		Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        
        Assert.assertEquals(201, response.getStatus());
	}

}
