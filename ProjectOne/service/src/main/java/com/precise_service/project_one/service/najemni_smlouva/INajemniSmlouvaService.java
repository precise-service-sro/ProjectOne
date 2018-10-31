package com.precise_service.project_one.service.najemni_smlouva;

import java.util.List;

import com.precise_service.project_one.entity.NajemniSmlouva;

public interface INajemniSmlouvaService {

  NajemniSmlouva postNajemniSmlouva(NajemniSmlouva najemniSmlouva);

  NajemniSmlouva putNajemniSmlouva(NajemniSmlouva najemniSmlouva);

  NajemniSmlouva getNajemniSmlouva(String idNajemniSmlouva);

  List<NajemniSmlouva> getNajemniSmlouvaAll();

  void deleteNajemniSmlouvaAll();

  void deleteNajemniSmlouva(String idNajemniSmlouva);
}
