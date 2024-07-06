package org.kosal.phoneshop.kosal1_phoneshop.service1;

import org.kosal.phoneshop.kosal1_phoneshop.dto.SaleDTO;
import org.kosal.phoneshop.kosal1_phoneshop.entities.Sale;

public interface SaleService {
  void sell(SaleDTO saleDTO);
  Sale getById(Long saleId);
  void cancel(Long saleId);
}
