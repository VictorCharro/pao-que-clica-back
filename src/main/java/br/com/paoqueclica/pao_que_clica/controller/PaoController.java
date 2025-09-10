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
        try {
            System.out.println("=== INICIANDO verificarPao() ===");
            Pao verificado = paoService.verificarPao();
            System.out.println("=== Pão verificado: " + verificado + " ===");
            return ResponseEntity.ok(verificado);
        } catch (Exception e) {
            System.err.println("=== ERRO em verificarPao(): " + e.getMessage() + " ===");
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Controller funcionando!");
    }

    @PostMapping("/clique")
    public ResponseEntity<Pao> clique(){
        try {
            System.out.println("=== INICIANDO clique() ===");
            Pao atualizado = paoService.clique();
            System.out.println("=== Pão atualizado: " + atualizado + " ===");
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            System.err.println("=== ERRO em clique(): " + e.getMessage() + " ===");
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/upgrade1")
    public ResponseEntity<Pao> upgrade1(){
        try {
            System.out.println("=== INICIANDO upgrade1() ===");
            Pao atualizado = paoService.upgrade1();
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            System.err.println("=== ERRO em upgrade1(): " + e.getMessage() + " ===");
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/cliqueAutomatico")
    public ResponseEntity<Pao> cliqueAutomatico(){
        try {
            System.out.println("=== INICIANDO cliqueAutomatico() ===");
            Pao atualizado = paoService.cliqueAutomatico();
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            System.err.println("=== ERRO em cliqueAutomatico(): " + e.getMessage() + " ===");
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<Pao> reset(){
        try {
            System.out.println("=== INICIANDO reset() ===");
            Pao atualizado = paoService.reset();
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            System.err.println("=== ERRO em reset(): " + e.getMessage() + " ===");
            e.printStackTrace();
            throw e;
        }
    }
}