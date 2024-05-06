package it.epicode.dao;

import it.epicode.entities.biglietti.TitoloDiViaggio;

public interface RivenditoreAutorizzatoDao {
    void conteggioTitoliDaRivenditore(TitoloDiViaggio titoloDiViaggio);
}
