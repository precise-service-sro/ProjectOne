package com.precise_service.project_one.web.login;

import java.io.IOException;

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

@Named
@Data
@Slf4j
public class IndexPageBean extends AbstractBean {

  private StreamedContent avatarFoto;

  private UploadedFile uploadedFile;
  //private StreamedContent file2;
  private ObjectId objectId;

  private final String ID_PRIHLASENY_UZIVATEL = "5bdb0c3d4f0e8eb71860baaa";

  public void handleFileUpload(FileUploadEvent event) {
    uploadedFile = event.getFile();
    showInfoMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
    objectId = storeFileIntoDatabase();

    Osoba osoba = osobaService.getOsoba(ID_PRIHLASENY_UZIVATEL);
    //osoba.setAvatarFoto(objectId);
    osoba = osobaService.putOsoba(osoba);
    showInfoMessage("Nastavené", uploadedFile.getFileName() + " nastaveno jako avatar foto pro osobu " + osoba.getCeleJmeno());
  }

  public void smazat() {

    if (objectId != null) {
      gridFsTemplate.delete(
          new Query(Criteria
              .where("_id").is(objectId)
          )
      );

      Osoba osoba = osobaService.getOsoba(ID_PRIHLASENY_UZIVATEL);
      //osoba.setAvatarFoto(null);
      osobaService.putOsoba(osoba);
    }
    uploadedFile = null;
    //file2 = null;
    objectId = null;
    avatarFoto = null;
  }

  private ObjectId storeFileIntoDatabase() {
    BasicDBObject metadata = new BasicDBObject();
    metadata.append(METADATA_ATTRIBUTE_SOUBOR_TYP, AVATAR_FOTO);
    metadata.append(METADATA_ATTRIBUTE_CONTENT_TYPE, METADATA_ATTRIBUTE_CONTENT_TYPE_PNG);
    metadata.append(ID_UZIVATEL, osobaService.getOsoba("5bdb0c3d4f0e8eb71860baaa").getId());

    try {
      return gridFsTemplate.store(uploadedFile.getInputstream(), uploadedFile.getFileName(), metadata);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /*
  public StreamedContent getFile2() {

    GridFSFile gridFSFile = getGridFSFile();

    //List<GridFSFile> files = new ArrayList<>();
    //gridFsTemplate.find(new Query(Criteria.where("metadata."+ ID_UZIVATEL).is("5bdb0c3d4f0e8eb71860baaa"))).into(files);

    //InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/images/kiyosaki.png");

    try {
      String filename = gridFSFile.getFilename();
      String contentType = (String) gridFSFile.getMetadata().get(METADATA_ATTRIBUTE_CONTENT_TYPE);
      file2 = new DefaultStreamedContent(gridFsTemplate.getResource(filename).getInputStream(), contentType, filename);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return file2;
  }
  */

  private GridFSFile getGridFSFile() {
    return gridFsTemplate.findOne(
        new Query(Criteria
            .where("_id").is(objectId)
            .and(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_SOUBOR_TYP).is(AVATAR_FOTO)
            .and(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_CONTENT_TYPE).is(METADATA_ATTRIBUTE_CONTENT_TYPE_PNG)
        )
    );
  }

  public StreamedContent getAvatarFoto() {
    GridFSFile gridFSFile = getGridFSFile();
    if (gridFSFile == null) {
      return null;
    }
    String filename = gridFSFile.getFilename();
    String contentType = (String) gridFSFile.getMetadata().get(METADATA_QUERY_PREFIX + METADATA_ATTRIBUTE_CONTENT_TYPE);
    try {
      avatarFoto = new DefaultStreamedContent(gridFsTemplate.getResource(filename).getInputStream(), contentType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return avatarFoto;
  }


}