package br.com.paoqueclica.pao_que_clica.service;

import br.com.paoqueclica.pao_que_clica.model.Pao;
import br.com.paoqueclica.pao_que_clica.repository.PaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaoService {

    @Autowired
    private PaoRepository paoRepository;

    public Pao verificarPao(String userId){
        Optional<Pao> verificado = paoRepository.findByUserId(userId);

        if (verificado.isPresent()){
            Pao paoExistente = verificado.get();
            Pao paoAtualizado = atualizarProducaoPassiva(paoExistente);
            return paoRepository.save(paoAtualizado);
        }
        else {
            Pao novoPao = new Pao();
            novoPao.setUserId(userId);
            novoPao.setSaldo(0);
            novoPao.setMultiplicador(1);
            novoPao.setPaoPorSegundo(0);
            novoPao.setCustoPadeiro(250);
            novoPao.setCustoForno(1000);
            novoPao.setUltimaAtualizacao(LocalDateTime.now());
            return paoRepository.save(novoPao);
        }
    }

    public Pao clique(String userId){
        Pao pao = verificarPao(userId);
        int ganhos = pao.getMultiplicador();
        pao.setSaldo(pao.getSaldo() + ganhos);
        return paoRepository.save(pao);
    }

    public Pao upgrade1(String userId) {
        Pao pao = verificarPao(userId);
        int custoUpgrade1 = (pao.getMultiplicador() * 50);
        if (pao.getSaldo() >= custoUpgrade1) {
            pao.setSaldo(pao.getSaldo() - custoUpgrade1);
            pao.setMultiplicador(pao.getMultiplicador() + 1);
            return paoRepository.save(pao);
        }
        return pao;
    }

    public Pao reset(String userId) {
        Pao pao = verificarPao(userId);
        pao.setMultiplicador(1);
        pao.setSaldo(0);
        pao.setPaoPorSegundo(0);
        pao.setCustoPadeiro(250);
        pao.setCustoForno(1000);
        return paoRepository.save(pao);
    }

    public Pao cliqueAutomatico(String userId) {
        Pao pao = verificarPao(userId);
        int custoPadeiro = pao.getCustoPadeiro();
        if (custoPadeiro == 0) {
            custoPadeiro = 250;
            pao.setCustoPadeiro(custoPadeiro);
        }
        if (pao.getCustoPadeiro() < 250){
            pao.setCustoPadeiro(250);
        }
        else {pao.setCustoPadeiro((int) (pao.getCustoPadeiro() * 1.5));}

        if (pao.getSaldo() >= custoPadeiro) {
            pao.setSaldo(pao.getSaldo() - custoPadeiro);
            pao.setPaoPorSegundo(pao.getPaoPorSegundo() + 1);

            return paoRepository.save(pao);
        }
        return pao;
    }

    private Pao atualizarProducaoPassiva(Pao pao) {
        if (pao.getUltimaAtualizacao() != null && pao.getPaoPorSegundo() != null && pao.getPaoPorSegundo() > 0) {
            LocalDateTime agora = LocalDateTime.now();
            // Calcula a diferença de tempo em segundos
            long segundosPassados = Duration.between(pao.getUltimaAtualizacao(), agora).toSeconds();

            if (segundosPassados > 0) {
                int paesGanhos = (int) (segundosPassados * pao.getPaoPorSegundo());
                pao.setSaldo(pao.getSaldo() + paesGanhos);
            }
        }
        // Atualiza o timestamp para o momento atual
        pao.setUltimaAtualizacao(LocalDateTime.now());
        return pao;
    }

    public Pao comprarForno(String userId) {
        Pao pao = verificarPao(userId);

        if (pao.getSaldo() >= pao.getCustoForno()){
            pao.setSaldo(pao.getSaldo() - pao.getCustoForno());
            pao.setPaoPorSegundo(pao.getPaoPorSegundo() + 5);
            pao.setCustoForno((int) (pao.getCustoForno() * 1.8));
            return paoRepository.save(pao);
        }
        return pao;
    }
}