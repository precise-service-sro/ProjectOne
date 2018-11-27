package com.precise_service.project_one.web.osoba;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.precise_service.project_one.entity.adresa.Adresa;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.entity.SouborTyp.AVATAR_FOTO;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_CONTENT_TYPE;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_SOUBOR_TYP;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_QUERY_PREFIX;

@Slf4j
@Data
@Named
public class OsobaPrehledBean extends AbstractBean {

  private List<Osoba> osobaList;
  private List<Osoba> filtrovanyOsobaList;
  private int osobaListSize;
  private int filtrovanyOsobaListSize;

  // zobrazovany obrazek
  private StreamedContent avatarFotoStreamedContent;

  public void init() {
    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    osobaList = osobaService.getOsobaAll(prihlasenyUzivatel.getId());
    filtrovanyOsobaList = null;
  }

  public void initJakoVlastnikNemovitosti(Nemovitost nemovitost) {
    osobaList = Arrays.asList(nemovitost.getVlastnik());
    filtrovanyOsobaList = null;
  }

  public void pridatOsoba() {
    log.trace("pridatOsoba()");

    Osoba osoba = new Osoba();
    osoba.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());
    osoba.setTrvaleBydliste(new Adresa());

    osobaService.postOsoba(osoba);
    init();

    showInfoMessage("Přidán", "Přidáná nová osoba");
  }

  public void smazatOsoba(Osoba deletedOsoba) {
    log.trace("smazatOsoba()");

    if (deletedOsoba == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedOsoba.toString());

    osobaService.deleteOsoba(deletedOsoba.getId());

    showInfoMessage("Smazán", "Smazána osoba " + deletedOsoba.getCeleJmeno());
    init();
  }

  public int getOsobaListSize() {
    if (osobaList == null) {
      return 0;
    }
    return osobaList.size();
  }

  public int getFiltrovanyOsobaListSize() {
    if (filtrovanyOsobaList == null) {
      return getOsobaListSize();
    }
    return filtrovanyOsobaList.size();
  }

  public StreamedContent getAvatarFotoStreamedContent() {
    FacesContext context = FacesContext.getCurrentInstance();

    String idOsoba = context.getExternalContext().getRequestParameterMap().get("idOsoba");

    if (idOsoba == null) {
      return new DefaultStreamedContent();
    }
    Osoba osoba = osobaService.getOsoba(idOsoba);

    if (osoba == null) {
      return null;
    }

    GridFSFile gridFSFile = gridFsTemplate.findOne(
        new Query(Criteria
            .where("_id").is(osoba.getAvatarFotoObjectId())
            .and(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_SOUBOR_TYP).is(AVATAR_FOTO)
        )
    );

    if (gridFSFile == null) {
      return null;
    }
    String filename = gridFSFile.getFilename();
    String contentType = (String) gridFSFile.getMetadata().get(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_CONTENT_TYPE);
    try {
      return new DefaultStreamedContent(gridFsTemplate.getResource(filename).getInputStream(), contentType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
