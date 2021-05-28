package br.alura.loja.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.alura.loja.dao.CarrinhoDAO;
import br.alura.loja.modelo.Carrinho;
import br.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Carrinho busca(@PathParam("id") long id) {

		CarrinhoDAO dao = new CarrinhoDAO();

		Carrinho carrinho = dao.busca(id);

		return carrinho;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinhoXML) throws URISyntaxException {
				
		CarrinhoDAO dao = new CarrinhoDAO();
		
		dao.adiciona(carrinhoXML);
		
		URI uri = new URI("/carrinhos/" + carrinhoXML.getId());
				
		return Response.created(uri).build();
	}
	
	@Path("{id}/produtos/{produtoId}")
	@DELETE
	public Response removerProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		return Response.ok().build();
	}
	
	@Path("{id}/produtos/{produtoId}")
	@PUT
	public Response alterarProduto(Produto conteudo, @PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.troca(conteudo);
		return Response.ok().build();
	}
	
	@Path("{id}/produtos/{produtoId}/qtd")
	@PUT
	public Response alteraqtd(Produto conteudo, @PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.trocaQuantidade(conteudo);
		return Response.ok().build();
	}

}
