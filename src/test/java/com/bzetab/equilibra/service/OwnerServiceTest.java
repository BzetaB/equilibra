package com.bzetab.equilibra.service;

import com.bzetab.equilibra.models.Owner;
import com.bzetab.equilibra.repositories.OwnerRepository;
import com.bzetab.equilibra.services.impl.OwnerServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    /* ------------------------------------------------------------------
     * GetOwnerByUniqueCode
     * ------------------------------------------------------------------ */

    @Nested
    class GetOwnerByUniqueCode{
        @Test
        void shouldThrowException_whenUniqueCodeIsBlank() {

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> ownerService.getOwnerByUniqueCode(" ")
            );

            assertEquals("The unique code must not be blank", exception.getMessage());
            verifyNoInteractions(ownerRepository);
        }

        @Test
        void shouldThrowException_whenOwnerDoesNotExist() {

            String uniqueCode = "ABC123";
            when(ownerRepository.findByUniqueCode(uniqueCode))
                    .thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> ownerService.getOwnerByUniqueCode(uniqueCode)
            );

            assertEquals(
                    "Owner not found with unique code: " + uniqueCode,
                    exception.getMessage()
            );
        }

        @Test
        void shouldReturnOwner_whenOwnerExists() {

            String uniqueCode = "ABC123";
            Owner owner = Owner.builder()
                    .uniqueCode(uniqueCode)
                    .isActive(true)
                    .build();

            when(ownerRepository.findByUniqueCode(uniqueCode))
                    .thenReturn(Optional.of(owner));

            Owner result = ownerService.getOwnerByUniqueCode(uniqueCode);

            assertNotNull(result);
            assertEquals(uniqueCode, result.getUniqueCode());
            assertTrue(result.getIsActive());
        }
    }

    /* ------------------------------------------------------------------
     * CreateOwner
     * ------------------------------------------------------------------ */

    @Nested
    class CreateOwner {
        @Test
        void shouldThrowException_whenCreatingOwnerWithBlankUniqueCode() {

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> ownerService.createOwner(" ")
            );

            assertEquals("The unique code must not be blank", exception.getMessage());
            verifyNoInteractions(ownerRepository);
        }

        @Test
        void shouldThrowException_whenOwnerAlreadyExists() {

            String uniqueCode = "ABC123";
            Owner existingOwner = Owner.builder()
                    .uniqueCode(uniqueCode)
                    .isActive(true)
                    .build();

            when(ownerRepository.findByUniqueCode(uniqueCode))
                    .thenReturn(Optional.of(existingOwner));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> ownerService.createOwner(uniqueCode)
            );

            assertEquals(
                    "Owner already exist with the unique code: " + uniqueCode,
                    exception.getMessage()
            );

            verify(ownerRepository, never()).save(any());
        }

        @Test
        void shouldCreateAndReturnOwner_whenUniqueCodeIsValidAndDoesNotExist() {

            String uniqueCode = "NEW123";

            when(ownerRepository.findByUniqueCode(uniqueCode))
                    .thenReturn(Optional.empty());

            when(ownerRepository.save(any(Owner.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            Owner result = ownerService.createOwner(uniqueCode);

            assertNotNull(result);
            assertEquals(uniqueCode, result.getUniqueCode());
            assertTrue(result.getIsActive());

            verify(ownerRepository).save(any(Owner.class));
        }
    }

    /* ------------------------------------------------------------------
     * UpdatedStatusOwner
     * ------------------------------------------------------------------ */

    @Nested
    class UpdatedStatusOwner{
        @Test
        void shouldThrowException_whenUpdatingStatusAndOwnerDoesNotExist() {

            String uniqueCode = "NOT_FOUND";

            when(ownerRepository.findByUniqueCode(uniqueCode))
                    .thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> ownerService.updatedStatusOwner(uniqueCode)
            );

            assertEquals(
                    "Owner not found with unique code: " + uniqueCode,
                    exception.getMessage()
            );
        }

        @Test
        void shouldDeactivateOwnerAndSave_whenOwnerExists() {

            String uniqueCode = "ABC123";
            Owner owner = Owner.builder()
                    .uniqueCode(uniqueCode)
                    .isActive(true)
                    .build();

            when(ownerRepository.findByUniqueCode(uniqueCode))
                    .thenReturn(Optional.of(owner));

            when(ownerRepository.save(owner)).thenReturn(owner);

            Owner result = ownerService.updatedStatusOwner(uniqueCode);

            assertFalse(result.getIsActive());
            verify(ownerRepository).save(owner);
        }
    }

    @Test
    void shouldToggleIsActiveFromFalseToTrue_andSaveOwner() {

        String uniqueCode = "ABC123";
        Owner owner = Owner.builder()
                .uniqueCode(uniqueCode)
                .isActive(false)
                .build();

        when(ownerRepository.findByUniqueCode(uniqueCode))
                .thenReturn(Optional.of(owner));

        when(ownerRepository.save(owner)).thenReturn(owner);

        Owner result = ownerService.updatedStatusOwner(uniqueCode);

        assertTrue(result.getIsActive());
        verify(ownerRepository).save(owner);
    }
}
