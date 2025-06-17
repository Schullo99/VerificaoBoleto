package br.com.unicuritiba.ProjectValidacaoBoleto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unicuritiba.ProjectValidacaoBoleto.dto.BoletoRequestDTO;
import br.com.unicuritiba.ProjectValidacaoBoleto.models.Boleto;
import br.com.unicuritiba.ProjectValidacaoBoleto.models.Fraude;
import br.com.unicuritiba.ProjectValidacaoBoleto.repositories.BoletoRepository;
import br.com.unicuritiba.ProjectValidacaoBoleto.repositories.FraudeRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/boleto")
public class BoletoControllers {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private FraudeRepository fraudeRepository;
    
    // Função auxiliar para evitar repetição de código
    private void registrarFraude(BoletoRequestDTO request) {
        Fraude fraude = new Fraude();
        fraude.setLinhaDigitavel(request.getLinhaDigitavel());
        fraude.setCodigo_banco(request.getCodigoBanco());
        fraude.setCpf(request.getCpf());
        fraude.setVencimento(request.getVencimento());
        fraude.setValor(request.getValor());
        fraudeRepository.save(fraude);
    }

    @PostMapping("/verificar")
    public ResponseEntity<?> verificarBoleto(@RequestBody BoletoRequestDTO request) {

        // 1. Validação da linha digitável
        if (request.getLinhaDigitavel() == null || !request.getLinhaDigitavel().matches("\\d{44}")) {
            registrarFraude(request); // <<< Registra a tentativa
            return ResponseEntity.badRequest().body("Linha digitável inválida. Deve conter 44 dígitos numéricos.");
        }

        // 2. Verificar código do banco
        String codigoBancoStr = String.format("%03d", request.getCodigoBanco());
        if (!request.getLinhaDigitavel().startsWith(codigoBancoStr)) {
            registrarFraude(request); // <<< Registra a tentativa
            return ResponseEntity.badRequest().body("Código do banco no código de barras não confere.");
        }

        // 3. Validar CPF
        if (request.getCpf() == null || !request.getCpf().matches("\\d{11}")) {
            registrarFraude(request); // <<< Registra a tentativa
            return ResponseEntity.badRequest().body("CPF inválido. Deve conter 11 dígitos numéricos.");
        }

        // 4. Validar valor
        try {
            double valor = Double.parseDouble(request.getValor().replace(",", "."));
            if (valor <= 0) {
                registrarFraude(request); // <<< Registra a tentativa
                return ResponseEntity.badRequest().body("Valor deve ser maior que zero.");
            }
        } catch (NumberFormatException e) {
            registrarFraude(request); // <<< Registra a tentativa
            return ResponseEntity.badRequest().body("Valor inválido.");
        }

        // 5. Validar vencimento
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate vencimento = LocalDate.parse(request.getVencimento(), formatter);
            if (vencimento.isBefore(LocalDate.now())) {
                registrarFraude(request); // <<< Registra a tentativa
                return ResponseEntity.badRequest().body("Boleto vencido.");
            }
        } catch (Exception e) {
            registrarFraude(request); // <<< Registra a tentativa
            return ResponseEntity.badRequest().body("Data de vencimento inválida.");
        }

        // 6. Verificar se já existe o boleto
        Optional<Boleto> boletoExistente = boletoRepository.findByLinhaDigitavel(request.getLinhaDigitavel());

        if (boletoExistente.isPresent()) {
            if (!boletoExistente.get().getCpf().equals(request.getCpf())) {
                registrarFraude(request); // Lógica de fraude que já existia
                return ResponseEntity.status(409).body("Fraude detectada: CPF não confere.");
            }
            return ResponseEntity.ok("Boleto válido e já existente.");
        }

        // 7. Salvar boleto válido
        Boleto novoBoleto = new Boleto();
        novoBoleto.setLinhaDigitavel(request.getLinhaDigitavel());
        novoBoleto.setCodigo_banco(request.getCodigoBanco());
        novoBoleto.setCpf(request.getCpf());
        novoBoleto.setVencimento(request.getVencimento());
        novoBoleto.setValor(request.getValor());

        boletoRepository.save(novoBoleto);

        return ResponseEntity.ok("Boleto verificado e salvo com sucesso.");
    }
}