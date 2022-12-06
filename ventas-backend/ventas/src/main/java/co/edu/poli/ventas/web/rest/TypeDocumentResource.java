package co.edu.poli.ventas.web.rest;

import co.edu.poli.ventas.domain.TypeDocument;
import co.edu.poli.ventas.repository.TypeDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TypeDocumentResource {
    @Autowired
    TypeDocumentRepository typeDocumentRepository;

    /**
     * {@code GET  /type-documents} : get all the typeDocuments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeDocuments in body.
     */
    @GetMapping("/typedocument")
    public ResponseEntity<List<TypeDocument>> findAll() {
        List<TypeDocument> typeDocumentList = (List<TypeDocument>) typeDocumentRepository.findAll();
        return ResponseEntity.ok().body(typeDocumentList);
    }

    @GetMapping("/typedocument/typedocument-name/{name}")
    public ResponseEntity<TypeDocument> findByName(@PathVariable String name) {
        TypeDocument typeDocument = typeDocumentRepository.findByNameDocument("Cedula de ciudadania");
        return ResponseEntity.ok().body(typeDocument);
    }

    @GetMapping("/typedocument/initials-like")
    public ResponseEntity<List<TypeDocument>> findByInitialsLike(@RequestParam(name = "like") String like) {
        List<TypeDocument> typeDocumentList = typeDocumentRepository.findByInitialsLike(like);
        return ResponseEntity.ok().body(typeDocumentList);
    }

    /**
     * {@code POST  /type-documents} : Create a new typeDocument.
     *
     * @param typeDocument the typeDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeDocumentDTO, or with status {@code 400 (Bad Request)} if the typeDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/typedocument")
    public ResponseEntity<TypeDocument> insert(@RequestBody TypeDocument typeDocument) throws URISyntaxException {
        if (typeDocument.getId() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            TypeDocument result = typeDocumentRepository.save(typeDocument);
            return ResponseEntity.created(new URI("/api/typedocument" + result.getId()))
                    .body(result);
        }
    }

    /**
     * {@code PUT  /type-documents} : Updates an existing typeDocument.
     *
     * @param typeDocument the typeDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the typeDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/typedocument")
    public ResponseEntity<TypeDocument> update(@RequestBody TypeDocument typeDocument) {
        if (typeDocument.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        TypeDocument result = typeDocumentRepository.save(typeDocument);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code DELETE  /type-documents/:id} : delete the "id" typeDocument.
     *
     * @param nameDocument the id of the typeDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/typedocument/{nameDocument}")
    public ResponseEntity<TypeDocument> delete(@PathVariable String nameDocument) {
        typeDocumentRepository.deleteByNameDocument(nameDocument);
        return ResponseEntity.ok().build();
    }

    /**
     * count
     * @return number
     */
    @GetMapping("/typedocument/count")
    public ResponseEntity findCount() {
        Long typeDocument = typeDocumentRepository.count();
        return ResponseEntity.ok().body(typeDocument);
    }

    /**
     * {@code DELETE  /type-documents} : delete all typeDocument.
     *
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/typedocument/deleteAll")
    public ResponseEntity<TypeDocument> deleteAll() {
        List<TypeDocument> query = (List<TypeDocument>) typeDocumentRepository.findAll();
        typeDocumentRepository.deleteAll(query);
        return ResponseEntity.ok().build();
    }
}