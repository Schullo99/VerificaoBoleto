package br.com.unicuritiba.ProjectValidacaoBoleto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unicuritiba.ProjectValidacaoBoleto.models.Empresa;
import br.com.unicuritiba.ProjectValidacaoBoleto.models.Fraude;

public interface FraudeRepository 
extends JpaRepository<Empresa, Long>{
	Optional<Empresa> findByCpf(String codigo_Cpf);

	Optional<Empresa> findByCodigo_barras(String codigo_barras);
	Optional<Empresa> findByNome(String nome);

	Fraude save(Fraude fraude);

}
