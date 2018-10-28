package com.precise_service.project_one.service.vyuctovani;

import java.util.List;

import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozkaTyp;

public interface IVyuctovaniPolozkaTypService {

  VyuctovaniPolozkaTyp postVyuctovaniPolozkaTyp(VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp);

  VyuctovaniPolozkaTyp putVyuctovaniPolozkaTyp(VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp);

  VyuctovaniPolozkaTyp getVyuctovaniPolozkaTyp(String idVyuctovaniPolozkaTyp);

  List<VyuctovaniPolozkaTyp> getVyuctovaniPolozkaTypAll();

  void deleteVyuctovaniPolozkaTyp(String idVyuctovaniPolozkaTyp);

  void deleteVyuctovaniPolozkaTypAll();
}
