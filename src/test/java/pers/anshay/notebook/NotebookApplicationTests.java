package pers.anshay.notebook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import pers.anshay.notebook.service.ISolutionService;
import pers.anshay.notebook.service.impl.SolutionServiceImpl;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = Start.class)
class NotebookApplicationTests {
    @InjectMocks
    ISolutionService service = spy(SolutionServiceImpl.class);

    @Test
    void contextLoads() throws Exception {
        int[] testA = new int[]{0, 1, 1, 2, 3, 5, 8, 13};
        for (int i = 0; i < testA.length; i++) {
            int result = service.getResult(i + 1);
            Assertions.assertEquals(testA[i], result, "");
        }

    }

    @Test
    public void test() {
        LinkedList mockedList = mock(LinkedList.class);

// stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first");

// the following prints "first"
        System.out.println(mockedList.get(0));

// the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
    }

}
