import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

// MOCKITO
// allows you to create mock objects for testing

@ExtendWith(MockitoExtension.class)
class PersonTest {
    // @Mock                                    -> mock instance of the class (except constructor , static)
    // @Spy                                     -> doesn't mock the class (constructor, static) but we can stub/mock specific methods
    // @InjectMocks                             -> automatically injects mocks spies based on @Mock @Spy
    // @Captor                                  -> capture arguments of a method call (work only with verify)
    // @ExtendWith(MockitoExtension.class)      -> initialize @Mock and @Spy automatically

    @Mock
    private Pet pet;

    @Spy
    private Work work;

    @InjectMocks
    private Person person;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @BeforeEach
    public void setUp() {
        // equivalent to @ExtendWith(MockitoExtension.class)
        openMocks(this);

        person = new Person("John Doe", 30, pet, work);
    }

    @Test
    @DisplayName("Should demonstrate various Mockito features")
    public void testMockitoFeatures() {
        // when().thenReturn()          -> stubbing a method in mocks  (doesn't call real method)
        // when().thenAnswer()          -> stubbing a method in mocks with more flexibility
        // doReturn().when()            -> stubbing a method in spies (call real method and overwrite return)
        when(pet.getName()).thenReturn("Golden Retriever");
        when(work.getTitle()).thenAnswer(invocation -> "Software Developer");
        doReturn(50000.0).when(work).getSalary();

        // doNothing()                  -> stub void methods to do nothing
        // doCallRealMethod()           -> forces a mock to call the real void method
        // doThrow()                    -> stub void methods to throw an exception
        // doAnswer()                   -> stub void methods with more flexibility
        doNothing().when(pet).feed();
        doCallRealMethod().when(work).doWork();
        doThrow(new IllegalStateException("Pet is sleeping")).when(pet).play();
        doAnswer(invocation -> {
            String food = invocation.getArgument(0);
            return "Eating " + food;
        }).when(pet).eat(anyString());

        assertEquals("Golden Retriever", person.getPet().getName());
        assertEquals("Software Developer", person.getWork().getTitle());
        assertEquals(50000.0, person.getWork().getSalary());

        person.getPet().feed();
        person.getPet().eat("kibble");
        person.getWork().doWork();
        person.getPet().eat("treats");

        // verify()                             -> verify that a method was called with specific arguments and specific times
        // verify(... , never())                -> verify that a method was never called
        // verify(... , times(2))               -> verify that a method was called exactly 2 times
        // verify().<method>(captor.capture())  -> verify that a method was called and capture the arguments
        verify(pet).feed();
        verify(work, never()).getTitle();
        verify(pet, times(2)).eat(stringCaptor.capture());
    }
}