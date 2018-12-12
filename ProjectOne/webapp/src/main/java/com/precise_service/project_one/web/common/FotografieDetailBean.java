package com.precise_service.project_one.web.common;

import java.io.IOException;

import javax.faces.context.FacesContext;
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
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.entity.SouborTyp.FOTOGRAFIE_NEMOVITOSTI;
import static com.precise_service.project_one.web.ApplicationConstants.ID_UZIVATEL;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_CONTENT_TYPE;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_CONTENT_TYPE_PNG;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_ATTRIBUTE_SOUBOR_TYP;
import static com.precise_service.project_one.web.ApplicationConstants.METADATA_QUERY_PREFIX;

@Slf4j
@Data
@Named
public class FotografieDetailBean extends AbstractBean {

  private Nemovitost nemovitost;

  // poradove cislo / index zobrazovaneho obrazku
  private int fotografieIndex = 0;

  private StreamedContent zobrazitObrazek;
  private StreamedContent zobrazit;

  public void init(Nemovitost nemovitost) {
    this.nemovitost = nemovitost;
  }

  public void posunoutDopredu() {
    fotografieIndex = (fotografieIndex + 1) % nemovitost.getFotografie().size();
  }

  public void posunoutDozadu() {
    if (fotografieIndex == 0) {
      fotografieIndex = nemovitost.getFotografie().size();
    }
    fotografieIndex = (fotografieIndex - 1) % nemovitost.getFotografie().size();
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

  public StreamedContent stahnoutFotografii() {
    // TODO: implement
    return new DefaultStreamedContent();
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
    if (fotografieIndex >= nemovitost.getFotografie().size()) {
      fotografieIndex = 0;
    }
    ObjectId objectId = nemovitost.getFotografie().get(fotografieIndex);
    return zobrazitObrazek(objectId);
  }

  public StreamedContent zobrazitObrazek(ObjectId objectId) {
    if (objectId == null) {
      return new DefaultStreamedContent();
    }

    GridFSFile gridFSFile = gridFsTemplate.findOne(
        new Query(Criteria
            .where("_id").is(objectId)
            .and(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_SOUBOR_TYP).is(FOTOGRAFIE_NEMOVITOSTI)
        )
    );

    if (gridFSFile == null) {
      return new DefaultStreamedContent();
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
}
