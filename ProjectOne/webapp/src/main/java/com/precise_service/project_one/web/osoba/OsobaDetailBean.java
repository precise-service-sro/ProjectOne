package com.precise_service.project_one.web.osoba;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.bson.types.ObjectId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.precise_service.project_one.entity.adresa.Stat;
import com.precise_service.project_one.entity.filter.OsobaFilter;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.entity.SouborTyp.AVATAR_FOTO;
import static com.precise_service.project_one.web.ApplicationConstants.ID_UZIVATEL;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_CONTENT_TYPE;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_CONTENT_TYPE_PNG;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_SOUBOR_TYP;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_QUERY_PREFIX;

@Slf4j
@Data
@Named
public class OsobaDetailBean extends AbstractBean {

  private Osoba osoba;
  private List<Stat> statList;
  private List<Osoba> osobaList;

  // zobrazovany obrazek
  private StreamedContent avatarFotoStreamedContent;

  public void init() {
    if (osoba == null) {
      osoba = osobaService.getOsoba(loginBean.getPrihlasenyUzivatel().getId());
      log.trace("Není vybraná žádná osoba ke zobrazení detailů. (zobrazuji aktuálního přihlášeného uživatele)");
    }
    statList = Arrays.asList(Stat.values());
    osobaList = osobaService.getOsobaList(new OsobaFilter()
        .setIdPrihlasenyUzivatel(loginBean.getPrihlasenyUzivatel().getId())
    );
  }

  public void tabChanged(TabChangeEvent event) {
    log.trace("tabChanged()");
  }

  public void ulozitZmenuOsoby() throws IOException {
    log.trace("ulozitZmenuOsoby()");
    osoba = osobaService.putOsoba(osoba);

    if (loginBean.getPrihlasenyUzivatel().getId().equals(osoba.getId())) {
      // aktualizovani udaju prihlaseneho uzivatele
      loginBean.setPrihlasenyUzivatel(osoba);
    }

    showInfoMessage("Uloženo", "Úprava osoby " + osoba.getCeleJmeno() + " byla uložena");
    routerBean.goToOsobaPrehledBean();
  }

  public void zrusitZmenuOsoby() throws IOException {
    log.trace("zrusitZmenuOsoby()");
    showInfoMessage("Zrušeno", "Úprava osoby " + osoba.getCeleJmeno() + " byla zrušena");
    routerBean.goToOsobaPrehledBean();
  }


  public void nastavitAvatarFoto(FileUploadEvent event) {
    // nahrany obrazek
    UploadedFile avatarFotoUploadedFile = event.getFile();
    showInfoMessage("Nahráno", "Obrázek " + avatarFotoUploadedFile.getFileName() + " byl nahrán.");

    ObjectId avatarFotoObjectId = storeFileIntoDatabase(avatarFotoUploadedFile);

    osoba.setAvatarFotoObjectId(avatarFotoObjectId);
    osoba = osobaService.putOsoba(osoba);
    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    if (prihlasenyUzivatel.getId().equals(osoba.getId())) {
      loginBean.setPrihlasenyUzivatel(osoba);
    }

    showInfoMessage("Nastavené", avatarFotoUploadedFile.getFileName() + " nastaveno jako avatar foto pro osobu " + osoba.getCeleJmeno());
  }

  private ObjectId storeFileIntoDatabase(UploadedFile avatarFotoUploadedFile) {
    BasicDBObject metadata = new BasicDBObject();
    metadata.append(METADATA_ATTRIBUTE_SOUBOR_TYP, AVATAR_FOTO);
    metadata.append(METADATA_ATTRIBUTE_CONTENT_TYPE, METADATA_ATTRIBUTE_CONTENT_TYPE_PNG);
    metadata.append(ID_UZIVATEL, osoba.getId());

    try {
      return gridFsTemplate.store(avatarFotoUploadedFile.getInputstream(), avatarFotoUploadedFile.getFileName(), metadata);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void smazatAvatarFoto() {
    ObjectId avatarFotoObjectId = osoba.getAvatarFotoObjectId();
    if (avatarFotoObjectId != null) {
      gridFsTemplate.delete(
          new Query(Criteria
              .where("_id").is(avatarFotoObjectId)
          )
      );

      osoba.setAvatarFotoObjectId(null);
      osoba = osobaService.putOsoba(osoba);

      if (loginBean.getPrihlasenyUzivatel().getId().equals(osoba.getId())) {
        loginBean.setPrihlasenyUzivatel(osoba);
      }
    }
  }

  public StreamedContent getAvatarFotoStreamedContent() {
    if (osoba == null) {
      return new DefaultStreamedContent();
    }

    GridFSFile gridFSFile = gridFsTemplate.findOne(
        new Query(Criteria
            .where("_id").is(osoba.getAvatarFotoObjectId())
            .and(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_SOUBOR_TYP).is(AVATAR_FOTO)
        )
    );

    if (gridFSFile == null) {
      return new DefaultStreamedContent();
    }
    String filename = gridFSFile.getFilename();
    String contentType = (String) gridFSFile.getMetadata().get(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_CONTENT_TYPE);
    try {
      return new DefaultStreamedContent(gridFsTemplate.getResource(filename).getInputStream(), contentType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new DefaultStreamedContent();
  }

  public void zmenaVybraneOsoby(final AjaxBehaviorEvent event) {
    showInfoMessage("Změněno", "Byla vybráná osoba " + osoba.getCeleJmeno());
  }
}
