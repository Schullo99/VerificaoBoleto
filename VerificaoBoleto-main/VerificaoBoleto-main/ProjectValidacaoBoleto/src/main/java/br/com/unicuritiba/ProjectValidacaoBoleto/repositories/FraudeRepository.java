package br.com.unicuritiba.ProjectValidacaoBoleto.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.unicuritiba.ProjectValidacaoBoleto.models.Fraude;

public interface FraudeRepository extends JpaRepository<Fraude, Long> {
    Optional<Fraude> findByCpf(String cpf);
    // CORRIGIDO: O método agora busca pela propriedade correta "linhaDigitavel"
    Optional<Fraude> findByLinhaDigitavel(String linhaDigitavel);
    Optional<Fraude> findByNome(String nome);
}