package it.epicode.dao;

import it.epicode.entities.biglietti.TitoloDiViaggio;

public interface DistributoreDao {
    void emettiTitoloDiViaggio (TitoloDiViaggio titoloDiViaggio);
    void conteggioTitoliTotali(TitoloDiViaggio titoloDiViaggio);
}
