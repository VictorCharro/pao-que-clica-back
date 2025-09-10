package br.com.paoqueclica.pao_que_clica.repository;

import br.com.paoqueclica.pao_que_clica.model.Pao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaoRepository extends JpaRepository<Pao, Long> {
}
