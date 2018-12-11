package com.precise_service.project_one.service.pronajem;

import java.util.List;

import com.precise_service.project_one.entity.filter.DataFilter;
import com.precise_service.project_one.entity.pronajem.NajemniSmlouva;

public interface INajemniSmlouvaService {

  NajemniSmlouva postNajemniSmlouva(NajemniSmlouva najemniSmlouva);

  NajemniSmlouva putNajemniSmlouva(NajemniSmlouva najemniSmlouva);

  NajemniSmlouva getNajemniSmlouva(String idNajemniSmlouva);

  List<NajemniSmlouva> getNajemniSmlouvaList(DataFilter dataFilter);

  void deleteNajemniSmlouvaAll();

  void deleteNajemniSmlouva(String idNajemniSmlouva);
}
