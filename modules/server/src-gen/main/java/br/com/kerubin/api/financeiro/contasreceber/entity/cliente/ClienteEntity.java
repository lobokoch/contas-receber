/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:26:04.535
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contasreceber.entity.cliente;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "cliente")
public class ClienteEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cpf_cnpj")
	private String cpfCNPJ;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCpfCNPJ() {
		return cpfCNPJ;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome != null ? nome.trim() : nome; // Chamadas REST fazem trim.
	}
	
	public void setCpfCNPJ(String cpfCNPJ) {
		this.cpfCNPJ = cpfCNPJ != null ? cpfCNPJ.trim() : cpfCNPJ; // Chamadas REST fazem trim.
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(ClienteEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setNome(source.getNome());
			this.setCpfCNPJ(source.getCpfCNPJ());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public ClienteEntity clone() {
		ClienteEntity theClone = new ClienteEntity();
		theClone.setId(this.getId());
		theClone.setNome(this.getNome());
		theClone.setCpfCNPJ(this.getCpfCNPJ());
		theClone.setDeleted(this.getDeleted());
		
		return theClone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteEntity other = (ClienteEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return 31;
	}
	
	/* 
	@Override
	public String toString() {
		// Enabling toString for JPA entities will implicitly trigger lazy loading on all fields.
	}
	*/

}