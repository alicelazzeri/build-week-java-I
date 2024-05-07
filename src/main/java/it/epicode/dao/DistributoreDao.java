package it.epicode.dao;

import it.epicode.entities.biglietti.Distributore;
import it.epicode.entities.biglietti.TitoloDiViaggio;
import it.epicode.entities.utenti.Tessera;

import java.time.LocalDate;

public interface DistributoreDao {
    void emettiTitoloDiViaggio (TitoloDiViaggio titoloDiViaggio);
    void conteggioTitoliTotali(TitoloDiViaggio titoloDiViaggio);
    void conteggioTitoli(Distributore distributore, TitoloDiViaggio titoloDiViaggio,
                         LocalDate inizioRicerca,LocalDate fineRicerca);
    void validaAbbonamento(Tessera tessera,TitoloDiViaggio titoloDiViaggio);

}
