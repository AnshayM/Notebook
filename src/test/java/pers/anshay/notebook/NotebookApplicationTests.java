package pers.anshay.notebook;

import anshay.notebook.Start;
import anshay.notebook.service.ISolutionService;
import anshay.notebook.service.SolutionServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.spy;

@SpringBootTest(classes = Start.class)
class NotebookApplicationTests {
    @InjectMocks
    ISolutionService service = spy(SolutionServiceImpl.class);

    @Test
    void contextLoads() throws Exception {
        int[] testA = new int[]{0, 1, 1, 2, 3, 5, 8, 13};
        for (int i = 0; i < testA.length; i++) {
            int result = service.getResult(i+1);
            Assertions.assertEquals(testA[i], result, "");
        }

    }

}
