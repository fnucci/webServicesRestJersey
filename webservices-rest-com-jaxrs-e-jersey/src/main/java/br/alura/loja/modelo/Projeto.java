package br.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {

	private String nome;

	private long id;

	private int anoDeInicio;

	public Projeto() {}

	public Projeto(long id, String nome, int anoDeInicio) {

		this.id = id;
		this.nome = nome;
		this.anoDeInicio = anoDeInicio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAnoDeInicio() {
		return anoDeInicio;
	}

	public void setAnoDeInicio(int anoDeInicio) {
		this.anoDeInicio = anoDeInicio;
	}

	public String toXml() {

		XStream stream = new XStream();

		return stream.toXML(this);
	}

	public String toJson() {

		Gson gson = new Gson();

		return gson.toJson(this);
	}

}
