package br.com.paoqueclica.pao_que_clica.controller;

import br.com.paoqueclica.pao_que_clica.model.Pao;
import br.com.paoqueclica.pao_que_clica.service.PaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pao")
public class PaoController {

    @Autowired
    private PaoService paoService;

    @GetMapping("/estado")
    public ResponseEntity<Pao> verificarPao(){
        Pao verificado = paoService.verificarPao();
        return ResponseEntity.ok(verificado);
    }

    @PostMapping("/clique")
    public ResponseEntity<Pao> clique(){
        Pao atualizado = paoService.clique();
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/upgrade1")
    public ResponseEntity<Pao> upgrade1(){
        Pao atualizado = paoService.upgrade1();
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/cliqueAutomatico")
    public ResponseEntity<Pao> cliqueAutomatico(){
        Pao atualizado = paoService.cliqueAutomatico();
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/reset")
    public ResponseEntity<Pao> reset(){
        Pao atualizado = paoService.reset();
        return ResponseEntity.ok(atualizado);
    }
}
