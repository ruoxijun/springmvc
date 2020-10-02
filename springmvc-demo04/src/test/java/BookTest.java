import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ruoxijun.pojo.Books;
import ruoxijun.service.BookService;

import java.util.List;

public class BookTest {
    @Test
    public void allBooks(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) applicationContext.getBean("BookServiceImpl");
        List<Books> books = bookServiceImpl.queryBooks();
        for (Books book : books) {
            System.out.println(book);
        }
    }
}