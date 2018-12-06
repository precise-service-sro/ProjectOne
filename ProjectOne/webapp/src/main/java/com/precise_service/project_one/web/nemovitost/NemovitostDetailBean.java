package com.precise_service.project_one.web.nemovitost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.bson.types.ObjectId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.precise_service.project_one.entity.adresa.Stat;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostDispozice;
import com.precise_service.project_one.entity.nemovitost.NemovitostDruhVlastnictvi;
import com.precise_service.project_one.entity.nemovitost.NemovitostTyp;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.entity.SouborTyp.AVATAR_FOTO;
import static com.precise_service.project_one.entity.SouborTyp.FOTOGRAFIE_NEMOVITOSTI;
import static com.precise_service.project_one.web.ApplicationConstants.ID_UZIVATEL;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_CONTENT_TYPE;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_CONTENT_TYPE_PNG;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_SOUBOR_TYP;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_QUERY_PREFIX;

@Slf4j
@Data
@Named
public class NemovitostDetailBean extends AbstractBean {

  private Nemovitost nemovitost;
  private List<Osoba> osobaList;

  private List<Nemovitost> nemovitostList;
  private List<NemovitostTyp> nemovitostTypList;
  private List<NemovitostDruhVlastnictvi> nemovitostDruhVlastnictviList;
  private List<NemovitostDispozice> nemovitostDispoziceList;
  private List<Stat> statList;

  // seznam zobrazovanych obrazku
  private int fotografieIndex = 0;
  private StreamedContent zobrazitObrazek;
  private StreamedContent zobrazit;

  public void posunoutDopredu() {
    fotografieIndex = (fotografieIndex + 1) % nemovitost.getFotografie().size();
    System.out.println(fotografieIndex);
  }

  public void posunoutDozadu() {
    if (fotografieIndex == 0) {
      fotografieIndex = nemovitost.getFotografie().size();
    }
    fotografieIndex = (fotografieIndex - 1) % nemovitost.getFotografie().size();
    System.out.println(fotografieIndex);
  }

  public void smazatFotografii() {
    ObjectId objectId = nemovitost.getFotografie().get(fotografieIndex);
    gridFsTemplate.delete(
        new Query(Criteria
            .where("_id").is(objectId)
        )
    );
    nemovitost.getFotografie().remove(objectId);
    nemovitost = nemovitostService.putNemovitost(nemovitost);
    fotografieIndex = 0;
  }

  public void nastavitFotografii(FileUploadEvent event) {
    System.out.println("fadsfasfas");
  }

  public void nastavitAvatarFoto2(FileUploadEvent event) {
    // nahrany obrazek
    UploadedFile avatarFotoUploadedFile = event.getFile();

    ObjectId avatarFotoObjectId = storeFileIntoDatabase(avatarFotoUploadedFile);
    nemovitost.getFotografie().add(avatarFotoObjectId);
    nemovitost = nemovitostService.putNemovitost(nemovitost);

    showInfoMessage("Přidáno", "Fotografie byla přidána");
  }

  private ObjectId storeFileIntoDatabase(UploadedFile avatarFotoUploadedFile) {
    BasicDBObject metadata = new BasicDBObject();
    metadata.append(METADATA_ATTRIBUTE_SOUBOR_TYP, FOTOGRAFIE_NEMOVITOSTI);
    metadata.append(METADATA_ATTRIBUTE_CONTENT_TYPE, METADATA_ATTRIBUTE_CONTENT_TYPE_PNG);
    metadata.append(ID_UZIVATEL, loginBean.getPrihlasenyUzivatel().getId());

    try {
      return gridFsTemplate.store(avatarFotoUploadedFile.getInputstream(), avatarFotoUploadedFile.getFileName(), metadata);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public StreamedContent zobrazit() {
    ObjectId objectId = nemovitost.getFotografie().get(fotografieIndex);
    return zobrazitObrazek(objectId);
  }

  public StreamedContent zobrazitObrazek(String idParam) {

    String idFotografie = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFotografie");

    if (idFotografie == null) {
      return null;
    }

    GridFSFile gridFSFile = gridFsTemplate.findOne(
        new Query(Criteria
            .where("_id").is(idFotografie)
            .and(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_SOUBOR_TYP).is(FOTOGRAFIE_NEMOVITOSTI)
        )
    );

    if (gridFSFile == null) {
      return null;
    }
    String filename = gridFSFile.getFilename();
    String contentType = (String) gridFSFile.getMetadata().get(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_CONTENT_TYPE);
    try {
      zobrazitObrazek = new DefaultStreamedContent(gridFsTemplate.getResource(filename).getInputStream(), contentType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return zobrazitObrazek;
  }

  public StreamedContent zobrazitObrazek(ObjectId objectId) {
    if (objectId == null) {
      return null;
    }

    String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFotografie");

    GridFSFile gridFSFile = gridFsTemplate.findOne(
        new Query(Criteria
            .where("_id").is(objectId)
            .and(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_SOUBOR_TYP).is(FOTOGRAFIE_NEMOVITOSTI)
        )
    );

    if (gridFSFile == null) {
      return null;
    }
    String filename = gridFSFile.getFilename();
    String contentType = (String) gridFSFile.getMetadata().get(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_CONTENT_TYPE);
    try {
      zobrazitObrazek = new DefaultStreamedContent(gridFsTemplate.getResource(filename).getInputStream(), contentType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return zobrazitObrazek;
  }

  public void init() {
    // pokud nemam vybranou zadnou nemovitost, tak vytahuji prvni nemovitost z DB
    if (nemovitost == null) {
      log.error("Není vybrána žádná nemovitosti pro zobrazení detailu");
      return;
    }

    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    osobaList = osobaService.getOsobaAll(prihlasenyUzivatel.getId());
    nemovitostList = nemovitostService.getNemovitostListByVlastnik(prihlasenyUzivatel.getId());

    nemovitostTypList = Arrays.asList(NemovitostTyp.values());
    statList = Arrays.asList(Stat.values());
    nemovitostDruhVlastnictviList = Arrays.asList(NemovitostDruhVlastnictvi.values());
    nemovitostDispoziceList = Arrays.asList(NemovitostDispozice.values());
    nemovitostDispoziceList = Arrays.asList(NemovitostDispozice.values());
  }

  public void pridatNemovitost() throws IOException {
    log.trace("pridatNemovitost()");
    showInfoMessage("Přidáno", "Nová nemovitost " + nemovitost.getNazev() + " byla přidáná");
    // TODO: ulozit novou nemovitost do DB
    routerBean.goToNemovitostPrehledBean();
  }

  public void ulozitZmenuNemovitosti() throws IOException {
    log.trace("ulozitZmenuNemovitosti()");
    nemovitost = nemovitostService.putNemovitost(nemovitost);
    showInfoMessage("Uloženo", "Úprava nemovitosti " + nemovitost.getNazev() + " byla uložena");
    routerBean.goToNemovitostPrehledBean();
  }

  public void zrusitZmenuNemovitosti() throws IOException {
    log.trace("zrusitZmenuNemovitosti()");
    showInfoMessage("Zrušeno", "Přidání/úprava nemovitosti byla zrušena");
    routerBean.goToNemovitostPrehledBean();
  }

  public void zmenaVybraneNemovitosti(final AjaxBehaviorEvent event) {
    showInfoMessage("Změněno", "Byla vybráná nemovitost " + nemovitost.getIdentifikaceNemovitosti());
  }
}
