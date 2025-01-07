//: sfg6lab.controller.MatrixVariableController.java

package sfg6lab.controller;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@Slf4j
@RestController
@RequestMapping("/mv")
class MatrixVariableController {

    // POST /pets/42;q=11;r=22
    @PostMapping("/pets/{petId}")
    public ResponseEntity<?> postPet(
            @PathVariable String petId,
            @MatrixVariable int iq,
            @MatrixVariable int eq,
            @NonNull final ServletUriComponentsBuilder uriBuilder) {

        // petId == 42
        // q == 11

        String mv = """
                {
                   "petId": %s,
                   "iq": %d,
                   "eq": %d 
                 }
                """.formatted(petId, iq, eq);

        URI newPetUri =  uriBuilder.fromCurrentRequest()
                .build()
                .toUri();

        return ResponseEntity.created(newPetUri).body(mv);
    }

    // POST /owners/42;iq=11;eq=22/pets/21;iq=33;eq=44
    @PostMapping("/owners/{ownerId}/pets/{petId}")
    public ResponseEntity<?> newPetForOwner(
            @PathVariable String ownerId,
            @MatrixVariable(name="iq", pathVar="ownerId") int ownerIQ,
            @MatrixVariable(name="eq", pathVar="ownerId") int ownerEQ,
            @PathVariable String petId,
            @MatrixVariable(name="iq", pathVar="petId") int petIQ,
            @MatrixVariable(name="eq", pathVar="petId") int petEQ,
            @NonNull final ServletUriComponentsBuilder uriBuilder) {

        URI newPetUri =  uriBuilder.fromCurrentRequest()
                .build()
                .toUri();

        String mvBody = """
                {
                   "ownerId": %s,
                   "owner-iq": %d,
                   "owner-eq": %d , 
                   "petId": %s, 
                   "pet-iq": %d, 
                   "pet-eq": %d
                 }
                """.formatted(
                        ownerId, ownerIQ, ownerEQ,
                        petId, petIQ, petEQ);

        return ResponseEntity.created(newPetUri).body(mvBody);
    }

    // POST /owners/42;iq=11;eq=22/pets/21;iq=33;eq=44
    @PostMapping("/v2/owners/{ownerId}/pets/{petId}")
    public ResponseEntity<?> newPetForOwner2(
            @PathVariable String ownerId,
            @MatrixVariable(pathVar="ownerId") MultiValueMap<String, String> ownerMV,
            @PathVariable String petId,
            @MatrixVariable(pathVar="petId") MultiValueMap<String, String> petMV,
            @MatrixVariable MultiValueMap<String, String> allMV,
            @NonNull final ServletUriComponentsBuilder uriBuilder) {

        URI newPetUri =  uriBuilder.fromCurrentRequest()
                .build()
                .toUri();

        String mvBody = """
                {
                   "ownerId": %s,
                   "owner-iq": %s,
                   "owner-eq": %s , 
                   "petId": %s, 
                   "pet-iq": %s, 
                   "pet-eq": %s, 
                   "Owner's matrix variables": %s,
                   "Pet's matrix variables": %s, 
                   "All matrix variables": %s
                 }
                """.formatted(
                ownerId, ownerMV.get("iq"), ownerMV.get("eq"),
                petId, petMV.get("iq"), petMV.get("eq"),
                ownerMV, petMV, allMV);

        return ResponseEntity.created(newPetUri).body(mvBody);
    }

    // POST /owners/firstName=Donald;lastName=Trump
    @PostMapping("/owners/{ownerName}")
    public ResponseEntity<?> getOwnersByName(
            @MatrixVariable(pathVar="ownerName") String firstName,
            @MatrixVariable(pathVar="ownerName") String lastName,
            @NonNull final ServletUriComponentsBuilder uriBuilder) {

        URI newPetUri =  uriBuilder.fromCurrentRequest()
                .build()
                .toUri();

        String mvBody = """
                {
                   "firstName": %s,
                   "lastName": %s
                }
                """.formatted(firstName, lastName);

        return ResponseEntity.created(newPetUri).body(mvBody);
    }

} /// :~