package com.precise_service.project_one.commons;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.precise_service.project_one.entity.filter.DataFilter;
import com.precise_service.project_one.entity.filter.FakturaFilter;
import com.precise_service.project_one.entity.filter.OsobaFilter;
import com.precise_service.project_one.entity.filter.PlatbaNajemnehoFilter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MongoQueryBuilder {
  public static Query getQuery(DataFilter dataFilter) {
    Query query = new Query();

    if (dataFilter.getIdPrihlasenyUzivatel() != null) {
      query.addCriteria(Criteria.where("idOsoba").is(dataFilter.getIdPrihlasenyUzivatel()));
    }
    if (dataFilter.getIdNemovitost() != null) {
      query.addCriteria(Criteria.where("nemovitost.id").is(dataFilter.getIdNemovitost()));
    }

    if (dataFilter instanceof FakturaFilter) {
      if (dataFilter.getCasovyInterval() != null) {
        if (dataFilter.getCasovyInterval().getZacatek() != null && dataFilter.getCasovyInterval().getKonec() == null) {
          query.addCriteria(Criteria.where("zuctovaciObdobi.zacatek").gte(dataFilter.getCasovyInterval().getZacatek()));
        }

        if (dataFilter.getCasovyInterval().getZacatek() == null && dataFilter.getCasovyInterval().getKonec() != null) {
          query.addCriteria(Criteria.where("zuctovaciObdobi.konec").lte(dataFilter.getCasovyInterval().getKonec()));
        }

        if (dataFilter.getCasovyInterval().getZacatek() != null && dataFilter.getCasovyInterval().getKonec() != null) {
          //@org.springframework.data.mongodb.repository.Query("{ $and: [ { 'idOsoba' : ?2 }, { $or: [ { 'zuctovaciObdobi.zacatek' : { $gte: ?0, $lte: ?1 } }, { 'zuctovaciObdobi.konec' : { $gte: ?0, $lte: ?1} } ] } ] }")
          //List<Faktura> getSeznamFakturVeZuctovacimObdobi(Date zacatekZuctovacihoObdobi, Date konecZuctovacihoObdobi, String idPrihlasenyUzivatel);

          query.addCriteria(new Criteria()
              .orOperator(
                  Criteria.where("zuctovaciObdobi.zacatek")
                      .gte(dataFilter.getCasovyInterval().getZacatek())
                      .lte(dataFilter.getCasovyInterval().getKonec()),
                  Criteria.where("zuctovaciObdobi.konec")
                      .gte(dataFilter.getCasovyInterval().getZacatek())
                      .lte(dataFilter.getCasovyInterval().getKonec()))
          );
        }
      }
    }

    if (dataFilter instanceof OsobaFilter) {
      if ("prijmeni".equals(dataFilter.getSeraditVzestupnePodle())) {
        query.with(new Sort(Sort.Direction.ASC, "prijmeni"));
      } else if ("prijmeni".equals(dataFilter.getSeraditSestupnePodle()))  {
        query.with(new Sort(Sort.Direction.DESC, "prijmeni"));
      }
    }

    if (dataFilter instanceof PlatbaNajemnehoFilter) {
      if (dataFilter.getCasovyInterval() != null) {

        if (dataFilter.getCasovyInterval().getZacatek() != null && dataFilter.getCasovyInterval().getKonec() != null) {
          query.addCriteria(Criteria.where("datumPlatby").gte(dataFilter.getCasovyInterval().getZacatek()).lte(dataFilter.getCasovyInterval().getKonec()));
        }
        else if (dataFilter.getCasovyInterval().getZacatek() != null) {
          query.addCriteria(Criteria.where("datumPlatby").gte(dataFilter.getCasovyInterval().getZacatek()));
        }
        else if (dataFilter.getCasovyInterval().getKonec() != null) {
          query.addCriteria(Criteria.where("datumPlatby").lte(dataFilter.getCasovyInterval().getKonec()));
        }

        if ("datumPlatby".equals(dataFilter.getSeraditVzestupnePodle())) {
          query.with(new Sort(Sort.Direction.ASC, "datumPlatby"));
        } else if ("datumPlatby".equals(dataFilter.getSeraditSestupnePodle()))  {
          query.with(new Sort(Sort.Direction.DESC, "datumPlatby"));
        }
      }
      PlatbaNajemnehoFilter platbaNajemnehoFilter = (PlatbaNajemnehoFilter) dataFilter;
      if (platbaNajemnehoFilter.getIdOdesilatel() != null) {
        query.addCriteria(Criteria.where("odesilatel.id").is(platbaNajemnehoFilter.getIdOdesilatel()));
      }
      if (platbaNajemnehoFilter.getIdPrijemce() != null) {
        query.addCriteria(Criteria.where("prijemce.id").is(platbaNajemnehoFilter.getIdPrijemce()));
      }
    }

    return query;
  }
}
