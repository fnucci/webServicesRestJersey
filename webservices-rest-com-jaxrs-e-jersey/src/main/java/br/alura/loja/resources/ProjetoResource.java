package br.alura.loja.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.alura.loja.dao.ProjetoDAO;
import br.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Projeto busca(@PathParam("id") long id) {

		ProjetoDAO dao = new ProjetoDAO();

		Projeto projeto = dao.busca(id);

		return projeto;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Projeto projetoXML) throws URISyntaxException {
		
		ProjetoDAO dao = new ProjetoDAO();

		dao.adiciona(projetoXML);

		URI uri = new URI("/projetos/" + projetoXML.getId());

		return Response.created(uri).build();
	}

	@DELETE
	@Path("{id}")
	public Response removerProjeto(@PathParam("id") long projetoId) {

		new ProjetoDAO().remove(projetoId);

		return Response.ok().build();
	}

}
