package br.com.paoqueclica.pao_que_clica.controller;

import br.com.paoqueclica.pao_que_clica.model.Pao;
import br.com.paoqueclica.pao_que_clica.service.PaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pao")
public class PaoController {

    @Autowired
    private PaoService paoService;

    @GetMapping("/estado")
    public ResponseEntity<Pao> verificarPao(@RequestHeader("X-User-ID") String userId){
        Pao verificado = paoService.verificarPao(userId);
        return ResponseEntity.ok(verificado);
    }

    @PostMapping("/clique")
    public ResponseEntity<Pao> clique(@RequestHeader("X-User-ID") String userId){
        Pao atualizado = paoService.clique(userId);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/upgrade1")
    public ResponseEntity<Pao> upgrade1(@RequestHeader("X-User-ID") String userId){
        Pao atualizado = paoService.upgrade1(userId);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/cliqueAutomatico")
    public ResponseEntity<Pao> cliqueAutomatico(@RequestHeader("X-User-ID") String userId){
        Pao atualizado = paoService.cliqueAutomatico(userId);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/reset")
    public ResponseEntity<Pao> reset(@RequestHeader("X-User-ID") String userId){
        Pao atualizado = paoService.reset(userId);
        return ResponseEntity.ok(atualizado);
    }
}
